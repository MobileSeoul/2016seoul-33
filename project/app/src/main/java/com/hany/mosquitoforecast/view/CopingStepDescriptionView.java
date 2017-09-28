package com.hany.mosquitoforecast.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hany.mosquitoforecast.R;

/**
 * Created by hany on 16. 8. 8..
 */
public class CopingStepDescriptionView extends LinearLayout {

    private ViewGroup mCopingInfoTableLayout;
    private ViewGroup mCopingGroupTitleLayout;
    private TextView mCopingBaseTextView;
    private TextView mCopingStepDefenseBottomTextView;
    private TextView mCopingStepActiveBottomTextView;
    private TextView mCopingStepDefenseMiddleTextView;
    private TextView mCopingStepActiveMiddleTextView;
    private TextView mCopingStepDefenseTopTextView;

    private TextView mCopingStepActiveTopTextView;
    private TextView mCopingGroupTitleTextView;
    private ImageView mCopingGroupTitleIcon;

    private ValueAnimator mAnimator;
    private Context mContext;
    private boolean isShowingCopingInfoTableLayout = false;

    public CopingStepDescriptionView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public CopingStepDescriptionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
        getAttrs(attrs);
        initCopingInfoTableLayoutAnimation();
        initGroupTitleLayoutClickListener();
    }

    public CopingStepDescriptionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
        getAttrs(attrs, defStyleAttr);
        initCopingInfoTableLayoutAnimation();
        initGroupTitleLayoutClickListener();
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.copingItem);
        initTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.copingItem, defStyleAttr, 0);
        initTypeArray(typedArray);
    }

    private void initTypeArray(TypedArray typedArray) {
        String strCopingBaseTextView = typedArray.getString(R.styleable.copingItem_coping_default_info_txt);
        String strCopingStepDefenseBottomText = typedArray.getString(R.styleable.copingItem_coping_step_defense_bottom_txt);
        String strCopingStepActiveBottomText = typedArray.getString(R.styleable.copingItem_coping_step_active_bottom_txt);
        String strCopingStepDefenseMiddleText = typedArray.getString(R.styleable.copingItem_coping_step_defense_middle_txt);
        String strCopingStepActiveMiddleText = typedArray.getString(R.styleable.copingItem_coping_step_active_middle_txt);
        String strCopingStepDefenseTopText = typedArray.getString(R.styleable.copingItem_coping_step_defense_top_txt);
        String strCopingStepActiveTopText = typedArray.getString(R.styleable.copingItem_coping_step_active_top_txt);
        String strCopingGroupTitleText = typedArray.getString(R.styleable.copingItem_coping_group_title_txt);
        int copingGroupIconResourceId = typedArray.getResourceId(R.styleable.copingItem_coping_group_title_icon, R.drawable.ic_keyboard_arrow_down_black_24dp);
        isShowingCopingInfoTableLayout = typedArray.getBoolean(R.styleable.copingItem_coping_step_info_showing, true);

        mCopingBaseTextView.setText(strCopingBaseTextView);
        mCopingStepDefenseBottomTextView.setText(strCopingStepDefenseBottomText);
        mCopingStepActiveBottomTextView.setText(strCopingStepActiveBottomText);
        mCopingStepDefenseMiddleTextView.setText(strCopingStepDefenseMiddleText);
        mCopingStepActiveMiddleTextView.setText(strCopingStepActiveMiddleText);
        mCopingStepDefenseTopTextView.setText(strCopingStepDefenseTopText);
        mCopingStepActiveTopTextView.setText(strCopingStepActiveTopText);
        mCopingGroupTitleTextView.setText(strCopingGroupTitleText);
        mCopingGroupTitleIcon.setBackgroundResource(copingGroupIconResourceId);

        typedArray.recycle();
    }


    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.view_coping_base_item, this, false);
        mCopingBaseTextView = (TextView) view.findViewById(R.id.txt_step_default_info);
        mCopingStepDefenseBottomTextView = (TextView) view.findViewById(R.id.txt_step_defense_bottom);
        mCopingStepActiveBottomTextView = (TextView) view.findViewById(R.id.txt_step_active_bottom);
        mCopingStepDefenseMiddleTextView = (TextView) view.findViewById(R.id.txt_step_defense_middle);
        mCopingStepActiveMiddleTextView = (TextView) view.findViewById(R.id.txt_step_active_middle);
        mCopingStepDefenseTopTextView = (TextView) view.findViewById(R.id.txt_step_defense_top);
        mCopingStepActiveTopTextView = (TextView) view.findViewById(R.id.txt_step_active_top);
        mCopingGroupTitleTextView = (TextView) view.findViewById(R.id.group_title_text_view);
        mCopingGroupTitleIcon = (ImageView) view.findViewById(R.id.group_icon_image_view);
        mCopingInfoTableLayout = (ViewGroup) view.findViewById(R.id.info_table_layout);
        mCopingGroupTitleLayout = (ViewGroup) view.findViewById(R.id.group_table_layout);
        addView(view);
    }

    private void initGroupTitleLayoutClickListener() {
        mCopingGroupTitleLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCopingInfoTableLayout.getVisibility() == View.GONE) {
                    expand();
                } else {
                    collapse();
                }
            }
        });

    }

    private void initCopingInfoTableLayoutAnimation() {
        mCopingInfoTableLayout.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mCopingInfoTableLayout.getViewTreeObserver().removeOnPreDrawListener(this);
                if (!isShowingCopingInfoTableLayout) {
                    mCopingInfoTableLayout.setVisibility(View.GONE);
                }

                final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                mCopingInfoTableLayout.measure(widthSpec, heightSpec);

                mAnimator = slideAnimator(0, mCopingInfoTableLayout.getMeasuredHeight());
                return true;
            }
        });

    }


    private ValueAnimator slideAnimator(int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = mCopingInfoTableLayout.getLayoutParams();
                layoutParams.height = value;
                mCopingInfoTableLayout.setLayoutParams(layoutParams);

//                if (getParent().getParent() instanceof ScrollView) {
//                    final ScrollView scrollView = (ScrollView) getParent().getParent();
//                    scrollView.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            int y = (int)getY();
////                            scrollView.smoothScrollTo(0,y);
////                            scrollView.fullScroll(ScrollView.FOCUS_DOWN);
//                        }
//                    });
//                }
            }
        });
        return animator;
    }


    public void expand() {
        mCopingInfoTableLayout.setVisibility(View.VISIBLE);
        mAnimator.start();
    }

    public void collapse() {
        int finalHeight = mCopingInfoTableLayout.getHeight();
        ValueAnimator mAnimator = slideAnimator(finalHeight, 0);
        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animator) {
                mCopingInfoTableLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        mAnimator.start();
    }

}
