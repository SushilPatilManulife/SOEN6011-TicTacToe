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

public class OnePlayerCard extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7021577685334986965L;
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
	
	public OnePlayerCard() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10,10,10,10);
		c.anchor = GridBagConstraints.CENTER;
		c.gridy++;
		this.add(new JLabel ("First move"), c);
		mark = new ButtonGroup();
		x = new JRadioButton("X");
		o = new JRadioButton("O");
		mark.add(x);
		mark.add(o);
		JPanel pnl1 = new JPanel();
		pnl1.add(x);
		pnl1.add(o);
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
		this.add(pnl0, c);
		p1Image = new JLabel();
		p2Image = new JLabel();
		Ximage = getClass().getResource("/X.png");
		Yimage = getClass().getResource("/O.png");
		final ImageIcon xIcon = new ImageIcon(new ImageIcon(Ximage).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		final ImageIcon oIcon = new ImageIcon(new ImageIcon(Yimage).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
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
		po.anchor = GridBagConstraints.CENTER;
		player1Panel.setBackground(new Color(230,230,230));
		player1Panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		player1Panel.add(new JLabel ("Player"),po);
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
		c.gridx=1;
		this.add(player1Panel,c);
		
	}
	
	ActionListener actionListener = new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        
	    }
	};

}
