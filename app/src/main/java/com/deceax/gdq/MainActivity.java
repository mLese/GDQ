package com.deceax.gdq;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.List;


public class MainActivity extends AppCompatActivity implements ScheduleConsumer, DailyScheduleFragment.OnFragmentInteractionListener{

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private List<Run> mRunList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        ScheduleClient client = new ScheduleClient(this);
        client.fetchSchedule();
    }

    @Override
    public void onScheduleRetrieved(List<Run> runList) {
        this.mRunList = runList;
        for (Fragment fragment : mSectionsPagerAdapter.getFragments()) {
            ((DailyScheduleFragment)fragment).setSchedule(runList);
        }
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
            "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
        };
    }
}