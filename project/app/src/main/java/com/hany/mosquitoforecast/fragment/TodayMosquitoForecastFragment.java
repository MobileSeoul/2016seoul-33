package com.hany.mosquitoforecast.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hany.mosquitoforecast.MainActivity;
import com.hany.mosquitoforecast.R;
import com.hany.mosquitoforecast.adapter.MainRecommendRecyclerAdapter;
import com.hany.mosquitoforecast.data.CopingDataManager;
import com.hany.mosquitoforecast.data.newenw.NewShoppingDataManager;
import com.hany.mosquitoforecast.data.covertor.NewMosquitoConverter;
import com.hany.mosquitoforecast.data.newenw.NewMosquitoDataManager;
import com.hany.mosquitoforecast.http.HttpRequestResultListener;
import com.hany.mosquitoforecast.util.DataManager;
import com.hany.mosquitoforecast.util.LogManager;
import com.hany.mosquitoforecast.vo.Key;
import com.hany.mosquitoforecast.vo.coping.StepCopingInfo;
import com.hany.mosquitoforecast.vo.mosquito.MosquitoStatus;
import com.hany.mosquitoforecast.vo.shopping.Channel;
import com.hany.mosquitoforecast.vo.shopping.Item;
import com.hany.mosquitoforecast.vo.shopping.Rss;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by HanyLuv on 2016-08-03.
 * 오늘의 모기 예보
 */
public class TodayMosquitoForecastFragment extends Fragment {
    private final int MAX_DATA = 1000;


    private LayoutInflater mInflater;
    private NestedScrollView mScrollView;

    private ProgressBar mMosquitoChartBar;
    private TextView mMosquitoActiveCountTv;
    private TextView mMosquitoStepTv;
    private LineChartView mLineChartView;
    private LineChartData mLineData;

    // 모기정보
    private MosquitoStatus mMosquitoStatus;
    private ArrayList<MosquitoStatus> mMosquitoStatusForWeekList;
    private MainActivity mParentActivity;
    private StepCopingInfo mStepCopingInfo;
    private Context mContext;
    private String mTodayDate;

    private TextView mActiveCopingTv;
    private TextView mDefenseCopingTv;
    private TextView mTodayDateTv;


