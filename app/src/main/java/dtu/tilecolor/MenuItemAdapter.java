package dtu.tilecolor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 17-Jun-16.
 */
public class MenuItemAdapter extends BaseAdapter {

    private ArrayList<MenuItem> items;
    private Context context;

    public MenuItemAdapter(Context context, ArrayList<MenuItem> menuItems) {
//        super(context, 0,menuItems);
        super();
        this.context = context;
        this.items = menuItems;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public MenuItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void update(ArrayList<MenuItem> newItems){
        this.items = newItems;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MenuItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.itemlayout, parent, false);
        }
        TextView numberOfSteps = (TextView) convertView.findViewById(R.id.steps);
        TextView timeToComplete = (TextView) convertView.findViewById(R.id.timeToComplete);

        numberOfSteps.setText(""+item.getSteps());
        timeToComplete.setText(""+item.getTime());

        RelativeLayout mFrame = (RelativeLayout) convertView.findViewById(R.id.littleframe);

        char[][] mapMatrix = item.getMap();
        mFrame.removeAllViews();
        int size = parent.getWidth() /16;
        for(int i = 1; i < mapMatrix.length-1;i++){
            for(int j = 1; j < mapMatrix[i].length-1; j++){
                mFrame.addView(new TileView(context,mapMatrix[i][j],i,j,i,j,false,size));
                if(mapMatrix[i][j]=='s'){
                    mFrame.addView(new TileView(context,mapMatrix[i][j],i,j,i,j,true,size));
                }
            }
        }
        mFrame.invalidate();
        convertView.invalidate();
        mFrame.setMinimumHeight(size*(mapMatrix.length-2) );


        return convertView;
    }
}