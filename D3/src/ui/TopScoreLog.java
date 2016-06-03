package ui;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.XmlIO;

public class TopScoreLog extends JPanel{

		JScrollPane ScorePnl; 
		static JDialog scoreDg; 

		public TopScoreLog(){

			scoreDg = new JDialog();
			scoreDg.setLocationRelativeTo(null);

			ArrayList<String> players = null;
			try {
				players = XmlIO.getTopTen();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Object[][] name = new String[10][3];
			int index =9;
			String info;
			String [] parts;
			for (int i=0; i<players.size(); i++){
				 info = players.get(i);
				 parts = info.split("=");
				 name [index][0] = ""+(index+1);
				 name [index][1] = parts[0];
				 name [index][2] = parts[1];
				 index--;
			}
			Object[] col = {"Rank", "Player name", "Number of games won"};
			JTable table = new JTable(name, col){
				
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column){
					return false;
				}
			};
			table.getColumnModel().getColumn(0).setPreferredWidth(10);
			ScorePnl = new JScrollPane(table);
			
			JPanel pnl = new JPanel();
			pnl.add(ScorePnl, BorderLayout.CENTER);			
			scoreDg.add(pnl);
			scoreDg.setTitle("Top 10 players");
			ScorePnl.setVisible(true);
			scoreDg.setBounds(100, 100, 300, 100);
			scoreDg.pack();
		}

		public static void getScoreList(){
			new TopScoreLog();
			scoreDg.setVisible(true);
		}

}
