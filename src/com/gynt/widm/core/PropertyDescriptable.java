package com.gynt.widm.core;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public abstract class PropertyDescriptable {
	
	private HashMap<String, Object> properties = new HashMap<String, Object>();
	
	public abstract List<PropertyDescriptor> getPropertyDescriptors();
	
	public void addPropertyDescriptor(PropertyDescriptor p) {
		this.getPropertyDescriptors().add(p);
	}
	
	public void removePropertyDescriptor(PropertyDescriptor p) {
		this.getPropertyDescriptors().remove(p);
	}
	
	public PropertyDescriptor getPropertyDescriptor(String name) {
		for(PropertyDescriptor p : this.getPropertyDescriptors()) {
			if(p.getPropertyname().equals(name)) return p;
		}
		return null;
	}
	
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
