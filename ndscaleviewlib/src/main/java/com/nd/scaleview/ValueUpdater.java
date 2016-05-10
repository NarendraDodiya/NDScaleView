package com.nd.scaleview;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.AdapterView;

/**
 * Created by NARENDRA on 10-05-2016.
 */
public abstract class ValueUpdater implements HorizontalScaleView.OnScrollListener{

    private Handler mHandler;

    public ValueUpdater(final HorizontalScaleView scaleView){
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Log.i("OnScrollListener", "scrolling :" + msg.arg1);
                int[] para = scaleView.getCurrentPosition();
                Log.i("Calculate ",+para[0]+" "+para[1]+" "+para[2]+" "+para[3]);

                int kg = para[0];
                int addition = para[1]/5;
                kg = kg + addition;

                int gm = (para[1]%5)*2;

                String value = kg+"."+gm+" kg";
                onValueAvailable(value);
                return true;
            }
        });
    }
    @Override
    public void onScroll(AdapterView<?> view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Message mMessage = Message.obtain();
        mMessage.arg1 = firstVisibleItem;
        mHandler.sendMessage(mMessage);
    }

    @Override
    public void onUp(AdapterView<?> view) {

    }

    @Override
    public void onScrollStateChanged(AdapterView<?> view, int status) {

    }

    @Override
    public void onFlingHorizontalView(int direction) {

    }

    public abstract void onValueAvailable(String value);
}
