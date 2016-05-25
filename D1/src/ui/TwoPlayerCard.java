package ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class TwoPlayerCard extends JPanel {
	
	public TwoPlayerCard() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints po = new GridBagConstraints();
		po.insets = new Insets(5,5,5,5);
		po.gridwidth = 3;
		po.fill = GridBagConstraints.CENTER;
		po.gridx = 0;
		po.gridy = 0;
		po.anchor = GridBagConstraints.PAGE_START;
		this.setBackground(new Color(230,230,230));
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.add(new JLabel ("First player"),po);
		po.gridx = 0;
		po.gridy = 1;
		po.anchor = GridBagConstraints.LINE_START;
		po.fill = GridBagConstraints.NONE;
		po.gridwidth = 1;
		this.add(new JLabel("Name"),po);
		po.gridx = 1;
		po.gridy = 1;
		this.add(new JTextField("Player 1", 10),po);
		po.gridx = 1;
		po.gridy = 2;
		po.gridwidth = 3;
		po.fill = GridBagConstraints.CENTER;
	}

}
