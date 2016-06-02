package ui;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ui.GameBoard;
public class Cards extends GUIParent implements ItemListener {
	//static JFrame frame;
    JPanel cards, formPanel; //a panel that uses CardLayout
    final static String BUTTONPANEL = "Two Players";
    final static String TEXTPANEL = "One Player";
    JPanel comboBoxPane = new JPanel(); //use FlowLayout
    String comboBoxItems[] = { TEXTPANEL, BUTTONPANEL };
    JComboBox cb = new JComboBox(comboBoxItems);
    static String music="background_computer.wav";
    
    public void addComponentToPane(Container pane) {
        //Put the JComboBox in a JPanel to get a nicer look.
        
        
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(new JLabel("Game mode"));
        comboBoxPane.add(cb);
        comboBoxPane.setOpaque(false);
        
        //Create the "cards".
        TwoPlayerCard card1 = new TwoPlayerCard();
        OnePlayerCard card2 = new OnePlayerCard();
        
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        card1.setOpaque(false);
        card2.setOpaque(false);
        cards.setOpaque(false);
        cards.add(card2, TEXTPANEL);
        cards.add(card1, BUTTONPANEL);
        
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        //c.anchor = GridBagConstraints.LINE_END;
        //pane.add(new JLabel("Game mode"), c);
        //c.gridx++;
        c.gridwidth =2;
        pane.add(comboBoxPane, c);
        c.gridx = 0;
        c.gridy++;
        c.gridwidth =1;
        pane.add(cards, c);
    }
   
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
//        if(cb.getSelectedItem().equals("Two Players"))
//        	music="background_player.wav";
        cl.show(cards, (String)evt.getItem());
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public void createAndShowGUI() {
    	initialize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create and set up the content pane.
        Cards demo = new Cards();
		formPanel = new JPanel(){	 
			public void paintComponent (Graphics g)
			{
				super.paintComponent(g);
				 try {
					g.drawImage(ImageIO.read(getClass().getResource("/bg1.jpg")), 0, 0, null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			};
			
        demo.addComponentToPane(formPanel);
        getContentPane().add(formPanel);
        //Display the window.
        packFrame();
        setVisible(true);

    }
 
}