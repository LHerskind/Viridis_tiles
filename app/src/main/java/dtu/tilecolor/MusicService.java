package dtu.tilecolor;

import android.app.Service;
import android.media.MediaPlayer;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

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
        player = MediaPlayer.create(getBaseContext(), intent.getIntExtra("id", 0));
        player.setLooping(true);
        player.setVolume(volume, volume);
        player.start();
        return 1;
    }

    public void onStop() {

    }
    public void onPause() {

    }
    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {

    }

    public IBinder onBind(Intent arg0) {

        return null;
    }

    public IBinder onUnBind(Intent arg0) {
        return null;
    }
}
