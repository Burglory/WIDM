package com.gynt.widm.testing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.AbstractLayoutCache;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;

import com.gynt.widm.core.ChoicePart;
import com.gynt.widm.core.ChoicePart.Choice;
import com.gynt.widm.core.ExamPart;
import com.gynt.widm.graphics.util.ImageGenerator;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;

import java.awt.event.FocusAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.EventObject;
import java.util.Iterator;

public class EditableTree extends JFrame {

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
	
	public static class AnswerNode implements MutableTreeNode {

		private MutableTreeNode parent;
		private Choice part;

		public AnswerNode(Choice choice) {
			part=choice;
		}

		@Override
		public Enumeration children() {
			return null;
		}

		@Override
		public boolean getAllowsChildren() {
			return false;
		}

		@Override
		public TreeNode getChildAt(int childIndex) {
			return null;
		}

		@Override
		public int getChildCount() {
			return 0;
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
		public void setUserObject(Object object) {
			part.text=(String) object;
		}
		
		@Override
		public String toString() {
			return part.text;
		}
		
	}
	
	public static class QuestionNode implements MutableTreeNode {

		private ChoicePart part;
		private ArrayList<AnswerNode> children = new ArrayList<AnswerNode>();
		private MutableTreeNode parent;

		public QuestionNode(ChoicePart e) {
			part = e;
		}
		
		@Override
		public Enumeration<AnswerNode> children() {
			return new Enumeration<EditableTree.AnswerNode>() {
				
				private final Iterator<AnswerNode> i = children.iterator();

				@Override
				public boolean hasMoreElements() {
					return i.hasNext();
				}

				@Override
				public AnswerNode nextElement() {
					return i.next();
				}
			};
		}

		@Override
		public boolean getAllowsChildren() {
			return true;
		}

		@Override
		public TreeNode getChildAt(int arg0) {
			return children.get(0);
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
			children.add(arg1, (AnswerNode) arg0);
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
			parent=arg0;
		}

		@Override
		public void setUserObject(Object arg0) {
			part.question=(String) arg0;
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
//		tree.setCellEditor(new TreeCellEditor() {
//			
//			@Override
//			public boolean stopCellEditing() {
//				return true;
//			}
//			
//			@Override
//			public boolean shouldSelectCell(EventObject anEvent) {
//				return true;
//			}
//			
//			public ArrayList<CellEditorListener> listeners = new ArrayList<>();
//			
//			@Override
//			public void removeCellEditorListener(CellEditorListener l) {
//				listeners.remove(l);
//			}
//			
//			@Override
//			public boolean isCellEditable(EventObject anEvent) {
//				return true;
//			}
//			
//			@Override
//			public Object getCellEditorValue() {
//				return editor.getText();
//			}
//			
//			@Override
//			public void cancelCellEditing() {
//				editor
//			}
//			
//			@Override
//			public void addCellEditorListener(CellEditorListener l) {
//				listeners.add(l);
//			}
//			
//			private final JTextField editor = new JTextField() {
//
//			    @Override
//			    public Dimension getPreferredSize() {
//			        Dimension dim = super.getPreferredSize();
//			        int length = getText().length();
//			        dim.width += length * 10;
//			        //dim.height += length * 2;
//			        return dim;
//			    }
//			};
//			{
//				editor.getDocument().addDocumentListener(new DocumentListener() {
//					
//
//				    protected void validateEditor(final JTextField field) {
//				        // the selectionModel's rowMapper is-a AbstractLayoutCache
//				        // BEWARE: implementation detail!
//				        TreeSelectionModel model = tree.getSelectionModel();
//				        // invalidate all cached node sizes/locations
//				        ((AbstractLayoutCache) model.getRowMapper()).invalidateSizes();
//				        // just a fancy cover method for revalidate/repaint
//				        tree.treeDidChange();
//				        // manually set the component's size
//				        field.setSize(field.getPreferredSize());
//				    }
//				    @Override
//				    public void insertUpdate(DocumentEvent e) {
//				        validateEditor(editor);
//				    }
//
//				    @Override
//				    public void removeUpdate(DocumentEvent e) {
//				        validateEditor(editor);
//				    }
//
//				    @Override
//				    public void changedUpdate(DocumentEvent e) {
//				    }
//				});
//			}
//			
//			
//			@Override
//			public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
//					boolean leaf, int row) {
//				editor.setText(((DefaultMutableTreeNode) value).getUserObject().toString());
//				return editor;
//			}
//		});
		JTextField field = new JTextField() {
		    @Override
		    public Dimension getPreferredSize() {
		        Dimension dim = super.getPreferredSize();
		        int length = getText().length();
		       // dim.width = dim
		        //dim.width=getText().length()*1.5;
		        //dim.height += length * 2;
		        return dim;
		    }
		};
		field.getDocument().addDocumentListener(new DocumentListener() {
			

		    protected void validateEditor(final JTextField field) {
		        // the selectionModel's rowMapper is-a AbstractLayoutCache
		        // BEWARE: implementation detail!
		        TreeSelectionModel model = tree.getSelectionModel();
		        // invalidate all cached node sizes/locations
		        ((AbstractLayoutCache) model.getRowMapper()).invalidateSizes();
		        // just a fancy cover method for revalidate/repaint
		        tree.treeDidChange();
		        // manually set the component's size
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

		    }
		});
		tree.setCellRenderer(new DefaultTreeCellRenderer() {
			@Override
			public Component getTreeCellRendererComponent(JTree tree,
			    Object value, boolean selected, boolean expanded,
			    boolean leaf, int row, boolean hasFocus) {
				DefaultTreeCellRenderer c = (DefaultTreeCellRenderer) super.getTreeCellRendererComponent(tree, value, selected,expanded, leaf, row, hasFocus);
			        MutableTreeNode nodo = (MutableTreeNode) value;
			        if (nodo instanceof QuestionNode) {
			           c.setIcon(ImageGenerator.getChoicePartIcon());
			        } else if (nodo instanceof AnswerNode) {
			            c.setIcon(ImageGenerator.getChoiceIcon());
			        } else {
			            //setIcon(leaf);
			        }
			        return c;
			}
		});
		tree.setCellEditor(new DefaultCellEditor(field));
		tree.setToggleClickCount(0);
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount()>1) {
					tree.startEditingAtPath(tree.getSelectionPath());
				}
			}
		});
		tree.setEditable(true);
		scrollPane.setViewportView(tree);
		QuestionNode root = new QuestionNode(new ChoicePart());
		root.children.add(new AnswerNode(new Choice()));
		tree.setModel(new DefaultTreeModel(root));
	}

}
