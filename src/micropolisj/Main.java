// This file is part of Zombiepolis
// Zombiepolis is based on MicropolisJ 
// Copyright (C) 2014 Brier Hylen Field, Max Marcuse, Sophie Arana, Ahlam Ismail, Carsten Keller, Nina Gerber, Anthea Wiederspohn  for Zombiepolis
// Portions Copyright (C) 2013 Jason Long for MicropolisJ
// Portions Copyright (C) 1989-2007 Electronic Arts Inc.
//
// Zombiepolis is free software; you can redistribute it and/or modify
// it under the terms of the GNU GPLv3, with additional terms.
// See the README file, included in this distribution, for details.


package micropolisj;

import javax.swing.*;

import micropolisj.gui.MainWindow;
import micropolisj.gui.Splashscreen;

public class Main
{
	public static Splashscreen splash = new Splashscreen();
	
	static void createAndShowGUI()
	{        

		splash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		splash.setSize(1000,700);
		splash.setResizable(false);
		splash.setVisible(true);
		//MainWindow win = new MainWindow();
		//win.setVisible(true);
		//win.doNewCity(true);
	}

	public static void main(String [] args)
	{
		
		SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			createAndShowGUI();
		}});
	}
}
