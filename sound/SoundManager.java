package sound;

import java.io.IOException;

import org.lwjgl.openal.AL;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;


public class SoundManager {

	private static SoundManager _instance = null;
	
	private Audio overworld;
	private Audio hell;
	private float volume = 0.5f;

	private SoundManager() {
		init();
	}

	public static SoundManager getInstance() {
		if (_instance == null) {
			_instance = new SoundManager();
		}
		return _instance;
	}

	private void init() {
		try {
			overworld = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sound/overworld.wav"));
			hell = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sound/hell.wav"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		SoundStore.get().setMusicVolume(volume);//default volume
	}

	public void playMusic(SoundName name) {
		switch (name) {
		case Overworld:
			overworld.playAsMusic(1.0f, 1.0f, true);
			break;
		case Hell:
			hell.playAsMusic(1.0f, 1.0f, true);
			break;
		default:
			break;
		}
	}

	public void playSound(SoundName name) {
		switch (name) {
		case Overworld:
			overworld.playAsSoundEffect(1.0f, 1.0f, true);
			break;
		case Hell:
			hell.playAsSoundEffect(1.0f, 1.0f, true);
			break;
		default:
			break;
		}
	}
	
	public void cleanUp(){
		AL.destroy();
	}
	

	public void SoundUp(){
		volume += 0.02f;
		SoundVolume((volume > 1.0f)?1.0f:volume);
	}
	
	public void SoundDown(){
		volume -= 0.02f;
		SoundVolume((volume < 0)?0:volume);
	}
	
	private void SoundVolume(float volume){
		SoundStore.get().setMusicVolume(volume);
	}
	
	
	
}
