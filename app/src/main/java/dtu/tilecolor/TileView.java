package dtu.tilecolor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

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
        this.c=c;
        this.i=i;
        this.j=j;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
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
        int padding = 10;
        int size = 100;
        int x = size*(j);
        int y = size*(i);

        canvas.drawRect(x,y,x+(size-padding),y+(size-padding),mPaint);
    }
}
