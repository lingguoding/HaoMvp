package com.hao.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

import com.hao.base.R;

/**
 * @Package com.hao.base.widget
 * @作 用:
 * @创 建 人: 林国定 邮箱：linggoudingg@gmail.com
 * @日 期: 2016年12月27日  17:24
 */
public class TitleBar extends RelativeLayout {
    private AppCompatCheckedTextView mTitleCtv;
    private AppCompatCheckedTextView mLeftCtv;
    private AppCompatCheckedTextView mRightCtv;
    private AppCompatCheckedTextView mRightSecondaryCtv;
    private Delegate mDelegate;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_titlebar, this);
        initView();
        setListener();
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        final int N = typedArray.getIndexCount();
        for (int i = 0; i < N; i++) {
            initAttr(typedArray.getIndex(i), typedArray);
        }
        typedArray.recycle();
    }

    protected void initView() {
        mLeftCtv = getViewById(R.id.ctv_titlebar_left);
        mRightCtv = getViewById(R.id.ctv_titlebar_right);
        mRightSecondaryCtv = getViewById(R.id.ctv_titlebar_right_secondary);
        mTitleCtv = getViewById(R.id.ctv_titlebar_title);
    }

    protected void setListener() {
        mLeftCtv.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (mDelegate != null) {
                    mDelegate.onClickLeftCtv();
                }
            }
        });
        mTitleCtv.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (mDelegate != null) {
                    mDelegate.onClickTitleCtv();
                }
            }
        });
        mRightCtv.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (mDelegate != null) {
                    mDelegate.onClickRightCtv();
                }
            }
        });
        mRightSecondaryCtv.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (mDelegate != null) {
                    mDelegate.onClickRightSecondaryCtv();
                }
            }
        });

    }

    protected void initAttr(int attr, TypedArray typedArray) {
        if (attr == R.styleable.TitleBar_bgatitlebar_leftText) {
            setLeftText(typedArray.getText(attr));
        } else if (attr == R.styleable.TitleBar_bgatitlebar_titleText) {
            setTitleText(typedArray.getText(attr));
        } else if (attr == R.styleable.TitleBar_bgatitlebar_rightText) {
            setRightText(typedArray.getText(attr));
        } else if (attr == R.styleable.TitleBar_bgatitlebar_rightSecondaryText) {
            setRightSecondaryText(typedArray.getText(attr));
        } else if (attr == R.styleable.TitleBar_bgatitlebar_leftDrawable) {
            setLeftDrawable(typedArray.getDrawable(attr));
        } else if (attr == R.styleable.TitleBar_bgatitlebar_titleDrawable) {
            setTitleDrawable(typedArray.getDrawable(attr));
        } else if (attr == R.styleable.TitleBar_bgatitlebar_rightDrawable) {
            setRightDrawable(typedArray.getDrawable(attr));
        } else if (attr == R.styleable.TitleBar_bgatitlebar_rightSecondaryDrawable) {
            setRightSecondaryDrawable(typedArray.getDrawable(attr));
        } else if (attr == R.styleable.TitleBar_bgatitlebar_leftAndRightTextSize) {
            int textSize = typedArray.getDimensionPixelSize(attr, sp2px(getContext(), 12));
            mLeftCtv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            mRightCtv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            mRightSecondaryCtv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        } else if (attr == R.styleable.TitleBar_bgatitlebar_titleTextSize) {
            mTitleCtv.setTextSize(TypedValue.COMPLEX_UNIT_PX, typedArray.getDimensionPixelSize(attr, sp2px(getContext(), 16)));
        } else if (attr == R.styleable.TitleBar_bgatitlebar_leftAndRightTextColor) {
            mLeftCtv.setTextColor(typedArray.getColorStateList(attr));
            mRightCtv.setTextColor(typedArray.getColorStateList(attr));
            mRightSecondaryCtv.setTextColor(typedArray.getColorStateList(attr));
        } else if (attr == R.styleable.TitleBar_bgatitlebar_titleTextColor) {
            mTitleCtv.setTextColor(typedArray.getColorStateList(attr));
        } else if (attr == R.styleable.TitleBar_bgatitlebar_titleDrawablePadding) {
            mTitleCtv.setCompoundDrawablePadding(typedArray.getDimensionPixelSize(attr, dp2px(getContext(), 3)));
        } else if (attr == R.styleable.TitleBar_bgatitlebar_leftDrawablePadding) {
            mLeftCtv.setCompoundDrawablePadding(typedArray.getDimensionPixelSize(attr, dp2px(getContext(), 3)));
        } else if (attr == R.styleable.TitleBar_bgatitlebar_rightDrawablePadding) {
            mRightCtv.setCompoundDrawablePadding(typedArray.getDimensionPixelSize(attr, dp2px(getContext(), 3)));
            mRightSecondaryCtv.setCompoundDrawablePadding(typedArray.getDimensionPixelSize(attr, dp2px(getContext(), 3)));
        } else if (attr == R.styleable.TitleBar_bgatitlebar_leftAndRightPadding) {
            int leftAndRightPadding = typedArray.getDimensionPixelSize(attr, dp2px(getContext(), 10));
            mLeftCtv.setPadding(leftAndRightPadding, 0, leftAndRightPadding, 0);
            mRightCtv.setPadding(leftAndRightPadding / 2, 0, leftAndRightPadding, 0);
            mRightSecondaryCtv.setPadding(leftAndRightPadding / 2, 0, leftAndRightPadding / 2, 0);
        } else if (attr == R.styleable.TitleBar_bgatitlebar_leftMaxWidth) {
            setLeftCtvMaxWidth(typedArray.getDimensionPixelSize(attr, dp2px(getContext(), 85)));
        } else if (attr == R.styleable.TitleBar_bgatitlebar_rightMaxWidth) {
            setRightCtvMaxWidth(typedArray.getDimensionPixelSize(attr, dp2px(getContext(), 85)));
        } else if (attr == R.styleable.TitleBar_bgatitlebar_titleMaxWidth) {
            setTitleCtvMaxWidth(typedArray.getDimensionPixelSize(attr, dp2px(getContext(), 144)));
        } else if (attr == R.styleable.TitleBar_bgatitlebar_isTitleTextBold) {
            mTitleCtv.getPaint().setTypeface(getTypeface(typedArray.getBoolean(attr, true)));
        } else if (attr == R.styleable.TitleBar_bgatitlebar_isLeftTextBold) {
            mLeftCtv.getPaint().setTypeface(getTypeface(typedArray.getBoolean(attr, false)));
        } else if (attr == R.styleable.TitleBar_bgatitlebar_isRightTextBold) {
            mRightCtv.getPaint().setTypeface(getTypeface(typedArray.getBoolean(attr, false)));
            mRightSecondaryCtv.getPaint().setTypeface(getTypeface(typedArray.getBoolean(attr, false)));
        }
    }

    private Typeface getTypeface(boolean isBold) {
        return isBold ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT;
    }

    public TitleBar setLeftCtvMaxWidth(int maxWidth) {
        mLeftCtv.setMaxWidth(maxWidth);
        return this;
    }

    public TitleBar setRightCtvMaxWidth(int maxWidth) {
        mRightCtv.setMaxWidth(maxWidth);
        mRightSecondaryCtv.setMaxWidth(maxWidth);
        return this;
    }

    public TitleBar setTitleCtvMaxWidth(int maxWidth) {
        mTitleCtv.setMaxWidth(maxWidth);
        return this;
    }

    public TitleBar hiddenLeftCtv() {
        mLeftCtv.setVisibility(GONE);
        return this;
    }

    public TitleBar showLeftCtv() {
        mLeftCtv.setVisibility(VISIBLE);
        return this;
    }

    public TitleBar setLeftText(@StringRes int resId) {
        setLeftText(getResources().getString(resId));
        return this;
    }

    public TitleBar setLeftText(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            mLeftCtv.setText("");
            if (mLeftCtv.getCompoundDrawables()[0] == null) {
                hiddenLeftCtv();
            }
        } else {
            mLeftCtv.setText(text);
            showLeftCtv();
        }
        return this;
    }

    public TitleBar setLeftDrawable(@DrawableRes int resId) {
        setLeftDrawable(getResources().getDrawable(resId));
        return this;
    }

    public TitleBar setLeftDrawable(Drawable drawable) {
        if (drawable == null) {
            mLeftCtv.setCompoundDrawables(null, null, null, null);
            if (TextUtils.isEmpty(mLeftCtv.getText())) {
                hiddenLeftCtv();
            }
        } else {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            mLeftCtv.setCompoundDrawables(drawable, null, null, null);
            showLeftCtv();
        }
        return this;
    }

    public TitleBar hiddenTitleCtv() {
        mTitleCtv.setVisibility(GONE);
        return this;
    }

    public TitleBar showTitleCtv() {
        mTitleCtv.setVisibility(VISIBLE);
        return this;
    }

    public TitleBar setTitleText(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            mTitleCtv.setText("");
            if (mTitleCtv.getCompoundDrawables()[2] == null) {
                hiddenTitleCtv();
            }
        } else {
            mTitleCtv.setText(text);
            showTitleCtv();
        }
        return this;
    }

    public TitleBar setTitleText(@StringRes int resid) {
        setTitleText(getResources().getString(resid));
        return this;
    }

    public TitleBar setTitleDrawable(@DrawableRes int resId) {
        setTitleDrawable(getResources().getDrawable(resId));
        return this;
    }

    public TitleBar setTitleDrawable(Drawable drawable) {
        if (drawable == null) {
            mTitleCtv.setCompoundDrawables(null, null, null, null);
            if (TextUtils.isEmpty(mTitleCtv.getText())) {
                hiddenTitleCtv();
            }
        } else {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            mTitleCtv.setCompoundDrawables(null, null, drawable, null);
            showTitleCtv();
        }
        return this;
    }

    public TitleBar hiddenRightSecondaryCtv() {
        mRightSecondaryCtv.setVisibility(GONE);
        return this;
    }

    public TitleBar showRightSecondaryCtv() {
        mRightSecondaryCtv.setVisibility(VISIBLE);
        return this;
    }

    public TitleBar setRightSecondaryText(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            mRightSecondaryCtv.setText("");
            if (mRightSecondaryCtv.getCompoundDrawables()[2] == null) {
                hiddenRightSecondaryCtv();
            }
        } else {
            mRightSecondaryCtv.setText(text);
            showRightSecondaryCtv();
        }
        return this;
    }

    public TitleBar setRightSecondaryText(@StringRes int resid) {
        setRightSecondaryText(getResources().getString(resid));
        return this;
    }

    public TitleBar setRightSecondaryDrawable(@DrawableRes int resId) {
        setRightSecondaryDrawable(getResources().getDrawable(resId));
        return this;
    }

    public TitleBar setRightSecondaryDrawable(Drawable drawable) {
        if (drawable == null) {
            mRightSecondaryCtv.setCompoundDrawables(null, null, null, null);
            if (TextUtils.isEmpty(mRightSecondaryCtv.getText())) {
                hiddenRightSecondaryCtv();
            }
        } else {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            mRightSecondaryCtv.setCompoundDrawables(null, null, drawable, null);
            showRightSecondaryCtv();
        }
        return this;
    }

    public TitleBar hiddenRightCtv() {
        mRightCtv.setVisibility(GONE);
        return this;
    }

    public TitleBar showRightCtv() {
        mRightCtv.setVisibility(VISIBLE);
        return this;
    }

    public TitleBar setRightText(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            mRightCtv.setText("");
            if (mRightCtv.getCompoundDrawables()[2] == null) {
                hiddenRightCtv();
            }
        } else {
            mRightCtv.setText(text);
            showRightCtv();
        }
        return this;
    }

    public TitleBar setRightText(@StringRes int resid) {
        setRightText(getResources().getString(resid));
        return this;
    }

    public TitleBar setRightDrawable(@DrawableRes int resId) {
        setRightDrawable(getResources().getDrawable(resId));
        return this;
    }

    public TitleBar setRightDrawable(Drawable drawable) {
        if (drawable == null) {
            mRightCtv.setCompoundDrawables(null, null, null, null);
            if (TextUtils.isEmpty(mRightCtv.getText())) {
                hiddenRightCtv();
            }
        } else {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            mRightCtv.setCompoundDrawables(null, null, drawable, null);
            showRightCtv();
        }
        return this;
    }

    public TitleBar setLeftCtvChecked(boolean checked) {
        mLeftCtv.setChecked(checked);
        return this;
    }

    public TitleBar setTitleCtvChecked(boolean checked) {
        mTitleCtv.setChecked(checked);
        return this;
    }

    public TitleBar setRightCtvChecked(boolean checked) {
        mRightCtv.setChecked(checked);
        return this;
    }

    public TitleBar setRightSecondaryCtvChecked(boolean checked) {
        mRightSecondaryCtv.setChecked(checked);
        return this;
    }

    public AppCompatCheckedTextView getLeftCtv() {
        return mLeftCtv;
    }

    public AppCompatCheckedTextView getRightCtv() {
        return mRightCtv;
    }

    public AppCompatCheckedTextView getRightSecondaryCtv() {
        return mRightSecondaryCtv;
    }

    public AppCompatCheckedTextView getTitleCtv() {
        return mTitleCtv;
    }

    public TitleBar setDelegate(Delegate delegate) {
        mDelegate = delegate;
        return this;
    }

    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }

    public static int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
    }

    public interface Delegate {
        void onClickLeftCtv();

        void onClickTitleCtv();

        void onClickRightCtv();

        void onClickRightSecondaryCtv();
    }

    public static class SimpleDelegate implements Delegate {

        @Override
        public void onClickLeftCtv() {
        }

        @Override
        public void onClickTitleCtv() {
        }

        @Override
        public void onClickRightCtv() {
        }

        @Override
        public void onClickRightSecondaryCtv() {
        }
    }

    public static abstract class OnNoDoubleClickListener implements View.OnClickListener {
        private int mThrottleFirstTime = 600;
        private long mLastClickTime = 0;

        public OnNoDoubleClickListener() {
        }

        public OnNoDoubleClickListener(int throttleFirstTime) {
            mThrottleFirstTime = throttleFirstTime;
        }

        @Override
        public void onClick(View v) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - mLastClickTime > mThrottleFirstTime) {
                mLastClickTime = currentTime;
                onNoDoubleClick(v);
            }
        }

        public abstract void onNoDoubleClick(View v);
    }

}
