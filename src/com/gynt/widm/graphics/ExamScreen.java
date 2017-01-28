package com.gynt.widm.graphics;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.gynt.widm.core.Preferences;
import com.gynt.widm.core.Preferences.PreferenceSub;
import com.gynt.widm.core.Preferences.Radio;
import com.gynt.widm.io.Serialization;

public class ExamScreen extends JPanel {

	static {
		PreferenceSub mode = Preferences.ROOT.registerDir("Exam").registerSub("VisualMode","Visual styling of the exam");
		mode.registerItem("1999-2005",  "Old styling (1999-2005)",Radio.class, Boolean.FALSE);
		mode.registerItem("2006-2010",  "Refreshed styling (2006-2010)", Radio.class, Boolean.FALSE);
		mode.registerItem("2011-2014",  "Newer styling (2011-2014)",Radio.class, Boolean.TRUE);
		mode.registerItem("2015-2017",  "Latest styling (2015-2017)",Radio.class, Boolean.FALSE);
		mode = Preferences.ROOT.registerDir("Exam").registerSub("MusicMode","Music styling of the exam");
		mode.registerItem("clues", "Old (Looking for Clues - David Arnold)", Radio.class, Boolean.FALSE);
		mode.registerItem("fourthkind", "New (The Fourth Kind - Atli Örvarsson)", Radio.class, Boolean.FALSE);
		mode.registerItem("custom", "Custom music", Radio.class, Boolean.TRUE);
		mode.registerItem("custompath", "Custom music path: ", File.class, Serialization.PATH_LOADER.getResource(".").getPath());
		mode.registerItem("none", "No music", Radio.class, Boolean.TRUE);
		mode.registerItem("loop", "Loop the music during exam", Boolean.class, Boolean.FALSE);

	}

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

		JButton btnTry = new JButton("Try...");
		toolBar.add(btnTry);

		JButton btnRun = new JButton("Run...");
		toolBar.add(btnRun);

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
