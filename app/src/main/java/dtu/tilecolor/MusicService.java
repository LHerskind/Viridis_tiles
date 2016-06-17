package dtu.tilecolor;

import android.app.ActivityManager;
import android.app.Service;
import android.content.pm.ResolveInfo;
import android.media.MediaPlayer;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.List;

/**
 * Created by steenlund on 6/15/16.
 */
public class MusicService extends Service {

    public MediaPlayer player;

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        float volume = intent.getIntExtra("volume", 0);
        volume /= 100;
        player = MediaPlayer.create(getApplicationContext(), intent.getIntExtra("id", 0));
        player.setLooping(true);
        player.setVolume(volume, volume);
        player.start();
        return START_STICKY;
    }


    public void onStop() {

    }

    public void onPause() {

    }
    @Override
    public void onDestroy() {
        player.stop();
        player.release();
        super.onDestroy();
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }

    public IBinder onUnBind(Intent arg0) {
        return null;
    }
}
