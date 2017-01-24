package com.gynt.widm.graphics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.gynt.widm.core.Participant;
import com.gynt.widm.core.Preferences;
import com.gynt.widm.core.Round;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ParticipantScreen extends JPanel {

	private char passwordchar = '\u25CF';
	private JPopupMenu popupMenu;
	private Round round;

	public ParticipantScreen() {
		setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);

		JButton btnAdd = new JButton("Add");
		btnAdd.setMnemonic('+');
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(round!=null) {
					((Round.RoundTableModel) table.getModel()).addRow(Participant.newPlayer());
				}
			}
		});
		toolBar.add(btnAdd);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(round!=null) {
					List<Integer> rows = new ArrayList<>();
					for(int row : table.getSelectedRows()) rows.add(row);
					rows.sort(new Comparator<Integer>() {
						@Override
						public int compare(Integer o1, Integer o2) {
							return o2-o1;
						}
					});
					for(int row : rows) {
						round.PARTICIPANTS.deleteRow(row);
					}
				}
			}
		});
		btnRemove.setMnemonic('-');
		toolBar.add(btnRemove);

		JButton btnView = new JButton("View");
		toolBar.add(btnView);

		popupMenu = new JPopupMenu();
		addPopup(btnView, popupMenu);

		JButton btnChooseAMole = new JButton("Choose a Mole");
		toolBar.add(btnChooseAMole);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new Round().buildModels().PARTICIPANTS);
		table.getColumn("Password").setCellRenderer(new DefaultTableCellRenderer() {

			/**
			 *
			 */
			private static final long serialVersionUID = 446692724803764602L;

		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		    	if(table!=null && table.getColumn("Password")!=null && column==table.getColumn("Password").getModelIndex()) {
		    		if(value==null || value.toString().length()==0) {
		    			setText("");
		    		} else
		    		setText("********");
		    		return this;
		    	} else {
		    		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		    	}
		    }
		});
		// table.getColumnModel().removeColumn(table.getColumn("ID"));
		scrollPane.setViewportView(table);
	}

	public ParticipantScreen(Round r) {
		this();
		round=r;
		table.setModel(r.buildModels().PARTICIPANTS);
		table.getColumn("Password").setCellRenderer(new DefaultTableCellRenderer() {

			/**
			 *
			 */
			private static final long serialVersionUID = 446692724803764602L;

		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		    	int length = value!=null?value.toString().length():0;
		    	JLabel j = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				j.setText(length>0?"********":"");
		    	return j;
//		    	if(table!=null && table.getColumn("Password")!=null && column==table.getColumn("Password").getModelIndex()) {
//		    		if(value==null || value.toString().length()==0) {
//		    			setText("");
//		    		} else
//		    		setText("********");
//		    		return this;
//		    	} else {
//		    		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//		    	}
		    }
		});
		table.getColumn("Type").setCellEditor(new DefaultCellEditor(new JComboBox<>(Participant.Type.values())));
		setupPreferences();
	}

	private void setupPreferences() {
		if (Preferences.loaded()) {
			Enumeration<TableColumn> t = table.getColumnModel().getColumns();
			popupMenu.removeAll();
			while (t.hasMoreElements()) {
				TableColumn c = t.nextElement();
				String name = (String) c.getHeaderValue();

				String settingsbase = "Game.Round.ParticipantScreen.Columns." + name;
				if (!Preferences.has(settingsbase + ".visible")) {
					Preferences.set(settingsbase + ".visible", Boolean.toString(true));
				}
				if (!Preferences.has(settingsbase + ".index")) {
					Preferences.set(settingsbase + ".index", Integer.toString(0));
				}

				boolean result = true;
				try {
					result = Boolean.parseBoolean(Preferences.get(settingsbase + ".visible"));
				} catch (Exception e) {

				}
				if (!result) {
					table.getColumnModel().removeColumn(c);
				}
				int index = Integer.parseInt(Preferences.get(settingsbase + ".index"));
				table.getColumnModel().moveColumn(table.getColumnModel().getColumnIndex(name), index);
				popupMenu.add(new JCheckBoxMenuItem(name, result));
			}

		}
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 8032171346542801207L;
	private JTable table;

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
