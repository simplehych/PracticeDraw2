package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();

    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

        //把所有的拐角变成圆角
        paint.setFilterBitmap(true);

        // 第一处：CornerPathEffect
        paint.setPathEffect(new CornerPathEffect(10));
        canvas.drawPath(path, paint);

        canvas.save();
        canvas.translate(300, 0);
        // 第二处：DiscretePathEffect
        paint.setPathEffect(new DiscretePathEffect(10, 5));
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 150);
        // 第三处：DashPathEffect
        paint.setPathEffect(new DashPathEffect(new float[]{20, 5}, 10));
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(300, 150);
        // 第四处：PathDashPathEffect
        Path dashPath = new Path();
        dashPath.lineTo(20, -30);
        dashPath.lineTo(40, 0);
        dashPath.close();
        paint.setPathEffect(new PathDashPathEffect(dashPath, 50, 0, PathDashPathEffect.Style.TRANSLATE));
        canvas.drawPath(this.path, paint);
        canvas.restore();

        DiscretePathEffect discretePathEffect = new DiscretePathEffect(10, 5);
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{30, 5}, 10);

        canvas.save();
        canvas.translate(0, 300);
        // 第五处：SumPathEffect
        SumPathEffect sumPathEffect = new SumPathEffect(discretePathEffect, dashPathEffect);
        paint.setPathEffect(sumPathEffect);
        canvas.drawPath(this.path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(300, 300);
        // 第六处：ComposePathEffect
        ComposePathEffect composePathEffect = new ComposePathEffect(discretePathEffect, dashPathEffect);
        paint.setPathEffect(composePathEffect);
        canvas.drawPath(this.path, paint);
        canvas.restore();
    }
}
