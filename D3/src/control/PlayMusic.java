package control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
/**
 * This class is used to run and stop music. 
 * @version 3.0
 */
public class PlayMusic{
		AudioPlayer MGP = AudioPlayer.player;
		AudioStream BGM;
		AudioData MD;
		ContinuousAudioDataStream loop = null;
		/**
		 * Constructor which accepts music filename to be played.
		 * @param filename
		 */
		public PlayMusic(String filename){
			try
			{
				InputStream test = new FileInputStream(filename);
				BGM = new AudioStream(test);
				MD=BGM.getData();
				loop= new ContinuousAudioDataStream(MD);
	       //AudioPlayer.player.start(BGM);
	        
			}
			catch(FileNotFoundException e){
				System.out.print(e.toString());
			}
			catch(IOException error)
			{
				System.out.print(error.toString());
			}
		}
		 /**
		  * Method to play music.   
		  */
		public void play(){	
			MGP.start(loop);
		}
		/**
		 * Method to stop music.
		 */
		public void stop(){
			if(MGP.isAlive())
			MGP.stop(loop);
		}
}