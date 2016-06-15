package dtu.tilecolor;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Jacob on 15/06/2016.
 */
public class LoadMap {

    private char[][] mapArray;
    private int col, row;
    public LoadMap(Context context, String filepath) throws IOException {
        InputStream input = context.getResources().getAssets().open(filepath);
        BufferedReader br = new BufferedReader(new InputStreamReader(input));

        row = Integer.parseInt(br.readLine());
        col = Integer.parseInt(br.readLine());
        String map = "";

        while(br.readLine()!= null){
            map+=br.readLine();
        }

        mapArray = new char[row][col];
        for(int i=0; i < row; i++) {
            for(int j =0; j < col; j++) {
                mapArray[i][j] = map.charAt(i*row+j);
            }
        }
    }

    public char[][] getMap() {
        return mapArray;
    }
    public int getCol() { return col; }
}
