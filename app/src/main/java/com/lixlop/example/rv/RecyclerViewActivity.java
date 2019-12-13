package com.lixlop.example.rv;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.lixlop.example.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class RecyclerViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, false);
        recyclerView.setAdapter(delegateAdapter);
        delegateAdapter.addAdapter(new TestAdapter(10,1));
//        delegateAdapter.addAdapter(new Test2Adapter(getSupportFragmentManager()));
        final SmartRefreshLayout smartRefreshLayout = findViewById(R.id.smart_refresh_layout);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("lixiaoyan","clear");
                        smartRefreshLayout.finishRefresh();
                        delegateAdapter.clear();
                        delegateAdapter.addAdapter(new TestAdapter(8,2));
                        delegateAdapter.notifyDataSetChanged();
//                        recyclerView.setAdapter(new TestAdapter(10,2));
                    }
                },5000);
            }
        });
    }

    public static class Test2Adapter extends DelegateAdapter.Adapter<Test2ViewHolder> {
        FragmentManager fragmentManager;

        public Test2Adapter(FragmentManager fragmentManager) {
            this.fragmentManager = fragmentManager;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return new LinearLayoutHelper();
        }

        @NonNull
        @Override
        public Test2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Test2ViewHolder(parent.getContext(), fragmentManager, LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container, parent, false),parent);
        }

        @Override
        public void onBindViewHolder(@NonNull Test2ViewHolder holder, int position) {
            holder.bind();
        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }

    public static class Test2ViewHolder extends RecyclerView.ViewHolder {
        ViewGroup parent;
        FrameLayout frameLayout;
        private boolean ns = false;
        Context context;
        public Test2ViewHolder(Context context, FragmentManager fragmentManager, @NonNull View itemView,ViewGroup parent) {
            super(itemView);
            this.context = context;
            this.parent = parent;
            frameLayout = itemView.findViewById(R.id.fl_container);
        }

        public void bind() {
            if (ns) {
                return;
            }
            ns = true;
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, parent.getHeight());
            YLinearLayout _linearLayout = new YLinearLayout(context);
            frameLayout.addView(_linearLayout, lp);
        }
    }

    public static class TestAdapter extends DelegateAdapter.Adapter<TestViewHolder> {
        int count;
        int type;
        public TestAdapter(int count,int type){
            this.count = count;
            this.type = type;
        }
        @Override
        public void onViewDetachedFromWindow(@NonNull TestViewHolder holder) {
            Log.i("lixiaoyan","onViewDetachedFromWindow");
            super.onViewDetachedFromWindow(holder);
        }

        @Override
        public void onViewAttachedToWindow(@NonNull TestViewHolder holder) {
            Log.i("lixiaoyan","onViewAttachedToWindow");
            super.onViewAttachedToWindow(holder);
        }

        @Override
        public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
            Log.i("lixiaoyan","onDetachedFromRecyclerView");
            super.onDetachedFromRecyclerView(recyclerView);
        }

        @Override
        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
            Log.i("lixiaoyan","onAttachedToRecyclerView");
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @NonNull
        @Override
        public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.i("lixiaoyan","onCreateViewHolder"+ type);
            return new TestViewHolder(parent.getContext(), LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false),type);
        }

        @Override
        public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
            Log.i("lixiaoyan","onBindViewHolder "+position);
            holder.bind(position,type);
        }

        @Override
        public int getItemCount() {
            return count;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return new LinearLayoutHelper();
        }
    }

    public static class TestViewHolder extends RecyclerView.ViewHolder {
        Context context;
        int type;
        TextView textView;
        public TestViewHolder(Context context, @NonNull View itemView,int type) {
            super(itemView);
            this.context = context;
            this.type = type;
            textView = itemView.findViewById(R.id.view_flipper);
        }

        public void bind(int position,int type) {
            textView.setText(position+"--->"+type);
        }
    }

    public static class TestViewHolder3 extends RecyclerView.ViewHolder {
        Context context;

        public TestViewHolder3(Context context, @NonNull View itemView) {
            super(itemView);
            this.context = context;
        }

        public void bind() {

        }
    }
}
