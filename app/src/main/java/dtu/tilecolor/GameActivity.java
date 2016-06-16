package dtu.tilecolor;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by Meowasaurus on 14-06-2016.
 */
public class GameActivity extends Activity {

    private RelativeLayout mFrame;
    private Bitmap mBitmap;
    private int mDisplayWidth, mDisplayHeight;
    private GestureDetector mGestureDetector;
    private Game_Background gb;
    private char[][] mapMatrix;
    private TileView[][] tileMatrix;
    private Context mContext;
    private int size;
    private TileView player;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.gamelayout);
        Intent intent = getIntent();
        mContext = this;
        mapMatrix = (char[][]) intent.getExtras().getSerializable("map");

        mFrame = (RelativeLayout) findViewById(R.id.gameframe);

        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        size = (point.x - 20) / 5;
        tileMatrix = new TileView[mapMatrix.length][mapMatrix[1].length];
        for (int i = 1; i < mapMatrix.length - 1; i++) {
            for (int j = 1; j < mapMatrix[i].length - 1; j++) {
                tileMatrix[i][j]= new TileView(mContext, mapMatrix[i][j], i, j, size);
                mFrame.addView(tileMatrix[i][j]);
                if (mapMatrix[i][j] == 's') {
                    player = new TileView(mContext, mapMatrix[i][j], i, j, i, j, true, size);
                }
            }
        }
        mFrame.addView(player);
        gb = new Game_Background(mapMatrix, tileMatrix);
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


                if (Math.abs(event1.getX() - event2.getX()) > Math.abs(event1.getY() - event2.getY())) {
                    if (event1.getX() > event2.getX()) {
                        if (gb.canMove("LEFT")) {

                            //swipe til venstre, ryk brik til venstre
                            gb.movePlayer("LEFT");
                            tileMatrix[gb.getLastPos()[0]][gb.getLastPos()[1]].invalidate();
                            player.startSlide("LEFT");

                            //flyt på skærm
                        }


                    } else {
                        if (gb.canMove("RIGHT")) {
                            //swipe til højre, ryk brik til højre
                            gb.movePlayer("RIGHT");
                            tileMatrix[gb.getLastPos()[0]][gb.getLastPos()[1]].invalidate();
                            //flyt på skærm
                            player.startSlide("RIGHT");
                        }
                    }
                } else {

                    if (event1.getY() > event2.getY()) {
                        if (gb.canMove("UP")) {
                            //swipe op, ryk brik op
                            gb.movePlayer("UP");
                            tileMatrix[gb.getLastPos()[0]][gb.getLastPos()[1]].invalidate();
                            //flyt på skærm
                            player.startSlide("UP");
                        }

                    } else {
                        if (gb.canMove("DOWN")) {
                            //swipe ned, ryk brik ned
                            gb.movePlayer("DOWN");
                            tileMatrix[gb.getLastPos()[0]][gb.getLastPos()[1]].invalidate();
                            //fly på skærm
                            player.startSlide("DOWN");
                        }

                    }
                }
                if(gb.hasWon()){
                    WinLoseFragment newFragment = new WinLoseFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.gameframe, newFragment);
                    transaction.addToBackStack(null);

                    transaction.commit();
                } else if(gb.hasLost()){
                    Toast.makeText(mContext, "Du er en kæmpe taber", Toast.LENGTH_SHORT).show();
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
