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
import com.gynt.widm.core.PlayerTableModel;
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

public class NewGameFrame extends JFrame {

	private JPanel contentPane;
	private List<Player> players;
	private PlayerTableModel ptm;
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
	private JList<Player> bowl;
	private JButton btnSelect;
	private Box verticalBox;
	private JButton btnDeselect;
	private JList<Player> selection;
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
	private Box verticalBox_2;
	private Box verticalBox_3;
	private JLabel lblAllPlayers;
	private JLabel lblSelection;

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
		this.ptm = new PlayerTableModel(Player.PROPERTY_DESCRIPTORS, new ArrayList<PropertyDescriptable>(this.players));
		table.setModel(ptm);
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
				System.out.println(table.getSelectedRows().length);
				int[] selected = table.getSelectedRows();
				List<Integer> list = new ArrayList<Integer>();
				for (int index : selected) {
					list.add(index);
				}
				Collections.sort(list);
				Collections.reverse(list);
				for (int i : list) {
					ptm.getItems().remove(i);
				}
				table.clearSelection();
				table.repaint();
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ptm.getItems().add(new Player(System.currentTimeMillis() + ""));
				table.repaint();
			}
		});

		scrollPane = new JScrollPane();
		panel_3.add(scrollPane);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setModel(
				new PlayerTableModel(new ArrayList<PropertyDescriptor>(), new ArrayList<PropertyDescriptable>()));
		scrollPane.setViewportView(table);

		panel_4 = new JPanel();
		panel_4.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				System.out.println("set omdels");
				bowl.setModel(new PlayerListModel<Player>(players));
				selectionList = new ArrayList<Player>();
				selection.setModel(new PlayerListModel<Player>(selectionList));
				bowl.repaint();
				selection.repaint();
			}
		});
		panel.add(panel_4, "name_14997351528952");
		panel_4.setLayout(new BorderLayout(0, 0));

		panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.CENTER);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[] {0, 0, 0};
		gbl_panel_5.rowHeights = new int[] {0};
		gbl_panel_5.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gbl_panel_5.rowWeights = new double[] { 0.0 };
		panel_5.setLayout(gbl_panel_5);
		
		verticalBox_2 = Box.createVerticalBox();
		GridBagConstraints gbc_verticalBox_2 = new GridBagConstraints();
		gbc_verticalBox_2.fill = GridBagConstraints.BOTH;
		gbc_verticalBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalBox_2.gridx = 0;
		gbc_verticalBox_2.gridy = 0;
		panel_5.add(verticalBox_2, gbc_verticalBox_2);
				
				lblAllPlayers = new JLabel("All players");
				verticalBox_2.add(lblAllPlayers);
		
				bowl = new JList<Player>();
				verticalBox_2.add(bowl);

		verticalBox = Box.createVerticalBox();
		GridBagConstraints gbc_verticalBox = new GridBagConstraints();
		gbc_verticalBox.fill = GridBagConstraints.BOTH;
		gbc_verticalBox.insets = new Insets(0, 0, 5, 5);
		gbc_verticalBox.gridx = 1;
		gbc_verticalBox.gridy = 0;
		panel_5.add(verticalBox, gbc_verticalBox);

		btnSelect = new JButton(">");
		btnSelect.setToolTipText("Add to selection.");
		verticalBox.add(btnSelect);

		btnDeselect = new JButton("<");
		btnDeselect.setToolTipText("Remove from selection.");
		verticalBox.add(btnDeselect);
		
		verticalBox_3 = Box.createVerticalBox();
		GridBagConstraints gbc_verticalBox_3 = new GridBagConstraints();
		gbc_verticalBox_3.fill = GridBagConstraints.BOTH;
		gbc_verticalBox_3.insets = new Insets(0, 0, 0, 5);
		gbc_verticalBox_3.gridx = 2;
		gbc_verticalBox_3.gridy = 0;
		panel_5.add(verticalBox_3, gbc_verticalBox_3);
				
				lblSelection = new JLabel("Selection");
				verticalBox_3.add(lblSelection);
		
				selection = new JList<Player>();
				verticalBox_3.add(selection);

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
				((CardLayout) panel.getLayout()).next(panel);
			}
		});
		horizontalBox.add(btnNext);

		btnCancel = new JButton("Cancel");
		horizontalBox.add(btnCancel);

		if (GUI.DebugMode) {
			this.players = DebugData.playerdebug;
			this.ptm = new PlayerTableModel(Player.PROPERTY_DESCRIPTORS,
					new ArrayList<PropertyDescriptable>(this.players));
			table.setModel(ptm);
		}
	}

}
