package ui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import control.Controller;

public class PlayerOptionMenu extends JFrame implements ActionListener{

	//TODO: set number of rounds 1, 3, 5
	private JPanel formPanel;
	private JTextField player1;
	private JTextField player2;
	private ButtonGroup mark;
	JButton startGame;
	JRadioButton x, o;
	JMenuBar gameMenu = new JMenuBar();
	JMenu help = new JMenu("Help");
	JMenuItem viewHelp = new JMenuItem("View Help");
	
	public PlayerOptionMenu(){
		setTitle("Tic Tac Toe");
		setBounds(100, 100, 524, 304);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameMenu.add(help);
		help.add(viewHelp);
		viewHelp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Help.getHelp();
			}
		});
		formPanel = new JPanel();
		new Help();
		formPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		setJMenuBar(gameMenu);
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		//formPanel.add(new JLabel ("Enter player names"), c);
		c.gridy++;
		formPanel.add(new JLabel (" "), c);
		c.gridy++;
		formPanel.add(new JLabel ("First player"), c);
		c.gridx++;
		formPanel.add(player1 = new JTextField("Player 1", 10), c);
		c.gridy++;
		formPanel.add(player2 = new JTextField("Player 2", 10), c);
		c.gridx--;
		formPanel.add(new JLabel("Second player"), c);
		c.gridy++;
		formPanel.add(new JLabel ("First move"), c);
		mark = new ButtonGroup();
		x = new JRadioButton("X");
		o = new JRadioButton("O");
		mark.add(x);
		mark.add(o);
		JPanel pnl1 = new JPanel();
		pnl1.add(x);
		pnl1.add(o);
		x.setSelected(true);
		c.gridx++;
		//formPanel.add(x, c);
		//c.gridx++;
		//formPanel.add(o, c);
		formPanel.add(pnl1, c);
		c.gridy++;
		formPanel.add(startGame = new JButton("Start game"), c);
		startGame.addActionListener(this);
		this.add(formPanel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String name1 = player1.getText();
		String name2 = player2.getText();
		if(!name1.equals(name2)){
		String mark1 = "X";
		String mark2 = "O";
		if (o.isSelected()){
			mark1 = "O";
			mark2 = "X";
		}		
		int totalRound = 1;
		//TODO: totalRound = radioButton
		
		new Controller(name1, name2, mark1, mark2, totalRound);
		dispose();
		}
		else{
			JOptionPane.showMessageDialog(null, "please pick unique names!");
		}
	}

}
