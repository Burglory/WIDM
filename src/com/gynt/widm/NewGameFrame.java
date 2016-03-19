package com.gynt.widm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gynt.debug.DebugData;
import com.gynt.widm.core.Player;
import com.gynt.widm.core.PlayerListModel;
import com.gynt.widm.core.PropertyDescriptorTableModel;
import com.gynt.widm.core.PropertyDescriptable;
import com.gynt.widm.core.PropertyDescriptor;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.sun.xml.internal.bind.v2.model.util.ArrayInfoUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JList;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.AbstractListModel;

public class NewGameFrame extends JFrame {

	private JPanel contentPane;
	private List<Player> players;
	private PropertyDescriptorTableModel ptm;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnAdd;
	private JButton btnDelete;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private Box horizontalBox;
	private JButton btnNext;
	private JButton btnPrevious;
	private JButton btnCancel;
	private Box horizontalBox_1;
	private JPanel panel_4;
	private JPanel panel_5;
	private JButton btnSelect;
	private JButton btnDeselect;
	protected ArrayList<Player> bowlList;
	protected ArrayList<Player> selectionList;
	private JPanel panel_6;
	private Box horizontalBox_2;
	private JLabel lblDrawMoles;
	private JSpinner spinner;
	private JButton btnDraw;
	private Box horizontalBox_3;
	private JButton btnSetCurrentSelection;
	private Box verticalBox_1;
	private JLabel lblAllPlayers;
	private JLabel lblSelection;
	private JList<String> list_2;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JList<String> list_1;
	private List<Player> selection;
	private List<Player> deselection;
	private PlayerListModel plm_1;
	private PlayerListModel plm_2;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGameFrame frame = new NewGameFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NewGameFrame(List<Player> players) {
		this();
		this.players = players;
		this.selection = new ArrayList<Player>();
		this.ptm = new PropertyDescriptorTableModel(Player.PROPERTY_DESCRIPTORS, new ArrayList<PropertyDescriptable>(this.players));
		table.setModel(ptm);
		table.repaint();
	}

	/**
	 * Create the frame.
	 */
	public NewGameFrame() {
		setTitle("New game wizard");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 596, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new CardLayout(0, 0));

		panel_3 = new JPanel();
		panel.add(panel_3, "name_14524238419972");
		panel_3.setLayout(new BorderLayout(0, 0));

		panel_2 = new JPanel();
		panel_3.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		horizontalBox_1 = Box.createHorizontalBox();
		panel_2.add(horizontalBox_1);

		btnAdd = new JButton("+");
		btnAdd.setToolTipText("Add player.");
		horizontalBox_1.add(btnAdd);

