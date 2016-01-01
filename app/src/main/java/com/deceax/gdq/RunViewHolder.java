package com.deceax.gdq;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.deceax.gdq.databinding.RunItemBinding;

public class RunViewHolder extends RecyclerView.ViewHolder {

    private RunItemBinding binding;
    private View runDetails;

    public RunViewHolder(View itemView) {
        super(itemView);
        runDetails = itemView.findViewById(R.id.run_details);
        binding = DataBindingUtil.bind(itemView);
    }

    public void bind(Run run) {
        binding.setRun(run);
        binding.setHandler(new ClickHandler(runDetails));
    }
}
