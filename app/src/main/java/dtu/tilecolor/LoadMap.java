package dtu.tilecolor;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Jacob on 15/06/2016.
 */
public class LoadMap {

    private char[][] mapArray;
    private int col, row;
    private String map;
    public LoadMap(Context context, String filepath) throws IOException {
        InputStream input = context.getResources().getAssets().open(filepath);
        Scanner scanner = new Scanner(input);

        row = scanner.nextInt();
        col = scanner.nextInt();
        Log.i("LoadMap", ""+row+" "+col);

        map = "";

        while(scanner.hasNextLine()){
            map+=scanner.nextLine();
        }

        mapArray = new char[row][col];
        for(int i=0, k=0; i < row; i++) {
            for(int j=0; j < col; j++, k++) {
                mapArray[i][j] = map.charAt(k);
            }
        }
    }
    public char[][] getMap() {
        return mapArray;
    }
}
