package dtu.tilecolor;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Meowasaurus on 14-06-2016.
 */
public class GameActivity extends Activity {

    private RelativeLayout mFrame;
    private Bitmap mBitmap;
    private int mDisplayWidth,mDisplayHeight;
    private GestureDetector mGestureDetector;
    private char[][] mapMatrix = {{'w','w','w','w'}{'w','s','r','w'}{'w','w','w','w'}};


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.main);

        mFrame = (RelativeLayout) findViewById(R.id.frame);



    }

    @Override
    protected void onResume() {
        //TODO - lyd
        super.onResume();

        //TODO - Visualize map

        for(int i = 0; i < mapMatrix.length;i++){
            for(int j = 0; j < mapMatrix[i].length; j++){
                //TODO - fix plads i tileView kald
                new tileView(mapMatrix[i][j]);

            }
        }

    }

    @Override
    protected void onPause() {
        //TODO - lyd
        super.onPause();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {

            // Get the size of the display so this View knows where borders are
            mDisplayWidth = mFrame.getWidth();
            mDisplayHeight = mFrame.getHeight();

        }
    }

    private void setupGestureDetector() {

        mGestureDetector = new GestureDetector(this,

                new GestureDetector.SimpleOnGestureListener() {


                    @Override
                    public boolean onFling(MotionEvent event1, MotionEvent event2,
                                           float velocityX, float velocityY) {

                        if (velocityX > velocityY) {
                            if (event1.getX() > event2.getX()) {
                                //TODO
                                //swipe til venstre, ryk brik til venstre
                            } else {
                                //swipe til højre, ryk brik til højre
                            }

                            if (event1.getY() > event2.getY()) {
                                //swipe op, ryk brik op
                            } else {
                                //swipe ned, ryk brik ned
                            }
                        }


                        return false;

                    }

                });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }




}
