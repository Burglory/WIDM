package com.gynt.widm.core;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class PlayerTableModel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Player> items = new ArrayList<Player>();

	public PlayerTableModel(List<Player> i) {
		super(i.size(), Player.PROPERTY_DESCRIPTORS.size());
		this.items = i;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return Player.PROPERTY_DESCRIPTORS.get(columnIndex).getPropertydatatype();
	}

	@Override
	public int getColumnCount() {
		return Player.PROPERTY_DESCRIPTORS.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return Player.PROPERTY_DESCRIPTORS.get(columnIndex).getPropertyname();
	}

	@Override
	public int getRowCount() {
		if(items==null) return super.getRowCount();
		return items.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.items.get(rowIndex).getProperty(Player.PROPERTY_DESCRIPTORS.get(columnIndex).getPropertyname());
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		this.items.get(rowIndex).setProperty(Player.PROPERTY_DESCRIPTORS.get(columnIndex).getPropertyname(), aValue);
		this.fireTableCellUpdated(rowIndex, columnIndex);
	}


	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	protected JTableHeader createDefaultTableHeader() {
		return new JTableHeader() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int index = columnModel.getColumnIndexAtX(p.x);
				int realIndex = 
						columnModel.getColumn(index).getModelIndex();
				return Player.PROPERTY_DESCRIPTORS.get(realIndex).getPropertydescription();
			}
		};
	}
	
}
