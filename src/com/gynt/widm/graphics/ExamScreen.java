package com.gynt.widm.graphics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListCellRenderer;
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
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import com.gynt.widm.core.ChoicePart;
import com.gynt.widm.core.ChoicePart.Choice;
import com.gynt.widm.core.Preferences.PreferenceSub;
import com.gynt.widm.core.Preferences.Radio;
import com.gynt.widm.core.TextPart;
import com.gynt.widm.core.EntryPart;
import com.gynt.widm.core.Exam;
import com.gynt.widm.core.ExamPart;
import com.gynt.widm.core.Preferences;
import com.gynt.widm.graphics.util.ImageGenerator;
import com.gynt.widm.graphics.util.TreeNodeUtil;
import com.gynt.widm.graphics.util.TreeNodeUtil.ChildrenNode;
import com.gynt.widm.graphics.util.TreeNodeUtil.Entity;
import com.gynt.widm.graphics.util.TreeNodeUtil.NoChildrenNode;
import com.gynt.widm.io.Serialization;

import javafx.scene.control.ComboBox;

import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class ExamScreen extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 4150609711492271645L;

	static {
		PreferenceSub mode = Preferences.ROOT.registerDir("Exam").registerSub("VisualMode","Visual styling of the exam");
		mode.registerItem("1999-2005",  "Old styling (1999-2005)",Radio.class, Boolean.FALSE);
		mode.registerItem("2006-2010",  "Refreshed styling (2006-2010)", Radio.class, Boolean.FALSE);
		mode.registerItem("2011-2014",  "Newer styling (2011-2014)",Radio.class, Boolean.TRUE);
		mode.registerItem("2015-2017",  "Latest styling (2015-2017)",Radio.class, Boolean.FALSE);
		mode = Preferences.ROOT.registerDir("Exam").registerSub("MusicMode","Music styling of the exam");
		mode.registerItem("clues", "Old (Looking for Clues - David Arnold)", Radio.class, Boolean.FALSE);
		mode.registerItem("fourthkind", "New (The Fourth Kind - Atli Orvarsson)", Radio.class, Boolean.FALSE);
		mode.registerItem("custom", "Custom music", Radio.class, Boolean.TRUE);
		mode.registerItem("custompath", "Custom music path: ", File.class, Serialization.PATH_LOADER.getResource(".").getPath());
		mode.registerItem("none", "No music", Radio.class, Boolean.TRUE);
		mode.registerItem("loop", "Loop the music during exam", Boolean.class, Boolean.FALSE);

	}

	public static class ExamNode extends ChildrenNode {

		private Exam part;

		public ExamNode(Exam exam) {
			part = exam;
			components.put("text", new TreeNodeUtil.Component<String>() {

				@Override
				public void set(String val) {
					part.name=val;
				}

				@Override
				public String get() {
					return part.name;
				}

			});
			components.put("icon", new TreeNodeUtil.Component<ImageIcon>() {

				private ImageIcon icon = ImageGenerator.getExamIcon();

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
			part.name = (String) object;
		}

		@Override
		public String toString() {
			return part.name;
		}

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
	
	public static class TextPartNode extends NoChildrenNode {

		private TextPart part;
		public TextPartNode(TextPart e) {
			part = e;
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
			part.text = (String) arg0;
		}

		@Override
		public String toString() {
			return part.text;
		}

	}

	private JComboBox<Class<?>> comboBox;
	private DefaultTreeModel model;

	/**
	 * Create the frame.
	 */
	public ExamScreen() {

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

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
		ExamNode base = new ExamNode(new Exam());
		ChoicePartNode root = new ChoicePartNode(new ChoicePart());
		root.children.add(new ChoiceNode(new Choice()));
		base.add(root);
		EntryPartNode entry = new EntryPartNode(new EntryPart());
		base.add(entry);
		model = new DefaultTreeModel(base);
		tree.setModel(model);

		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);

		JButton btnTry = new JButton();
		btnTry.setIcon(ImageGenerator.getTryExamIcon());
		btnTry.setToolTipText("Try");
		toolBar.add(btnTry);

		JButton btnRun = new JButton();
		btnRun.setIcon(ImageGenerator.getRunExamIcon());
		btnRun.setToolTipText("Run");
		toolBar.add(btnRun);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator);

		JButton btnNew = new JButton("New");
		toolBar.add(btnNew);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tree.getSelectionPath()==null) return;
				MutableTreeNode last = (MutableTreeNode) tree.getSelectionPath().getLastPathComponent();
				if(last instanceof ChildrenNode) {
					if(last instanceof ChoicePartNode) {
						Choice c = new Choice();
						((ChoicePartNode) last).part.choices.add(c);
						((ChildrenNode) last).add(new ChoiceNode(c));
					}
				} else {
					ExamNode parent = (ExamNode) last.getParent();
					switch(((Class<?>) comboBox.getSelectedItem()).getSimpleName()) {
					case "EntryPart": {
						System.out.println("ADding");
						EntryPart ep = new EntryPart();
						parent.part.parts.add(ep);
						parent.add(new EntryPartNode(ep));
						break;
					}
					case "TextPart": {
						TextPart ep = new TextPart();
						parent.part.parts.add(ep);
						parent.add(new TextPartNode(ep));
						break;
					}
					case "ChoicePart": {
						ChoicePart ep = new ChoicePart();
						parent.part.parts.add(ep);
						parent.add(new ChoicePartNode(ep));
						break;
					}
						
					}
				}
			}
		});
		toolBar.add(btnAdd);

		comboBox = new JComboBox<>();
		comboBox.setRenderer(new DefaultListCellRenderer() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 5943145235145801746L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel c =  (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				switch(((Class<?>) value).getSimpleName()) {
				case "ChoicePart": {
					c.setText("Multiple choice");
					break;
				}
				case "TextPart": {
					c.setText("Display text");
					break;
				}
				case "EntryPart": {
					c.setText("Text entry");
					break;
				}
				case "Choice": {
					c.setText("Option");
					break;
				}
				}
				return c;
			}

		});
		toolBar.add(comboBox);
		
		comboBox.addItem(ChoicePart.class);
		comboBox.addItem(EntryPart.class);
		comboBox.addItem(TextPart.class);
		comboBox.addItem(Choice.class);

		JButton btnRemove = new JButton("Remove");
		toolBar.add(btnRemove);

		JButton btnAddTemplate = new JButton("Add mole question");
		toolBar.add(btnAddTemplate);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_1);

		JButton btnImport = new JButton("Import...");
		toolBar.add(btnImport);

		JButton btnExport = new JButton("Export...");
		toolBar.add(btnExport);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_2);

		JButton btnAdvanced = new JButton("Advanced...");
		toolBar.add(btnAdvanced);

		JToolBar statusBar = new JToolBar();
		statusBar.setFloatable(false);
		add(statusBar, BorderLayout.SOUTH);

		JLabel lblStatus = new JLabel("status");
		statusBar.add(lblStatus);


	}

}
