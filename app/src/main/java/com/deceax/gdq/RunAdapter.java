package com.deceax.gdq;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RunAdapter extends RecyclerView.Adapter<RunViewHolder> {
    private final List<Run> runs;

    public static RunAdapter newInstance() {
        List<Run> runs = new ArrayList<>();
        return new RunAdapter(runs);
    }

    RunAdapter(List<Run> runs) {
        this.runs = runs;
    }

    @Override
    public RunViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View gameViewContainer = inflater.inflate(R.layout.run_item, parent, false);
        return new RunViewHolder(gameViewContainer);
    }

    @Override
    public void onBindViewHolder(RunViewHolder holder, int position) {
        Run run = runs.get(position);
        holder.bind(run);
    }

    @Override
    public int getItemCount() {
        return runs.size();
    }

    public void setRuns(List<Run> runs) {
        this.runs.clear();
        this.runs.addAll(runs);
        notifyDataSetChanged();
    }
}
