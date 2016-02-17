package com.gynt.widm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gynt.debug.DebugData;
import com.gynt.widm.core.Player;
import com.gynt.widm.core.PlayerTableModel;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class NewGameFrame extends JFrame {

	private JPanel contentPane;
	private List<Player> players;
	private PlayerTableModel ptm;
	private JTable table;

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
		this.ptm = new PlayerTableModel(this.players);
		table.setModel(ptm);
	}
	
	/**
	 * Create the frame.
	 */
	public NewGameFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 513, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		if(GUI.DebugMode) {
			this.players = DebugData.playerdebug;
			this.ptm = new PlayerTableModel(this.players);
			table.setModel(ptm);
		}
	}

}
