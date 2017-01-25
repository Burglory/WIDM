package com.gynt.widm.graphics;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class PreferencesPropertiesScreen extends JPanel {

	public static class PropertyNode implements MutableTreeNode, TableModel {
		public String fullpath;
		public String partpath;
		public PropertyNode parent;
		public ArrayList<PropertyNode> children = new ArrayList<>();
		public Object value;

		@Override
		public Enumeration<PropertyNode> children() {
			return new Enumeration<PropertyNode>() {

				private Iterator<PropertyNode> i = children.iterator();

				@Override
				public boolean hasMoreElements() {
					return i.hasNext();
				}

				@Override
				public PropertyNode nextElement() {
					return i.next();
				}
			};
		}

		@Override
		public boolean getAllowsChildren() {
			return true;
		}

		@Override
		public TreeNode getChildAt(int childIndex) {
			return children.get(childIndex);
		}

		@Override
		public int getChildCount() {
			return children.size();
		}

		@Override
		public int getIndex(TreeNode node) {
			return -1;
		}

		@Override
		public TreeNode getParent() {
			return parent;
		}

		@Override
		public boolean isLeaf() {
			return children.size() == 0;
		}

		@Override
		public void insert(MutableTreeNode child, int index) {
			children.add(index, (PropertyNode) child);
			child.setParent(this);
		}

		@Override
		public void remove(int index) {
			children.remove(index);
		}

		@Override
		public void remove(MutableTreeNode node) {
			children.remove(node);
		}

		@Override
		public void removeFromParent() {
			parent.remove(this);
		}

		@Override
		public void setParent(MutableTreeNode newParent) {
			parent = (PropertyNode) newParent;

		}

		@Override
		public void setUserObject(Object object) {
			value = object;
			properties.setProperty(fullpath, (String) object);
		}

		@Override
		public String toString() {
			return partpath;
		}

		public boolean hasChild(String element) {
			for (PropertyNode p : children) {
				if (p.partpath.equals(element))
					return true;
			}
			return false;
		}

		public PropertyNode getChild(String element) {
			for (PropertyNode p : children) {
				if (p.partpath.equals(element))
					return p;
			}
			return null;
		}

		@Override
		public void addTableModelListener(TableModelListener arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void removeTableModelListener(TableModelListener arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public Class<?> getColumnClass(int arg0) {
			return String.class;
		}

		@Override
		public int getColumnCount() {
			return 2;
		}

		@Override
		public String getColumnName(int arg0) {
			return arg0 == 0 ? "Name" : "Value";
		}

		@Override
		public int getRowCount() {
			return children.size();
		}

		@Override
		public Object getValueAt(int arg0, int arg1) {
			return arg1 == 0 ? children.get(arg0).partpath : children.get(arg0).value;
		}

		@Override
		public boolean isCellEditable(int arg0, int arg1) {
			return arg1 == 1;
		}

		@Override
		public void setValueAt(Object arg0, int arg1, int arg2) {
			children.get(arg1).value = arg0;
		}
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 5624141494366472216L;
	private static Properties properties;
	private PropertyNode root;
	private JTree tree;
	private DefaultTreeModel model;
	private JTable table;

	public PreferencesPropertiesScreen() {
		setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		add(splitPane, BorderLayout.CENTER);

		splitPane.setResizeWeight(0.50);

		JPanel panel_2 = new JPanel();
		splitPane.setLeftComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);

		tree = new JTree();
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				if (!(arg0.getPath().getLastPathComponent() instanceof PropertyNode))
					return;
				PropertyNode last = (PropertyNode) arg0.getPath().getLastPathComponent();
				if (last.isLeaf()) {
					table.setVisible(false);
				} else {
					table.setVisible(true);
					table.setModel(last);
				}
			}
		});
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Properties") {
			/**
			 *
			 */
			private static final long serialVersionUID = 2260085701458495448L;

			{
			}
		}));
		scrollPane.setViewportView(tree);

		JPanel panel_3 = new JPanel();
		splitPane.setRightComponent(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_3.add(scrollPane_1);

		table = new JTable();
		scrollPane_1.setViewportView(table);
	}

	public void setProperties(Properties p) {
		properties = p;
		Enumeration<?> e = p.propertyNames();
		root = new PropertyNode();
		root.fullpath = "";
		root.partpath = "Properties";
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			String[] elements = name.split("[.]");
			PropertyNode current = root;
			// String currentpath="";
			for (String element : elements) {
				if (!current.hasChild(element)) {
					PropertyNode newchild = new PropertyNode();
					newchild.fullpath = name;
					newchild.partpath = element;
					newchild.value = p.getProperty(name);
					current.children.add(newchild);
				} else {
					current = current.getChild(element);
				}
			}
		}
		model = new DefaultTreeModel(root);
		tree.setModel(model);
	}

}
