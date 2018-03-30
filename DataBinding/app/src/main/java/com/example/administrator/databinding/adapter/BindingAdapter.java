package com.example.administrator.databinding.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.databinding.BR;
import com.example.administrator.databinding.R;
import com.example.administrator.databinding.bean.PlainUser;

import java.util.List;

public class BindingAdapter extends RecyclerView.Adapter<BindingAdapter.BindingHolder> {

    private List<PlainUser> userBeans;

    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item,
                parent,
                false);
        BindingHolder holder = new BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BindingHolder holder, int position) {
        PlainUser userBean = userBeans.get(position);
        holder.getBinding().setVariable(BR.PlainUser, userBean);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }

        public BindingHolder(View itemView) {
            super(itemView);
        }
    }
}
