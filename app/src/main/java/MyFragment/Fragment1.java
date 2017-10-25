package MyFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.huangkaijie.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import DataAdapter.MyFragmentViewPageAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by edison on 2017/10/25.
 */

public class Fragment1 extends Fragment {

    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    Unbinder unbinder;
    String titiles[] = {"first", "secong"};
    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        System.out.println("BBBBBBBBBBB____onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("BBBBBBBBBBB____onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("BBBBBBBBBBB____onCreateView");
        View view = inflater.inflate(R.layout.viewpager_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());
        MyFragmentViewPageAdapter myFragmentViewPageAdapter = new MyFragmentViewPageAdapter(getFragmentManager(), fragmentList, titiles);
        mViewPager.setAdapter(myFragmentViewPageAdapter);
        //tabs.setViewPager(mViewPager);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("BBBBBBBBBBB____onActivityCreated");
//        this.getView().findViewById(R.id.clickme).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // 获得绑定的FragmentActivity
//                MainActivity activity = ((MainActivity)getActivity());
//                // 获得TabAFm的控件
//                EditText editText = (EditText) activity.fragments.get(0).getView().findViewById(R.id.edit);
//
//                Toast.makeText(activity, activity.hello + editText.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("BBBBBBBBBBB____onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("BBBBBBBBBBB____onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("BBBBBBBBBBB____onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("BBBBBBBBBBB____onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("BBBBBBBBBBB____onDestroyView");
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("BBBBBBBBBBB____onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("BBBBBBBBBBB____onDetach");
    }


}
