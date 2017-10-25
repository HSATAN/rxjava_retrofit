package MyFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huangkaijie.myapplication.R;

/**
 * Created by edison on 2017/10/26.
 */

public class Fragment4 extends Fragment {

    @Override
    public void onAttach(Context activity) {

        super.onAttach(activity);
        System.out.println("3333333333333333333____onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("3333333333333333333____onCreateView");
        return inflater.inflate(R.layout.fragment_4_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //activity=(MyMainActivity)getActivity();
        //这个方法会返回当前Fragment所附加的Activity，当Fragment生命周期结束并销毁时，getActivity()返回的是null
        //所以在使用时要注意判断null或者捕获空指针异常。


    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("3333333333333333333____onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("3333333333333333333____onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("3333333333333333333____onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("3333333333333333333____onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("AAAAAAAAAA____onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("AAAAAAAAAA____onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("AAAAAAAAAA____onDetach");
    }
}
