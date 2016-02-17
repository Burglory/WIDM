package com.gynt.widm.core;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public abstract class PropertyDescriptable {

	public abstract List<PropertyDescriptor> getPropertyDescriptors();
	
	private HashMap<String, Object> properties = new HashMap<String, Object>();;
	
	private boolean containsProperty(String propertyname) {
		for (PropertyDescriptor p : getPropertyDescriptors()) {
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