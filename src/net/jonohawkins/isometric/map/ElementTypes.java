package net.jonohawkins.isometric.map;

public enum ElementTypes {
	
	Basic(net.jonohawkins.isometric.map.elements.BasicMapElement.class);
	
	public Class elementClass;
	
	ElementTypes(Class x) {
		elementClass = x;
	}
	
	
}
