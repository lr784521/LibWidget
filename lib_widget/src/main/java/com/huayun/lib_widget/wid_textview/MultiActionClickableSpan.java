package com.huayun.lib_widget.wid_textview;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

public class MultiActionClickableSpan extends ClickableSpan {
    private int mStart;
    private int mEnd;
    private boolean changeColor;
    private int mColor;
    private boolean mUnderline;
    private boolean mInterceptClick;
    private OnTextClickedListener mListener;
    private Object mTag;

    public MultiActionClickableSpan(int start, int end) {
        mStart = start;
        mEnd = end;
        changeColor = false;
        mUnderline = true;
        setInterceptClick(false);
    }

    public MultiActionClickableSpan(int start, int end, int color, boolean underline,
                                    boolean interceptClick, OnTextClickedListener listener) {
        mStart = start;
        mEnd = end;
        changeColor = true;
        mColor = color;
        mUnderline = underline;
        setInterceptClick(interceptClick);
        setOnTextClickedListener(listener);
    }

    @Override
    public void onClick(View widget) {
        if (widget instanceof WidMultiActionTextView) {
            WidMultiActionTextView actionTextView = (WidMultiActionTextView) widget;
            actionTextView.setInterceptClick(mInterceptClick);
        }
        if (mListener != null) {
            mListener.onTextClicked(widget, this);
        }
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setUnderlineText(mUnderline);
        ds.clearShadowLayer();
        if (changeColor)
            ds.setColor(mColor);
    }

    public int getStart() {
        return mStart;
    }

    public int getEnd() {
        return mEnd;
    }

    public void setInterceptClick(boolean interceptClick) {
        mInterceptClick = interceptClick;
    }

    public void setOnTextClickedListener(OnTextClickedListener listener) {
        mListener = listener;
    }

    public Object getTag() {
        return mTag;
    }

    public void setTag(Object tag) {
        mTag = tag;
    }

    /**
     * 文字点击监听
     */
    public interface OnTextClickedListener {
        void onTextClicked(View view, MultiActionClickableSpan span);
    }
}
