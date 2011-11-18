package net.jonohawkins.isometric.map;

import java.awt.image.BufferedImage;


public abstract class MapElement {
	
	private ElementType		type;
	private BufferedImage[] 	textures = new BufferedImage[3];
	private double 			length, //	X
							width,  //	Z
							height; //	Y
						
	public MapElement (ElementType eT, BufferedImage[] tex) {
		setType(eT);
		if (tex.length == 3) setTextures(tex);
	}
	
	public abstract void render(long tick);
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

	public ElementType getType() {
		return type;
	}

	public void setType(ElementType type) {
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