    private ViewGroup mRecommendShoppingItemView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (getActivity() instanceof MainActivity) {
            mParentActivity = (MainActivity) getActivity();
        }
        mMosquitoStatusForWeekList = new ArrayList<>();
        initData();
        LogManager.e("TodayMosquitoForecastFragment onAttach");
    }

    private void initData() {
        DataManager dataManager = new DataManager();
        mTodayDate = dataManager.getTodayDate(DataManager.FormattingType.DATE_FORMAT_KR);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogManager.e("TodayMosquitoForecastFragment onDetach");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = inflater;
        View view = mInflater.inflate(R.layout.fragment_mosquito_main, null);
        initView(view);
//        createRecommendShoppingItemView();
//        if (checkRequestSucceeded()) {
//            view = inflater.inflate(R.layout.fragment_mosquito_main, null);
//            initView(view);
//        }
        return view;
    }

    //데이터를 체크 로직
    private boolean checkRequestSucceeded() {
        if (mMosquitoStatus != null) {
            String code = mMosquitoStatus.getResult().getCode();
            return code.equals(Key.RESULT_SUCCESS);
        }
        return false;
    }

    private boolean checkRequestForWeekSucceeded() {
        if (mMosquitoStatusForWeekList != null && mMosquitoStatusForWeekList.size() > 0) {
            for (MosquitoStatus mosquitoStatus : mMosquitoStatusForWeekList) {
                String code = mosquitoStatus.getResult().getCode();
                if (!code.equals(Key.RESULT_SUCCESS)) {
                    return false;
                }
            }
        }
        return true;
    }

    //받아온 데이터 컨버팅
    public MosquitoStatus convertingMosquito(String strJson) {
        NewMosquitoConverter newMosquitoConverter = new NewMosquitoConverter();
        MosquitoStatus mosquitoStatus = newMosquitoConverter.converting(strJson);
        if (mosquitoStatus != null&& checkRequestSucceeded()) {
            String mosquitoStep = newMosquitoConverter.getMosquitoStep(mosquitoStatus.getRow().getMosquitoValue());
            mosquitoStatus.setMosquitoStep(mosquitoStep);
        }
        return mosquitoStatus;
    }


    private void requestMosquitoData() {
        //데이터 요청하자
        NewMosquitoDataManager newMosquitoDataManager = new NewMosquitoDataManager(mContext);
        newMosquitoDataManager.requestTodayMosquitoStatus(new HttpRequestResultListener() {
            @Override
            public void onSuccess(String strJson) {
                LogManager.e("requestResultXmlString " + strJson);
                mMosquitoStatus = convertingMosquito(strJson);

                if (!checkRequestSucceeded()) {
                    Toast.makeText(getContext(),"갱신 된 데이터를 불러오는데 실패했습니다.\n" +"오전 7시 이후 다시 시도해주세요.",Toast.LENGTH_SHORT).show();
                    mLineChartView.setVisibility(View.GONE);
                    return;
                }

                if (mMosquitoStatus != null) {
                    CopingDataManager copingDataManager = new CopingDataManager(mContext);
                    mStepCopingInfo = copingDataManager.getStepCopingInfo(mMosquitoStatus.getRow().getMosquitoValue());
                }

                createHorizontalGraph();
                mActiveCopingTv.setText(mStepCopingInfo.getStepActive());
                mDefenseCopingTv.setText(mStepCopingInfo.getStepDefence());
                mScrollView.setOnScrollChangeListener(scrollChangeListener);
                mMosquitoStepTv.setText(mMosquitoStatus.getMosquitoStep());
                //데이터 보여주장.
            }
            @Override
            public void onFailed() {
                LogManager.e("request Failed");
                Toast.makeText(getContext(), "데이터를 불러오는데 실패했습니다.\n나중에 다시 시도 해주세요.", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayList<String> weekDate = new DataManager().getWeekDate();
        for (int count = 0; count < weekDate.size(); count++) {
            newMosquitoDataManager.requestMosquitoStatus(weekDate.get(count), new HttpRequestResultListener() {
                @Override
                public void onSuccess(String strJson) {
                    mMosquitoStatusForWeekList.add(convertingMosquito(strJson));
                    if (checkRequestForWeekSucceeded()) {
                        createLineChartGraph();
                    }
                }

                @Override
                public void onFailed() {
                    LogManager.e("request Failed");
                    Toast.makeText(getContext(), "데이터를 불러오는데 실패했습니다.\n나중에 다시 시도 해주세요.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requestMosquitoData();
        createRecommendShoppingItemView();
//        if (checkRequestSucceeded()) {
//            createHorizontalGraph();
//            createLineChartGraph();
//        }
    }

    List<String> mSearchVocaList;
    //메인화면에 뿌려질 쇼핑몰아이템리스트를 만드는 메소드.
    private void createRecommendShoppingImageList() {
        NewShoppingDataManager shoppingDataManager = new NewShoppingDataManager(mContext);
        mSearchVocaList = Arrays.asList(mContext.getResources().getStringArray(R.array.search_voca));
        for (int count = 0; count < mSearchVocaList.size(); count++) {
            final String searchKey = mSearchVocaList.get(count);
            shoppingDataManager.requestMosquitoShoppingItemChannel(searchKey, 1, new HttpRequestResultListener() {
                @Override
                public void onSuccess(String strJson) {
                    Channel channel = convertingChannel(strJson);
                    if (channel != null && (channel.getItems() != null && channel.getItems().size() > 0)) {
                        Item item = channel.getItems().get(0);
                        item.setTitle("#" + searchKey);
                        mRecommendImages.add(item);
                        mMainRecommendRecyclerAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailed() {
                    Toast.makeText(getContext(), "데이터를 불러오는데 실패했습니다.\n나중에 다시 시도 해주세요.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    //받아온 데이터 컨버팅
    public Channel convertingChannel(String strResult) {
        Channel channels = null;
        if (!TextUtils.isEmpty(strResult)) {
            Serializer serializer = new Persister();
            try {
                channels = serializer.read(Rss.class, strResult).getChannel();
            } catch (Exception e) {
                e.printStackTrace();
                LogManager.e(e.getMessage());
            }
        }
        return channels;
    }


    private MainRecommendRecyclerAdapter mMainRecommendRecyclerAdapter;
    private RecyclerView mRecommendRecyclerView;
    private ArrayList<Item> mRecommendImages;

    private void createRecommendShoppingItemView() {
        //시간없어서..-_-; 리스트뷰로 바꿔주는게 낫겟다..

        ViewGroup recommendLayout = (ViewGroup) mInflater.inflate(R.layout.view_recommend_shopping_item_list, null);
        mRecommendImages = new ArrayList<>();
        mRecommendRecyclerView = (RecyclerView) recommendLayout.findViewById(R.id.main_recommend_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mMainRecommendRecyclerAdapter = new MainRecommendRecyclerAdapter(mContext, mRecommendImages);
        mRecommendRecyclerView.setLayoutManager(layoutManager);
        mRecommendRecyclerView.setAdapter(mMainRecommendRecyclerAdapter);
        mRecommendShoppingItemView.addView(recommendLayout);
        createRecommendShoppingImageList();
    }

    private void initView(View view) {
        mScrollView = (NestedScrollView) view.findViewById(R.id.mosquito_main_scroll_view);
        mMosquitoChartBar = (ProgressBar) view.findViewById(R.id.mosquito_chart_bar);
        mMosquitoActiveCountTv = (TextView) view.findViewById(R.id.mosquito_active_count_text_view);
        mMosquitoStepTv = (TextView) view.findViewById(R.id.mosquito_active_text_view);
        mLineChartView = (LineChartView) view.findViewById(R.id.line_chart_view);

        mActiveCopingTv = (TextView) view.findViewById(R.id.active_coping_text_view);
        mDefenseCopingTv = (TextView) view.findViewById(R.id.defense_coping_text_view);
        mTodayDateTv = (TextView) view.findViewById(R.id.today_date_text_view);
        if (!TextUtils.isEmpty(mTodayDate)) {
            mTodayDateTv.setText(mTodayDate);
        }
        mRecommendShoppingItemView = (ViewGroup) view.findViewById(R.id.view_recommend_shopping_item_list);
    }

    private void createHorizontalGraph() {
        if (mMosquitoActiveCountTv != null && mMosquitoChartBar != null) {
            LogManager.e("mMosquitoStatus : " + mMosquitoStatus.getRow().getMosquitoValue());
            String mosquitoValue = mMosquitoStatus.getRow().getMosquitoValue();
            mMosquitoActiveCountTv.setText(String.valueOf(Float.valueOf(mosquitoValue)));
            mMosquitoChartBar.setProgress((int) ((Float.valueOf(mosquitoValue)) * 0.10f));
        }
    }

    /**
     * String 형태의 모기 측정값을 float 형태로 변경해준다.
     */
    private float getMosquitoValue(String mValue) {
        return Float.valueOf(mValue);
    }


    private String convertData(String dada) {
        SimpleDateFormat SDformat = new SimpleDateFormat("yyyy-MM-dd"); //표시 형식
        String now = dada; //시스템의 현재 날짜 저장
        String transStr = "";

        Date transdate = null; //날짜 타입으로 변환
        try {
            transdate = SDformat.parse(now);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (transdate != null) {
            SimpleDateFormat toFormat = new SimpleDateFormat("MM-dd");
            transStr = toFormat.format(transdate); //스트링 타입으로 변환
        }
        return transStr;
}

    private void createLineChartGraph() {
        //데이터 아무것도 없는걸 미리 셋팅하는 로직을 넣자..
        if (mLineChartView != null) {
            List<AxisValue> axisValues = new ArrayList<AxisValue>();
            List<PointValue> values = new ArrayList<PointValue>();

            int numValues = mMosquitoStatusForWeekList.size();
            for (int count = 0; count < numValues; count++) {
                float fMosquitoValue = getMosquitoValue(mMosquitoStatusForWeekList.get(count).getRow().getMosquitoValue());
                int iMosquitoValue = (int) fMosquitoValue;

                PointValue pointValue = new PointValue(count, getMosquitoValue(mMosquitoStatusForWeekList.get(count).getRow().getMosquitoValue()));
                pointValue.setTarget(pointValue.getX(), iMosquitoValue);
                values.add(pointValue);

                String originalData = mMosquitoStatusForWeekList.get(count).getRow().getMosquitoDate();
                String data = "";
                if (!TextUtils.isEmpty(originalData)) {
                    data = convertData(originalData);
                }
                if (!TextUtils.isEmpty(data)) {
                    axisValues.add(new AxisValue(count).setLabel(data));
                } else {
                    axisValues.add(new AxisValue(count).setLabel(originalData));
                }
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.COLOR_RED).setCubic(true);
            List<Line> lines = new ArrayList<>();
            line.setHasLabels(true);
            lines.add(line);

            mLineData = new LineChartData(lines);
            mLineData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
            mLineData.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(3));
            mLineChartView.setLineChartData(mLineData);
            mLineChartView.setViewportCalculationEnabled(false);

//            Viewport v = new Viewport(0, MAX_DATA, (numValues - 1), 0);
            Viewport v = new Viewport(0, MAX_DATA, (numValues - 1), 0);
            mLineChartView.setMaximumViewport(v);
//            mLineChartView.setCurrentViewport(v);
            mLineChartView.setCurrentViewportWithAnimation(v, 1000);
            mLineChartView.setZoomType(ZoomType.HORIZONTAL);
            mLineChartView.setVisibility(View.VISIBLE);
        }
    }

    //scroll이 바닥에 닿았을때 하단 메뉴를 나타낸다.
    private NestedScrollView.OnScrollChangeListener scrollChangeListener = new NestedScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView scrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            View view = scrollView.getChildAt(0);
            int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));
            if (diff <= 0) {
                mParentActivity.showBtnLayout();
            }
        }
    };
}
