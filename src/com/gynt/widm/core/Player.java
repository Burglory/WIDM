package com.gynt.widm.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Player extends PropertyDescriptable {

	public static List<PropertyDescriptor> PROPERTY_DESCRIPTORS = new ArrayList<PropertyDescriptor>();
	
	static {
		PROPERTY_DESCRIPTORS.add(new PropertyDescriptor("id","ID", "Players ID.", String.class, ""));
		PROPERTY_DESCRIPTORS.add(new PropertyDescriptor("name","Name", "Players name.", String.class, ""));
		PROPERTY_DESCRIPTORS.add(new PropertyDescriptor("email","Email address", "Players email address.", String.class, ""));
		PROPERTY_DESCRIPTORS.add(new PropertyDescriptor("mole","Mole", "Players mole status.", Boolean.class, ""));
	}
	
	public Player(String id) {
		this.setProperty("id", id);
	}

	@Override
	public List<PropertyDescriptor> getPropertyDescriptors() {
		return PROPERTY_DESCRIPTORS;
	}
//	
//	@Override
//	public String toString() {
//		return (String) this.getProperty("Name");
//	}

}
