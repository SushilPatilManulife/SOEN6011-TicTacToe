package ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

import control.Controller;

public class OnePlayerCard extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7021577685334986965L;
	private JPanel player1Panel, player2Panel;
	private JPanel playerNumberPanel;
	private JTextField player1;
	private JTextField player2;
	private ButtonGroup mark, rounds, level, turn;
	JButton startGame;
	JRadioButton x, o, b1, b3, b5, computer, human;
	JLabel p1Image, p2Image;
	JLabel name;
	JFrame topFrame;
	URL Ximage, Yimage;
	
	public OnePlayerCard() {
		Ximage = getClass().getResource("/X.png");
		Yimage = getClass().getResource("/O.png");
		final ImageIcon xIcon = new ImageIcon(new ImageIcon(Ximage).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		final ImageIcon oIcon = new ImageIcon(new ImageIcon(Yimage).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10,10,10,10);
		c.gridy++;
		c.anchor = GridBagConstraints.LINE_START;
	
		this.add(new JLabel ("Number of Rounds"), c);
		rounds = new ButtonGroup();
		b1 = new JRadioButton("1");
		b3 = new JRadioButton("3");
		b5 = new JRadioButton("5");
		rounds.add(b1);
		rounds.add(b3);
		rounds.add(b5);
		b1.setOpaque(false);
		b3.setOpaque(false);
		b5.setOpaque(false);
		JPanel pnl0 = new JPanel();
		pnl0.add(b1);
		pnl0.add(b3);
		pnl0.add(b5);
		pnl0.setOpaque(false);
		b3.setSelected(true);
		c.gridx++;

		this.add(pnl0, c);
		p1Image = new JLabel();
		p2Image = new JLabel();

;
		p1Image.setIcon(xIcon);
		p2Image.setIcon(oIcon);

		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		player1Panel = new JPanel();
		player1Panel.setLayout(new GridBagLayout());
		GridBagConstraints po = new GridBagConstraints();
		po.insets = new Insets(5,5,5,5);
		po.gridwidth = 3;
		po.fill = GridBagConstraints.CENTER;
		po.gridx = 0;
		po.gridy = 0;
		po.anchor = GridBagConstraints.CENTER;
		player1Panel.setBackground(new Color(230,230,230));
		player1Panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		player1Panel.add(new JLabel ("Human Player"),po);
		po.gridx = 0;
		po.gridy = 1;
		po.anchor = GridBagConstraints.LINE_START;
		po.fill = GridBagConstraints.NONE;
		po.gridwidth = 1;
		player1Panel.add(new JLabel("Name"),po);
		po.gridx = 1;
		po.gridy = 1;
		po.gridwidth = 3;
		player1Panel.add(player1 = new JTextField("Player 1", 10),po);
		po.gridx = 0;
		po.gridy = 2;
		po.gridwidth = 1;
		
		player1Panel.add(new JLabel ("Player mark"), po);
		
		po.gridx ++;		
		po.anchor = GridBagConstraints.CENTER;
		mark = new ButtonGroup();
		x = new JRadioButton();
		o = new JRadioButton();
		mark.add(x);
		mark.add(o);
		JPanel pnl1 = new JPanel();
		pnl1.add(x);
		pnl1.add(new  JLabel(xIcon));
		pnl1.add(o);
		pnl1.add(new  JLabel(oIcon));
		pnl1.setOpaque(false);
		x.setSelected(true);
		player1Panel.add(pnl1, po);
		
		c.gridy=0;
		c.gridx=0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		this.add(player1Panel,c);
		
		c.gridwidth=1;
		JPanel levelPnl = new JPanel();
		JLabel levelLbl = new JLabel("level");
		level = new ButtonGroup();
		JRadioButton easy = new JRadioButton("Easy");
		easy.setActionCommand("easy");
		JRadioButton medium = new JRadioButton("Medium");
		medium.setActionCommand("medium");
		JRadioButton hard = new JRadioButton("Hard");
		hard.setActionCommand("hard");
		level.add(easy);
		level.add(medium);
		level.add(hard);
		levelPnl.add(easy);
		levelPnl.add(medium);
		levelPnl.add(hard);
		easy.setSelected(true);
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_START;
		easy.setOpaque(false);
		medium.setOpaque(false);
		hard.setOpaque(false);
		levelPnl.setOpaque(false);
		this.add(levelLbl, c);
		c.gridx++;
		this.add(levelPnl, c);
		
		c.gridy++;
		JPanel turnPnl = new JPanel();
		JLabel turnLbl = new JLabel("First move by");
		turn = new ButtonGroup();
		computer = new JRadioButton("Computer Player");
		human = new JRadioButton("Human Player");
		human.setSelected(true);
		turn.add(human);
		turn.add(computer);
		turnPnl.add(human);
		turnPnl.add(computer);
		turnPnl.setOpaque(false);
		computer.setOpaque(false);
		human.setOpaque(false);
		c.gridx = 0;
		this.add(turnLbl, c);
		c.gridx++;
		this.add(turnPnl,c);
		
		c.gridy++;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		this.add(startGame = new JButton("Start game"), c);
		startGame.addActionListener(actionListener);
		
	}
	
	ActionListener actionListener = new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	int roundNum = 0;
	    	String name = player1.getText();
	    	String levelName = level.getSelection().getActionCommand();
			String mark1 = "X";
			String mark2 = "O";
			if (o.isSelected()){
				mark1 = "O";
				mark2 = "X";
			}
			int humanTurn = 1;
			if(computer.isSelected())
				humanTurn = 2;
			
			 for (Enumeration<AbstractButton> buttons = rounds.getElements(); buttons.hasMoreElements();) {
		            AbstractButton button = buttons.nextElement();

		            if (button.isSelected()) {
		                roundNum = Integer.parseInt(button.getText());
		            }
		        }
			 if(!name.equals("Computer")){
	    	new Controller( name,  mark1, mark2, roundNum, humanTurn, levelName);
	    	
			 }
			 else
				 JOptionPane.showMessageDialog(null, "please pick a different name!");
	    }
	};


}
