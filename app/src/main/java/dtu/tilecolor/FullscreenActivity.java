package dtu.tilecolor;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    private View mContentView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mContext = getApplicationContext();
        // mContentView = findViewById(R.id.fullscreen_content);

        final GridView gridView = (GridView) findViewById(R.id.gridview);

        ArrayList<MenuItem> values = new ArrayList<MenuItem>();

        for(int i = 0; i < 30; i++){
            values.add(new MenuItem("0:"+i,""+i));
        }

        MenuItemAdapter adapter = new MenuItemAdapter (this, values);


        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                MenuItem item = (MenuItem) parent.getItemAtPosition(position);
                Toast.makeText(mContext, item.toString(), Toast.LENGTH_LONG).show();
                // Når vi skal opdatere hvordan det ser ud, brug adapter.notifyDataSetChanged();
            }
        });
    }

    public class MenuItemAdapter extends ArrayAdapter<MenuItem> {
        public MenuItemAdapter(Context context, ArrayList<MenuItem> menuItems) {
            super(context, 0, menuItems);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            MenuItem item = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemlayout, parent, false);
            }
            TextView numberOfSteps = (TextView) convertView.findViewById(R.id.steps);
            TextView timeToComplete = (TextView) convertView.findViewById(R.id.timeToComplete);

            numberOfSteps.setText(item.getSteps());
            timeToComplete.setText(item.getTime());

            return convertView;
        }
    }

}
