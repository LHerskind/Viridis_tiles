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
		
		for(int i = 1; i < map.length-1; i++){
			for(int j = 1; j < map[i].length-1; j++){ 
				mapList[i+j] = map[i][j];
			}
		}
		
	}
	public char[] getMapList(){
		return mapList;
	}
	
	public char[][] getMap(){
		return map;
	}
}
