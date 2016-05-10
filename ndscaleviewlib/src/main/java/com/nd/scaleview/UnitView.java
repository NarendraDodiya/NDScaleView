package com.nd.scaleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class UnitView extends View {

    private static final int HEIGHT = 160;
    private static final int WIDTH = 80;

    private static final int TOP_OFFSET = 100;
    private static final int SMALL_SCALE_OFFSET = TOP_OFFSET + 50;
    private int division = 5;

    private Context m_context = null;
    private Paint p = null;
    private int strokeWidth;

    private String position = "null";

    public UnitView(Context context) {
        super(context);
        init(context);
    }

    public UnitView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UnitView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        m_context = context;
        p = new Paint();
        p.setColor(Color.parseColor("#D4D7D9"));
        p.setTextSize(pxFromDp(20));
        strokeWidth = (int)pxFromDp(2);
    }

    public void setDivision(int division){
        this.division = division;
    }

    public int getDivision(){
        return division;
    }

    public int getStrokeWidth(){
        return strokeWidth;
    }

    public void setPosition(int position, int displayFactor) {
        this.position = String.valueOf(position*displayFactor);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setStrokeWidth(strokeWidth);
        int height = getHeight();
        int width = getWidth();

        canvas.drawLine(0, TOP_OFFSET, 0, height, p);

        int smallUnit = width / division;
        p.setStrokeWidth(strokeWidth);
        for (int i = 1; i < division; i++) {
            canvas.drawLine(smallUnit * i, SMALL_SCALE_OFFSET, smallUnit * i, height, p);
        }
        p.setTextSize(40f);
        canvas.drawText(position, 0, 50, p);

        p.setStrokeWidth(strokeWidth);
        canvas.drawLine(width, TOP_OFFSET, width, height, p);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((int)pxFromDp(WIDTH), (int)pxFromDp(HEIGHT));
    }

    private float pxFromDp(float dp) {
        return dp * m_context.getResources().getDisplayMetrics().density;
    }

}
