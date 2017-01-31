package com.gynt.widm.testing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.AbstractLayoutCache;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import com.gynt.widm.core.ChoicePart;
import com.gynt.widm.core.ChoicePart.Choice;
import com.gynt.widm.core.EntryPart;
import com.gynt.widm.graphics.util.ImageGenerator;
import com.gynt.widm.graphics.util.TreeNodeUtil;
import com.gynt.widm.graphics.util.TreeNodeUtil.ChildrenNode;
import com.gynt.widm.graphics.util.TreeNodeUtil.Entity;
import com.gynt.widm.graphics.util.TreeNodeUtil.NoChildrenNode;

public class EditableTree extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 4150609711492271645L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		for (LookAndFeelInfo lafi : UIManager.getInstalledLookAndFeels()) {
			if (lafi.getName().equals("Nimbus")) {
				try {
					UIManager.setLookAndFeel(lafi.getClassName());
				} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
						| IllegalAccessException e) {
					e.printStackTrace();
				}

			}
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditableTree frame = new EditableTree();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	public static class ChoiceNode extends NoChildrenNode {

		private Choice part;

		public ChoiceNode(Choice choice) {
			part = choice;
			components.put("text", new TreeNodeUtil.Component<String>() {

				@Override
				public void set(String val) {
					part.text=val;
				}

				@Override
				public String get() {
					return part.text;
				}

			});
			components.put("icon", new TreeNodeUtil.Component<ImageIcon>() {

				private ImageIcon icon = ImageGenerator.getChoiceIcon();

				@Override
				public void set(ImageIcon val) {
					icon=val;
				}

				@Override
				public ImageIcon get() {
					return icon;
				}
			});
		}

		@Override
		public void setUserObject(Object object) {
			part.text = (String) object;
		}

		@Override
		public String toString() {
			return part.text;
		}

	}

	public static class ChoicePartNode extends ChildrenNode {

		private ChoicePart part;

		public ChoicePartNode(ChoicePart e) {
			part = e;
			components.put("text", new TreeNodeUtil.Component<String>() {

				@Override
				public void set(String val) {
					part.question=val;
				}

				@Override
				public String get() {
					return part.question;
				}

			});
			components.put("icon", new TreeNodeUtil.Component<ImageIcon>() {

				private ImageIcon icon = ImageGenerator.getChoicePartIcon();

				@Override
				public void set(ImageIcon val) {
					icon=val;
				}

				@Override
				public ImageIcon get() {
					return icon;
				}
			});
		}

		@Override
		public void setUserObject(Object arg0) {
			part.question = (String) arg0;
		}

		@Override
		public String toString() {
			return part.question;
		}

	}


	public static class EntryPartNode extends NoChildrenNode {

		private EntryPart part;
		public EntryPartNode(EntryPart e) {
			part = e;
			components.put("text", new TreeNodeUtil.Component<String>() {

				@Override
				public void set(String val) {
					part.question=val;
				}

				@Override
				public String get() {
					return part.question;
				}

			});
			components.put("icon", new TreeNodeUtil.Component<ImageIcon>() {

				private ImageIcon icon = ImageGenerator.getEntryPartIcon();

				@Override
				public void set(ImageIcon val) {
					icon=val;
				}

				@Override
				public ImageIcon get() {
					return icon;
				}
			});
		}

		@Override
		public void setUserObject(Object arg0) {
			part.question = (String) arg0;
		}

		@Override
		public String toString() {
			return part.question;
		}

	}

	/**
	 * Create the frame.
	 */
	public EditableTree() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JTree tree = new JTree();
		JTextField field = new JTextField();
		field.getDocument().addDocumentListener(new DocumentListener() {

			protected void validateEditor(final JTextField field) {
				if(tree.getSelectionPath().getLastPathComponent() instanceof Entity) {
					if(((Entity) tree.getSelectionPath().getLastPathComponent()).components.containsKey("text")) {
						((Entity) tree.getSelectionPath().getLastPathComponent()).components.get("text").set(field.getText());
					}
				}
				TreeSelectionModel model = tree.getSelectionModel();
				((AbstractLayoutCache) model.getRowMapper()).invalidateSizes();
				tree.revalidate();
				field.setSize(field.getPreferredSize());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				validateEditor(field);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				validateEditor(field);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				validateEditor(field);
			}
		});
		tree.setCellRenderer(new DefaultTreeCellRenderer() {
			/**
			 *
			 */
			private static final long serialVersionUID = -224398418091734517L;

			@Override
			public java.awt.Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
					boolean leaf, int row, boolean hasFocus) {
				DefaultTreeCellRenderer c = (DefaultTreeCellRenderer) super.getTreeCellRendererComponent(tree, value,
						selected, expanded, leaf, row, hasFocus);
				if(value instanceof Entity) {
					if(((Entity) value).components.containsKey("icon")) {
						c.setIcon((Icon) ((Entity) value).components.get("icon").get());
					}
				}
				return c;
			}
		});
		tree.setCellEditor(new DefaultCellEditor(field));
		tree.setToggleClickCount(0);
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() > 1) {
					tree.startEditingAtPath(tree.getSelectionPath());
				}
			}
		});
		tree.setEditable(true);
		scrollPane.setViewportView(tree);
		DefaultMutableTreeNode base = new DefaultMutableTreeNode("'Exam'");
		ChoicePartNode root = new ChoicePartNode(new ChoicePart());
		root.children.add(new ChoiceNode(new Choice()));
		base.add(root);
		EntryPartNode entry = new EntryPartNode(new EntryPart());
		base.add(entry);
		tree.setModel(new DefaultTreeModel(base));


	}

}
