package dtu.tilecolor;

import android.util.Log;

/**
 * Created by matiasdaugaard on 14/06/16.
 */
public class GameLogic {

    private char[][] mapMatrix;
    private TileView[][] tileMatrix;
    private int playerRow;
    private int playerCol;
    private int[] lastPos = new int[2];


    public GameLogic(char[][] mapMatrix, TileView[][] tileMatrix){
        this.mapMatrix = mapMatrix;
        this.tileMatrix = tileMatrix;
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
        if(mapMatrix[playerRow][playerCol] == 'r' || mapMatrix[playerRow][playerCol] == 's'){
            mapMatrix[playerRow][playerCol] = 'g';
            tileMatrix[playerRow][playerCol].setChar('g');
        }else if(mapMatrix[playerRow][playerCol] == 'g'){
            mapMatrix[playerRow][playerCol] = 'w';
            tileMatrix[playerRow][playerCol].setChar('w');
        }
    }

    public void movePlayer(String direction){

            updateMap();
            lastPos[0] = playerRow;
            lastPos[1] = playerCol;

            if (direction.equals("UP")) {
                playerRow--;
            }
            if (direction.equals("DOWN")) {
                playerRow++;
            }
            if (direction.equals("LEFT")) {
                playerCol--;
            }
            if (direction.equals("RIGHT")) {
                playerCol++;

        }
    }

    public boolean canMove(String direction){
        if(direction.equals("UP")){
            return (mapMatrix[playerRow-1][playerCol] != 'w');
        }if(direction.equals("DOWN")){
            return (mapMatrix[playerRow+1][playerCol] != 'w');
        }if(direction.equals("LEFT")){
            return (mapMatrix[playerRow][playerCol-1] != 'w');
        }if(direction.equals("RIGHT")){
            return (mapMatrix[playerRow][playerCol+1] != 'w');
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


