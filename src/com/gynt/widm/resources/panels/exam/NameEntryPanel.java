package com.gynt.widm.resources.panels.exam;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Dimension;

public class NameEntryPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public NameEntryPanel() {
		setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {30, 0, 30};
		gridBagLayout.rowHeights = new int[] {30, 0, 30};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		textField = new JTextField();
		textField.setMinimumSize(new Dimension(6, 30));
		textField.setPreferredSize(new Dimension(6, 30));
		textField.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);

	}

}
