package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
/** 
 * This class is to display GUI of menu bar. 
 * @version 3.0 
 * @see ui
 */
public class GUIParent extends JFrame{
	JMenuBar gameMenu = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenu help = new JMenu("Help");
	
    JMenuItem mnNewGame = new JMenuItem("New Game");
	JMenuItem exit = new JMenuItem("Exit");
	JMenuItem viewHelp = new JMenuItem("View Help");
	JMenuItem top = new JMenuItem("View top 10 players");
	/**
	 * this method creates the frame and sets common properties
	 */
	public void initialize(){
		setTitle("Tic Tac Toe");
		ImageIcon icon = new ImageIcon("src/icon.gif");
		setIconImage(icon.getImage());
     	setBounds(100, 100, 600, 350);
     	setResizable(false);
		setLocationRelativeTo(null);
		new Help();
		gameMenu.add(file);
		gameMenu.add(help);
		file.add(mnNewGame);
		file.add(top);
		file.add(exit);
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
				if (res == JOptionPane.YES_OPTION)
					System.exit(1);
			}
		});
		help.add(viewHelp);
		this.setJMenuBar(gameMenu);
		viewHelp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Help.getHelp();
			}
		});
		top.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				TopScoreLog.getScoreList();
			}
			});
	}
	
	/**
	 * method to resize frame
	*/
	public void packFrame(){
		pack();
	}
	
	
}
