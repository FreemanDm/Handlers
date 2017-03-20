package com.freeman.handlers.handlers;


import android.os.Handler;

/**
 * Created by Freeman on 08.01.2017.
 */

public class EmptyMsgThread extends Thread {
    private Handler handler;

    public EmptyMsgThread(Handler handler) {
        this.handler = handler;
    }



    @Override
    public void run() {
        handler.sendEmptyMessage(MainActivity.START_WORK);

        try{
            Thread.sleep(5000);
            if (true) {
                throw new InterruptedException();
            }
            handler.sendEmptyMessage(MainActivity.WORK_WAS_DONE);
        }
        catch (InterruptedException e){
            e.printStackTrace();
            handler.sendEmptyMessage(MainActivity.WORK_WAS_DONE_WITH_ERROR);
        }
    }
}
