package dtu.tilecolor;

import java.io.Serializable;

/**
 * Created by User on 15-Jun-16.
 */
public class MenuItem implements Serializable {

    private String time, steps;
    private char[][] map, playedMap;

    public MenuItem(String time, String steps, char[][] map) {
        this.time = time;
        this.steps = steps;
        this.map = map;
    }

    public void setPlayedMap(char[][] map){
        this.playedMap = map;
    }

    public char[][] getPlayedMap(){ return playedMap; }

    public String getTime (){
        return time;
    }

    public String getSteps(){
        return steps;
    }

    public char[][] getMap() {return map; }

    public String toString(){
        return time +" " + steps;
    }


}
