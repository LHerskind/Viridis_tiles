package dtu.tilecolor;

import android.app.Service;
import android.media.MediaPlayer;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {

    public static MediaPlayer player;
    public static int duration;
    public static int song = 0;

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null) {
            float volume = 50;
            if (intent.hasExtra("volume"))
                volume = intent.getIntExtra("volume", 0);
            volume /= 100;
            if (intent.hasExtra("id")) {
                player = MediaPlayer.create(getApplicationContext(), intent.getIntExtra("id", 0));
                if (song == intent.getIntExtra("id", 1))
                    player.seekTo(duration);
                else
                    song = intent.getIntExtra("id", 1);
            }
            player.setLooping(true);
            player.setVolume(volume, volume);
            player.start();
        }
        return START_STICKY;
    }


    public void onStop() {

    }

    public void onPause() {

    }
    @Override
    public void onDestroy() {
        duration = player.getCurrentPosition();
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
