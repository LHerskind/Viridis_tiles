package dtu.tilecolor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

import dtu.tilecolor.R;

/**
 * Created by steenlund on 6/15/16.
 */
public class MusicOptions extends Activity {
    Intent musicService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_selector);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final SeekBar seekValue = (SeekBar) findViewById(R.id.volume_bar);
        final Switch toggle = (Switch) findViewById(R.id.music_switch);

        Button dinosaur = (Button) findViewById(R.id.dinosaur);
        dinosaur.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggle.isChecked()) {
                    if(musicService != null) {
                        stopService(musicService);
                        musicService = null;
                    }
                    musicService = new Intent(getBaseContext(), MusicService.class);
                    musicService.putExtra("volume", seekValue.getProgress());
                    musicService.putExtra("id", R.raw.dinosaur);
                    startService(musicService);
                }
            }
        });

        Button september = (Button) findViewById(R.id.september);
        september.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggle.isChecked()) {
                    if(musicService != null) {
                        stopService(musicService);
                        musicService = null;
                    }
                    musicService = new Intent(getBaseContext(), MusicService.class);
                    musicService.putExtra("volume", seekValue.getProgress());
                    musicService.putExtra("id", R.raw.september);
                    startService(musicService);
                }
            }
        });

        Button expecto = (Button) findViewById(R.id.expecto);
        expecto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggle.isChecked()) {
                    if(musicService != null) {
                        stopService(musicService);
                        musicService = null;
                    }
                    musicService = new Intent(getBaseContext(), MusicService.class);
                    musicService.putExtra("volume", seekValue.getProgress());
                    musicService.putExtra("id", R.raw.expecto);
                    startService(musicService);
                }
            }
        });

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b && musicService != null)
                    stopService(musicService);
                else
                    if(musicService != null)
                        startService(musicService);
            }
        });
    }

    public void onDestroy() {
        stopService(musicService);
        super.onDestroy();
    }
}
