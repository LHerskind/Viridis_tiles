package dtu.tilecolor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Random;

/**
 * Created by User on 20-Jun-16.
 */
public class CreateNewMap {

    private Context mContext;
    private Intent intent;
    public CreateNewMap(Context mContext){
        this.mContext = mContext;
        createMap();
    }

    public void createMap(){
        boolean solveAble = false;
        while(!solveAble){
            RandomMap randomMap = new RandomMap();
            AI ai = new AI();
            solveAble = ai.isSolvable(randomMap.getMapList());

            if(solveAble){
                char[][] map = randomMap.getMap();
                intent = new Intent(mContext, GameActivity.class);
                Bundle bundle = new Bundle();
                MenuItem item = new MenuItem(0,0,map,-1);
                bundle.putSerializable("item", item);
                intent.putExtras(bundle);
            }
        }
    }

    public Intent getIntent(){
        return intent;
    }


}
