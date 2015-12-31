package com.deceax.gdq;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.deceax.gdq.databinding.RunItemBinding;

/**
 * Created by Lese on 11/23/15.
 */
public class RunViewHolder extends RecyclerView.ViewHolder {

    private RunItemBinding binding;

    public RunViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public void bind(Run run) {
        binding.setRun(run);
    }
}
