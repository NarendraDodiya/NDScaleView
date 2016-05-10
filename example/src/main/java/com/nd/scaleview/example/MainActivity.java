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
import com.nd.scaleview.ValueUpdateListener;


public class MainActivity extends AppCompatActivity {

    private HorizontalScaleView mScaleView = null;
    private ScaleAdapter mScaleAdapter = null;
    private Context mContext = null;
    private TextView m_tvWeight = null;
    private int mFactor = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;

        m_tvWeight = (TextView) findViewById(R.id.tv_value);

        mScaleView = (HorizontalScaleView) findViewById(R.id.scale);
        int minValue = 0;
        int maxValue = 300;
        mScaleAdapter = new ScaleAdapter(mContext, minValue, maxValue, mFactor);
        mScaleView.setAdapter(mScaleAdapter);
        mScaleView.setCurrentValue(25);
        mScaleView.setValueUpdateListener(new ValueUpdateListener() {
            @Override
            public void onValueUpdate(String value) {
                m_tvWeight.setText(value);
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
}
