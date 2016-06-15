package dtu.tilecolor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
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
    private int size;

    public TileView(Context context, char c, int i, int j){
        super(context);
        this.size = 100;
        this.c=c;
        this.i=i;
        this.j=j;
    }

    public TileView(Context context, char c, int i, int j, int size){
        this(context,c,i,j);
        this.size = size;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int black = ContextCompat.getColor(getContext(), R.color.dark_background);
        int red = ContextCompat.getColor(getContext(), R.color.red);
        int green = ContextCompat.getColor(getContext(), R.color.green);

        if(c == 'w') {
            mPaint.setColor(black);
        } else if (c == 'r'){
            mPaint.setColor(red);
        } else if (c == 'g'){
            mPaint.setColor(green);
            //} else if (c == 'p'){
            // TODO - player
        }
        int padding = 10;
        int x = size*(j);
        int y = size*(i);

        canvas.drawRect(x,y,x+(size-padding),y+(size-padding),mPaint);
    }
}
