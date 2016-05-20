package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
<<<<<<< HEAD

/** 
 * This class is to display GUI of menu bar. 
 * @version 1.0 
 * @see ui
=======
/**
 *  This class is the parent GUI for Menu bar and shared properties. 
 *@version 1.0 
>>>>>>> 90c82befe8495ae71366f17e46fee36c8b43f895
 */
public class GUIParent extends JFrame{
	ImageIcon bgImage;
	JMenuBar gameMenu = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenu help = new JMenu("Help");
	JMenuItem mnNewGame = new JMenuItem("New Game"),
	exit = new JMenuItem("Exit"),
	viewHelp = new JMenuItem("View Help"),
	about = new JMenuItem("About");
<<<<<<< HEAD
	/**
	 * Display the menu bar.
	 */
=======
	JLabel bgImageLabel;
/**
 * this method creates the frame and sets common properties
 */
>>>>>>> 90c82befe8495ae71366f17e46fee36c8b43f895
	public void initialize(){
		setTitle("Tic Tac Toe");
		setResizable(false);
		ImageIcon icon = new ImageIcon("src/icon.gif");
		setIconImage(icon.getImage());
		setBounds(100, 100, 600, 350);
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
	/**
<<<<<<< HEAD
	 * pack frame
	 */
=======
	 * method to resize frame
	 */

>>>>>>> 90c82befe8495ae71366f17e46fee36c8b43f895
	public void packFrame(){
		pack();
	}
}
