package dtu.tilecolor;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import dtu.tilecolor.R;

public class MusicOptions extends Activity {
    public static Intent musicService;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_selector);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final SeekBar seekValue = (SeekBar) findViewById(R.id.volume_bar);
        final Switch toggle = (Switch) findViewById(R.id.music_switch);
        TextView statement = (TextView) findViewById(R.id.music_statement);

        Button goingHigher = (Button) findViewById(R.id.going_higher);
        goingHigher.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggle.isChecked()) {
                    if(musicService != null) {
                        stopService(musicService);
                        musicService = null;
                    }
                    musicService = new Intent(getBaseContext(), MusicService.class);
                    musicService.putExtra("volume", seekValue.getProgress());
                    musicService.putExtra("id", R.raw.going_higher);
                    startService(musicService);
                }
            }
        });

        Button jazzyFrench = (Button) findViewById(R.id.jazzy_french);
        jazzyFrench.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggle.isChecked()) {
                    if(musicService != null) {
                        stopService(musicService);
                        musicService = null;
                    }
                    musicService = new Intent(getBaseContext(), MusicService.class);
                    musicService.putExtra("volume", seekValue.getProgress());
                    musicService.putExtra("id", R.raw.jazzy_french);
                    startService(musicService);
                }
            }
        });

        Button newBeginning = (Button) findViewById(R.id.new_beginning);
        newBeginning.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggle.isChecked()) {
                    if(musicService != null) {
                        stopService(musicService);
                        musicService = null;
                    }
                    musicService = new Intent(getBaseContext(), MusicService.class);
                    musicService.putExtra("volume", seekValue.getProgress());
                    musicService.putExtra("id", R.raw.new_beginning);
                    startService(musicService);
                }
            }
        });

        Button sweet = (Button) findViewById(R.id.sweet);
        sweet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggle.isChecked()) {
                    if(musicService != null) {
                        stopService(musicService);
                        musicService = null;
                    }
                    musicService = new Intent(getBaseContext(), MusicService.class);
                    musicService.putExtra("volume", seekValue.getProgress());
                    musicService.putExtra("id", R.raw.sweet);
                    startService(musicService);
                }
            }
        });

        seekValue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                stopService(musicService);
                musicService.putExtra("volume", progress);
                startService(musicService);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b && musicService != null)
                    stopService(new Intent(MusicOptions.this, MusicService.class));
                else
                    if (musicService != null)
                        startService(musicService);
            }
        });

        statement.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Music Creator Statement: ", Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(), "You can use the music for free in " +
                        "your multimedia project (online videos (Youtube,...)," +
                        " websites, animations, etc.) as long as you credit me. ", Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(), "Credit: " + "www.bensound.com", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onDestroy() {
//        stopService(musicService);
        super.onDestroy();
    }
}
