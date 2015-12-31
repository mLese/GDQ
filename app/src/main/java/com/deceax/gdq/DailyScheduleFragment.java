package com.deceax.gdq;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DailyScheduleFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";
    private static final String ARG_RUNLIST = "runlist";

    RunAdapter adapter;
    RecyclerView recyclerView;
    private List<Run> mRuns;

    private int mPosition;

    private OnFragmentInteractionListener mListener;

    public DailyScheduleFragment() {
        // Required empty public constructor
    }

    public static DailyScheduleFragment newInstance(int position) {
        DailyScheduleFragment fragment = new DailyScheduleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_daily_schedule, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.run_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = RunAdapter.newInstance();
        recyclerView.setAdapter(adapter);

        if (mRuns != null) {
            adapter.setRuns(mRuns);
        }

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setSchedule(List<Run> runs, int position) {

        if (position == -1) {
            position = mPosition;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("E");

        mRuns = new ArrayList<>();

        for (Run run : runs) {
            if (sdf.format(run.getDate()).equals(dayOfWeek[position])) {
                mRuns.add(run);
            }
        }

        if (adapter != null) {
            adapter.setRuns(mRuns);
        }
    }

    public void setSchedule(List<Run> runs) {
        setSchedule(runs, -1);
    }

    private final String dayOfWeek[] = {
            "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
    };
}
