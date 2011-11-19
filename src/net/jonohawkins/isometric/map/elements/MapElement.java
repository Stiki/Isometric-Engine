package net.jonohawkins.isometric.map.elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import net.jonohawkins.isometric.map.ElementTypes;

public abstract class MapElement {
	
	private ElementTypes		type;
	private BufferedImage[] 	textures = new BufferedImage[3];
	private double 			length, //	X
							width,  //	Z
							height; //	Y
						
	public MapElement (ElementTypes eT) {
		setType(eT);
	}
	
	public abstract void render(Graphics gtx);
	public abstract void update(long tick);

	public BufferedImage[] getTextures() {
		return textures;
	}

	public void setTextures(BufferedImage[] textures) {
		this.textures = textures;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public ElementTypes getType() {
		return type;
	}

	public void setType(ElementTypes type) {
		this.type = type;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

}
