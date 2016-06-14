package dtu.tilecolor;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.widget.RelativeLayout;

/**
 * Created by Meowasaurus on 14-06-2016.
 */
public class GameActivity extends Activity {

    private RelativeLayout mFrame;
    private Bitmap mBitmap;
    private int mDisplayWidth,getmDisplayHeigth;
    private GestureDetector mGestureDetector;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.main);

        mFrame = (RelativeLayout) findViewById(R.id.frame);

       // mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.g);


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
