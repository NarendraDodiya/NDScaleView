package com.nd.scaleview.example;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.TextView;

import com.nd.scaleview.HorizontalScaleView;
import com.nd.scaleview.ScaleAdapter;



public class MainActivity extends AppCompatActivity {

    private HorizontalScaleView mScaleView = null;
    private ScaleAdapter mScaleAdapter = null;
    private Context mContext = null;
    private TextView m_tvWeight = null;
    private int mFactor = 1;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;

        m_tvWeight = (TextView) findViewById(R.id.tv_value);

        mScaleView = (HorizontalScaleView) findViewById(R.id.scale);
        mScaleView.setOnScrollListener(m_ScrollListener);
        int minValue = 0;
        int maxValue = 300;
        mScaleAdapter = new ScaleAdapter(mContext, minValue, maxValue, mFactor);
        mScaleView.setAdapter(mScaleAdapter);
        mScaleView.setCurrentValue(25);

        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Log.i("OnScrollListener", "scrolling :" + msg.arg1);
                int[] para = mScaleView.getCurrentPosition();
                Log.i("Calculate ",+para[0]+" "+para[1]+" "+para[2]+" "+para[3]);

                int kg = para[0];
                int addition = para[1]/5;
                kg = kg + addition;

                int gm = (para[1]%5)*2;
                m_tvWeight.setText(kg+"."+gm+" kg");
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    HorizontalScaleView.OnScrollListener m_ScrollListener=new HorizontalScaleView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(final AdapterView<?> view, int status) {

            if (((HorizontalScaleView) view).isScrollFinish()) {
//                Toast.makeText(mContext, "scroll finished", Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void onScroll(AdapterView<?> view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            Message mMessage = Message.obtain();
            mMessage.arg1 = firstVisibleItem;
            mHandler.sendMessage(mMessage);
        }

        public void onFlingHorizontalView(int mDirection) {

        }

        @Override
        public void onUp(AdapterView<?> view) {

        }
    };
}
