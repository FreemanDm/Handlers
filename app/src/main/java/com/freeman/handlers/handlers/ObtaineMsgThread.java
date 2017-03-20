package com.freeman.handlers.handlers;

import android.os.Handler;
import android.os.Message;

/**
 * Created by Freeman on 08.01.2017.
 */

public class ObtaineMsgThread extends Thread {
    private Handler handler;

    public ObtaineMsgThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        handler.sendEmptyMessage(MainActivity.START_WORK);
        try {
            sleep(5000);
            String name = "Vasya";
//            Message msg = new Message();
//            msg.what = MainActivity.GET_NAME;
//            msg.obj = name;
//            handler.sendMessage(msg);

            Message msg = handler.obtainMessage(MainActivity.GET_NAME, name);
            handler.sendMessage(msg);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
