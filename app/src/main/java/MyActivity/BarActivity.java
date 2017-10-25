package MyActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.huangkaijie.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import DataAdapter.MyFragmentViewPageAdapter;
import MyFragment.Fragment1;
import MyFragment.Fragment2;
import MyFragment.Fragment3;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by edison on 2017/10/25.
 */

public class BarActivity extends FragmentActivity {


    String titiles[] = {"first", "secong", "third"};
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.bottom_layout);
        ButterKnife.bind(this);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());
        MyFragmentViewPageAdapter myFragmentViewPageAdapter = new MyFragmentViewPageAdapter(getSupportFragmentManager(), fragmentList, titiles);
        mViewPager.setAdapter(myFragmentViewPageAdapter);
        //tabs.setViewPager(mViewPager);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                return true;
            }
        });
    }

}
