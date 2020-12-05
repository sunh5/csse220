import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BackgroundMusic {
	
	private String name;
	private Clip music;
	private Clip bossMusic;
	private Clip chillMusic;
	private Clip shoot;
	private Clip stop;

	public BackgroundMusic(String musicName) {
		this.name = musicName;
		try {
			AudioInputStream welcomeStream = AudioSystem.getAudioInputStream(new File("startMusic.wav"));
			this.music = AudioSystem.getClip();
			this.music.open(welcomeStream);
			AudioInputStream welcomeStream2 = AudioSystem.getAudioInputStream(new File("chillMusic.wav"));
			this.chillMusic = AudioSystem.getClip();
			this.chillMusic.open(welcomeStream2);
			AudioInputStream welcomeStream3 = AudioSystem.getAudioInputStream(new File("bossMusic.wav"));
			this.bossMusic = AudioSystem.getClip();
			this.bossMusic.open(welcomeStream3);
			AudioInputStream welcomeStream4 = AudioSystem.getAudioInputStream(new File("shoot.wav"));
			this.shoot = AudioSystem.getClip();
			this.shoot.open(welcomeStream4);
			AudioInputStream welcomeStream5 = AudioSystem.getAudioInputStream(new File("quite.wav"));
			this.stop = AudioSystem.getClip();
			this.stop.open(welcomeStream5);
		} catch (Exception e) {
			System.out.println("Music not found");
		}
		
		

	}

	public void stopMusic() {
		if (this.music != null||this.chillMusic != null||this.bossMusic!=null||this.shoot!=null||this.stop!=null) {
			this.music.stop();
			this.chillMusic.stop();
			this.bossMusic.stop();
			this.shoot.stop();
			this.stop.stop();
		}
	}

	public void playMusic() {
//		this.music.setFramePosition(0);
//		this.stopMusic();
//		System.out.println(this.name);
//		this.music.loop(Clip.LOOP_CONTINUOUSLY);
		this.music.start();
	}
	public void playChillMusic() {
//		this.stopMusic();
//		this.chillMusic.start();
		this.chillMusic.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void playBossMusic() {
//		this.stopMusic();
		this.bossMusic.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void playShootMusic() {
		this.shoot.setFramePosition(0);
		this.stopMusic();
		System.out.print("aaaa");
		this.shoot.start();
	}
	public void play() {
		this.stopMusic();
//		this.shoot.loop(Clip.LOOP_CONTINUOUSLY);
		this.stop.start();
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
