package graphics;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage[] isola;
	public static BufferedImage[] mare;
	public static BufferedImage[] ship_left;
	public static BufferedImage bullet;
	public static BufferedImage[] btn_start;
	public static BufferedImage[] btn_settings;
	public static BufferedImage[] btn_credits;
	public static BufferedImage[] btn_exit;
	public static BufferedImage[] btn_back;
	public static BufferedImage[] btn_records;
	public static BufferedImage[] bgMenu;
	public static BufferedImage[] cliff_top;
	public static BufferedImage[] cliff_bottom;
	public static BufferedImage[] cliff_right;
	public static BufferedImage[] cliff_left;
	public static BufferedImage[] cliff_angle1;
	public static BufferedImage[] cliff_angle2;
	public static BufferedImage[] cliff_angle3;
	public static BufferedImage[] cliff_angle4;
	public static BufferedImage[] enemy1;
	public static BufferedImage[] splash;
	public static BufferedImage healthContainer;
	public static BufferedImage compass, compassStick;
	public static BufferedImage keyW, keyS, key1, key2, key3;
	public static BufferedImage skillBar, bgState;
	public static BufferedImage healIcon, radarIcon, aoeIcon;
	public static BufferedImage minimapFrame;
	
	
	public static void init(){
		
		btn_back = new BufferedImage[2];
		ship_left = new BufferedImage[2];
		mare = new BufferedImage[1];
		isola = new BufferedImage[1];
		btn_start = new BufferedImage[2];
		btn_settings = new BufferedImage[2];
		btn_credits = new BufferedImage[2];
		btn_exit = new BufferedImage[2];
		btn_records = new BufferedImage[2];
		bgMenu = new BufferedImage[1];
		cliff_top = new BufferedImage[1];
		cliff_bottom = new BufferedImage[1];
		cliff_left = new BufferedImage[1];
		cliff_right = new BufferedImage[1];
		cliff_angle1 = new BufferedImage[1];
		cliff_angle2 = new BufferedImage[1];
		cliff_angle3 = new BufferedImage[1];
		cliff_angle4 = new BufferedImage[1];
		enemy1 = new BufferedImage[2];
		splash = new BufferedImage[6];
		
		bullet = ImageLoader.loadImage("/textures/Shell.png");
		
		bgState = ImageLoader.loadImage("/textures/StateBG.png");
		
		minimapFrame = ImageLoader.loadImage("/textures/MinimapFrame.png");
		
		skillBar = ImageLoader.loadImage("/textures/SkillBar.png");
		healIcon = ImageLoader.loadImage("/textures/HealIcon.png");
		radarIcon = ImageLoader.loadImage("/textures/RadarIcon.png");
		aoeIcon = ImageLoader.loadImage("/textures/AOEIcon.png");
		
		splash[0] = ImageLoader.loadImage("/textures/splash1.png");
		splash[1] = ImageLoader.loadImage("/textures/splash2.png");
		splash[2] = ImageLoader.loadImage("/textures/splash3.png");
		splash[3] = ImageLoader.loadImage("/textures/splash4.png");
		splash[4] = ImageLoader.loadImage("/textures/splash5.png");
		splash[5] = ImageLoader.loadImage("/textures/splash6.png");
		//splash[6] = ImageLoader.loadImage("/textures/splash7.png");
		
		keyW = ImageLoader.loadImage("/textures/KeyW.png");
		keyS = ImageLoader.loadImage("/textures/KeyS.png");
		key1 = ImageLoader.loadImage("/textures/Key1.png");
		key2 = ImageLoader.loadImage("/textures/Key2.png");
		key3 = ImageLoader.loadImage("/textures/Key3.png");
		
		healthContainer = ImageLoader.loadImage("/textures/HealthContainer.png");
		
		compass = ImageLoader.loadImage("/textures/Compass.png");
		compassStick = ImageLoader.loadImage("/textures/Stick.png");

		enemy1[0] = ImageLoader.loadImage("/textures/leftUp.png");
		enemy1[1] = ImageLoader.loadImage("/textures/leftDown.png");

		ship_left[0] = ImageLoader.loadImage("/textures/leftUp.png");
		ship_left[1] = ImageLoader.loadImage("/textures/leftDown.png");

		isola[0] = ImageLoader.loadImage("/textures/island.png");
		cliff_top[0] = ImageLoader.loadImage("/textures/Cliff.png");
		cliff_bottom[0] = ImageLoader.loadImage("/textures/CliffDown.png");
		cliff_left[0] = ImageLoader.loadImage("/textures/CliffLeft.png");
		cliff_right[0] = ImageLoader.loadImage("/textures/CliffRight.png");
		cliff_angle1[0] = ImageLoader.loadImage("/textures/CliffHighLeft.png");
		cliff_angle2[0] = ImageLoader.loadImage("/textures/CliffHighRight.png");
		cliff_angle3[0] = ImageLoader.loadImage("/textures/CliffBottomRight.png");
		cliff_angle4[0] = ImageLoader.loadImage("/textures/CliffBottomLeft.png");
		mare [0] = ImageLoader.loadImage("/textures/mare3.png");
		
		btn_start[0] = ImageLoader.loadImage("/textures/StartButtonNormal.png");
		btn_start[1] = ImageLoader.loadImage("/textures/StartButtonHovered.png");
		
		btn_settings[0] = ImageLoader.loadImage("/textures/SettingsButtonNormal.png");
		btn_settings[1] = ImageLoader.loadImage("/textures/SettingsButtonHovered.png");
		
		btn_credits[0] = ImageLoader.loadImage("/textures/CreditsButtonNormal.png");
		btn_credits[1] = ImageLoader.loadImage("/textures/CreditsButtonHovered.png");
		
		btn_exit[0] = ImageLoader.loadImage("/textures/ExitButtonNormal.png");
		btn_exit[1] = ImageLoader.loadImage("/textures/ExitButtonHovered.png");

		btn_back[1] = ImageLoader.loadImage("/textures/BackButtonHovered.png");
		btn_back[0] = ImageLoader.loadImage("/textures/BackButton.png");
		
		btn_records[0] = ImageLoader.loadImage("/textures/RecordsButtonNormal.png");
		btn_records[1] = ImageLoader.loadImage("/textures/RecordsButtonHovered.png");
		
		bgMenu[0] = ImageLoader.loadImage("/textures/BGMenu.png");
	}

}
