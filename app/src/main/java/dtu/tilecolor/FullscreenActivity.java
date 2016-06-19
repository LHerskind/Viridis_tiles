package dtu.tilecolor;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FullscreenActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mContext = getApplicationContext();

        final GridView gridView = (GridView) findViewById(R.id.gridview);

        ArrayList<MenuItem> values = new LoadMenuItems(mContext).getLoadedList();

        MenuItemAdapter adapter = new MenuItemAdapter (this, values);

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()  {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                MenuItem item = (MenuItem) parent.getItemAtPosition(position);
                // Når vi skal opdatere hvordan det ser ud, brug adapter.notifyDataSetChanged();
                    Intent intent = new Intent(mContext, GameActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("item",item);
                    intent.putExtras(bundle);
                    startActivity(intent);

            }
        });
    }

    public void onPause() {
        Intent musicService = new Intent(getBaseContext(), MusicService.class);
        stopService(musicService);
        super.onPause();
    }


    }
