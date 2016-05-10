package com.nd.scaleview;

/**
 * Created by narendra on 10/5/16.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ScaleAdapter extends BaseAdapter {
    private Context mContext;
    private int MIN = 5;
    private int MAX = 0;
    private int factor = 1;

    public ScaleAdapter(Context m_Context, int min, int max, int factor){
        this.mContext = m_Context;
        this.MIN = min;
        this.MAX = max;
        this.factor = factor;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public int getCount() {
        return MAX - MIN;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UnitView unitView;
        if(convertView == null) {
            unitView = new UnitView(mContext);
        } else {
            unitView = (UnitView)convertView;
        }
        unitView.setDivision(5);
        unitView.setPosition(MIN + position, factor);
        return unitView;
    }

}