/*
 * Created on Dec 14, 2003
 *
 * org.thaidev.crossstitch.ui.ColorPanel
 * 
 */
package org.thaidev.crossstitch.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.thaidev.crossstitch.ColorTable;

/**
 * @author Weerasak W. (weerasak@hotmail.com)
 *
 * ColorPanel
 */
public class ColorPanel extends JPanel {
	private static final int NUM_COLUMNS = 24;
	private static final int BLOCK_SIZE = 15;
	private static final int BLOCK_SPACE = 20;
	private static final int OFFSET_X = 5;
	private static final int OFFSET_Y = 5;

	private int selectedColor = -1;
	private ColorTable colorTable;
	private JFrame parent;
	private javax.swing.JLabel jLabel = null;
	private javax.swing.JPanel jPanel = null;
	private javax.swing.JButton jButton = null;
	private javax.swing.JPanel jPanel1 = null;
	private javax.swing.JPanel jPanel2 = null;
	private javax.swing.JPanel jPanel3 = null;
	private javax.swing.JPanel jPanel4 = null;
	/**
	 * 
	 */
	public ColorPanel(JFrame parent, ColorTable table) {
		super();
		this.parent = parent;
		colorTable = table;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setLayout(new java.awt.BorderLayout());
		this.add(getJLabel(), java.awt.BorderLayout.NORTH);
		this.add(getJPanel(), java.awt.BorderLayout.SOUTH);
		this.add(getJPanel1(), java.awt.BorderLayout.CENTER);
		//this.setSize(300, 200);
	}
	/**
	 * This method initializes jLabel
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getJLabel() {
		if (jLabel == null) {
			jLabel = new javax.swing.JLabel();
			jLabel.setText("<HTML><B>Color Table</B></HTML>");
			jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		}
		return jLabel;
	}
	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new javax.swing.JPanel();
			jPanel.setLayout(new BorderLayout());
			jPanel.add(getJPanel2(), BorderLayout.CENTER);
		}
		return jPanel;
	}

	private javax.swing.JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new javax.swing.JPanel();
			jPanel2.setLayout(new BorderLayout());
			jPanel2.add(getJPanel3(), java.awt.BorderLayout.CENTER);
			jPanel2.add(getJPanel4(), java.awt.BorderLayout.NORTH);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getJButton() {
		if (jButton == null) {
			jButton = new javax.swing.JButton();
			jButton.setText("OK");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					parent.dispose();
				}
			});
		}
		return jButton;
	}
	/**
	 * This method initializes jPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new javax.swing.JPanel() {
				/* (non-Javadoc)
				 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
				 */
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					int size = colorTable.getColorTableSize();
					int index = 0;
					int rows = (size / NUM_COLUMNS) + 1;
					for (int r = 0; r < rows; r++) {
						for (int c = 0; c < NUM_COLUMNS; c++) {
							if (index >= size)
								break;
							g.setColor(
								new Color(colorTable.getColorTableAt(index)));
							g.fill3DRect(
								OFFSET_X + (c * BLOCK_SPACE),
								OFFSET_Y + (r * BLOCK_SPACE),
								BLOCK_SIZE,
								BLOCK_SIZE,
								true);
							index++;
						}
					}
				}

				public Dimension getPreferredSize() {
					int size = colorTable.getColorTableSize();
					int rows = (size / NUM_COLUMNS) + 1;
					return new Dimension(
						(BLOCK_SPACE * NUM_COLUMNS) + 10,
						(rows * BLOCK_SPACE) + 10);
				}

			};
			jPanel1.addMouseMotionListener(new MouseMotionAdapter() {
				/* (non-Javadoc)
				 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
				 */
				public void mouseMoved(MouseEvent e) {
					int x = e.getX() - OFFSET_X;
					int y = e.getY() - OFFSET_Y;
					if (x < 0 || y < 0)
						return;
					int itemX = x / BLOCK_SPACE;
					int itemY = y / BLOCK_SPACE;
					if (itemX >= NUM_COLUMNS)
						return;
					x = x % BLOCK_SPACE;
					y = y % BLOCK_SPACE;
					if (x > BLOCK_SIZE || y > BLOCK_SIZE)
						return;
					selectedColor =
						colorTable.getColorTableAt(itemY * NUM_COLUMNS + itemX);
					jPanel4.repaint();
				}

			});
		}
		return jPanel1;
	}
	/**
	 * This method initializes jPanel3
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new javax.swing.JPanel();
			jPanel3.add(getJButton(), null);
		}
		return jPanel3;
	}
	/**
	 * This method initializes jPanel4
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJPanel4() {
		if (jPanel4 == null) {
			jPanel4 = new javax.swing.JPanel() {
				/* (non-Javadoc)
				 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
				 */
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					if (selectedColor != -1) {
						g.drawString(
							"Color = #" + Integer.toHexString(selectedColor),
							20,
							15);
						g.setColor(new Color(selectedColor));
						g.fill3DRect(2, 2, 15, 15, true);
					} else {
						g.drawString("Move mouse over each color to see color value.", 20, 15);
					}
				}
				/* (non-Javadoc)
				 * @see javax.swing.JComponent#getPreferredSize()
				 */
				public Dimension getPreferredSize() {
					return new Dimension(200, 20);
				}

			};
		}
		return jPanel4;
	}
	public ColorTable getColorTable() {
		return colorTable;
	}
	public void setColorTable(ColorTable colorTable) {
		this.colorTable = colorTable;
	}
}
