package com.gynt.widm.graphics;

import java.awt.GraphicsDevice;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class FullscreenFrame extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 4009363245616373310L;
	
	public FullscreenFrame() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ESCAPE) FullscreenFrame.this.dispose();
			}
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
	}

	@Override
	public void setVisible(boolean state) {
		super.setVisible(state);
		if (state) {

			GraphicsDevice device = this.getGraphicsConfiguration().getDevice();
			device.setFullScreenWindow(this);
			// this.addFocusListener(new FocusListener() {
			//
			// @Override
			// public void focusLost(FocusEvent e) {
			// setAlwaysOnTop(false);;
			// }
			//
			// @Override
			// public void focusGained(FocusEvent e) {
			// setAlwaysOnTop(true);;
			// }
			// });
		}
	}

	public static void main(String[] args) {
		// Debug
		JFrame a = new FullscreenFrame();
		a.setVisible(true);
	}

}
