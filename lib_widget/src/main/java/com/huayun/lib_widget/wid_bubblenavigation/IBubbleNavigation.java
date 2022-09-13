package com.huayun.lib_widget.wid_bubblenavigation;

import android.graphics.Typeface;

import com.huayun.lib_widget.wid_bubblenavigation.listener.BubbleNavigationChangeListener;


public interface IBubbleNavigation {
    void setNavigationChangeListener(BubbleNavigationChangeListener navigationChangeListener);

    void setTypeface(Typeface typeface);

    int getCurrentActiveItemPosition();

    void setCurrentActiveItem(int position);

    void setBadgeValue(int position, String value);
}