		btnDelete = new JButton("-");
		btnDelete.setToolTipText("Delete player.");
		horizontalBox_1.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selected = table.getSelectedRows();
				ptm.removeItems(selected);
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ptm.addItem(new Player(System.currentTimeMillis()+""));
			}
		});

		scrollPane = new JScrollPane();
		panel_3.add(scrollPane);

		table = new JTable();
		table.setFillsViewportHeight(true);
		//		table.setModel(
		//				new PropertyDescriptorTableModel(new ArrayList<PropertyDescriptor>(), new ArrayList<PropertyDescriptable>()));
		scrollPane.setViewportView(table);

		panel_4 = new JPanel();
		panel.add(panel_4, "name_14997351528952");
		panel_4.setLayout(new BorderLayout(0, 0));

		panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));

		panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.CENTER);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[] {0};
		gbl_panel_7.rowHeights = new int[] {35, 35, 30, 30, 30, 30, 30, 30, 0, 0, 35, 35, 35, 35, 35, 35, 35, 35};
		gbl_panel_7.columnWeights = new double[]{0.0};
		gbl_panel_7.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);

		btnDeselect = new JButton("<");
		btnDeselect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plm_1.addPlayers(plm_2.grabPlayers(list_2.getSelectedIndices()));
			}
		});

		btnSelect = new JButton(">");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plm_2.addPlayers(plm_1.grabPlayers(list_1.getSelectedIndices()));
			}
		});
		btnSelect.setToolTipText("Add to selection.");
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.insets = new Insets(0, 0, 5, 0);
		gbc_btnSelect.fill = GridBagConstraints.BOTH;
		gbc_btnSelect.gridx = 0;
		gbc_btnSelect.gridy = 8;
		panel_7.add(btnSelect, gbc_btnSelect);
		btnDeselect.setToolTipText("Remove from selection.");
		GridBagConstraints gbc_btnDeselect = new GridBagConstraints();
		gbc_btnDeselect.fill = GridBagConstraints.BOTH;
		gbc_btnDeselect.gridx = 0;
		gbc_btnDeselect.gridy = 9;
		panel_7.add(btnDeselect, gbc_btnDeselect);

		panel_8 = new JPanel();
		panel_5.add(panel_8, BorderLayout.WEST);
		panel_8.setLayout(new BorderLayout(0, 0));

		lblAllPlayers = new JLabel("All players");
		panel_8.add(lblAllPlayers, BorderLayout.NORTH);

		scrollPane_1 = new JScrollPane();
		panel_8.add(scrollPane_1);

		list_1 = new JList<String>();
		scrollPane_1.setViewportView(list_1);

		panel_9 = new JPanel();
		panel_5.add(panel_9, BorderLayout.EAST);
		panel_9.setLayout(new BorderLayout(0, 0));

		lblSelection = new JLabel("Selection");
		panel_9.add(lblSelection, BorderLayout.NORTH);

		scrollPane_2 = new JScrollPane();
		panel_9.add(scrollPane_2);

		list_2 = new JList<String>();
		scrollPane_2.setViewportView(list_2);
		//list_2.setModel(new PlayerListModel(selection));

		panel_6 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_6.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_6, BorderLayout.SOUTH);

		verticalBox_1 = Box.createVerticalBox();
		panel_6.add(verticalBox_1);

		horizontalBox_2 = Box.createHorizontalBox();
		horizontalBox_2.setAlignmentX(Component.LEFT_ALIGNMENT);
		verticalBox_1.add(horizontalBox_2);

		lblDrawMoles = new JLabel("Amount moles:");
		horizontalBox_2.add(lblDrawMoles);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
		horizontalBox_2.add(spinner);

		btnDraw = new JButton("Draw");
		btnDraw.setToolTipText("Draw mole(s) randomly from selection.");
		horizontalBox_2.add(btnDraw);

		horizontalBox_3 = Box.createHorizontalBox();
		horizontalBox_3.setAlignmentX(Component.LEFT_ALIGNMENT);
		verticalBox_1.add(horizontalBox_3);

		btnSetCurrentSelection = new JButton("Set selection as mole(s)");
		horizontalBox_3.add(btnSetCurrentSelection);

		panel_1 = new JPanel();
		panel_1.setBorder(null);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel_1, BorderLayout.SOUTH);

		horizontalBox = Box.createHorizontalBox();
		panel_1.add(horizontalBox);

		btnPrevious = new JButton("Previous");
		horizontalBox.add(btnPrevious);

		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(players.size());
				((CardLayout) panel.getLayout()).next(panel);
				deselection = new ArrayList<Player>(players);
				plm_1 = new PlayerListModel(deselection);
				list_1.setModel(plm_1);
				list_1.repaint();
				selection = new ArrayList<Player>();
				plm_2 = new PlayerListModel(selection);
				list_2.setModel(plm_2);
				list_2.repaint();
			}
		});
		horizontalBox.add(btnNext);

		btnCancel = new JButton("Cancel");
		horizontalBox.add(btnCancel);

		if (GUI.DebugMode) {
			this.players = DebugData.playerdebug;
			this.ptm = new PropertyDescriptorTableModel(Player.PROPERTY_DESCRIPTORS, this.players);
			table.setModel(ptm);
		}
	}

}
