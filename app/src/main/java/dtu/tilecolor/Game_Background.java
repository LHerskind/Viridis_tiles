package dtu.tilecolor;

/**
 * Created by matiasdaugaard on 14/06/16.
 */
public class Game_Background {


    private char[][] mapMatrix;
    private int[] playerTile = new int[2];


    public Game_Background(char[][] mapMatrix){
        this.mapMatrix = mapMatrix;
        playerTile = getStartPosition();
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

    public void updateMap(int[] position){

    }

    public boolean hasWon(){
        return false;
    }

    public boolean hasLost(){
        return false;
    }


    public char[][] getMapMatrix() {
        return mapMatrix;
    }

    public int[] getPlayerTile() {
        return playerTile;
    }
}


