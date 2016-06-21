package dtu.tilecolor;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import android.os.Handler;

public class MainScreen extends AppCompatActivity {

    private Context mContext;
    private MenuItemAdapter adapter;
    private GridView gridView;
    private boolean clicked = false;
    private static boolean running = true;
    private ProgressBar progressBar;
    private CreateNewMap newMap;
    private Intent intentSend;
    private Thread t;

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(intentSend);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mContext = getApplicationContext();

        gridView = (GridView) findViewById(R.id.gridview);

        final ArrayList<MenuItem> values = new LoadMenuItems(mContext).getLoadedList();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        adapter = new MenuItemAdapter(this, values);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                if (!clicked) {
                    MenuItem item = (MenuItem) parent.getItemAtPosition(position);
                    Intent intent = new Intent(mContext, GameActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("item", item);
                    intent.putExtras(bundle);
                    clicked = true;
                    startActivity(intent);
                }
            }
        });

        Button randomMapButton = (Button) findViewById(R.id.randommap);

        assert randomMapButton != null;
        randomMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!clicked) {
                    progressBar.setVisibility(View.VISIBLE);
                    clicked = true;
                    t = new Thread(){
                        public void run(){
                            newMap = new CreateNewMap(mContext);
                            intentSend = newMap.getIntent();
                            handler.sendEmptyMessage(0);
                        }
                    };
                    t.start();
                }
            }
        });
    }

    public void onStart() {
        if (MusicOptions.musicService == null) {
            MusicOptions.musicService = new Intent(getBaseContext(), MusicService.class);
            MusicOptions.musicService = new Intent(getBaseContext(), MusicService.class);
            MusicOptions.musicService.putExtra("volume", 50);
            MusicOptions.musicService.putExtra("id", R.raw.sweet);
            startService(MusicOptions.musicService);
        }
        super.onStart();
    }

    public void onResume() {
        progressBar.setVisibility(View.GONE);
        adapter.update(new LoadMenuItems(mContext).getLoadedList());
        clicked = false;
        if (MusicOptions.musicService != null && !running && MusicOptions.checked_music)
            startService(MusicOptions.musicService);
        super.onResume();
    }

    public void onPause(){
        super.onPause();
        t = null;
        running = false;
        if (clicked == false) {
            stopService(MusicOptions.musicService);
        }
        GameActivity.musicStopped = false;
    }

    public void onStop() {
        super.onStop();
    }
}
