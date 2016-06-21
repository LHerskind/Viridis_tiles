package dtu.tilecolor;

import android.util.Log;

import java.util.Random;

public class RandomMap {

	Random r = new Random();
	char[][] map = new char[9][7];
	char[] mapList = new char[35];
	
	public RandomMap(){
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){ 
				if(i == 0 || j == 0 || i == 8 || j == 6){
					map[i][j]= 'w';
				}else if(i == 1 && j == 1){
					map[i][j] = 's';
				}
				else{
					int x = r.nextInt(99)+1;
					if(x > 50){
						map[i][j] = 'r';
					}
					else if(x > 25){
						map[i][j]='g';
					}
					else{
						map[i][j]='w';
					}
				}
			}	
		}

		int k= 0;
		for(int i = 1; i < map.length-1; i++){
			for(int j = 1; j < map[i].length-1; j++, k++){
				mapList[k] = map[i][j];
			}
		}
	}

	public int[] getMapList(){
		int[] list = new int[mapList.length];
		for(int i = 0; i < mapList.length; i++){
			if(mapList[i] == 'w'){
				list[i] = 0;
			} else if(mapList[i] == 'r'){
				list[i] = 2;
			} else if(mapList[i] == 'g'){
				list[i] = 1;
			} else if(mapList[i] == 's') {
				list[i]=-1;
			}
		}
		return list;
	}


	
	public char[][] getMap(){
		return map;
	}
}
