package com.wangyz.handlerthread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String THREAD_NAME = "HandlerThread";

    private static final int MSG_THREAD_UPDATE = 1;

    private static final int MSG_MAIN_UPDATE = 2;

    private Button mSend;

    private TextView mInfo;

    private MainHandler mMainHandler;

    private Handler mThreadHandler;

    private HandlerThread mHandlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSend = findViewById(R.id.btn_send);
        mInfo = findViewById(R.id.tv_info);

        mSend.setOnClickListener(this);

        mHandlerThread = new HandlerThread(THREAD_NAME);
        mHandlerThread.start();

        mThreadHandler = new Handler(mHandlerThread.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_THREAD_UPDATE:
                        //在子线程中执行耗时任务
                        SystemClock.sleep(3000);
                        mMainHandler.sendEmptyMessage(MSG_MAIN_UPDATE);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        mMainHandler = new MainHandler(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_send) {
            mThreadHandler.sendEmptyMessage(MSG_THREAD_UPDATE);
        }
    }

    public void updateInfo() {
        mInfo.setText("更新数据" + new Random().nextInt(10));
    }

    static class MainHandler extends Handler {

        private WeakReference<Activity> mWeakReference;

        public MainHandler(Activity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = (MainActivity) mWeakReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case MSG_MAIN_UPDATE:
                        activity.updateInfo();
                        break;
                    default:
                        break;
                }
            }

        }
    }
}
