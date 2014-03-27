// This file is part of Zombiepolis
// Zombiepolis is based on MicropolisJ
// Copyright (C) 2014 Brier Hylen Field, Max Marcuse, Sophie Arana, Ahlam Ismail, Carsten Keller, Nina Gerber, Anthea Wiederspohn for Zombiepolis
//
// Zombiepolis is free software; you can redistribute it and/or modify
// it under the terms of the GNU GPLv3, with additional terms.
// See the README file, included in this distribution, for details.

package micropolisj.gui;

import static micropolisj.gui.MainWindow.EXTENSION;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;

import micropolisj.Main;
import micropolisj.gui.*;
import micropolisj.engine.Micropolis;

import javax.swing.JDialog;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Splashscreen extends JFrame{

	/*JButton jbNewGame; //Everything commented is the button stuff of the earlier version.
	JButton jbLoadGame;
	JButton jbLastGame;
	JButton jbExit;
	JButton jbScenario;*/
	JPanel panel;
	JPanel panelNewGame;
	JPanel panelLoad;
	JPanel panelLeave;
	BufferedImage image;
	JLabel jlSplashImage;
	JLabel jlStartNewGame;
	JLabel jlLoadCity; 
	JLabel jlLeaveGame;


	static final ResourceBundle strings = MainWindow.strings;

	public Splashscreen(){
			super("WELCOME");
			try{
					image = ImageIO.read(new File("graphics/splash.png"));
					jlStartNewGame = new JLabel(new ImageIcon(ImageIO.read(new File("graphics/splash_1.png"))));
					jlLoadCity = new JLabel(new ImageIcon(ImageIO.read(new File("graphics/splash_2.png"))));
					jlLeaveGame = new JLabel(new ImageIcon(ImageIO.read(new File("graphics/splash_3.png"))));	
			} catch(IOException e){
					e.printStackTrace();
			}
			
			ImageIcon appIcon = new ImageIcon(MainWindow.class.getResource("/micropolism.png"));
			setIconImage(appIcon.getImage());

			jlSplashImage = new JLabel(new ImageIcon(image));
			jlSplashImage.setBounds(0,0,1000,700);
			jlStartNewGame.setBounds(0,0,1000,700);
			jlLoadCity.setBounds(0,0,1000,700);
			jlLeaveGame.setBounds(0,0,1000,700);


			panel = new JPanel(null,true);
			panel.setSize(1000,700);
			setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-panel.getWidth()/2,Toolkit.getDefaultToolkit().getScreenSize().height/2-panel.getHeight()/2);
			getContentPane().add(panel);
			panel.add(jlSplashImage);
			
			panelNewGame = new JPanel(null,true);
			panelNewGame.setBounds(430,440,260,130);
			HandlerNewGame handlerng = new HandlerNewGame();
			panelNewGame.addMouseListener(handlerng);
			panel.add(panelNewGame);
			
			panelLoad = new JPanel(null,true);
			panelLoad.setBounds(120,580,300,110);
			HandlerLoad handlerl = new HandlerLoad();
			panelLoad.addMouseListener(handlerl);
			panel.add(panelLoad);
			
			panelLeave = new JPanel(null,true);
			panelLeave.setBounds(730,500,180,150);
			HandlerLeave handlerle = new HandlerLeave();
			panelLeave.addMouseListener(handlerle);
			panel.add(panelLeave);
			

	}

private class HandlerNewGame implements MouseListener{
		public void mouseClicked(MouseEvent event){
			dispose();
			MainWindow win = new MainWindow();

			win.setVisible(true);
			win.doNewCity(true);
		}
		public void mousePressed(MouseEvent event){

		}
		public void mouseReleased(MouseEvent event){

		}
		public void mouseEntered(MouseEvent event){
				panel.remove(jlSplashImage);
				removeBackgroundPanels();
				panel.add(jlStartNewGame);
				addBackgroundPanels();
				panel.repaint();
		}
		public void mouseExited(MouseEvent event){
				panel.remove(jlStartNewGame);
				removeBackgroundPanels();
				panel.add(jlSplashImage);
				addBackgroundPanels();
				panel.repaint();
		}
}


private class HandlerLoad implements MouseListener{
	public void mouseClicked(MouseEvent event){
			try
			{	
					JFileChooser fc = new JFileChooser();
					FileNameExtensionFilter filter1 = new FileNameExtensionFilter(strings.getString("cty_file"), EXTENSION);
					fc.setFileFilter(filter1);

					int rv = fc.showOpenDialog(Splashscreen.this);
					if (rv == JFileChooser.APPROVE_OPTION) {
							File file = fc.getSelectedFile();
							Micropolis newEngine = new Micropolis();
							newEngine.load(file);
							startPlaying(newEngine, file);

					}
			}
			catch (Exception e)
			{
					e.printStackTrace(System.err);
					JOptionPane.showMessageDialog(Splashscreen.this, e, strings.getString("main.error_caption"),
							JOptionPane.ERROR_MESSAGE);
			}
	}
	public void mousePressed(MouseEvent event){

	}
	public void mouseReleased(MouseEvent event){

	}
	public void mouseEntered(MouseEvent event){
			panel.remove(jlSplashImage);
			removeBackgroundPanels();
			panel.add(jlLoadCity);
			addBackgroundPanels();
			panel.repaint();
	}
	public void mouseExited(MouseEvent event){
			panel.remove(jlLoadCity);
			removeBackgroundPanels();
			panel.add(jlSplashImage);
			addBackgroundPanels();
			panel.repaint();
	}
}

	private class HandlerLeave implements MouseListener{
			public void mouseClicked(MouseEvent event){
				System.exit(0);
			}
			public void mousePressed(MouseEvent event){

			}
			public void mouseReleased(MouseEvent event){
			
			}
			public void mouseEntered(MouseEvent event){
					panel.remove(jlSplashImage);
					removeBackgroundPanels();
					panel.add(jlLeaveGame);
					addBackgroundPanels();
					panel.repaint();
			}
			public void mouseExited(MouseEvent event){
					panel.remove(jlLeaveGame);
					removeBackgroundPanels();
					panel.add(jlSplashImage);
					addBackgroundPanels();
					panel.repaint();
			}
	}

    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(image, 0, 0, null);
    }


    	void startPlaying(Micropolis newEngine, File file)
    	{

				MainWindow win = new MainWindow();
				win.setVisible(true);
				win.setEngine(newEngine);
				if (newEngine.oldMapLoaded < 0)
				{
					win.oldLoad();
				}
				win.currentFile = file;
				win.makeClean();
				dispose();
		}
				
		private void removeBackgroundPanels(){
				panel.remove(panelNewGame);
				panel.remove(panelLoad);
				panel.remove(panelLeave);
				
		}
		private void addBackgroundPanels(){
				panel.add(panelNewGame);
				panel.add(panelLoad);
				panel.add(panelLeave);
		}

}