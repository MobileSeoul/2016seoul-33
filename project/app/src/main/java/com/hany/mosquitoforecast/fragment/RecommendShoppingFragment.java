package com.hany.mosquitoforecast.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.hany.mosquitoforecast.MainActivity;
import com.hany.mosquitoforecast.R;
import com.hany.mosquitoforecast.adapter.RecommendListAdapter;
import com.hany.mosquitoforecast.adapter.RecommendRecyclerItemAdapter;
import com.hany.mosquitoforecast.data.newenw.NewShoppingDataManager;
import com.hany.mosquitoforecast.http.HttpRequestResultListener;
import com.hany.mosquitoforecast.util.LogManager;
import com.hany.mosquitoforecast.vo.shopping.Channel;
import com.hany.mosquitoforecast.vo.shopping.Rss;
import com.hany.mosquitoforecast.vo.shopping.apdater.RecommendItem;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HanyLuv on 2016-09-27.
 */

public class RecommendShoppingFragment extends Fragment {

    private NewShoppingDataManager mDataManager;
    private ListView mListView;
    private Context mContext;
    private MainActivity mParentActivity;
    boolean isLastItemVisible = false;        //화면에 리스트의 마지막 아이템이 보여지는지 체크


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mDataManager = new NewShoppingDataManager(mContext);
        if (getActivity() instanceof MainActivity) {
            mParentActivity = (MainActivity) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mosquito_recommand_shopping, container, false);
        mListView = (ListView) view.findViewById(R.id.recommend_shopping_list_view);
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.view_menu_bottom_footer, null);
        mListView.addFooterView(footerView);
        mListView.setOnScrollListener(onScrollListener);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initShoppingList();
    }


    private RecommendListAdapter mRecommendListAdapter;
    ArrayList<RecommendItem> mRecommendItems;


    private void initShoppingList() {
        createShoppingList();
        mRecommendItems = new ArrayList<>();
        mRecommendListAdapter = new RecommendListAdapter(mContext, mRecommendItems);
        mListView.setAdapter(mRecommendListAdapter);
    }

    //추천 쇼핑 아이템 목록을 가져온다.
    private void createShoppingList() {
        List<String> strings = Arrays.asList(mContext.getResources().getStringArray(R.array.search_voca));

        for (final String searchKeyWord : strings) {
            //검색어당 5개의 아이템을 가져옴.
            mDataManager.requestMosquitoShoppingItemChannel(searchKeyWord, new HttpRequestResultListener() {
                @Override
                public void onSuccess(String strJson) {
                    Channel channel = convertingChannel(strJson);
                    if (channel != null && (channel.getItems() != null && channel.getItems().size() > 0)) {
                        RecommendRecyclerItemAdapter itemAdapter = new RecommendRecyclerItemAdapter(mContext, channel.getItems());
                        RecommendItem recommendItem = new RecommendItem(itemAdapter, searchKeyWord);
                        mRecommendItems.add(recommendItem);
                        mRecommendListAdapter.notifyDataSetInvalidated();
                    }
                }

                @Override
                public void onFailed() {
                    Toast.makeText(getContext(),"데이터를 불러오는데 실패했습니다.\n나중에 다시 시도 해주세요.",Toast.LENGTH_SHORT).show();

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



    private AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                //현재 화면에 보이는 첫번째 리스트 아이템의 번호(firstVisibleItem) + 현재 화면에 보이는 리스트 아이템의 갯수(visibleItemCount)가 리스트 전체의 갯수(totalItemCount) -1 보다 크거나 같을때
            isLastItemVisible = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
        }

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (isLastItemVisible != false) {
                mParentActivity.showBtnLayout();
                return;
            }

            if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                mParentActivity.hideBtnLayout();
            } else if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                mParentActivity.showBtnLayout();
            }
        }

    };
}
