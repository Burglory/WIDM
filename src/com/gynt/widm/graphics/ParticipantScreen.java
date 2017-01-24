package com.gynt.widm.graphics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.TableColumn;

import com.gynt.widm.core.Preferences;
import com.gynt.widm.core.Round;

public class ParticipantScreen extends JPanel {

	private char passwordchar = '\u25CF';
	private JPopupMenu popupMenu;

	public ParticipantScreen() {
		setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);

		JButton btnAdd = new JButton("Add");
		toolBar.add(btnAdd);

		JButton btnRemove = new JButton("Remove");
		toolBar.add(btnRemove);

		JButton btnView = new JButton("View");
		toolBar.add(btnView);

		popupMenu = new JPopupMenu();
		addPopup(btnView, popupMenu);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new Round().buildModels().PARTICIPANTS);
		// table.getColumnModel().removeColumn(table.getColumn("ID"));
		scrollPane.setViewportView(table);
	}

	public ParticipantScreen(Round r) {
		table.setModel(r.buildModels().PARTICIPANTS);
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
