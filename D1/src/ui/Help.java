package ui;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Help extends JPanel{

	JPanel helpPnl; 
	JTextArea content;
	static JDialog helpDg; 
	String helpPage = "Rules:\n"
			+ "Player 1 moves first.\n"
			+ "A piece may be placed on any empty space.\n"
			+ "A player wins by being the first to connect a line of friendly pieces \n"
			+ "from one side or corner of the board to the other. \n"
			+ "The game ends when either one player wins or it is no longer possible for a player\n"
			+ " to win (in which case the result is a draw).\n";
	public Help(){
		helpPnl = new JPanel();
	    helpDg = new JDialog();
		content = new JTextArea();

		content.setBounds(28, 42, 660, 148);
		helpPnl.setBounds(100, 100, 698, 297);
		helpPnl.setLayout(null);

		helpPnl.setLayout(new BorderLayout());
		helpDg.setLocationRelativeTo(null);

		content.setText(helpPage);
		helpPnl.add(content);
		helpDg.add(helpPnl);
		helpDg.setTitle("Help");
		content.setEditable(false);
		helpPnl.setVisible(true);
		helpDg.pack();
	}
	public static void getHelp(){
		helpDg.setVisible(true);
	}
}
