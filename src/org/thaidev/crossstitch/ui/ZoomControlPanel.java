/*
 * Created on Dec 13, 2003
 *
 * org.thaidev.crossstich.ZoomControlPanel
 * 
 */
package org.thaidev.crossstitch.ui;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.thaidev.crossstitch.event.ImageZoomEvent;
import org.thaidev.util.Observable;
import org.thaidev.util.Observer;

/**
 * LGPL Open source copyright 
 * 
 * @author Mr Sweetcorn mcgyver@writeme.com
 * @version 2.0 20031214
 *
 * ZoomControlPanel
 */
public class ZoomControlPanel extends JPanel implements Observable {
	private List observers = new ArrayList();
	private double zoomLevel;
	private javax.swing.JLabel jLabel = null;
	private javax.swing.JTextField zoomLevelTextField = null;
	private javax.swing.JSlider zoomLevelSlider = null;
	/**
	 * This is the default constructor
	 */
	public ZoomControlPanel() {
		super();
		initialize();
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setLayout(new java.awt.FlowLayout());
		this.add(getJLabel(), null);
		this.add(getZoomLevelTextField(), null);
		this.add(getZoomLevelSlider(), null);
	}
	/**
	 * This method initializes jLabel
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getJLabel() {
		if (jLabel == null) {
			jLabel = new javax.swing.JLabel();
			jLabel.setText("Zoom");
		}
		return jLabel;
	}
	/**
	 * This method initializes zoomLevelTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private javax.swing.JTextField getZoomLevelTextField() {
		if (zoomLevelTextField == null) {
			zoomLevelTextField = new javax.swing.JTextField();
			zoomLevelTextField.setText("100%");
			zoomLevelTextField.setEditable(false);
			zoomLevelTextField.setColumns(6);
		}
		return zoomLevelTextField;
	}
	/**
	 * This method initializes zoomLevelSlider
	 * 
	 * @return javax.swing.JSlider
	 */
	private javax.swing.JSlider getZoomLevelSlider() {
		if (zoomLevelSlider == null) {
			zoomLevelSlider = new javax.swing.JSlider();
			zoomLevelSlider.setMaximum(4);
			zoomLevelSlider.setMinimum(-4);
			zoomLevelSlider.setValue(0);
			zoomLevelSlider.setMinorTickSpacing(1);
			zoomLevelSlider.setMajorTickSpacing(2);
			zoomLevelSlider.setPaintTicks(true);
			zoomLevelSlider.setPreferredSize(new java.awt.Dimension(200, 60));
			Hashtable labelTable = new Hashtable();
			labelTable.put(new Integer(-4), new JLabel("x1/5"));
			labelTable.put(new Integer(-2), new JLabel("x1/3"));
			labelTable.put(new Integer(0), new JLabel("x1"));
			labelTable.put(new Integer(2), new JLabel("x3"));
			labelTable.put(new Integer(4), new JLabel("x5"));
			zoomLevelSlider.setLabelTable(labelTable);
			zoomLevelSlider.setPaintLabels(true);
			zoomLevelSlider.setSnapToTicks(true);
			zoomLevelSlider
				.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					int value = zoomLevelSlider.getValue();
					updateValue(value);
				}
			});
		}
		return zoomLevelSlider;
	}
	private void updateValue(int value) {
		double newZoom = 1;
		switch (value) {
			case -4 :
				newZoom = 0.2;
				break;
			case -3 :
				newZoom = 0.25;
				break;
			case -2 :
				newZoom = 0.33;
				break;
			case -1 :
				newZoom = 0.5;
				break;
			case 0 :
				newZoom = 1;
				break;
			case 1 :
				newZoom = 2;
				break;
			case 2 :
				newZoom = 3;
				break;
			case 3 :
				newZoom = 4;
				break;
			case 4 :
				newZoom = 5;
				break;
		}
		setZoomLevel(newZoom);
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
	public void setZoomLevel(double newZoom) {
		if (newZoom != zoomLevel) {
			zoomLevel = newZoom;
			getZoomLevelTextField().setText((newZoom * 100) + "%");
			notifyObservers(new ImageZoomEvent(newZoom));
		}
	}
	/* (non-Javadoc)
	 * @see org.thaidev.util.Observable#addObserver(org.thaidev.util.Observer)
	 */
	public void addObserver(Observer o) {
		observers.add(o);
	}

	/* (non-Javadoc)
	 * @see org.thaidev.util.Observable#removeObserver(org.thaidev.util.Observer)
	 */
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	/* (non-Javadoc)
	 * @see org.thaidev.util.Observable#notifyObservers(java.lang.Object)
	 */
	public void notifyObservers(Object arg) {
		for (Iterator iter = observers.iterator(); iter.hasNext();) {
			Observer o = (Observer) iter.next();
			o.update(this, arg);
		}
	}

}
