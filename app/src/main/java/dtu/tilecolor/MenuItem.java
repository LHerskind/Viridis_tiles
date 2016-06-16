package dtu.tilecolor;

/**
 * Created by User on 15-Jun-16.
 */
public class MenuItem {

    private String time;
    private String steps;
    private char[][] map;

    public MenuItem(String time, String steps, char[][] map) {
        this.time = time;
        this.steps = steps;
        this.map = map;
    }

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
