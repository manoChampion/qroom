package com.example.luis.qroom;

import android.Manifest;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity implements FragmentOne.SendMessage{

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private final int REQUEST_PERMISSION_CAMERA=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        ActivityCompat.requestPermissions(MainActivity.this,
        new String[]{Manifest.permission.CAMERA},
                REQUEST_PERMISSION_CAMERA);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentOne(), "CÂMERA");
        adapter.addFragment(new FragmentTwo(), "INFO");
        adapter.addFragment(new FragmentThree(), "CADASTRAR");
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
    }

    @Override
    public void sendData(String message) {
        String tag = "android:switcher:" + R.id.viewpager + ":" + 1;
        FragmentTwo f2 = (FragmentTwo) getSupportFragmentManager().findFragmentByTag(tag);
        f2.carregarSala(message);
    }
}