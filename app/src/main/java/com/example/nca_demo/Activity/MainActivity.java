package com.example.nca_demo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;

import com.example.nca_demo.Adapter.HomeViewPagerAdapter;
import com.example.nca_demo.HomeFragments.AboutFragment;
import com.example.nca_demo.HomeFragments.HomeFragment;
import com.example.nca_demo.HomeFragments.NewsFragment;
import com.example.nca_demo.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler();

    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.setupWithViewPager(viewPager);

        StartViewPager();

    }

    private void StartViewPager() {
        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new AboutFragment(), "About");
        adapter.addFragment(new HomeFragment(), "Home");
        adapter.addFragment(new NewsFragment(), "News");
        viewPager.setAdapter(adapter);
    }


}
















