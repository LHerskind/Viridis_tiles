package dtu.tilecolor;

import android.app.ActivityManager;
import android.app.FragmentManager;
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
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends AppCompatActivity {

    private Context mContext;
    private MenuItemAdapter adapter;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mContext = getApplicationContext();

        gridView = (GridView) findViewById(R.id.gridview);

        ArrayList<MenuItem> values = new LoadMenuItems(mContext).getLoadedList();

        adapter = new MenuItemAdapter(this, values);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                MenuItem item = (MenuItem) parent.getItemAtPosition(position);
                Intent intent = new Intent(mContext, GameActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", item);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Button randomMapButton = (Button) findViewById(R.id.randommap);

        randomMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewMap newMap = new CreateNewMap(mContext);
                startActivity(newMap.getIntent());
            }
        });
    }

    public void onResume(){
        adapter.update(new LoadMenuItems(mContext).getLoadedList());
        super.onResume();
    }

    public void onStop() {
        Intent musicService = new Intent(getBaseContext(), MusicService.class);
        stopService(musicService);
        super.onStop();
    }


}
