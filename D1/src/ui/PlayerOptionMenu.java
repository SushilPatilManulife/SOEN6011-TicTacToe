package ui;
import control.Controller;
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
import javax.swing.SwingUtilities;

import control.Controller;

public class PlayerOptionMenu extends GUIParent implements ActionListener{

	private JPanel formPanel;
	private JTextField player1;
	private JTextField player2;
	private ButtonGroup mark;
	JButton startGame;
	JRadioButton x, o;

	public PlayerOptionMenu(){
		initialize();
		mnNewGame.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formPanel = new JPanel();
		formPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		formPanel.add(new JLabel ("Enter player names"), c);
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
		new Controller(name1, name2, mark1, mark2);
		dispose();
		}
		else{
			JOptionPane.showMessageDialog(null, "please pick unique names!");
		}
	}

}
