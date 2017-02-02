package com.gynt.widm.graphics.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class TreeNodeUtil {

	public static abstract class Entity {

		public HashMap<String, Component> components = new HashMap<String, Component>();

	}

	public abstract static class Component<T> {
		public abstract void set(T val);
		public abstract T get();
	}

	public static abstract class ChildrenNode extends Entity implements MutableTreeNode {

		public ArrayList<MutableTreeNode> children = new ArrayList<MutableTreeNode>();
		private MutableTreeNode parent;

		public ChildrenNode() {

		}

		@Override
		public Enumeration<MutableTreeNode> children() {
			return new Enumeration<MutableTreeNode>() {

				int index = 0;

				//private final Iterator<MutableTreeNode> i = children.iterator();

				@Override
				public boolean hasMoreElements() {
					return index>children.size();
					//return i.hasNext();
				}

				@Override
				public MutableTreeNode nextElement() {
					return children.get(index);
					//return i.next();
				}
			};
		}

		@Override
		public boolean getAllowsChildren() {
			return true;
		}

		@Override
		public TreeNode getChildAt(int arg0) {
			return children.get(arg0);
		}

		@Override
		public int getChildCount() {
			return children.size();
		}

		@Override
		public int getIndex(TreeNode arg0) {
			return children.indexOf(arg0);
		}

		@Override
		public TreeNode getParent() {
			return parent;
		}

		@Override
		public boolean isLeaf() {
			return false;
		}

		@Override
		public void insert(MutableTreeNode arg0, int arg1) {
			children.add(arg1, arg0);
		}

		public void add(MutableTreeNode arg0) {
			children.add(arg0);
		}

		@Override
		public void remove(int arg0) {
			children.remove(arg0);
		}

		@Override
		public void remove(MutableTreeNode arg0) {
			children.remove(arg0);
		}

		@Override
		public void removeFromParent() {
			parent.remove(this);
		}

		@Override
		public void setParent(MutableTreeNode arg0) {
			parent = arg0;
		}

		@Override
		public abstract void setUserObject(Object arg0);

	}

	public abstract static class NoChildrenNode  extends Entity implements MutableTreeNode {

		private MutableTreeNode parent;

		@Override
		public Enumeration<MutableTreeNode> children() {
			return null;
		}

		@Override
		public boolean getAllowsChildren() {
			return false;
		}

		@Override
		public TreeNode getChildAt(int arg0) {
			return null;
		}

		@Override
		public int getChildCount() {
			return 0;
		}

		@Override
		public int getIndex(TreeNode arg0) {
			return -1;
		}

		@Override
		public TreeNode getParent() {
			return parent;
		}

		@Override
		public boolean isLeaf() {
			return true;
		}

		@Override
		public void insert(MutableTreeNode child, int index) {

		}

		@Override
		public void remove(int index) {

		}

		@Override
		public void remove(MutableTreeNode node) {

		}

		@Override
		public void removeFromParent() {
			parent.remove(this);
		}

		@Override
		public void setParent(MutableTreeNode newParent) {
			parent=newParent;
		}

		@Override
		public abstract void setUserObject(Object object);

	}

}
