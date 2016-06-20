package dtu.tilecolor;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Meowasaurus on 14-06-2016.
 */
public class GameActivity extends Activity {

    private RelativeLayout mFrame;
    private RelativeLayout mTextFrame;
    private TextView stepsView;
    private TextView timeView;
    private GestureDetector mGestureDetector;
    private GameLogic gb;
    private char[][] mapMatrix;
    private TileView[][] tileMatrix;
    private Context mContext;
    private int size;
    private TileView player;
    private boolean timeRunning = false;
    private int steps = 1;
    private int timeSpent;
    private boolean ended = false;
    private Button restart;
    private Button music;
    private MenuItem item;
    private MediaPlayer mp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mp = MediaPlayer.create(this, R.raw.perc);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.gamelayout);
        Intent intent = getIntent();
        mContext = getApplicationContext();
        Bundle bundle = intent.getExtras();
        item = (MenuItem) bundle.getSerializable("item");
        mapMatrix = item.getMap();

        mFrame = (RelativeLayout) findViewById(R.id.gameframe);
        mTextFrame = (RelativeLayout) findViewById(R.id.gametextframe);
        timeView = (TextView) findViewById(R.id.textView);
        stepsView = (TextView) findViewById(R.id.textView2);

        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        size = (point.x) / 5;

        mFrame.setPadding(10, size / 2, 10, 10);
        mTextFrame.setMinimumHeight(size / 2);


        tileMatrix = new TileView[mapMatrix.length][mapMatrix[1].length];
        for (int i = 1; i < mapMatrix.length - 1; i++) {
            for (int j = 1; j < mapMatrix[i].length - 1; j++) {
                tileMatrix[i][j] = new TileView(mContext, mapMatrix[i][j], i, j, size);
                mFrame.addView(tileMatrix[i][j]);
                if (mapMatrix[i][j] == 's') {
                    player = new TileView(mContext, mapMatrix[i][j], i, j, i, j, true, size);
                }
            }
        }
        mFrame.addView(player);
        gb = new GameLogic(mapMatrix, tileMatrix);
        restart = (Button) findViewById(R.id.buttonGameActivityRestart);
        music = (Button) findViewById(R.id.buttonGameActivityMusic);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent musicIntent = new Intent(GameActivity.this, MusicOptions.class);
                startActivity(musicIntent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        setupGestureDetector();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    private void setupGestureDetector() {
        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
                if (player.isReady() && !ended) {
                    if (mp != null) {
                        mp.start();
                    }
                    if (!timeRunning) {
                        timeRunning = true;
                        startTime();
                    }
                    if (Math.abs(event1.getX() - event2.getX()) > Math.abs(event1.getY() - event2.getY())) {
                        if (event1.getX() > event2.getX()) {
                            if (gb.canMove("LEFT")) {
                                stepsView.setText("" + steps++);
                                gb.movePlayer("LEFT");
                                tileMatrix[gb.getLastPos()[0]][gb.getLastPos()[1]].invalidate();
                                player.startSlide("LEFT");
                            }
                        } else {
                            if (gb.canMove("RIGHT")) {
                                stepsView.setText("" + steps++);
                                gb.movePlayer("RIGHT");
                                tileMatrix[gb.getLastPos()[0]][gb.getLastPos()[1]].invalidate();
                                player.startSlide("RIGHT");
                            }
                        }
                    } else {
                        if (event1.getY() > event2.getY()) {
                            if (gb.canMove("UP")) {
                                stepsView.setText("" + steps++);
                                gb.movePlayer("UP");
                                tileMatrix[gb.getLastPos()[0]][gb.getLastPos()[1]].invalidate();
                                player.startSlide("UP");
                            }
                        } else {
                            if (gb.canMove("DOWN")) {
                                stepsView.setText("" + steps++);
                                gb.movePlayer("DOWN");
                                tileMatrix[gb.getLastPos()[0]][gb.getLastPos()[1]].invalidate();
                                player.startSlide("DOWN");
                            }
                        }
                    }
                    if (gb.hasWon()) {
                        hasEnded(true);
                    } else if (gb.hasLost()) {
                        hasEnded(false);
                    }
                }
                return true;
            }
        });
    }

    private void hasEnded(boolean won) {
        ended = true;
        restart.setVisibility(View.GONE);
        music.setVisibility(View.GONE);
        timeRunning = false;
        WinLoseFragment winLoseFragment = new WinLoseFragment();
        Bundle bundle = new Bundle();
        if (won) {
            if (item.getIndex() != -1) {
                if ((steps - 1 < item.getSteps()) || item.getSteps() == 0) {
                    item.setSteps(steps - 1);
                    item.setTime(timeSpent);
                    new LoadMenuItems(mContext).update(item);
                }
            }
            bundle.putString("text", "You won");
            bundle.putBoolean("won", true);
        } else {
            bundle.putString("text", "You lost");
            bundle.putBoolean("won", false);
        }
        winLoseFragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.gameframe, winLoseFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void startTime() {
        Thread th = new Thread(new Runnable() {
            private long startTime = System.currentTimeMillis();

            public void run() {
                while (timeRunning) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            timeSpent = (int) ((System.currentTimeMillis() - startTime) / 1000);
                            timeView.setText("" + timeSpent);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        th.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
