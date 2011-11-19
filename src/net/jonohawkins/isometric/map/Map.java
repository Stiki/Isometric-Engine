package net.jonohawkins.isometric.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
		if (((mL - 1) & (mL - 2)) == 0) {
			mapLength = mL;
		} else {
			mapLength = (int) Math.pow(2,TerrainFactory.log2(mL)) + 1;
			System.out.println("Map Length Changed to: " + mapLength);
		}
		waterLevel = wL;
		cloudLevel = cL;
	}
	
	public static void main(String[] args) {
		Map m = new Map(513, new Random().nextLong(), 1);
		m.setMapArray(TerrainFactory.generateRandomMap(m, new Random().nextLong(),10));
		final MapElement[][] array = m.getMapArray();
		JFrame x = new JFrame("Hello");
		JPanel p = new JPanel() {
			private boolean ispainting = false;
			Graphics2D bufferGraphics;
			Image offScreen;
			public void init () {
				offScreen = createImage(getWidth(), getHeight());
				bufferGraphics = (Graphics2D) offScreen.getGraphics();
			}
			public void paintComponent(Graphics gt) {
				if (bufferGraphics == null || offScreen == null) {
					init();
				}
				Graphics2D g2 = (Graphics2D)gt;
				if (!ispainting) {
					ispainting = true;
					for (int x = 0; x < array.length; x++) {
						for (int y = 0; y < array[x].length; y++) {
							int green = (int)(255 * (array[x][y].getHeight()/128) + 128);
							if (green > 255) green = 255;
							if (green < 0) green = 0;
							bufferGraphics.setColor(new Color(0,green,0));
							bufferGraphics.fillRect(x * 1, y * 1, 1, 1);
						}
					}
					g2.drawImage(offScreen, 0, 0, 257, 257, null);
					ispainting = false;
				}
			}
			public void update(Graphics g) {
				paintComponent(g);
			}
		};
		
		x.setSize(800,800);
		x.add(p);
		x.setVisible(true);
		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
