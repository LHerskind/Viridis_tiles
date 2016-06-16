package dtu.tilecolor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.view.View;

/**
 * Created by Meowasaurus on 14-06-2016.
 */
public class TileView extends View {

    private final Paint mPaint = new Paint();
    private char c;
    private int i;
    private int j;
    private int i2;
    private int j2;
    private int size;
    private int size2;
    private boolean isPlayer = false;
    private boolean isStart;

    public TileView(Context context, char c, int i, int j, int i2, int j2, boolean isStart){
        super(context);
        this.size = 100*3;
        this.size2 = 86*3;
        this.c=c;
        this.i=i;
        this.j=j;
        this.i2=i2;
        this.j2=j2;
        this.isPlayer=true;
        this.isStart=isStart;

    }

    public TileView(Context context, char c, int i, int j){
        super(context);
        this.size = 100*3;
        this.c=c;
        this.i=i;
        this.j=j;
    }

    public TileView(Context context, char c, int i, int j, int size){
        this(context,c,i,j);
        this.size = size;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isPlayer){
            mPaint.setColor(Color.BLUE);
            int padding = size / 10;
            int x = size * (j - 1) + (size-size2)/2;
            int y =  (size * (i - 1) + (size-size2)/2);
            canvas.drawRect(x, y, x + (size2 - padding), y + (size2 - padding), mPaint);
            if(!isStart) {
                setColor(c);
                padding = size / 10;
                x = size * (j2 - 1);
                y = size * (i2 - 1);
                canvas.drawRect(x, y, x + (size - padding), y + (size - padding), mPaint);
                isPlayer = false;
            }
        }else {
            setColor(c);
            int padding = size / 10;
            int x = size * (j - 1);
            int y = size * (i - 1);
            canvas.drawRect(x, y, x + (size - padding), y + (size - padding), mPaint);
        }
    }


    public void setColor(char c){
        int black = ContextCompat.getColor(getContext(), R.color.dark_background);
        int red = ContextCompat.getColor(getContext(), R.color.red);
        int green = ContextCompat.getColor(getContext(), R.color.green);
        if(c == 'w') {
            mPaint.setColor(black);
        } else if (c == 'r'||c == 's'){
            mPaint.setColor(red);
        } else if (c == 'g'){
            mPaint.setColor(green);
        }
    }


}
