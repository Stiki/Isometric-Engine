package net.jonohawkins.isometric.map.elements;

import java.awt.Graphics;
import java.awt.Graphics2D;
import net.jonohawkins.isometric.map.ElementTypes;

public class BasicMapElement extends MapElement {
	
	public BasicMapElement(ElementTypes eT, double h) {
		
		super(eT);
		
		setHeight(h);
		
	}
	
	public void update(long ticks) {
		
	}
	
	public void render(Graphics gtx) {
		
		Graphics2D g2 = (Graphics2D)gtx;
		
	}

}
