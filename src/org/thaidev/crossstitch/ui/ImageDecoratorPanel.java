/*
 * Created on Dec 13, 2003
 *
 * org.thaidev.crossstich.ImageDecoratorPanel
 * 
 */
package org.thaidev.crossstitch.ui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * LGPL Open source copyright 
 * 
 * @author Mr Sweetcorn mcgyver@writeme.com
 * @version 2.0 20031214
 *
 * ImageDecoratorPanel
 */
public class ImageDecoratorPanel extends JPanel {

	private javax.swing.JScrollPane jScrollPane = null;
	private org.thaidev.crossstitch.ui.ZoomControlPanel zoomControlPanel = null;
	private org.thaidev.crossstitch.ui.ImagePanel imagePanel = null;
	private javax.swing.JPanel jPanel = null;
	private javax.swing.JPanel jPanel1 = null;
	private javax.swing.JLabel jLabel = null;
	private javax.swing.JTextField xTextField = null;
	private javax.swing.JLabel jLabel1 = null;
	private javax.swing.JTextField yTextField = null;
	private javax.swing.JLabel jLabel2 = null;
	private javax.swing.JTextField colorTextField = null;
	private javax.swing.JPanel colorPanel = null;
	/**
	 * This is the default constructor
	 */
	public ImageDecoratorPanel() {
		super();
		initialize();
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setLayout(new java.awt.BorderLayout());
		this.add(getJScrollPane(), java.awt.BorderLayout.CENTER);
		this.add(getZoomControlPanel(), java.awt.BorderLayout.SOUTH);
		this.add(getJPanel(), java.awt.BorderLayout.NORTH);
		this.setSize(300, 200);
	}
	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private javax.swing.JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new javax.swing.JScrollPane();
			jScrollPane.setViewportView(getImagePanel());
		}
		return jScrollPane;
	}
	/**
	 * This method initializes zoomControlPanel
	 * 
	 * @return org.thaidev.crossstich.ZoomControlPanel
	 */
	public org.thaidev.crossstitch.ui.ZoomControlPanel getZoomControlPanel() {
		if (zoomControlPanel == null) {
			zoomControlPanel = new org.thaidev.crossstitch.ui.ZoomControlPanel();
		}
		return zoomControlPanel;
	}
	/**
	 * This method initializes imagePanel
	 * 
	 * @return org.thaidev.crossstich.ImagePanel
	 */
	public org.thaidev.crossstitch.ui.ImagePanel getImagePanel() {
		if (imagePanel == null) {
			imagePanel = new org.thaidev.crossstitch.ui.ImagePanel();
			imagePanel.addMouseMotionListener(new MouseMotionAdapter() {
				/* (non-Javadoc)
				 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
				 */
				public void mouseMoved(MouseEvent e) {
					BufferedImage img = getImagePanel().getImage();
					if (img != null) {
						int x = e.getX();
						int y = e.getY();
						x = (int)((double)x / getImagePanel().getZoomLevel());
						y = (int)((double)y / getImagePanel().getZoomLevel());
						xTextField.setText("" + x);
						yTextField.setText("" + y);
						if (x < img.getWidth() && y < img.getHeight()) {
							int color = img.getRGB(x, y);
							colorTextField.setText(
								"#" + Integer.toHexString(color & 0x00FFFFFF).toUpperCase());
							colorPanel.setBackground(
								new Color(color & 0x00FFFFFF));
						}
					}
				}

			});
		}
		return imagePanel;
	}
	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new javax.swing.JPanel();
			jPanel.setLayout(new java.awt.BorderLayout());
			jPanel.add(getJPanel1(), java.awt.BorderLayout.CENTER);
		}
		return jPanel;
	}
	/**
	 * This method initializes jPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new javax.swing.JPanel();
			jPanel1.add(getJLabel(), null);
			jPanel1.add(getXTextField(), null);
			jPanel1.add(getJLabel1(), null);
			jPanel1.add(getYTextField(), null);
			jPanel1.add(getJLabel2(), null);
			jPanel1.add(getColorTextField(), null);
			jPanel1.add(getColorPanel(), null);
		}
		return jPanel1;
	}
	/**
	 * This method initializes jLabel
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getJLabel() {
		if (jLabel == null) {
			jLabel = new javax.swing.JLabel();
			jLabel.setText("X ");
		}
		return jLabel;
	}
	/**
	 * This method initializes xTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private javax.swing.JTextField getXTextField() {
		if (xTextField == null) {
			xTextField = new javax.swing.JTextField();
			xTextField.setColumns(3);
			xTextField.setEnabled(true);
			xTextField.setEditable(false);
		}
		return xTextField;
	}
	/**
	 * This method initializes jLabel1
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new javax.swing.JLabel();
			jLabel1.setText("Y ");
		}
		return jLabel1;
	}
	/**
	 * This method initializes yTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private javax.swing.JTextField getYTextField() {
		if (yTextField == null) {
			yTextField = new javax.swing.JTextField();
			yTextField.setColumns(3);
			yTextField.setEditable(false);
		}
		return yTextField;
	}
	/**
	 * This method initializes jLabel2
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new javax.swing.JLabel();
			jLabel2.setText("Color");
		}
		return jLabel2;
	}
	/**
	 * This method initializes colorTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private javax.swing.JTextField getColorTextField() {
		if (colorTextField == null) {
			colorTextField = new javax.swing.JTextField();
			colorTextField.setColumns(6);
			colorTextField.setEditable(false);
		}
		return colorTextField;
	}
	/**
	 * This method initializes colorPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getColorPanel() {
		if (colorPanel == null) {
			colorPanel = new javax.swing.JPanel();
			colorPanel.setPreferredSize(new java.awt.Dimension(15, 15));
			colorPanel.setBorder(
				javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.gray,
					1));
		}
		return colorPanel;
	}
}
