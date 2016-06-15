package dtu.tilecolor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
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
    //private char[][] mapMatrix = {{'w','w','w','w'},{'w','s','r','w'},{'w','w','w','w'}};

    private char[][] mapMatrix;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        Intent intent = getIntent();
        mapMatrix = (char[][]) intent.getExtras().getSerializable("map");

        mFrame = (RelativeLayout) findViewById(R.id.frame);

        for(int i = 1; i < mapMatrix.length-1;i++){
            for(int j = 1; j < mapMatrix[i].length-1; j++){
                mFrame.addView(new TileView(this,mapMatrix[i][j],i,j));
            }
        }
    }

    @Override
    protected void onResume() {
        //TODO - lyd
        super.onResume();
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
            mDisplayWidth = mFrame.getWidth();
            mDisplayHeight = mFrame.getHeight();

        }
    }

    private void setupGestureDetector() {

        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {


                    @Override
                    public boolean onFling(MotionEvent event1, MotionEvent event2,
                                           float velocityX, float velocityY) {
                    /*
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
                        }*/
                        return true;
                    }
                });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }




}
