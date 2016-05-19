package ui;
import control.Controller;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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
	private JPanel player1Panel;
	private JPanel player2Panel;
	private JTextField player1;
	private JTextField player2;
	private BufferedImage imageX;
	private BufferedImage imageO;
	private ButtonGroup mark, rounds;
	JButton startGame;
	JRadioButton x, o, b1, b3, b5;
	

	public PlayerOptionMenu(){
		initialize();
		mnNewGame.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formPanel = new JPanel();
		formPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10,10,10,10);
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		// formPanel.add(new JLabel ("Enter player names"), c);
		c.gridy++;
		
		formPanel.add(new JLabel ("Number of Rounds"), c);
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
		formPanel.add(pnl0, c);
		c.gridx--;
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
		
		try {
			imageX = ImageIO.read(new File(ClassLoader.getSystemResource("X.png").toURI()));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			imageO = ImageIO.read(new File(ClassLoader.getSystemResource("O.png").toURI()));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		player1Panel = new JPanel();
		player1Panel.setLayout(new GridBagLayout());
		GridBagConstraints po = new GridBagConstraints();
		po.insets = new Insets(5,5,5,5);
		po.gridx = 1;
		po.gridy = 0;
		po.anchor = GridBagConstraints.PAGE_START;
		player1Panel.setBackground(Color.GRAY);
		player1Panel.add(new JLabel ("First player Name"),po);
		po.gridx = 1;
		po.gridy = 1;
		player1Panel.add(player1 = new JTextField("Player 1", 10),po);
		po.gridx = 1;
		po.gridy = 3;
		player1Panel.add(new JLabel(new ImageIcon(imageX.getScaledInstance(30,30,20))),po);
		c.gridy=0;
		c.gridx=0;
		formPanel.add(player1Panel,c);
		
		player2Panel = new JPanel();
		player2Panel.setLayout(new GridBagLayout());
		GridBagConstraints pt = new GridBagConstraints();
		pt.insets = new Insets(5,5,5,5);
		pt.gridx = 1;
		pt.gridy = 0;
		pt.anchor = GridBagConstraints.PAGE_START;
		player2Panel.setBackground(Color.GRAY);
		player2Panel.add(new JLabel ("Second player Name"),pt);
		pt.gridx = 1;
		pt.gridy = 1;
		player2Panel.add(player1 = new JTextField("Player 2", 10),pt);
		pt.gridx = 1;
		pt.gridy = 3;
		player2Panel.add(new JLabel(new ImageIcon(imageO.getScaledInstance(30,30,20))),pt);
		c.gridx++;
		formPanel.add(player2Panel,c);
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
