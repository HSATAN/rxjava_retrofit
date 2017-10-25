package MyActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.huangkaijie.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import DataAdapter.MyFragmentViewPageAdapter;
import MyFragment.Fragment1;
import MyFragment.Fragment2;
import MyFragment.Fragment3;
import MyFragment.Fragment4;
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
    @BindView(R.id.mViewPager)
     ViewPager mViewPager;
    private List<Fragment> fragmentList;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.bottom_layout);
        ButterKnife.bind(this);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());
        fragmentList.add(new Fragment4());
        final MyFragmentViewPageAdapter myFragmentViewPageAdapter = new MyFragmentViewPageAdapter(getSupportFragmentManager(), fragmentList, titiles);
        mViewPager.setAdapter(myFragmentViewPageAdapter);
        //tabs.setViewPager(mViewPager);

        //禁止ViewPager滑动
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add:
                        Log.d("item id ====", Integer.toString(0));
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.delete:
                        Log.d("item id ====", Integer.toString(1));
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.setting:
                        Log.d("item id ====", Integer.toString(2));
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.me:
                        Log.d("item id ====", Integer.toString(3));
                        mViewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }


}
