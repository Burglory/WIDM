package com.gynt.widm.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Preferences {

	public static class Radio {
		public static Boolean parseRadio(Object val) {
			return Boolean.parseBoolean((String) val);
		}
		public static String toString(Boolean c) {
			return Boolean.toString(c);
		}
	}

	public static Game game;
	public static Properties PROPERTIES = new Properties();

	public static void save() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PROPERTIES.store(bos, "");
		game.fileinterface.store(bos.toByteArray(), "preferences.properties");
	}

	public static void load() throws IOException {
		if (game.fileinterface != null) {
			PROPERTIES.clear();
			ByteArrayInputStream bis = new ByteArrayInputStream(game.fileinterface.retrieve("preferences.properties"));
			PROPERTIES.load(bis);
		}
	}

	public static boolean loaded() {
		return game != null && PROPERTIES != null;
	}


	public static PreferenceDir ROOT;
	static {
		ROOT = new PreferenceDir();
		ROOT.name="Preferences";
		PreferenceSub mode = ROOT.registerDir("Exam").registerSub("VisualMode","Visual styling of the exam");
		mode.registerItem("1999-2005",  "Old styling (1999-2005)",Radio.class, Boolean.FALSE);
		mode.registerItem("2006-2010",  "Refreshed styling (2006-2010)", Radio.class, Boolean.FALSE);
		mode.registerItem("2011-2014",  "Newer styling (2011-2014)",Radio.class, Boolean.TRUE);
		mode.registerItem("2015-2017",  "Latest styling (2015-2017)",Radio.class, Boolean.FALSE);
		mode = ROOT.registerDir("Exam").registerSub("MusicMode","Music styling of the exam");
		mode.registerItem("none", "No music", Radio.class, Boolean.TRUE);
		mode.registerItem("clues", "Looking for Clues - David Arnold", Radio.class, Boolean.FALSE);
		mode.registerItem("fourthkind", "The Fourth Kind - Atli Írvarsson", Radio.class, Boolean.FALSE);
		mode.registerItem("loop", "Loop the music during exam", Boolean.class, Boolean.FALSE);
	}

	public static interface Parentable {
		Parentable getParent();
	}

	public static interface ChangeListener {
		void onChange(Object oldValue, Object newValue);
	}

	public static class PreferenceItem implements Parentable {
		public String name;
		public Class<?> type;
		public PreferenceSub parent;
		private String path;
		public String description;

		public ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();

		public PreferenceItem(PreferenceSub p, String n, Class<?> t, Object val) {
			parent = p;
			name = n;
			type = t;
			path = buildPath();
			setValue(val);
		}

		private void tell(Object oldValue, Object newValue) {
			for(ChangeListener cl : listeners) {
				cl.onChange(oldValue, newValue);
			}
		}

		public void setValue(Object val) {
			Object oval = getValue();
			switch(type.getSimpleName()) {
			case "String":
				PROPERTIES.setProperty(path, val.toString());
				break;
			case "Boolean":
				PROPERTIES.setProperty(path, Boolean.toString((Boolean) val));
				break;
			case "Radio":
				PROPERTIES.setProperty(path, Radio.toString((Boolean) val));
				break;
			case "Integer":
				PROPERTIES.setProperty(path, Integer.toString((int) val));
				break;
			case "Double":
				PROPERTIES.setProperty(path, Double.toString((double) val));
				break;
			default:
				throw new RuntimeException();
			}
			if(!oval.equals(val)) tell(oval, val);
		}

		public Object getValue() {
			switch(type.getSimpleName()) {
			case "String":
				return PROPERTIES.getProperty(path);
			case "Boolean":
				return Boolean.parseBoolean(PROPERTIES.getProperty(path));
			case "Radio":
				return Radio.parseRadio(PROPERTIES.getProperty(path));
			case "Integer":
				return Integer.parseInt(PROPERTIES.getProperty(path));
			case "Double":
				return Double.parseDouble(PROPERTIES.getProperty(path));
			default:
				throw new RuntimeException();
			}
		}

		public String buildPath() {
			String path = name;
			Parentable parent = getParent();
			while(parent!=null) {
				path=parent.toString()+"."+path;
				parent = parent.getParent();
			}
			return path;
		}

		@Override
		public Parentable getParent() {
			return parent;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public static class PreferenceSub implements Parentable {
		public String name;
		public PreferenceDir parent;
		public ArrayList<PreferenceItem> items = new ArrayList<>();
		public String description;

		public PreferenceItem lookUp(String name) {
			for(PreferenceItem child : items) {
				if(child.name.equals(name))  return child;
			}
			return null;
		}

		public PreferenceItem registerItem(String name, String description, Class<?> type, Object defaultvalue) {
			PreferenceItem i = lookUp(name);
			if(i!=null) return i;

			PreferenceItem result = new PreferenceItem(this, name, type, defaultvalue);
			result.description=description;
			items.add(result);
			return result;
		}

		@Override
		public Parentable getParent() {
			return parent;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public static class PreferenceDir implements Parentable {

		public String name;
		public PreferenceDir parent;
		public String description;

		public ArrayList<PreferenceDir> dirs = new ArrayList<>();
		public ArrayList<PreferenceSub> subs = new ArrayList<>();

		public PreferenceDir lookUpDir(String name) {
			for(PreferenceDir child : this.dirs) {
				if(child.name==name) {
					return child;
				}
			}
			return null;
		}

		public PreferenceSub lookUpSub(String name) {
			for(PreferenceSub child : this.subs) {
				if(child.name==name) {
					return child;
				}
			}
			return null;
		}

		public PreferenceDir registerDir(String string) {
			PreferenceDir i = lookUpDir(string);
			if(i!=null) return i;

			PreferenceDir result = new PreferenceDir();
			result.name=string;
			result.parent=this;
			dirs.add(result);
			return result;
		}

		public PreferenceSub registerSub(String string, String description) {
			PreferenceSub i = lookUpSub(string);
			if(i!=null) return i;

			PreferenceSub result = new PreferenceSub();
			result.name=string;
			result.parent=this;
			result.description=description;
			subs.add(result);
			return result;
		}

		@Override
		public Parentable getParent() {
			return parent;
		}

		@Override
		public String toString() {
			return name;
		}
	}



}
