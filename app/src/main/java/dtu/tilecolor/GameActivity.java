package dtu.tilecolor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * Created by Meowasaurus on 14-06-2016.
 */
public class GameActivity extends Activity {

    private RelativeLayout mFrame;
    private Bitmap mBitmap;
    private int mDisplayWidth,mDisplayHeight;
    private GestureDetector mGestureDetector;
    private Game_Background gb;
    private char[][] mapMatrix;
    private Context mContext;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.gamelayout);
        Intent intent = getIntent();
        mContext = this;
        mapMatrix = (char[][]) intent.getExtras().getSerializable("map");
        gb = new Game_Background(mapMatrix);

        mFrame = (RelativeLayout) findViewById(R.id.gameframe);


        for(int i = 1; i < mapMatrix.length-1;i++){
            for(int j = 1; j < mapMatrix[i].length-1; j++){
                mFrame.addView(new TileView(mContext,mapMatrix[i][j],i,j));
                if(mapMatrix[i][j]=='s'){
                    mFrame.addView(new TileView(mContext,mapMatrix[i][j],i,j,i,j,true));
                }
            }
        }
    }

    @Override
    protected void onResume() {
        //TODO - lyd
        super.onResume();
        setupGestureDetector();
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


                        if (velocityX > velocityY) {
                            if (event1.getX() > event2.getX()) {
                                if (gb.canMove("LEFT")) {

                                    //swipe til venstre, ryk brik til venstre
                                    gb.movePlayer("LEFT");

                                    //flyt på skærm
                                    mFrame.addView(new TileView(mContext, mapMatrix[gb.getLastPos()[0]][gb.getLastPos()[1]]
                                            , gb.getPlayerRow(), gb.getPlayerCol(), gb.getLastPos()[0], gb.getLastPos()[1], false));
                                }


                            } else {
                                if (gb.canMove("RIGHT")) {
                                    //swipe til højre, ryk brik til højre
                                    gb.movePlayer("RIGHT");
                                    //flyt på skærm
                                    mFrame.addView(new TileView(mContext, mapMatrix[gb.getLastPos()[0]][gb.getLastPos()[1]]
                                            , gb.getPlayerRow(), gb.getPlayerCol(), gb.getLastPos()[0], gb.getLastPos()[1], false));
                                }
                            }
                        } else{

                            if (event1.getY() > event2.getY()) {
                                if(gb.canMove("UP")){
                                    //swipe op, ryk brik op
                                    gb.movePlayer("UP");
                                    //flyt på skærm
                                    mFrame.addView(new TileView(mContext,mapMatrix[gb.getLastPos()[0]][gb.getLastPos()[1]]
                                            ,gb.getPlayerRow(),gb.getPlayerCol(),gb.getLastPos()[0],gb.getLastPos()[1],false));
                                }

                            } else {
                                if(gb.canMove("DOWN")){
                                    //swipe ned, ryk brik ned
                                    gb.movePlayer("DOWN");
                                    //fly på skærm
                                    mFrame.addView(new TileView(mContext,mapMatrix[gb.getLastPos()[0]][gb.getLastPos()[1]]
                                            ,gb.getPlayerRow(),gb.getPlayerCol(),gb.getLastPos()[0],gb.getLastPos()[1],false));
                                }

                            }
                        }
                        return true;
                    }
                });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }




}
