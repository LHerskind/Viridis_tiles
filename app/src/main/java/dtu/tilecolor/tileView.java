package dtu.tilecolor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Meowasaurus on 14-06-2016.
 */
public class tileView extends View {

    private final Paint mPaint = new Paint();
    private LinearLayout mFrame;
    private char c;
    private int i;
    private int j;

    public tileView(Context context, char c, int i, int j){
        super(context);

        this.c=c;
        this.i=i;
        this.j=j;



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(c == 'w') {
            mPaint.setColor(Color.BLACK);
        } else if (c == 'r'){
            mPaint.setColor(Color.RED);
        } else if (c == 'g'){
            mPaint.setColor(Color.GREEN);
            //} else if (c == 'p'){
            // TODO - player
        }

        mPaint.setStrokeWidth(0);

        canvas.drawRect(16+(j-1)*(50+10),16+(i-1)*(50+10),50,50,mPaint);

    }
}
