package DataAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by huangkaijie on 2017/10/26.
 */

public class IndexAdapter extends FragmentPagerAdapter {
    private List<Fragment> mfragment;
    String titles[]  ;
    public IndexAdapter(FragmentManager fragmentManager, List<Fragment> fragments, String titles[]) {
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
