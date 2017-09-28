package com.hany.mosquitoforecast;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ProgressBar;

import com.hany.mosquitoforecast.fragment.CopingMosquitoFragment;
import com.hany.mosquitoforecast.fragment.RecommendShoppingFragment;
import com.hany.mosquitoforecast.fragment.TodayMosquitoForecastFragment;

public class MainActivity extends AppCompatActivity {
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private boolean mIsShowing;
    private boolean mIsHiding;

    private ProgressBar mProgressBar;
    private ViewGroup btnsLayout;
    private Button mInfoMfBtn;
    private Button mTodayMfBtn;
    private Button mShoppingMfBtn;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        initButtons();
        replaceFragment(new TodayMosquitoForecastFragment());
//        replaceFragment(new ShoppingFragment());
    }

    private void replaceFragment(Fragment fragment) {
        replaceFragment(fragment, null);
    }

    private void replaceFragment(Fragment fragment, Bundle bundle) {
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        getSupportFragmentManager().beginTransaction()
//                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out, R.anim.slide_left_in, R.anim.slide_left_out)
//                .setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up)
//                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }


    private void initButtons() {
        btnsLayout = (ViewGroup) findViewById(R.id.menus_layout);
        mInfoMfBtn = (Button) findViewById(R.id.info_mf_btn);
        mTodayMfBtn = (Button) findViewById(R.id.today_mf_btn);
        mShoppingMfBtn = (Button) findViewById(R.id.shopping_mf_btn);

        mInfoMfBtn.setOnClickListener(infoMfBtnClickListener);
        mTodayMfBtn.setOnClickListener(todayMfBtnClickListener);
        mShoppingMfBtn.setOnClickListener(shoppingMfBtnClickListener);
    }

//    private Bundle createArguments() {
//        Bundle bundle = new Bundle();
//        MosquitoDataManager dataManager = new MosquitoDataManager(getBaseContext());
//
//
//        MosquitoStatus mosquitoStatus = dataManager.requestTodayMosquitoStatus();
//        ArrayList<MosquitoStatus> mosquitoStatusForWeek = dataManager.requestMosquitoStatusForWeek();
//
//        if (mosquitoStatus != null && mosquitoStatusForWeek != null) {
//            bundle.putParcelable(Key.TODAY_MOSQUITO_DATA, mosquitoStatus);
//            bundle.putParcelableArrayList(Key.WEEK_MOSQUITO_DATA, mosquitoStatusForWeek);
//        }
//        return bundle;
//    }

    private void setProgressBarVisible(final int isVisible) {
        mProgressBar.setVisibility(isVisible);
    }


    // TODO: 16. 8. 9. 버튼 계속 눌리지않도록 처리
    private View.OnClickListener infoMfBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mInfoMfBtn.setClickable(false);
            mTodayMfBtn.setClickable(true);
            mShoppingMfBtn.setClickable(true);
            replaceFragment(new CopingMosquitoFragment());
        }
    };

    private View.OnClickListener todayMfBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mTodayMfBtn.setClickable(false);
            mInfoMfBtn.setClickable(true);
            mShoppingMfBtn.setClickable(true);
            replaceFragment(new TodayMosquitoForecastFragment());
        }
    };
    private View.OnClickListener shoppingMfBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mTodayMfBtn.setClickable(true);
            mInfoMfBtn.setClickable(true);
            mShoppingMfBtn.setClickable(false);
            replaceFragment(new RecommendShoppingFragment());
        }
    };


    public boolean isBtnLayoutVisible() {
        return btnsLayout.getVisibility() == View.VISIBLE;
    }

    public void showBtnLayout() {
        mIsShowing = true;
        ViewPropertyAnimator animator = btnsLayout.animate()
                .translationY(0)
                .setInterpolator(INTERPOLATOR)
                .setDuration(200);

        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                btnsLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mIsShowing = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                // 취소되면 다시 숨김
                mIsShowing = false;
                if (!mIsHiding) {
                    hideBtnLayout();
                }
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });

        animator.start();
    }

    public void hideBtnLayout() {
        mIsHiding = true;
        ViewPropertyAnimator animator = btnsLayout.animate()
                .translationY(btnsLayout.getHeight())
                .setInterpolator(INTERPOLATOR)
                .setDuration(200);

        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mIsHiding = false;
                btnsLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                // 취소되면 다시 보여줌
                mIsHiding = false;
                if (!mIsShowing) {
                    showBtnLayout();
                }
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animator.start();
    }


}