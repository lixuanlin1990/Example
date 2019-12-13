package com.lixlop.example.rv;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.lixlop.example.R;

public class TestFragment extends Fragment {
    static TestFragment getInstance(int p) {
        TestFragment f = new TestFragment();
        f.p = p;
        return f;
    }

    int p;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, false);
        recyclerView.setAdapter(delegateAdapter);
        delegateAdapter.addAdapter(new TestAdapter());
        recyclerView.setTag("inner_"+p);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(new TestAdapter());
//        recyclerView.setTag("inner_"+p);
    }

    public static class TestAdapter extends DelegateAdapter.Adapter<RecyclerViewActivity.TestViewHolder> {

        @NonNull
        @Override
        public RecyclerViewActivity.TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecyclerViewActivity.TestViewHolder(parent.getContext(), LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false),1);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewActivity.TestViewHolder holder, int position) {
            holder.bind(position,0);
        }

        @Override
        public int getItemCount() {
            return 50;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return new LinearLayoutHelper();
        }
    }

    public static class TestViewHolder extends RecyclerView.ViewHolder {
        Context context;

        public TestViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            this.context = context;
        }

        public void bind() {

        }
    }
}
