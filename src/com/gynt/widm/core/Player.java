package com.gynt.widm.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Player extends PropertyDescriptable {

	public static List<PropertyDescriptor> PROPERTY_DESCRIPTORS = new ArrayList<PropertyDescriptor>();
	
	static {
		PROPERTY_DESCRIPTORS.add(new PropertyDescriptor("ID", "Players ID.", String.class, ""));
		PROPERTY_DESCRIPTORS.add(new PropertyDescriptor("Name", "Players name.", String.class, ""));
		PROPERTY_DESCRIPTORS.add(new PropertyDescriptor("Email address", "Players email address.", String.class, ""));
		PROPERTY_DESCRIPTORS.add(new PropertyDescriptor("Mole", "Players mole status.", Boolean.class, ""));
	}
	
	public Player(String id) {
		this.setProperty("ID", id);
	}

	@Override
	public List<PropertyDescriptor> getPropertyDescriptors() {
		return PROPERTY_DESCRIPTORS;
	}

	@Override
	public void addPropertyDescriptor(PropertyDescriptor p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePropertyDescriptor(String name) {
		// TODO Auto-generated method stub
		
	}	

}
