package net.jonohawkins.isometric.map;

import java.util.Arrays;
import java.util.Random;

import net.jonohawkins.isometric.map.elements.*;

public class DiamondQuareTerrainFactory {
	
	public static double lastMax;
	
	public static double log2(double x) {
		return (Math.ceil(Math.log(x)/Math.log(2)));
	}
	
	public static double randomMinMax(double min, double max) {
		return (Math.random() * (max - min)) + min;
	}
	
	public static double randomMinMax(double min, double max, Random r) {
		return (r.nextDouble() * (max - min)) + min;
	}
	
	public static MapElement[][] generateRandomMap (Map map, long seed, double roughness) {
		
		Random random = new Random(seed);
		
		if (roughness > 1) roughness = 1;
		if (roughness < 0) roughness = 0;
		
		System.out.println("Map Length: " + map.getMapLength());
		MapElement[][] tempMap = new BasicMapElement[map.getMapLength()][map.getMapLength()];
		// Num squares = 2 ^ (I + 2) therefore lb(Num squares) - 2 = I
		int iterations = (int)log2(map.getMapLength() ^ 2) - 1;
		
		for (int x1 = 0; x1 < map.getMapLength(); x1++) {
			for (int y1 = 0; y1 < map.getMapLength(); y1++) {
				tempMap[x1][y1] = new BasicMapElement(ElementTypes.Basic, 0);
			}
		}
		
		int maxLength = map.getMapLength() - 1;
		
		//	Seed values
		tempMap[0][0] = 
		tempMap[0][maxLength] = 
		tempMap[maxLength][0] = 
		tempMap[maxLength][maxLength] = new BasicMapElement(ElementTypes.Basic, randomMinMax(roughness * -128, 128 * roughness, random));
		
		int divisor = 1;
		for (int cI = iterations; cI > 0 ; cI--) {
			System.out.println("Iteration: " + cI);
			// Diamond
				int cDivisor = (int)(maxLength / divisor);
				for (int x = 0; x < maxLength; x += cDivisor) {
					int mX = x + cDivisor; // Max X of current square
					int cX = (x + mX) / 2; // Centre of square
					for (int y = 0; y < maxLength; y += cDivisor) {
						int mY = y + cDivisor;
						int cY = (y + mY) / 2;
						//System.out.println(x + " " + y + " " + mX + " " + cX + " " + mY + " " + cY);
						MapElement tempElement = tempMap[cX][cY];
						// Centre Value
							double avgHeight = 
									(tempMap[x][y].getHeight() +
									tempMap[x][mY].getHeight() + 
									tempMap[mX][y].getHeight() +
									tempMap[mX][mY].getHeight()) / 4;
							avgHeight += randomMinMax((double)(cDivisor/maxLength) * roughness * -128, (double)(cDivisor/maxLength) * 128 * roughness, random);
							if (avgHeight > lastMax) lastMax = avgHeight;
							tempElement.setHeight(avgHeight);
						// Corner Values
							avgHeight = ((tempMap[x][y].getHeight() + tempMap[x][mY].getHeight()) / 2);
							
							tempMap[x][cY].setHeight((avgHeight + randomMinMax((double)(cDivisor/maxLength) * roughness * (-128 - avgHeight), (double)(cDivisor/maxLength) * (128 - avgHeight) * roughness, random)));
							if (tempMap[x][cY].getHeight() > lastMax) lastMax = tempMap[x][cY].getHeight();
							
							avgHeight = ((tempMap[mX][y].getHeight() + tempMap[mX][mY].getHeight()) / 2);
							
							tempMap[mX][cY].setHeight(avgHeight + randomMinMax((double)(cDivisor/maxLength) * roughness * (-128 - avgHeight), (double)(cDivisor/maxLength) * (128 - avgHeight) * roughness, random));
							if (tempMap[mX][cY].getHeight() > lastMax) lastMax = tempMap[mX][cY].getHeight();
							
							avgHeight = ((tempMap[x][y].getHeight() + tempMap[mX][y].getHeight()) / 2);
							
							tempMap[cX][y].setHeight((avgHeight + randomMinMax((double)(cDivisor/maxLength) * roughness * (-128 - avgHeight), (double)(cDivisor/maxLength) * (128 - avgHeight) * roughness, random)));
							if (tempMap[cX][y].getHeight() > lastMax) lastMax = tempMap[cX][y].getHeight();
							
							avgHeight = ((tempMap[x][mY].getHeight() + tempMap[mX][mY].getHeight()) / 2);
							
							tempMap[cX][mY].setHeight(avgHeight + randomMinMax((double)(cDivisor/maxLength) * roughness * (-128 - avgHeight), (double)(cDivisor/maxLength) * (128 - avgHeight) * roughness, random));
							if (tempMap[mX][mY].getHeight() > lastMax) lastMax = tempMap[mX][mY].getHeight();
					}
				}
				divisor *= 2;
		}
		
		/*for (int x = 0; x < map.getMapLength(); x++) {
			for (int y = 0; y < map.getMapLength(); y++) {
				System.out.print(tempMap[x][y].getHeight() + "\t");
			}
			System.out.println();
		}*/
		
		System.out.println("- " + lastMax);
		
		return tempMap;
		
	}

}
