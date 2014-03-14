package micropolisj;
 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

import micropolisj.gui.MainWindow;

class SplashscreenZombie extends JWindow {
 private int duration;

 public SplashscreenZombie(int d) {
   duration = d;

   JLabel lbl = new JLabel(new ImageIcon(MainWindow.class.getResource("/zombiecity.png")));
   getContentPane().add(lbl, BorderLayout.CENTER);
   pack();
   setLocationRelativeTo(null);
   setVisible(true);
   try {
     Thread.sleep(duration);
   } catch (Exception e) {
   }
   setVisible(false);
 }
}