package dtu.tilecolor;

import android.content.Context;
import android.util.Log;
import android.view.Menu;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by User on 17-Jun-16.
 */
public class LoadMenuItems {

    private ArrayList<MenuItem> loadedList = new ArrayList<MenuItem>();
    private Context mContext;
    private String FILENAME = "testFilemost.srl";
    private File path;

    public LoadMenuItems(Context mContext) {
        this.mContext = mContext;
        path = mContext.getFilesDir();
        try {
            read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(MenuItem updateThis){
        loadedList.remove(updateThis.getIndex());
        loadedList.add(updateThis.getIndex(),updateThis);
        write(loadedList);
    }

    public void write(ArrayList<MenuItem> saveList){
        ObjectOutput out = null;
        try {
            File file = new File(path,"");
            file.getCanonicalFile().delete();
            out = new ObjectOutputStream(new FileOutputStream(file+File.separator+FILENAME));
            out.writeObject(saveList);
            Log.i("Tag","Skriver til fil");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void read(){
        ObjectInputStream input;
        try {
            input = new ObjectInputStream(new FileInputStream(new File(new File(path,"")+File.separator+FILENAME)));
            loadedList = (ArrayList<MenuItem>) input.readObject();
            Log.i("TAG","Reading from file"+loadedList.get(0).getSteps());
            input.close();
        } catch (Exception e) {
            create();
            write(loadedList);
        }

    }

    public void create() {
        for (int i = 0; i < 6; i++) {
            try {
                LoadMap loadmap = new LoadMap(mContext, "maps/map" + (i+1) + ".txt");
                char[][] map = loadmap.getMap();
                loadedList.add(new MenuItem(0, 0, map,i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<MenuItem> getLoadedList() {
        return loadedList;
    }

}
