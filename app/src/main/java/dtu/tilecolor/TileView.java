package dtu.tilecolor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Meowasaurus on 14-06-2016.
 */
public class TileView extends View {

    private final Paint mPaint = new Paint();
    private char c;
    private int i2;
    private int j2;
    private int size;
    private int size2;
    private boolean isPlayer = false;
    private boolean isStart;
    private int padding;

    private int x;
    private int y;
    private int x2;
    private int y2;

    public TileView(Context context, char c, int i, int j, int size){
        super(context);
        this.size = size;
        this.padding = size/10;
        this.c=c;
        x = size * (j - 1);
        y = size * (i - 1);
    }

    public TileView(Context context, char c, int i, int j, int i2, int j2, boolean isStart, int size){
        super(context);
        this.padding = size/10;
        this.size = size;
        this.size2 = (int)(size*0.86);
        this.c = c;
        x = size * (j - 1) + (size-size2)/2;
        y =  (size * (i - 1) + (size-size2)/2);
        x2 = size * (j2 - 1);
        y2 = size * (i2 - 1);
        this.isPlayer=true;
        this.isStart=isStart;
    }


    @Override
    protected synchronized void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        canvas.save();
        if(!isPlayer){
            setColor(c);
            canvas.drawRect(x, y, x + (size - padding), y + (size - padding), mPaint);
        }else {
            mPaint.setColor(Color.BLUE);
            canvas.drawRect(x, y, x + (size2 - padding), y + (size2 - padding), mPaint);
            if(!isStart) {
                setColor(c);
                canvas.drawRect(x2, y2, x2 + (size - padding), y2 + (size - padding), mPaint);
                isPlayer = false;
            }
        }
        canvas.restore();
    }

    private boolean isReady = true;
    private int moved;
    public void startSlide(final String direction) {
        if (isPlayer){
        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            moved = 0;
        executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                isReady = false;
                int velocity = size/50;
                if(moved >= size){
                    isReady = true;
                    executor.shutdown();
                } else {
                    moved += velocity;
                    slide(direction, velocity);
                }
            }
        }, 0, 2, TimeUnit.MILLISECONDS);
        }
    }

    public boolean isReady(){
        return isReady;
    }

    private synchronized void slide(String direction, int velocity){
        if(direction.equals("RIGHT")) {
            x+= velocity;
        } else if (direction.equals("LEFT")) {
            x-= velocity;
        } else if (direction.equals("UP")) {
            y-= velocity;
        } else if (direction.equals("DOWN")) {
            y+= velocity;
        }
        this.postInvalidate();
    }


    public void setChar(char c){
        this.c = c;
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
