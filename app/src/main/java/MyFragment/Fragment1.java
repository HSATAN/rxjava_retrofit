package MyFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
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

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.huangkaijie.myapplication.R.id.mViewPager;

/**
 * Created by edison on 2017/10/25.
 */

public class Fragment1 extends Fragment {

    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(mViewPager)
    ViewPager subViewPager;
    Unbinder unbinder;
    String titiles[] = {"first", "secong"};

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        System.out.println("1111111111111111111____onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("1111111111111111111____onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("1111111111111111111____onCreateView");
        View view = inflater.inflate(R.layout.viewpager_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new ViewFragment1());
        fragmentList.add(new ViewFragment2());
        Log.d("fragmentlist-------",Integer.toString(fragmentList.size()));
        MyFragmentViewPageAdapter myFragmentViewPageAdapter = new MyFragmentViewPageAdapter(getFragmentManager(), fragmentList, titiles);
        subViewPager.setAdapter(myFragmentViewPageAdapter);
        tabs.setViewPager(subViewPager);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("1111111111111____onActivityCreated");
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
        System.out.println("1111111111111111111____onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("1111111111111111111____onResume");
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
