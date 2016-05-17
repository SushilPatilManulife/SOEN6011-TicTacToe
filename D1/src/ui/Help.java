package ui;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Help extends JPanel{

	JPanel helpPnl; 
	JTextArea content;
	String helpPage = "Rules:\n"
			+ "Player 1 moves first.\n"
			+ "A piece may be placed on any empty space.\n"
			+ "A player wins by being the first to connect a line of friendly pieces \n"
			+ "from one side or corner of the board to the other. \n"
			+ "The game ends when either one player wins or it is no longer possible for a player\n"
			+ " to win (in which case the result is a draw).\n";
	public Help(){
		helpPnl = new JPanel();
		content = new JTextArea();
		helpPnl.setBounds(100, 100, 650, 650);
		content.setText(helpPage);
		helpPnl.add(content);
		content.setEditable(false);
		helpPnl.setVisible(true);
	}
	public JPanel getHelp(){
		return helpPnl;
	}
	
}
