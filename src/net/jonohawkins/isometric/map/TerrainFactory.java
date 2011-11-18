package net.jonohawkins.isometric.map;

public class TerrainFactory {
	
	public static double log2(double x) {
		return Math.pow(2,(Math.ceil(Math.log(x)/Math.log(2))));
	}
	
	public static MapElement[][] generateRandomMap (Map map, long seed) {
		
		MapElement[][] tempMap = new MapElement[map.getMapLength()][map.getMapLength()];
		// Num squares = 2 ^ (I + 2) therefore lb(Num squares) - 2 = I
		int iterations = (int)log2(map.getMapLength() ^ 2) - 2;
		boolean square = true;
		
		for (int cI = iterations; cI > 0 ; cI++) {
			if (square) {
				
			} else {
				// Diamond
				
			}
			square = !square;
		}
		
	}

}
