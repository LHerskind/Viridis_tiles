package dtu.tilecolor;

import android.util.Log;

/**
 * Created by matiasdaugaard on 14/06/16.
 */
public class Game_Background {


    private char[][] mapMatrix;
    private int playerRow;
    private int playerCol;
    private int[] lastPos = new int[2];


    public Game_Background(char[][] mapMatrix){
        this.mapMatrix = mapMatrix;
        playerRow = getStartPosition()[0];
        playerCol = getStartPosition()[1];
        lastPos[0] = playerRow;
        lastPos[1]= playerCol;

    }

    private int[] getStartPosition() {
        for(int i = 0; i < mapMatrix.length; i++){
            for(int j = 0; j < mapMatrix[i].length; j++){
                if(mapMatrix[i][j] == 's'){
                    return new int[] {i,j};
                }
            }
        }
        return new int[] {0,0};
    }

    public void updateMap(){
        if(mapMatrix[playerRow][playerCol] == 'r'){
            mapMatrix[playerRow][playerCol] = 'g';
        }else if(mapMatrix[playerRow][playerCol] == 'g'){
            mapMatrix[playerRow][playerCol] = 'w';
        }
    }

    public void movePlayer(String direction){

            updateMap();
            if (direction.equals("UP")) {
                Log.i("PlayerMove","UP");
                lastPos[0] = playerRow;
                playerRow--;
            }
            if (direction.equals("DOWN")) {
                Log.i("PlayerMove","DOWN");
                lastPos[0] = playerRow;
                playerRow++;
            }
            if (direction.equals("LEFT")) {
                Log.i("PlayerMove","LEFT");
                lastPos[1] = playerCol;
                playerCol--;
            }
            if (direction.equals("RIGHT")) {
                Log.i("PlayerMove","RIGHT");
                lastPos[1] = playerCol;
                playerCol++;

        }
    }

    public boolean canMove(String direction){
        Log.i("PlayerMove","canMove");
        if(direction.equals("UP")){
            return (mapMatrix[playerRow-1][playerCol] != 'w');
        }if(direction.equals("DOWN")){
            return (mapMatrix[playerRow+1][playerCol] != 'w');
        }if(direction.equals("LEFT")){
            return (mapMatrix[playerRow][playerCol-1] != 'w');
        }if(direction.equals("RIGHT")){
            return (mapMatrix[playerRow][playerRow+1] != 'w');
        }
        return false;
    }

    public boolean hasWon(){
        for(int i = 0; i < mapMatrix.length; i++){
            for(int j = 0;j < mapMatrix[i].length; j++){
                if(mapMatrix[i][j] == 'r'){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasLost(){
        return (mapMatrix[playerRow-1][playerCol]=='w'
                && mapMatrix[playerRow+1][playerCol]=='w'
                && mapMatrix[playerRow][playerCol-1]=='w'
                && mapMatrix[playerRow][playerCol+1]=='w');
    }

    public char[][] getMapMatrix() {

        return mapMatrix;
    }

    public int[] getLastPos(){
        return lastPos;
    }

    public int getPlayerRow(){
        return playerRow;
    }

    public int getPlayerCol() {
        return playerCol;
    }

    public int[] getPlayerTile() {
        return new int[] {playerRow,playerCol};
    }
}


