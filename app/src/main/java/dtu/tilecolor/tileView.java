package dtu.tilecolor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Meowasaurus on 14-06-2016.
 */
public class TileView extends View {

    private final Paint mPaint = new Paint();
    private char c;
    private int i;
    private int j;

    public TileView(Context context, char c, int i, int j){
        super(context);
        this.c = c;
        this.i = i;
        this.j = j;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStrokeWidth(0);
        if(c == 'r'){
            mPaint.setColor(Color.RED);
        }
        if(c == 'g'){
            mPaint.setColor(Color.GREEN);
        }
        canvas.drawRect(i,j,10,10,mPaint);
    }
}
