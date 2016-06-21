package dtu.tilecolor;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by User on 17-Jun-16.
 */
public class LoadMenuItems {

    private ArrayList<MenuItem> loadedList = new ArrayList<MenuItem>();
    private Context mContext;
    private String FILENAME = "gamedata.srl";
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
        updateThis.resetMap();
        loadedList.remove(updateThis.getIndex());
        loadedList.add(updateThis.getIndex(),updateThis);
        write(loadedList);
    }

    // Kan gøres væsentligt smartere, lige nu skriver vi den hele på nu, kan vi ikke bare ændre i noget af det istedet.
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
            Log.i("TAG","Reading from file");
            input.close();
        } catch (Exception e) {
            create();
            write(loadedList);
        }

    }
    // Vi læser hvor mange filer der er og looper henover
    public void create() {
        String[] list = new String[0];
        try {
            list = mContext.getAssets().list("maps");
            Log.i("Tag",""+list.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.length; i++) {
            try {
                LoadMap loadmap = new LoadMap(mContext, "maps/map" + (i+1) + ".txt");
                char[][] map = loadmap.getMap();
                loadedList.add(new MenuItem(0, 0, map,i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for(int i= 0; i < list.length-1; i++){
            loadedList.get(i).setNext(loadedList.get(i+1));
        }

    }

    public ArrayList<MenuItem> getLoadedList() {
        return loadedList;
    }

}
