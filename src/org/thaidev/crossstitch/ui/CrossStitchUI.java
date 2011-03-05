/*
 * Created on Dec 13, 2003
 *
 */
package org.thaidev.crossstitch.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.thaidev.crossstitch.DMC;
import org.thaidev.crossstitch.event.CancelEvent;
import org.thaidev.crossstitch.event.DoStitchEvent;
import org.thaidev.crossstitch.event.ErrorStatusMessageEvent;
import org.thaidev.crossstitch.event.FileExportHTMLEvent;
import org.thaidev.crossstitch.event.FileOpenEvent;
import org.thaidev.crossstitch.event.FileSaveEvent;
import org.thaidev.crossstitch.event.ProgressEvent;
import org.thaidev.crossstitch.event.StatusMessageEvent;
import org.thaidev.util.Observable;
import org.thaidev.util.Observer;

/**
 * Cross Stitch matching user interface LGPL Open source copyright
 * 
 * @author Zombibi
 * @version 0.9 20031012
 * @author Mr Sweetcorn mcgyver@writeme.com
 * @version 1.0 20031013
 * @version 2.0 20031214
 * @version 2.0.1 20040428 Fix toolbar bug
 * 
 */
public class CrossStitchUI extends JFrame implements Observable, Observer {
	public final static String STITCH_ONE_FIFTH = "STITCH_ONE_FIFTH";
	public final static String STITCH_ONE_FOURTH = "STITCH_ONE_FOURTH";
	public final static String STITCH_ONE_THIRD = "STITCH_ONE_THIRD";
	public final static String STITCH_HALF = "STITCH_HALF";
	public final static String STITCH_FULL = "STITCH_FULL"; 
	
	public final Icon ICON_OPEN = new ImageIcon(getClass().getResource("/images/open.gif"));
	public final Icon ICON_SAVE = new ImageIcon(getClass().getResource("/images/save_active.gif"));
	public final Icon ICON_STOP = new ImageIcon(getClass().getResource("/images/stop_active.gif"));
	public final Icon ICON_EXIT = new ImageIcon(getClass().getResource("/images/exit.gif"));
	public final Icon ICON_EXPORT = new ImageIcon(getClass().getResource("/images/export_active.gif"));
	public final Icon ICON_SHOW = new ImageIcon(getClass().getResource("/images/show.gif"));
	
	private String lastFolder = "";
	
	private List observers = new ArrayList();
	private LocalActionListener localListener = new LocalActionListener();
	
	private javax.swing.JPanel jContentPane = null;

