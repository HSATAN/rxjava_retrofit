package DataAdapter;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by edison on 2017/10/25.
 */

public class MyFragmentViewPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> mfragment;
    String titles[]  ;
    public MyFragmentViewPageAdapter(FragmentManager fragmentManager, List<Fragment> fragments, String titles[]) {
        super(fragmentManager);
        this.titles = titles;
        mfragment = fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return mfragment.get(position);
    }
    @Override
    public int getCount() {
        return mfragment.size();
    }
    @Override
    public CharSequence getPageTitle(int pos)
    {
        return titles[pos];
    }
}
