package com.deceax.gdq;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ScheduleConsumer, DailyScheduleFragment.OnFragmentInteractionListener{

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private List<Run> mRunList;

    private TextView mConnectivityError;

    private ScheduleClient mClient;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mConnectivityError = (TextView) findViewById(R.id.connectivity_error);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        mViewPager.setCurrentItem(day - 1);

        PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_title_strip);
        pagerTabStrip.setTabIndicatorColor(ContextCompat.getColor(this, R.color.colorAccent));

        mClient = new ScheduleClient(this);
        fetchSchedule();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchSchedule();
            }
        });
    }

    private void fetchSchedule() {
        mProgressBar.setVisibility(View.VISIBLE);
        mConnectivityError.setVisibility(View.GONE);
        mClient.fetchSchedule();
    }

    @Override
    public void onScheduleRetrieved(List<Run> runList) {
        mConnectivityError.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        this.mRunList = runList;
        for (Fragment fragment : mSectionsPagerAdapter.getFragments()) {
            ((DailyScheduleFragment)fragment).setSchedule(runList);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Snackbar.make(mViewPager, t.toString(), Snackbar.LENGTH_LONG).show();
        mConnectivityError.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            DailyScheduleFragment fragment = DailyScheduleFragment.newInstance(position);
            if (mRunList != null) {
                fragment.setSchedule(mRunList, position);
            }
            return fragment;
        }

        public List<Fragment> getFragments() {
            return getSupportFragmentManager().getFragments();
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return dayOfWeek[position];
        }

        private final String dayOfWeek[] = {
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
        };
    }
}