	private javax.swing.JMenuBar jJMenuBar = null;
	private javax.swing.JMenu jMenu = null;
	private javax.swing.JMenuItem jMenuItem = null;
	private javax.swing.JMenuItem jMenuItem1 = null;
	private javax.swing.JMenu jMenu2 = null;
	private javax.swing.JMenuItem jMenuItem2 = null;
	private javax.swing.JMenuItem jMenuItem3 = null;
	private javax.swing.JMenuItem jMenuItem4 = null;
	private javax.swing.JMenuItem jMenuItem5 = null;
	private javax.swing.JMenuItem jMenuItem6 = null;
	private javax.swing.JPanel jPanel = null;
	private javax.swing.JLabel statusLabel = null;
	private javax.swing.JProgressBar statusProgressBar = null;
	private javax.swing.JSplitPane jSplitPane = null;
	private org.thaidev.crossstitch.ui.ImageDecoratorPanel inputImagePanel = null;
	private org.thaidev.crossstitch.ui.ImageDecoratorPanel outputImagePanel = null;
	private javax.swing.JMenuItem jMenuItem7 = null;
	private javax.swing.JMenuItem jMenuItem8 = null;
	private javax.swing.JButton jButton = null;
	private javax.swing.JToolBar jToolBar = null;
	private javax.swing.JButton jButton1 = null;
	private javax.swing.JButton jButton2 = null;
	private javax.swing.JButton jButton3 = null;
	private javax.swing.JButton jButton4 = null;
	private javax.swing.JButton jButton5 = null;
	/**
	 * This is the default constructor
	 */
	public CrossStitchUI() {
		super();
		initialize();
		/*
		this.getGlassPane().addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				String keyString = KeyEvent.getKeyText(key);
				if ("Escape".equals(keyString)) {
					System.out.println("esc hit");
					notifyObservers(new CancelEvent());
					getStatusLabel().setText("Cancelled.");
				} else {
					System.out.println("key pressed = " + key);
				}
				
			}
		
		});
		*/
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(780, 580);
		this.setContentPane(getJContentPane());
		this.setJMenuBar(getJJMenuBar());
		this.setTitle("CrossStitch 2.0 by ThaiDev.org");
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setLookAndFeel();
	}
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//				"com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if (jContentPane == null) {
			
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
			jContentPane.add(getJPanel(), java.awt.BorderLayout.SOUTH);
			javax.swing.JPanel internalPanel = 
			new javax.swing.JPanel(new java.awt.BorderLayout());
			internalPanel.add(getJSplitPane(), java.awt.BorderLayout.CENTER);
			internalPanel.add(getJToolBar(), java.awt.BorderLayout.NORTH);
			jContentPane.add(internalPanel, java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}
	/**
	 * This method initializes jJMenuBar
	 * 
	 * @return javax.swing.JMenuBar
	 */
	private javax.swing.JMenuBar getJJMenuBar() {
		if(jJMenuBar == null) {
			jJMenuBar = new javax.swing.JMenuBar();
			jJMenuBar.add(getJMenu());
			jJMenuBar.add(getJMenu2());
		}
		return jJMenuBar;
	}
	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private javax.swing.JMenu getJMenu() {
		if(jMenu == null) {
			jMenu = new javax.swing.JMenu();
			jMenu.add(getJMenuItem());
			jMenu.add(getJMenuItem7());
			jMenu.add(getJMenuItem8());
			jMenu.addSeparator();
			jMenu.add(getJMenuItem1());
			jMenu.setText("File");
		}
		return jMenu;
	}
	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getJMenuItem() {
		if(jMenuItem == null) {
			jMenuItem = new javax.swing.JMenuItem();
			jMenuItem.setText("Open image file...");
			jMenuItem.setIcon(ICON_OPEN);
			jMenuItem.setActionCommand("OPEN");
			jMenuItem.addActionListener(new LocalOpenListener());
		}
		return jMenuItem;
	}
	/**
	 * This method initializes jMenuItem1
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getJMenuItem1() {
		if(jMenuItem1 == null) {
			jMenuItem1 = new javax.swing.JMenuItem();
			jMenuItem1.setText("Exit");
			jMenuItem1.setIcon(ICON_EXIT);
			jMenuItem1.setActionCommand("EXIT");
			jMenuItem1.addActionListener(new LocalExitListener());
		}
		return jMenuItem1;
	}
	/**
	 * This method initializes jMenu2
	 * 
	 * @return javax.swing.JMenu
	 */
	private javax.swing.JMenu getJMenu2() {
		if(jMenu2 == null) {
			jMenu2 = new javax.swing.JMenu();
			jMenu2.add(getJMenuItem2());
			jMenu2.add(getJMenuItem3());
			jMenu2.add(getJMenuItem4());
			jMenu2.add(getJMenuItem5());
			jMenu2.add(getJMenuItem6());
			jMenu2.setText("Stitch");
		}
		return jMenu2;
	}
	/**
	 * This method initializes jMenuItem2
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getJMenuItem2() {
		if(jMenuItem2 == null) {
			jMenuItem2 = new javax.swing.JMenuItem();
			jMenuItem2.setText("1/5-sized Image");
			jMenuItem2.setActionCommand(STITCH_ONE_FIFTH);
			jMenuItem2.addActionListener(localListener);
		}
		return jMenuItem2;
	}
	/**
	 * This method initializes jMenuItem3
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getJMenuItem3() {
		if(jMenuItem3 == null) {
			jMenuItem3 = new javax.swing.JMenuItem();
			jMenuItem3.setText("1/4-sized Image");
			jMenuItem3.setActionCommand(STITCH_ONE_FOURTH);
			jMenuItem3.addActionListener(localListener);
		}
		return jMenuItem3;
	}
	/**
	 * This method initializes jMenuItem4
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getJMenuItem4() {
		if(jMenuItem4 == null) {
			jMenuItem4 = new javax.swing.JMenuItem();
			jMenuItem4.setText("1/3-sized Image");
			jMenuItem4.setActionCommand(STITCH_ONE_THIRD);
			jMenuItem4.addActionListener(localListener);
		}
		return jMenuItem4;
	}
	/**
	 * This method initializes jMenuItem5
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getJMenuItem5() {
		if(jMenuItem5 == null) {
			jMenuItem5 = new javax.swing.JMenuItem();
			jMenuItem5.setText("Half-sized Image");
			jMenuItem5.setActionCommand(STITCH_HALF);
			jMenuItem5.addActionListener(localListener);
		}
		return jMenuItem5;
	}
	/**
	 * This method initializes jMenuItem6
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getJMenuItem6() {
		if(jMenuItem6 == null) {
			jMenuItem6 = new javax.swing.JMenuItem();
			jMenuItem6.setText("Full-sized Image");
			jMenuItem6.setActionCommand(STITCH_FULL);
			jMenuItem6.addActionListener(localListener);
		}
		return jMenuItem6;
	}
	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJPanel() {
		if(jPanel == null) {
			jPanel = new javax.swing.JPanel();
			jPanel.setLayout(new java.awt.BorderLayout());
			jPanel.add(getStatusLabel(), java.awt.BorderLayout.WEST);
			JPanel panel = new JPanel();
			panel.add(getStatusProgressBar());
			panel.add(getJButton());
			jPanel.add(panel, java.awt.BorderLayout.EAST);
		}
		return jPanel;
	}
	/**
	 * This method initializes jLabel
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getStatusLabel() {
		if(statusLabel == null) {
			statusLabel = new javax.swing.JLabel();
			statusLabel.setText("Welcome to ThaiDev.org CrossStitch, Open an Image File to begin.");
		}
		return statusLabel;
	}
	/**
	 * This method initializes statusProgressBar
	 * 
	 * @return javax.swing.JProgressBar
	 */
	private javax.swing.JProgressBar getStatusProgressBar() {
		if(statusProgressBar == null) {
			statusProgressBar = new javax.swing.JProgressBar();
			statusProgressBar.setStringPainted(true);
		}
		return statusProgressBar;
	}
	/**
	 * This method initializes jSplitPane
	 * 
	 * @return javax.swing.JSplitPane
	 */
	private javax.swing.JSplitPane getJSplitPane() {
		if(jSplitPane == null) {
			jSplitPane = new javax.swing.JSplitPane();
			jSplitPane.setLeftComponent(getInputImagePanel());
			jSplitPane.setRightComponent(getOutputImagePanel());
			jSplitPane.setOneTouchExpandable(true);
			jSplitPane.setContinuousLayout(true);
			jSplitPane.setDividerLocation(400);
		}
		return jSplitPane;
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
		for (Iterator iter = observers.iterator(); iter.hasNext(); ) {
			Observer o = (Observer)iter.next();
			o.update(this, arg);
		}
	}
	/* (non-Javadoc)
	 * @see org.thaidev.util.Observer#update(org.thaidev.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
		if (arg instanceof StatusMessageEvent) {
			if (arg instanceof ErrorStatusMessageEvent)
				getStatusLabel().setForeground(Color.RED);
			getStatusLabel().setText(((StatusMessageEvent)arg).getMessage());
		} if (arg instanceof ProgressEvent) {
			int percentValue = ((ProgressEvent)arg).getPercentValue();
			if (percentValue == 0) {
				getStatusLabel().setText("Stitching...press 'Stop' button to stop");
				getJButton().setEnabled(true);
			} else if (percentValue == 100) {
				getStatusLabel().setText("Done.");
				getJButton().setEnabled(false);
			}
			statusProgressBar.setValue(percentValue);
		}
	}
	/**
	 * This method initializes inputImagePanel
	 * 
	 * @return org.thaidev.crossstich.ImageDecoratorPanel
	 */
	public org.thaidev.crossstitch.ui.ImageDecoratorPanel getInputImagePanel() {
		if(inputImagePanel == null) {
			inputImagePanel = new org.thaidev.crossstitch.ui.ImageDecoratorPanel();
			inputImagePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Input Image", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
		}
		return inputImagePanel;
	}
	/**
	 * This method initializes outputImagePanel
	 * 
	 * @return org.thaidev.crossstich.ImageDecoratorPanel
	 */
	public org.thaidev.crossstitch.ui.ImageDecoratorPanel getOutputImagePanel() {
		if(outputImagePanel == null) {
			outputImagePanel = new org.thaidev.crossstitch.ui.ImageDecoratorPanel();
			outputImagePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Output Image", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
		}
		return outputImagePanel;
	}
	
