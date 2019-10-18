package com.lixlop.example.rv;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.lixlop.example.R;

public class RecyclerViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new TestAdapter());
        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, false);
        recyclerView.setAdapter(delegateAdapter);
        delegateAdapter.addAdapter(new TestAdapter());
    }

    public static class TestAdapter extends DelegateAdapter.Adapter<TestViewHolder> {

        @NonNull
        @Override
        public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new TestViewHolder(parent.getContext(), LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
            if (position == 19)
                holder.bind();
        }

        @Override
        public int getItemCount() {
            return 20;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return new LinearLayoutHelper();
        }
    }

    public static class TestViewHolder extends RecyclerView.ViewHolder {
        ViewFlipper viewFlipper;
        Context context;

        public TestViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            this.context = context;
            viewFlipper = itemView.findViewById(R.id.view_flipper);
        }

        public void bind() {
            for (int i = 0; i < 4; i++) {
                ImageView imageView = new ImageView(context);
                if (i % 2 == 0)
                    imageView.setImageResource(R.mipmap.im_ai_robot_head);
                else
                    imageView.setImageResource(R.mipmap.ic_launcher);
                viewFlipper.addView(imageView);
            }
            viewFlipper.startFlipping();
        }
    }
}
