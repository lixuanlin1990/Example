package com.lixlop.example.rv;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.vlayout.VirtualLayoutManager;

public class CustomLinearLayoutManager extends VirtualLayoutManager {

    public CustomLinearLayoutManager(@NonNull Context context) {
        super(context);
    }

    public CustomLinearLayoutManager(@NonNull Context context, int orientation) {
        super(context, orientation);
    }

    public CustomLinearLayoutManager(@NonNull Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
