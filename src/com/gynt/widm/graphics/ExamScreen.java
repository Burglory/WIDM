package com.gynt.widm.graphics;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JSplitPane;

public class ExamScreen extends JPanel {
	public ExamScreen() {
		setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);

		JButton btnAddQuestion = new JButton("New question");
		toolBar.add(btnAddQuestion);

		JButton btnRemoveQuestion = new JButton("Remove question");
		toolBar.add(btnRemoveQuestion);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("<Add template>");
		comboBox.addItem("Who is the Mole?");
		comboBox.addItem("What gender is the Mole?");
		toolBar.add(comboBox);

		JButton btnImport = new JButton("Import...");
		toolBar.add(btnImport);

		JButton btnExport = new JButton("Export...");
		toolBar.add(btnExport);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);

		JTree tree = new JTree();
		scrollPane.setViewportView(tree);

		JPanel panel_2 = new JPanel();
		splitPane.setRightComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null }, { null, null }, { null, null }, { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null }, { null, null }, { null, null }, },
				new String[] { "Applies", "Participant" }) {
			Class[] columnTypes = new Class[] { Boolean.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(0).setMinWidth(60);
		table.getColumnModel().getColumn(0).setMaxWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setMinWidth(150);
		scrollPane_1.setViewportView(table);

		table.setSize(table.getPreferredSize());
		scrollPane_1.setSize(table.getPreferredSize());
		scrollPane_1.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(final ComponentEvent e) {
				if (table.getPreferredSize().width < table.getParent().getWidth()) {
					table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				} else {
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				}
			}
		});

		splitPane.setDividerLocation(.75);
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 8032171346542801207L;
	private JTable table;

}
