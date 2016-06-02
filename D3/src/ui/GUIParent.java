package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GUIParent extends JFrame{
	JMenuBar gameMenu = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenu help = new JMenu("Help");
	static JMenuItem mnNewGame = new JMenuItem("New Game");
	JMenuItem exit = new JMenuItem("Exit");
	JMenuItem viewHelp = new JMenuItem("View Help");
	JMenuItem about = new JMenuItem("About");
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
		file.add(exit);
		help.add(viewHelp);
		this.setJMenuBar(gameMenu);
		viewHelp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Help.getHelp();
			}
		});
	}
	
	
	public void packFrame(){
		pack();
	}
	
	
}
