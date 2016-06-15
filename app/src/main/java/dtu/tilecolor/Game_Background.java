package dtu.tilecolor;

/**
 * Created by matiasdaugaard on 14/06/16.
 */
public class Game_Background {


    private char[][] mapMatrix = {{'w','w','w','w'},{'w','s','r','w'},{'w','r','r','w'},{'w','w','w','w'}};
    private int playerRow;
    private int playerCol;


    public Game_Background(char[][] mapMatrix){
        this.mapMatrix = mapMatrix;
        playerRow = getStartPosition()[0];
        playerCol = getStartPosition()[1];

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
        if(mapMatrix[playerRow][playerCol] == 'r'){
            mapMatrix[playerRow][playerCol] = 'g';
        }else if(mapMatrix[playerRow][playerCol] == 'g'){
            mapMatrix[playerRow][playerCol] = 'w';
        }
    }

    public void movePlayer(String direction){
        if(direction.equals("UP")){
            playerRow--;
        }if(direction.equals("DOWN")){
            playerRow++;
        }if(direction.equals("LEFT")){
           playerCol--;
        }if(direction.equals("RIGHT")){
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

    public int[] getPlayerTile() {
        return new int[] {playerRow,playerCol};
    }
}