	private final class LocalExitListener implements java.awt.event.ActionListener {
		public void actionPerformed(java.awt.event.ActionEvent e) {    
			System.exit(0);
		}
	}
	private final class LocalExportListener implements java.awt.event.ActionListener {
		public void actionPerformed(java.awt.event.ActionEvent e) {    
			JFileChooser fc = new JFileChooser();
			int retVal = fc.showSaveDialog(CrossStitchUI.this);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				File fileSelected = fc.getSelectedFile();
				notifyObservers(new FileExportHTMLEvent(fileSelected));	
			}
		}
	}
	private final class LocalSaveListener implements java.awt.event.ActionListener {
		public void actionPerformed(java.awt.event.ActionEvent e) {    
			JFileChooser fc = new JFileChooser(lastFolder);
			int retVal = fc.showSaveDialog(CrossStitchUI.this);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				File fileSelected = fc.getSelectedFile();
				lastFolder = fileSelected.getAbsolutePath();
				notifyObservers(new FileSaveEvent(fileSelected));	
			}
		}
	}
	private final class LocalOpenListener implements java.awt.event.ActionListener {
		public void actionPerformed(java.awt.event.ActionEvent e) {    
			JFileChooser fc = new JFileChooser(lastFolder);
			int retVal = fc.showOpenDialog(CrossStitchUI.this);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				File fileSelected = fc.getSelectedFile();
				lastFolder = fileSelected.getAbsolutePath();
				notifyObservers(new FileOpenEvent(fileSelected));	
			}
		}
	}
	private class LocalActionListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			int step = 0;
			if (STITCH_FULL.equals(cmd))
				step = 1;
			else if (STITCH_HALF.equals(cmd))
				step = 2;
			else if (STITCH_ONE_THIRD.equals(cmd))
				step = 3;
			else if (STITCH_ONE_FOURTH.equals(cmd))
				step = 4;
			else if (STITCH_ONE_FIFTH.equals(cmd))
				step = 5;
			if (step != 0)
				notifyObservers(new DoStitchEvent(step));
		}
		
	}
	/**
	 * This method initializes jMenuItem7
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getJMenuItem7() {
		if(jMenuItem7 == null) {
			jMenuItem7 = new javax.swing.JMenuItem();
			jMenuItem7.setText("Save image as...");
			jMenuItem7.setIcon(ICON_SAVE);
			jMenuItem7.setActionCommand("SAVE");
			jMenuItem7.addActionListener(new LocalSaveListener());
		}
		return jMenuItem7;
	}
	/**
	 * This method initializes jMenuItem8
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getJMenuItem8() {
		if(jMenuItem8 == null) {
			jMenuItem8 = new javax.swing.JMenuItem();
			jMenuItem8.setText("Export to HTML...");
			jMenuItem8.setIcon(ICON_EXPORT);
			jMenuItem8.setActionCommand("HTML");
			jMenuItem8.addActionListener(new LocalExportListener());
		}
		return jMenuItem8;
	}
	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getJButton() {
		if(jButton == null) {
			jButton = new javax.swing.JButton();
			jButton.setText("Stop");
			jButton.setIcon(ICON_STOP);
			jButton.setPreferredSize(new java.awt.Dimension(100,25));
			jButton.setEnabled(false);
			jButton.addActionListener(new ActionListener() {
				/* (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					notifyObservers(new CancelEvent());
					getStatusLabel().setText("Cancelled.");
					jButton.setEnabled(false);
				}
			});
		}
		return jButton;
	}
	/**
	 * This method initializes jToolBar
	 * 
	 * @return javax.swing.JToolBar
	 */
	private javax.swing.JToolBar getJToolBar() {
		if(jToolBar == null) {
			jToolBar = new javax.swing.JToolBar();
			jToolBar.setMargin(new java.awt.Insets(5,5,5,5));
			jToolBar.add(getOpenButton());
			jToolBar.add(getSaveButton());
			jToolBar.add(getExportButton());
			jToolBar.add(getShowColorsButton());
			jToolBar.add(getExitButton());
		}
		return jToolBar;
	}
	/**
	 * This method initializes jButton1
	 * 
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getOpenButton() {
		if(jButton1 == null) {
			jButton1 = new javax.swing.JButton();
			jButton1.setText("Open");
			jButton1.setIcon(ICON_OPEN);
			jButton1.addActionListener(new LocalOpenListener());
		}
		return jButton1;
	}
	/**
	 * This method initializes jButton2
	 * 
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getSaveButton() {
		if(jButton2 == null) {
			jButton2 = new javax.swing.JButton();
			jButton2.setText("Save");
			jButton2.setIcon(ICON_SAVE);
			jButton2.addActionListener(new LocalSaveListener());
		}
		return jButton2;
	}
	/**
	 * This method initializes jButton3
	 * 
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getExportButton() {
		if(jButton3 == null) {
			jButton3 = new javax.swing.JButton();
			jButton3.setText("Export HTML");
			jButton3.addActionListener(new LocalExportListener());
			jButton3.setIcon(ICON_EXPORT);
		}
		return jButton3;
	}
	/**
	 * This method initializes jButton4
	 * 
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getExitButton() {
		if(jButton4 == null) {
			jButton4 = new javax.swing.JButton();
			jButton4.setText("Exit");
			jButton4.setIcon(ICON_EXIT);
			jButton4.addActionListener(new LocalExitListener());
		}
		return jButton4;
	}
	/**
	 * This method initializes jButton5
	 * 
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getShowColorsButton() {
		if(jButton5 == null) {
			jButton5 = new javax.swing.JButton();
			jButton5.setText("Show Colors");
			jButton5.setIcon(ICON_SHOW);
			jButton5.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					JFrame colorFrame = new JFrame();
					colorFrame.setContentPane(new ColorPanel(colorFrame, new DMC()));
					colorFrame.setTitle("Color Table");
					colorFrame.pack();
					colorFrame.setVisible(true);
				}
			});
		}
		return jButton5;
	}
}
