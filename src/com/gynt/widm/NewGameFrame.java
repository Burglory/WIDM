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
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.sun.xml.internal.bind.v2.model.util.ArrayInfoUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JLabel label;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 596, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ptm.getItems().add(new Player(System.currentTimeMillis()+""));
				table.repaint();
			}
		});
		panel_2.add(btnAdd);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(table.getSelectedRows().length);
				int[] selected = table.getSelectedRows();
				List<Integer> list = Arrays.asList(table.getSelectedRows());
				
				for(int i : list) {
					ptm.getItems().remove(i-1);
				}
				table.repaint();
			}
		});
		panel_2.add(btnDelete);
		
		label = new JLabel("");
		panel_2.add(label);
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setModel(new PlayerTableModel(new ArrayList<PropertyDescriptor>(), new ArrayList<PropertyDescriptable>()));
		scrollPane.setViewportView(table);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		if(GUI.DebugMode) {
			this.players = DebugData.playerdebug;
			this.ptm = new PlayerTableModel(Player.PROPERTY_DESCRIPTORS, new ArrayList<PropertyDescriptable>(this.players));
			table.setModel(ptm);
		}
	}

}
