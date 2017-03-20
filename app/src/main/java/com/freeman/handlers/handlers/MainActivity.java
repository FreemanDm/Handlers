package com.freeman.handlers.handlers;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private FrameLayout progressFrame;
    private Button startThreadBtn, getNameBtn;
    private TextView resultTxt;
    private Handler handler;
    public static final int WORK_WAS_DONE = 1;
    public static final int START_WORK = 2;
    public static final int WORK_WAS_DONE_WITH_ERROR = 3;
    public static final int GET_NAME = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressFrame = (FrameLayout) findViewById(R.id.progress_frame);
        startThreadBtn = (Button) findViewById(R.id.start_thread_btn);
        getNameBtn = (Button) findViewById(R.id.get_name_btn);
        resultTxt = (TextView) findViewById(R.id.result_txt);
        progressFrame.setOnClickListener(null);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == WORK_WAS_DONE){
                    progressFrame.setVisibility(View.GONE);
                }
                else if (msg.what == START_WORK){
                    progressFrame.setVisibility(View.VISIBLE);
                }
                else if (msg.what == WORK_WAS_DONE_WITH_ERROR){
                    progressFrame.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Fatal exception! All was bad!", Toast.LENGTH_SHORT).show();
                }else if (msg.what == GET_NAME){
                    progressFrame.setVisibility(View.GONE);
                    String name = (String) msg.obj;
                    resultTxt.setText(name);
                }
            }
        };
        startThreadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EmptyMsgThread(handler).start();
            }
        });

        getNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ObtaineMsgThread(handler).start();
            }
        });
    }
}
