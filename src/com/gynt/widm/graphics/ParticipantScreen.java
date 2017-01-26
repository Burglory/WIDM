package com.gynt.widm.graphics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import com.gynt.widm.core.Participant;
import com.gynt.widm.core.Preferences;
import com.gynt.widm.core.Preferences.PreferenceItem;
import com.gynt.widm.core.Preferences.PreferenceSub;
import com.gynt.widm.core.Round;

public class ParticipantScreen extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 8032171346542801207L;
	private static PreferenceItem passwords;
	private static PreferenceItem hidemole;
	private static PreferenceItem pid;
	private JTable table;
	private char passwordchar = '\u25CF';
	private Round round;
	private TableColumn namecolumn;
	private TableColumn idcolumn;
	private TableColumn passwordcolumn;
	private TableColumn typecolumn;

	static {

		passwords = Preferences.ROOT.registerDir("Participants").registerSub("Security","Security").registerItem("Use_passwords","Let participants have a password", Boolean.class, Boolean.TRUE);
		PreferenceSub general = Preferences.ROOT.registerDir("Participants").registerSub("General","General");
		hidemole = general.registerItem("Hidden", "Hide who the mole is", Boolean.class, Boolean.TRUE);
		pid = general.registerItem("ID", "Show participant ID", Boolean.class, Boolean.FALSE);

	}

	public ParticipantScreen() {
		setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);

		JButton btnAdd = new JButton("Add");
		btnAdd.setMnemonic('+');
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (round != null) {
					((Round.RoundTableModel) table.getModel()).addRow(Participant.newPlayer());
				}
			}
		});
		toolBar.add(btnAdd);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (round != null) {
					List<Integer> rows = new ArrayList<>();
					for (int row : table.getSelectedRows())
						rows.add(row);
					rows.sort(new Comparator<Integer>() {
						@Override
						public int compare(Integer o1, Integer o2) {
							return o2 - o1;
						}
					});
					for (int row : rows) {
						round.PARTICIPANTS.deleteRow(row);
					}
				}
			}
		});
		btnRemove.setMnemonic('-');
		toolBar.add(btnRemove);

		JButton btnView = new JButton("View");
		toolBar.add(btnView);

		JButton btnChooseAMole = new JButton("Choose a Mole");
		toolBar.add(btnChooseAMole);

		JComboBox comboBox = new JComboBox();
		toolBar.add(comboBox);

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
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (table != null && table.getColumn("Password") != null
						&& column == table.getColumn("Password").getModelIndex()) {
					if (value == null || value.toString().length() == 0) {
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
		round = r;
		table.setModel(r.buildModels().PARTICIPANTS);
		table.getColumn("Password").setCellRenderer(new DefaultTableCellRenderer() {

			/**
			 *
			 */
			private static final long serialVersionUID = 446692724803764602L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				int length = value != null ? value.toString().length() : 0;
				JLabel j = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);
				j.setText(length > 0 ? "********" : "");
				return j;
			}
		});
		table.getColumn("Type").setCellEditor(new DefaultCellEditor(new JComboBox<>(Participant.Type.values())));

		namecolumn = table.getColumn("Name");
		idcolumn = table.getColumn("ID");
		typecolumn = table.getColumn("Type");
		passwordcolumn = table.getColumn("Password");

		if(pid.getValue()==Boolean.FALSE) {
			table.getColumnModel().removeColumn(idcolumn);
		}
		if(hidemole.getValue()==Boolean.TRUE) {
			table.getColumnModel().removeColumn(typecolumn);
		}
		if(passwords.getValue()==Boolean.FALSE) {
			table.getColumnModel().removeColumn(passwordcolumn);
		}

		pid.listeners.add(new Preferences.ChangeListener() {

			@Override
			public void onChange(Object oldValue, Object newValue) {
				if(oldValue==Boolean.FALSE && newValue==Boolean.TRUE) {
					table.getColumnModel().addColumn(idcolumn);
				} else {
					table.getColumnModel().removeColumn(idcolumn);
				}
			}
		});
		hidemole.listeners.add(new Preferences.ChangeListener() {

			@Override
			public void onChange(Object oldValue, Object newValue) {
				if(oldValue==Boolean.FALSE && newValue==Boolean.TRUE) {
					table.getColumnModel().removeColumn(typecolumn);
				} else {
					table.getColumnModel().addColumn(typecolumn);
				}
			}
		});
		passwords.listeners.add(new Preferences.ChangeListener() {

			@Override
			public void onChange(Object oldValue, Object newValue) {
				if(oldValue==Boolean.FALSE && newValue==Boolean.TRUE) {
					table.getColumnModel().addColumn(passwordcolumn);
				} else {
					table.getColumnModel().removeColumn(passwordcolumn);
				}
			}
		});
	}

}
