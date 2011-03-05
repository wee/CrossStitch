/*
 * Created on Dec 13, 2003
 *
 * org.thaidev.crossstich.ImagePanel
 * 
 */
package org.thaidev.crossstitch.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.thaidev.crossstitch.event.ImageUpdateEvent;
import org.thaidev.crossstitch.event.ImageZoomEvent;
import org.thaidev.util.Observable;
import org.thaidev.util.Observer;

/**
 * Cross Stitch image panel
 * LGPL Open source copyright 
 * 
 * @author Zombibi
 * @version 0.9 20031012
 * @author Mr Sweetcorn mcgyver@writeme.com
 * @version 1.0 20031013
 * @version 2.0 20031214
 * 
 */
public class ImagePanel extends JPanel implements Observer {
	//	private CrossStitchModel model;
	private BufferedImage image;
	private double zoomLevel = 1d;

	/**
	 * This is the default constructor
	 */
	public ImagePanel() {
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
		this.setSize(300, 200);
		this.setBackground(new java.awt.Color(255, 255, 255));
		// The cross hair cursor is difficult to see, so I don't use it
		/*
		this.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));				
			}
		});
		*/
	}

	/* (non-Javadoc)
	 * @see org.thaidev.util.Observer#update(org.thaidev.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
		if (arg instanceof ImageUpdateEvent) {
			setImage(((ImageUpdateEvent) arg).getImage());
			repaint();
			invalidate();
		} else if (arg instanceof ImageZoomEvent) {
			setZoomLevel(((ImageZoomEvent) arg).getZoomLevel());
			repaint();
			invalidate();
		}

	}
	/**
	 * @return
	 */
	/*	
		public CrossStitchModel getModel() {
			return model;
		}
	*/
	/**
	 * @param model
	 */
	/*	
		public void setModel(CrossStitchModel model) {
			this.model = model;
		}
	*/
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	protected void paintComponent(Graphics g) {
		int w, h;
		super.paintComponent(g);
		if (image != null) {
			if (zoomLevel != 1d) {
				Image img =
					image.getScaledInstance(
						(int) (image.getWidth() * zoomLevel),
						-1,
						Image.SCALE_FAST);
				g.drawImage(img, 0, 0, null);
				w = img.getWidth(this);
				h = img.getHeight(this);
				img = null;
			} else {
				g.drawImage(image, 0, 0, null);
				w = image.getWidth(this);
				h = image.getHeight(this);
			}
			setPreferredSize(new Dimension(w, h));
			revalidate();
		}
	}

	/**
	 * @return
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * @param image
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * @return
	 */
	public double getZoomLevel() {
		return zoomLevel;
	}

	/**
	 * @param d
	 */
	public void setZoomLevel(double d) {
		double old = getZoomLevel();
		if (old != d) {
			zoomLevel = d;
			repaint();
		}
	}

}
