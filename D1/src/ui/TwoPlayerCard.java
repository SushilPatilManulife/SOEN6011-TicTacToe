package ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import control.Controller;

public class TwoPlayerCard extends JPanel {
	
	private static final long serialVersionUID = 8353620773890305366L;
	private JPanel player1Panel, player2Panel;
	private JPanel playerNumberPanel;
	private JTextField player1;
	private JTextField player2;
	private ButtonGroup mark, rounds;
	JButton startGame;
	JRadioButton x, o, b1, b3, b5;
	JLabel p1Image, p2Image;
	JLabel name;

	URL Ximage, Yimage;
	
	public TwoPlayerCard() {
		Ximage = getClass().getResource("/X.png");
		Yimage = getClass().getResource("/O.png");
		final ImageIcon xIcon = new ImageIcon(new ImageIcon(Ximage).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		final ImageIcon oIcon = new ImageIcon(new ImageIcon(Yimage).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10,10,10,10);
		c.anchor = GridBagConstraints.CENTER;
		c.gridy++;
		this.add(new JLabel ("First move"), c);
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
		c.gridx++;
		pnl1.setOpaque(false);
		this.add(pnl1, c);
		
		c.gridx--;
		c.gridy++;
		//TODO: add in deliverable 2
		//this.add(new JLabel ("Number of Rounds"), c);
		rounds = new ButtonGroup();
		b1 = new JRadioButton("1");
		b3 = new JRadioButton("3");
		b5 = new JRadioButton("5");
		rounds.add(b1);
		rounds.add(b3);
		rounds.add(b5);
		JPanel pnl0 = new JPanel();
		pnl0.add(b1);
		pnl0.add(b3);
		pnl0.add(b5);
		b3.setSelected(true);
		c.gridx++;
		//TODO: add in deliverable 2
		//this.add(pnl0, c);
		p1Image = new JLabel();
		p2Image = new JLabel();

		x.setSelected(true);
		p1Image.setIcon(xIcon);
		p2Image.setIcon(oIcon);

		x.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				p1Image.setIcon(xIcon);
				p2Image.setIcon(oIcon); 
				}
		});
		o.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				p2Image.setIcon(xIcon);
				p1Image.setIcon(oIcon); 
			}
		});

		c.gridy++;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		this.add(startGame = new JButton("Start game"), c);
		startGame.addActionListener(actionListener);
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
		po.anchor = GridBagConstraints.PAGE_START;
		player1Panel.setBackground(new Color(230,230,230));
		player1Panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		player1Panel.add(new JLabel ("First player"),po);
		po.gridx = 0;
		po.gridy = 1;
		po.anchor = GridBagConstraints.LINE_START;
		po.fill = GridBagConstraints.NONE;
		po.gridwidth = 1;
		player1Panel.add(new JLabel("Name"),po);
		po.gridx = 1;
		po.gridy = 1;
		player1Panel.add(player1 = new JTextField("Player 1", 10),po);
		po.gridx = 1;
		po.gridy = 2;
		po.gridwidth = 3;
		po.fill = GridBagConstraints.CENTER;
		player1Panel.add(p1Image,po);
		c.gridy=0;
		c.gridx=0;
		this.add(player1Panel,c);
		
		player2Panel = new JPanel();
		player2Panel.setLayout(new GridBagLayout());
		player2Panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		GridBagConstraints pt = new GridBagConstraints();
		pt.gridwidth = 3;
		pt.fill = GridBagConstraints.CENTER;
		pt.insets = new Insets(5,5,5,5);
		pt.gridx = 0;
		pt.gridy = 0;
		player2Panel.setBackground(new Color(230,230,230));
		player2Panel.add(new JLabel ("Second player"),pt);
		pt.gridx = 0;
		pt.gridy = 1;
		pt.anchor = GridBagConstraints.LINE_START;
		pt.fill = GridBagConstraints.NONE;
		pt.gridwidth = 1;
		player2Panel.add(new JLabel("Name"),pt);
		pt.gridx = 1;
		pt.gridy = 1;
		player2Panel.add(player2 = new JTextField("Player 2", 10),pt);
		pt.gridx = 1;
		pt.gridy = 2;
		pt.gridwidth = 3;
		pt.fill = GridBagConstraints.CENTER;
		player2Panel.add(p2Image,pt);
		c.gridx++;
		this.add(player2Panel,c);
	}
	
	ActionListener actionListener = new ActionListener() {
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
			Cards.frame.dispose();
			}
			else{
				JOptionPane.showMessageDialog(null, "please pick unique names!");
			}
	    }
	};

}
