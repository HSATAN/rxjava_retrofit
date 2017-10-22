package com.example.huangkaijie.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by edison on 2017/10/21.
 */

public class RecyleActivity extends Activity {


    @BindView(R.id.recyle_textView)
    TextView recyleTextView;
    @BindView(R.id.recyle_button2)
    Button recyleButton2;
    @BindView(R.id.recyle_button)
    Button recyleButton;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.recyle_layout);
        ButterKnife.bind(this);
        recyleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
