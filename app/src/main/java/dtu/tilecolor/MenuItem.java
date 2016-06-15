package dtu.tilecolor;

/**
 * Created by User on 15-Jun-16.
 */
public class MenuItem {

    private String time;
    private String steps;

    public MenuItem(String time, String steps) {
        this.time = time;
        this.steps = steps;
    }

    public String getTime (){
        return time;
    }

    public String getSteps(){
        return steps;
    }

    public String toString(){
        return time +" " + steps;
    }


}
