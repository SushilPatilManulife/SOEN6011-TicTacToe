package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Board extends JPanel implements ActionListener{

	static JButton btnOnGameBoard[] = new JButton[9];

	public Board(){
		setBounds(25, 25, 314, 191);
		setLayout(new GridLayout(0, 3, 0, 0));
		for(int i = 0 ; i < 9 ; i++){
			btnOnGameBoard[i]=new JButton();	
			btnOnGameBoard[i].setFont(new Font("Tahoma", Font.BOLD, 40));
			btnOnGameBoard[i].setText("");
			btnOnGameBoard[i].addActionListener(this);
			btnOnGameBoard[i].setBackground(new Color(32,22,63));
			this.add(btnOnGameBoard[i]);			
		}
	}
	public static JButton getButton(int i){
		return btnOnGameBoard [i];
	}
	public void actionPerformed(ActionEvent e) {
		JButton checkClick=(JButton) e.getSource();
	    try {
			GameBoard.cellClicked(checkClick);
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static int update(JButton checkClick, Color color, String mark){
		int index = 0;
		for (int i = 0; i < 9 ; i++) {
			if(checkClick==btnOnGameBoard[i]){
				btnOnGameBoard[i].setForeground(color);
				btnOnGameBoard[i].setText(mark);
				index = i;
										}
			}
	return index;
	}
	public static void reset(){
		for(int i = 0 ; i < 9 ; i++){
		btnOnGameBoard[i].setText("");
		btnOnGameBoard[i].setEnabled(true);
		btnOnGameBoard[i].setBackground(new Color(32,22,63));
		}
	}
	public static void displayRoundResult(int[] line)
	{
		for (int i = 0; i < 9 ; i++) {
			btnOnGameBoard[i].setEnabled(false);
			}
		for (int j = 0; j < 3 ; j++) {
			btnOnGameBoard[line[j]].setBackground(Color.blue);
			}
	}
}
