package com.gynt.widm.core;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PropertyDescriptor {

	private String propertyname;
	private String propertydescription;
	private Class<?> propertydatatype;
	private String list;

	private static final String separationcharacter = "|";

	public PropertyDescriptor(String propertyname, String propertydescription, Class<?> propertydatatype, String list) {
		this.propertyname = propertyname;
		this.propertydescription = propertydescription;
		this.propertydatatype = propertydatatype;
		this.list = list;
	}

	public String getPropertyname() {
		return propertyname;
	}

	public void setPropertyname(String propertyname) {
		this.propertyname = propertyname;
	}

	public String getPropertydescription() {
		return propertydescription;
	}

	public void setPropertydescription(String propertydescription) {
		this.propertydescription = propertydescription;
	}

	public Class<?> getPropertydatatype() {
		return propertydatatype;
	}

	public void setPropertydatatype(Class<?> propertydatatype) {
		this.propertydatatype = propertydatatype;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public static final Collection<PropertyDescriptor> readFromFile(File file) {

		try {
			List<PropertyDescriptor> result = new ArrayList<PropertyDescriptor>();
			List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
			for (String line : lines) {
				String[] segments = line.split(separationcharacter);
				Class<?> c = null;
				try {
					c = Class.forName(segments[2]);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					c = String.class;
				}
				result.add(new PropertyDescriptor(segments[0], segments[1], c, segments[3]));
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String toString() {
		return propertyname + separationcharacter + propertydescription + separationcharacter
				+ propertydatatype.getName() + separationcharacter + list;
	}

	public void writeToFile(File file, Collection<PropertyDescriptor> properties) {
		List<String> lines = new ArrayList<String>();
		for (PropertyDescriptor p : properties) {
			lines.add(p.propertyname + separationcharacter + p.propertydescription + separationcharacter
					+ p.propertydatatype.getName() + separationcharacter + p.list);
		}
		try {
			Files.write(file.toPath(), lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
