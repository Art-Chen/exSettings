package moe.chenxy.extrasettings.dolby;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import moe.chenxy.extrasettings.R;

public class DashLineView extends View {
    private Paint mPaint = new Paint(1);
    private Path mPath;

    public DashLineView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPaint.setColor(context.getColor(R.color.dolby_dash_line));
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(2.0f);
        this.mPaint.setPathEffect(new DashPathEffect(new float[]{2.0f, 4.0f}, 0.0f));
        this.mPath = new Path();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.mPath.reset();
        float height = (float) (getHeight() / 2);
        this.mPath.moveTo(0.0f, height);
        this.mPath.lineTo((float) getWidth(), height);
        canvas.drawPath(this.mPath, this.mPaint);
    }
}