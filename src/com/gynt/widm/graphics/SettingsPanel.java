package com.gynt.widm.graphics;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.gynt.easysettings.DefaultSettingsRenderer;
import com.gynt.easysettings.Settings.Item;
import com.gynt.widm.core.GlobalSettings;

public class SettingsPanel extends JPanel {


	/**
	 *
	 */
	private static final long serialVersionUID = 4104785855475407998L;
	private com.gynt.easysettings.SettingsPanel panel;
	
	public SettingsPanel() {
		setLayout(new BorderLayout(0, 0));
		panel = new com.gynt.easysettings.SettingsPanel(GlobalSettings.settings);
		panel.setRenderer(new DefaultSettingsRenderer() {
			@Override
			public JComponent getItemComponent(JComponent subpanel, Item pi, ButtonGroup bg) {
				JComponent j = super.getItemComponent(subpanel, pi, bg);
				if(pi.name=="Brightness") {
					JPanel jj = (JPanel) j;
					JSpinner spinner = (JSpinner) jj.getComponent(1);
					spinner.setModel(new SpinnerNumberModel((Number) pi.getValue(), 0.0D, 1.0D, 0.1D));
				}
				return j;
			}
		});
		//panel.render();
		add(panel, BorderLayout.CENTER);
	}
	
}
