package net.jonohawkins.isometric.map;

import java.awt.Dimension;
import net.jonohawkins.isometric.render.*;
import net.jonohawkins.isometric.map.elements.*;

public class Map {

	private double		waterLevel,
						cloudLevel;
	private Camera 		camera;
	private int 		mapLength;
	private MapElement[][]
						mapArray;
	
	public Map (int mL, double wL, double cL) {
		if ((mL & (mL - 1)) == 0) {
			mapLength = mL;
		} else {
			mapLength = (int) TerrainFactory.log2(mL);
			System.out.println("Map Length Changed to: " + mapLength);
		}
		waterLevel = wL;
		cloudLevel = cL;
	}

	public double getWaterLevel() {
		return waterLevel;
	}

	public void setWaterLevel(double waterLevel) {
		this.waterLevel = waterLevel;
	}

	public double getCloudLevel() {
		return cloudLevel;
	}

	public void setCloudLevel(double cloudLevel) {
		this.cloudLevel = cloudLevel;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public int getMapLength() {
		return mapLength;
	}

	public void setMapLength(int mapLength) {
		this.mapLength = mapLength;
	}

	
	public MapElement[][] getMapArray() {
		return mapArray;
	}

	
	public void setMapArray(MapElement[][] mapArray) {
		this.mapArray = mapArray;
	}
	
	
	
}
