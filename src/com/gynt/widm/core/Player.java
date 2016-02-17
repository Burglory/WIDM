package com.gynt.widm.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Player {

	public static List<PropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		PROPERTY_DESCRIPTORS = new ArrayList<PropertyDescriptor>();
		PROPERTY_DESCRIPTORS.add(new PropertyDescriptor("ID", "Players ID.", String.class, ""));
		PROPERTY_DESCRIPTORS.add(new PropertyDescriptor("Name", "Players name.", String.class, ""));
		PROPERTY_DESCRIPTORS.add(new PropertyDescriptor("Email address", "Players email address.", String.class, ""));
		PROPERTY_DESCRIPTORS.add(new PropertyDescriptor("Mole", "Players mole status.", Boolean.class, ""));
	}

	private HashMap<String, Object> properties;

	public Player(String id) {
		this.properties = new HashMap<String, Object>();
		this.properties.put("ID", id);
	}
	
	private boolean containsProperty(String propertyname) {
		for (PropertyDescriptor p : PROPERTY_DESCRIPTORS) {
			if (p.getPropertyname().equals(propertyname))
				return true;
		}
		return false;
	}

	public Object getProperty(String name) {
		if (containsProperty(name)) {
			return properties.get(name);
		} else {
			
			return null;
		}
	}

	public void setProperty(String name, Object obj) {
		if (containsProperty(name)) {
			properties.put(name, obj);
		} else {
			
		}
	}

	public Set<String> getPropertyNames() {
		return properties.keySet();
	}

}
