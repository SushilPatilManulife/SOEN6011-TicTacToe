package control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class PlayMusic{
		AudioPlayer MGP = AudioPlayer.player;
		AudioStream BGM;
		AudioData MD;
		ContinuousAudioDataStream loop = null;
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
		    
		public void play(){	
			MGP.start(loop);
		}
		public void stop(){
			if(MGP.isAlive())
			MGP.stop(loop);
		}
		public void start(){
			AudioPlayer.player.start(BGM);
		}
}