package com.hany.mosquitoforecast.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.hany.mosquitoforecast.MainActivity;
import com.hany.mosquitoforecast.R;
import com.hany.mosquitoforecast.adapter.CopingListAdapter;
import com.hany.mosquitoforecast.data.CopingDataManager;

/**
 * Created by hany on 16. 8. 8..
 */
public class CopingMosquitoFragment extends Fragment {

    private Context mContext;
    private ListView mCopingListView;
    boolean isLastItemVisible = false;        //화면에 리스트의 마지막 아이템이 보여지는지 체크
    private MainActivity mParentActivity; //스크롤 이벤트 넘기기위한건데 거지같으니 다시 만들자.
    private CopingDataManager mCopingDataManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mCopingDataManager = new CopingDataManager(mContext);

        if (getActivity() instanceof MainActivity) {
            mParentActivity = (MainActivity) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mosquito_coping, null);
        mCopingListView = (ListView) view.findViewById(R.id.coping_list_view);
        initScrollView();
        return view;
    }


    private void initScrollView() {
        CopingListAdapter copingListAdapter = new CopingListAdapter(mContext, mCopingDataManager.getCopingInfoList());
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View headerView = inflater.inflate(R.layout.view_coping_header,null);
        TextView linkTextView = (TextView)headerView.findViewById(R.id.link);
        Linkify.addLinks(linkTextView,Linkify.WEB_URLS);

        mCopingListView.addHeaderView(headerView);
        mCopingListView.addFooterView(inflater.inflate(R.layout.view_menu_bottom_footer, null));
        mCopingListView.setAdapter(copingListAdapter);
        mCopingListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                //현재 화면에 보이는 첫번째 리스트 아이템의 번호(firstVisibleItem) + 현재 화면에 보이는 리스트 아이템의 갯수(visibleItemCount)가 리스트 전체의 갯수(totalItemCount) -1 보다 크거나 같을때
                isLastItemVisible = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
               /*   SCROLL_STATE_FLING ( 2 ) : 터치 후 손을 뗀 상태에서 아직 스크롤 되고 있는 상태입니다.
                    SCROLL_STATE_IDLE ( 0 ) : 스크롤이 종료되어 어떠한 애니메이션도 발생하지 않는 상태입니다.
                    SCROLL_STATE_TOUCH_SCROLL ( 1 ) : 스크린에 터치를 한 상태에서 스크롤하는 상태입니다.
                */
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

        });

    }


}

