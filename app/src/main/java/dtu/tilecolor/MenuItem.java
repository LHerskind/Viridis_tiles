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
    private char[][] map, playedMap;

    public MenuItem(int time, int steps, char[][] map, int index) {
        this.time = time;
        this.steps = steps;
        this.map = map;
        this.index = index;
    }

    public void setPlayedMap(char[][] map){
        this.playedMap = map;
    }

    public char[][] getPlayedMap(){ return playedMap; }

    public double getTime (){
        return time;
    }

    public void setTime(double time){ this.time = time;}

    public int getSteps(){
        return steps;
    }

    public void setSteps(int steps){ this.steps = steps;}

    public char[][] getMap() {return map; }

    public String toString(){
        return time +" " + steps;
    }

    public int getIndex() { return index; }


}
