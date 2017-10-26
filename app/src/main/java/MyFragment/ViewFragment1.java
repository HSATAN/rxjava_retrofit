package MyFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.huangkaijie.myapplication.R;
import com.example.huangkaijie.myapplication.RecylerActivity;

import java.util.ArrayList;
import java.util.List;

import MyActivity.PersonalActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by edison on 2017/10/26.
 */

public class ViewFragment1 extends Fragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    Unbinder unbinder;
    private List<String> datas;
    @Override
    public void onAttach(Context activity) {

        super.onAttach(activity);
        System.out.println("AAAAAAAAAA____onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("AAAAAAAAAA____onCreateView");
        View view = inflater.inflate(R.layout.recylerlayout, container, false);
        unbinder = ButterKnife.bind(this, view);

        initData();
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.addItemDecoration(new Docoration());
        recyclerview.setAdapter(new RecylerViewAdapter(getContext(),datas,recyclerview));
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState== RecyclerView.SCROLL_STATE_IDLE)
                {
                    datas.add("我是最后添加的");
                }
            }
        });
        return view;
    }
    public void initData()
    {
        datas = new ArrayList<>();
        for(int i=0;i<100;i++)
        {
            datas.add("item :"+ i);
        }
    }
    public class Docoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //设定底部边距为1px
            outRect.set(0, 0, 0, 3);//设置分割线的高度
        }

    }
    public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.MyViewHolder> {

        private List<String> datas;
        private AdapterView.OnItemClickListener itemClickListener;
        private Context context;
        private final RecyclerView recyclerView;
        public RecylerViewAdapter(Context context,List<String> datas, RecyclerView recyclerView1){
            recyclerView=recyclerView1;
            inflater=LayoutInflater.from(context);
            this.datas = datas;
            this.context=context;
        }


        public  void adddatas(){
            notifyDataSetChanged();
        }

        private LayoutInflater inflater;
        @Override
        public RecylerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
        {
            //创建viewholder

            View itemView = inflater.inflate(R.layout.test_item,parent,false);
            //R.layout.test_item是包含要使用的控件的布局文件，不是recylerview布局文件
            return new MyViewHolder(itemView);
        }
        @Override
        public void onBindViewHolder(RecylerViewAdapter.MyViewHolder holder,int position)
        {
            //将数据与item绑定

            holder.textView.setText(datas.get(position));
            Glide.with(context).load("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2925211415,1996121311&fm=27&gp=0.jpg").into(holder.iv);
            //加载图像到imageview



            /**
             * Glide 用法
             * 1.网络加载图片到ImageView中
             *Glide.with(context).load(imageUrl).placeholder(R.mipmap.ic_launcher).into(imageView);
             *
             *
             *2.当加载网络图片时，由于加载过程中图片未能及时显示，此时可能需要设置等待时的图片，通过placeHolder()方法
             * Glide.with(context).load(imageUrl).placeholder(R.mipmap.ic_launcher).into(imageView);
             *
             * 3.当加载图片失败时，通过error(Drawable drawable)方法设置加载失败后的图片显示
             * Glide.with(context).load(imageUrl).error(R.mipmap.ic_launcher).into(imageView);
             *
             *
             * 4.图片的缩放，centerCrop()和fitCenter()：
             * 1)使用centerCrop是利用图片图填充ImageView设置的大小，如果ImageView的Height是match_parent则图片就会被拉伸填充
             * Glide.with(context).load(imageUrl).centerCrop().into(imageView);
             *
             * 2)使用fitCenter即缩放图像让图像都测量出来等于或小于 ImageView 的边界范围,该图像将会完全显示，但可能不会填满整个
             * Glide.with(context).load(imageUrl).fitCenter().into(imageView);
             *
             * 5.显示gif动画,asGif()判断是否是gif动画
             * Glide.with(context).load(imageUrl).asGif().into(imageView);
             *
             * 6.显示本地视频
             * String filePath = "/storage/emulated/0/Pictures/example_video.mp4";
             Glide.with( context ).load( Uri.fromFile( new File( filePath ) ) ).into(imageViewGifAsBitmap );


             7.缓存策略
             Glide.with( context ).load(imageUrl).skipMemoryCache(true).into(imageViewInternet );//跳过内存缓存
             Glide.with( context ).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.NONE).into( imageViewInternet );//跳过硬盘缓存
             DiskCacheStrategy.NONE 什么都不缓存
             DiskCacheStrategy.SOURCE 仅仅只缓存原来的全分辨率的图像
             DiskCacheStrategy.RESULT 仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
             DiskCacheStrategy.ALL 缓存所有版本的图像（默认行为）

             8.优先级，设置图片加载的顺序：

             Glide.with(context).load(imageUrl).priority( Priority.HIGH).into( imageView);


             一些使用技巧

             1.Glide.with(context).resumeRequests()和 Glide.with(context).pauseRequests()

             当列表在滑动的时候，调用pauseRequests()取消请求，滑动停止时，调用resumeRequests()恢复请求。这样是不是会好些呢？

             2.Glide.clear()

             当你想清除掉所有的图片加载请求时，这个方法可以帮助到你。

             3.ListPreloader

             如果你想让列表预加载的话，不妨试一下ListPreloader这个类。


             */
        }

        @Override
        public int getItemCount()
        {
            return datas.size();
        }
        class MyViewHolder extends RecyclerView.ViewHolder
        {
            public TextView textView;
            public ImageView iv;

            public MyViewHolder(View itemView)
            {

                super(itemView);
                textView = (TextView)itemView.findViewById(R.id.textView2);
                iv = (ImageView) itemView.findViewById(R.id.iv);

                itemView.setOnClickListener(new View.OnClickListener() {//点击事件
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(RecylerActivity.this,"-------------------",Toast.LENGTH_SHORT);
                        Intent intent = new Intent(getActivity(), PersonalActivity.class);
                        startActivity(intent);
                    }
                });
                itemView.setOnLongClickListener(new View.OnLongClickListener() {//长按事件
                    @Override
                    public boolean onLongClick(View view) {

                        Log.d("longclick","------------");

                        return true;
                    }
                });
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Glide.with(context).load("http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=971de28cc1ef7609280691dc46b4c9b9/4a36acaf2edda3cce7305e310be93901203f92cf.jpg").into(iv);
                    }
                });

            }
        }
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
        System.out.println("AAAAAAAAAA____onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("AAAAAAAAAA____onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("AAAAAAAAAA____onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("AAAAAAAAAA____onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("AAAAAAAAAA____onDestroyView");
        unbinder.unbind();
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
