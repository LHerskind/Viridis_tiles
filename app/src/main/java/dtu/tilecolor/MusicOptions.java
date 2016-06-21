package dtu.tilecolor;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
    private static int sound_level;
    public static boolean checked_music = true;
    private static boolean checked_tile = true;
    private static int progress_levels = 50;
    public static boolean entered = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_selector);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final SeekBar seekValue = (SeekBar) findViewById(R.id.volume_bar);
        final Switch music_toggle = (Switch) findViewById(R.id.music_switch);
        final Switch tile_toggle = (Switch) findViewById(R.id.tile_sound);

        music_toggle.setChecked(checked_music);
        tile_toggle.setChecked(checked_tile);
        seekValue.setProgress(progress_levels);

        TextView statement = (TextView) findViewById(R.id.music_statement);

        final Button goingHigher = (Button) findViewById(R.id.going_higher);
        goingHigher.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(music_toggle.isChecked()) {
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
                if(music_toggle.isChecked()) {
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
                if(music_toggle.isChecked()) {
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
                if(music_toggle.isChecked()) {
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
                progress_levels = progress;
                if(musicService != null) {
                    stopService(musicService);
                    musicService.putExtra("volume", progress);
                    startService(musicService);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        music_toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b) {
                    checked_music = false;
                    if(musicService != null)
                        stopService(new Intent(MusicOptions.this, MusicService.class));
                } else {
                    checked_music = true;
                    if (musicService != null) {
                        startService(musicService);
                    }
                }
            }
        });

        tile_toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checked_tile = !checked_tile;
                if(b)
                    GameActivity.play_sound = true;
                else
                    GameActivity.play_sound = false;
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
        super.onDestroy();
    }

    public void onResume() {
        if(MusicOptions.musicService != null && entered == false)
            startService(MusicOptions.musicService);
        super.onResume();
    }

    public void onPause() {
        entered = false;
        GameActivity.musicStopped = true;
        stopService(musicService);
        super.onPause();
    }
}
