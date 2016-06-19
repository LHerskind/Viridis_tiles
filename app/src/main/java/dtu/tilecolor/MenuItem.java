package dtu.tilecolor;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by User on 15-Jun-16.
 */
public class MenuItem implements Serializable {

    private int index;
    private double time;
    private int steps;
    private char[][] map, oriMap;

    public MenuItem(int time, int steps, char[][] map, int index) {
        this.time = time;
        this.steps = steps;
        this.map = map;
        this.index = index;
        this.oriMap = new char[map.length][map[1].length];
        for(int i = 0; i < map.length; i++) {
            for(int j=0; j < map[1].length; j++){
                oriMap[i][j] = map[i][j];
            }
        }
    }

    public void resetMap(){
        for(int i = 0; i < this.oriMap.length; i++) {
            for(int j =0; j < this.oriMap[1].length; j++){
                this.map[i][j] = this.oriMap[i][j];
            }
        }
    }

    public char[][] getOriMap() {
        return oriMap;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] newMap) {
        this.map = newMap;
    }

    public String toString() {
        return time + " " + steps;
    }

    public int getIndex() {
        return index;
    }


}
