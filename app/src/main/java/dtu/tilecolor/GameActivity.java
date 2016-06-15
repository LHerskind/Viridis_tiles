package dtu.tilecolor;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

/**
 * Created by Meowasaurus on 14-06-2016.
 */
public class GameActivity extends Activity {

    private RelativeLayout mFrame;
    private TableLayout mTable;
    private Bitmap mBitmap;
    private int mDisplayWidth,mDisplayHeight;
    private GestureDetector mGestureDetector;
    private char[][] mapMatrix = {{'w','w','w','w'},{'w','p','r','w'},{'w','w','w','w'}};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        mFrame = (RelativeLayout) findViewById(R.id.frame);
        Log.i("test","onCreate her");

        for(int i = 1; i < mapMatrix.length-1;i++){
            for(int j = 1; j < mapMatrix[i].length-1; j++){
                Log.i("test","2nd Loop her");

                //TODO - fix plads i tileView kald

                mFrame.addView(new TileView(this,mapMatrix[i][j],i,j));
            }
        }

    }

    @Override
    protected void onResume() {
        //TODO - lyd
        super.onResume();

        //TODO - Visualize map (Tror det er gjort)


    }

    @Override
    protected void onPause() {
        //TODO - lyd
        super.onPause();
    }



    private void setupGestureDetector() {

        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {


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
