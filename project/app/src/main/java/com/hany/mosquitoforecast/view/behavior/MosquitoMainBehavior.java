package com.hany.mosquitoforecast.view.behavior;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.animation.Interpolator;
import android.widget.ScrollView;

import com.hany.mosquitoforecast.util.LogManager;
import com.hany.mosquitoforecast.vo.mosquito.MosquitoStatus;

/**
 * Created by HanyLuv on 2016-08-23.
 * 참고 https://medium.com/@bherbst/quick-return-with-recyclerview-e70c8da9b4c1#.l0cux05yk
 */
public class MosquitoMainBehavior extends CoordinatorLayout.Behavior<View> {
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    private int mDySinceDirectionChange;
    private boolean mIsShowing;
    private boolean mIsHiding;

    public MosquitoMainBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        if (dy > 0 && mDySinceDirectionChange < 0 || dy < 0 && mDySinceDirectionChange > 0) {
            child.animate().cancel();
            mDySinceDirectionChange = 0;
        }
        mDySinceDirectionChange += dy;
        if (mDySinceDirectionChange > child.getHeight() && child.getVisibility() == View.VISIBLE&& !mIsHiding) {
            hide(child);
        } else if (mDySinceDirectionChange < 0
                && child.getVisibility() == View.GONE
                && !mIsShowing) {
            show(child);
        }
        //// TODO: 2016-08-23 스크롤뷰 바닥에 닿았는지 안닿앗는지 파악하는 메소드도 통일화 시키자.
        if (isScrollHitToBottom(target)) {
            show(child);
        }
    }

    private boolean isScrollHitToBottom(View v) {
        boolean result = false;
        if (v instanceof NestedScrollView) {
            NestedScrollView scrollView = (NestedScrollView) v;
            View view = scrollView.getChildAt(0);
            int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));
            LogManager.e("dif :" + diff);
            result = (diff <= 0);
        }
        return result;
    }

    //// TODO: 2016-08-23 hide,show 정리좀하자 ㅠㅠ 중복되자나 코드가... 애니메이션을만들어 리턴하는걸 만드는게 나을것같기도.. 음 ㅠ
    private void hide(final View view) {
        mIsHiding = true;
        ViewPropertyAnimator animator = view.animate()
                .translationY(view.getHeight())
                .setInterpolator(INTERPOLATOR)
                .setDuration(200);

        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                // Prevent drawing the View after it is gone
                mIsHiding = false;
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                // Canceling a hide should show the view
                mIsHiding = false;
                if (!mIsShowing) {
                    show(view);
                }
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });

        animator.start();
    }

    private void show(final View view) {
        mIsShowing = true;
        ViewPropertyAnimator animator = view.animate()
                .translationY(0)
                .setInterpolator(INTERPOLATOR)
                .setDuration(200);

        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mIsShowing = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                // Canceling a show should hide the view
                mIsShowing = false;
                if (!mIsHiding) {
                    hide(view);
                }
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });

        animator.start();
    }
}

