import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PokemonExplorerPanel extends JPanel{
	// Variables
	private ImageIcon current, map, textBorder, pokemon;
	private ImageIcon right1, right2, left1, left2, up1, up2 ,down1, down2;
	private ImageIcon pichuR, pichuL, pichuU, pichuD;
	private ImageIcon pikachuR, pikachuL, pikachuU, pikachuD;
	private ImageIcon raichuR, raichuL, raichuU, raichuD;
	private ImageIcon bulbasaur, squirtle, charmander, 
		ivysaur, wartortle, charmeleon, 
		venusaur, blastoise, charizard, mewTwo;
	private int stage = 1, move = 15, lv = 1;
	private int x = 1250, y =325;
	private int px = x, py = y;
	private int bx= -100, by= -100;
	int bulbasaurLv = 1, squirtleLv = 2, charmanderLv = 3, 
		ivysaurLv = 4, wartortleLv = 9, charmeleonLv = 14, 
		venusaurLv = 19, blastoiseLv = 34, charizardLv = 49, mewTwoLv = 65;
	JLabel label, text, currentLv; 
	boolean battling, evolve1, evolve2;
	
	// Constructor 
	public PokemonExplorerPanel() {
		setLayout(null);
		addKeyListener(new DirectionListener());
		setPreferredSize(new Dimension(1500,1000));
	
		map = new ImageIcon("map.png");
		textBorder = new ImageIcon("textBorder.png");
		
		/* sorry about all the 33 images, I realized using BufferedImages would 
		 * allow all the image to be on one sprite sheet, but I never got it to work
		 */
		// Initializes trainer motion images
		right1 = new ImageIcon("right1.png");
		right2 = new ImageIcon("right2.png");
		left1 = new ImageIcon("left1.png");
		left2 = new ImageIcon("left2.png");
		up1 = new ImageIcon("up1.png");
		up2 = new ImageIcon("up2.png");
		down1 = new ImageIcon("down1.png");
		down2 = new ImageIcon("down2.png");
		
		// Initializes pokemon Pichu motion images
		pichuR = new ImageIcon("pichuR.png");
		pichuL = new ImageIcon("pichuL.png");
		pichuU = new ImageIcon("pichuU.png");
		pichuD = new ImageIcon("pichuD.png");
		
		// Initializes pokemon Pikachu motion images
		pikachuR = new ImageIcon("pikachuR.png");
		pikachuL = new ImageIcon("pikachuL.png");
		pikachuU = new ImageIcon("pikachuU.png");
		pikachuD = new ImageIcon("pikachuD.png");
		
		// Initializes pokemon Raichu motion images
		raichuR = new ImageIcon("raichuR.png");
		raichuL = new ImageIcon("raichuL.png");
		raichuU = new ImageIcon("raichuU.png");
		raichuD = new ImageIcon("raichuD.png");
		
		pokemon = new ImageIcon("pichuD.png");
	
		current = down1;
		
		// Initializes enemy pokemon motion images
		bulbasaur = new ImageIcon("bulbasaur.png");
		squirtle = new ImageIcon("squirtle.png");
		charmander = new ImageIcon("charmander.png");
		
		ivysaur =new ImageIcon("ivysaur.png");
		wartortle = new ImageIcon("wartortle.png");
		charmeleon = new ImageIcon("charmeleon.png");
		
		venusaur = new ImageIcon("venusaur.png");
		blastoise = new ImageIcon("blastoise.png");
		charizard = new ImageIcon("charizard.png");
		
		mewTwo = new ImageIcon("mewTwo.png");
		
		// label for coordinates 
		label = new JLabel();
		label.setBounds(0, 0, 300, 50);
		add(label);
		label.setFont(new Font("Times New", Font.BOLD, 40));
		label.setForeground(Color.white);
		
		// label for game text
		text = new JLabel("Welcome to the world of POKEMON!!");
		text.setBounds(25, 800, 700, 100);
		text.setFont(new Font("Times New", Font.BOLD, 30));
		add(text);
		
		// label shows your current level
		currentLv = new JLabel("Level: " + lv);
		currentLv.setBounds(0, 650, 500, 200);
		add(currentLv);
		currentLv.setFont(new Font("Times New", Font.BOLD, 80));
		currentLv.setForeground(Color.black);
		
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		map.paintIcon(this, g, 0, 0);
		//paints your pokemon
		pokemon.paintIcon(this, g, px+10, py -55);
		// paints trainer 
		current.paintIcon(this, g, x, y);
		
		// paints enemies
		bulbasaur.paintIcon(this, g, 1085, 350);
		squirtle.paintIcon(this, g, 800, 275);
		charmander.paintIcon(this, g, 1200, 90);
		
		ivysaur.paintIcon(this, g, 300, 225);
		wartortle.paintIcon(this, g, 525, 600);
		charmeleon.paintIcon(this, g, 650,100);
		
		venusaur.paintIcon(this, g, 165,450);
		blastoise.paintIcon(this, g, 800,490);
		charizard.paintIcon(this, g, 900,735);
		
		mewTwo.paintIcon(this, g, bx,by);
		
		textBorder.paintIcon(this, g, 0, 790);
	}
	
	// Key events and controls
	private class DirectionListener implements KeyListener
	{
		public void keyPressed(KeyEvent event)
		{
			switch (event.getKeyCode())
			{
			case KeyEvent.VK_UP:
				y-=move;
				px = x;
				py = y+150;	
				// changes trainer walking image
				if(y%2==1)
					current = up1;
				if(y%2==0)
					current = up2;
				// checks evolution 
				if (stage == 1)
					pokemon = pichuU;
				if (stage == 2)
					pokemon = pikachuU;
				if (stage == 3) {
					pokemon = raichuU;
					px = x-5;
					py = y+135;
				}
				label.setText("x:" + x + " "+ "y:" + y);
				battle();
				break;
			
			case KeyEvent.VK_DOWN:
				y+=move;
				px = x;
				py = y-5;
				if(y%2==1)
					current = down1;
				if(y%2==0)
					current = down2;
				
				if (stage == 1)
					pokemon = pichuD;
				if (stage == 2)
					pokemon = pikachuD;
				if (stage == 3) {
					pokemon = raichuD;
					px = x-30;
					py = y-15;
				}
				label.setText("x:" + x + " "+ "y:" + y);
				battle();
				break;
				
			case KeyEvent.VK_LEFT:
				x-=move;
				px = x+75;
				py = y+75;
				if(x%2==1)
					current = left1;
				if(x%2==0)
					current = left2;
				
				if (stage == 1)
					pokemon = pichuL;
				if (stage == 2)
					pokemon = pikachuL;
				if (stage == 3) {
					pokemon = raichuL;
					px = x+75;
					py = y+65;
				}
				label.setText("x:" + x + " "+ "y:" + y);
				battle();
				break;
				
			case KeyEvent.VK_RIGHT:
				x+=move;
				px = x-75;
				py = y+75;
				if(x%2==1)
					current = right1;
				if(x%2==0)
					current = right2;
				
				if (stage == 1)
					pokemon = pichuR;
				if (stage == 2)
					pokemon = pikachuR;
				if (stage == 3) {
					pokemon = raichuR;
					px = x-115;
					py = y+65;
				}
				label.setText("x:" + x + " "+ "y:" + y);
				battle();
				break;
			
			// cheat codes	
			case KeyEvent.VK_1:
				stage=1;
				break;
			case KeyEvent.VK_2:
				stage=2;
				break;
			case KeyEvent.VK_3:
				stage=3;
				break;
			case KeyEvent.VK_4:
				lv++;
				break;
				
			// Battle key
			case KeyEvent.VK_SPACE:
				if (battling)
					fight();
				break;
			}
			battle();
			repaint();
		}
	public void keyTyped(KeyEvent event) {}
	public void keyReleased(KeyEvent event) {}
	}
	 
	
	// Checks of trainer is near an enemy and starts battle
	public void battle() {
		 if(x > 1100 && x < 1200 && y > 250 && y < 350) {
			 move = 0;
			 text.setText("Bulbasaur LV 1, press space to BATTLE");
			 battling = true;
		 }
		 if(x > 800 && x <900 && y > 220 && y <320) {
			 move = 0;
			 text.setText("Squirtle LV 2, press space to BATTLE");
			 battling = true;
		 }
		 if(x > 1100 && x <1200 && y > 10 && y <110) {
			 move = 0;
			 text.setText("Charmander LV 3, press space to BATTLE");
			 battling = true;
		 }
		 
		 if(x > 300 && x < 400 && y > 175 && y < 275) {
			 move = 0;
			 text.setText("Ivysaur LV 4, press space to BATTLE");
			 battling = true;
		 }
		 
		 if(x > 425 && x < 525 && y > 550 && y < 650) {
			 move = 0;
			 text.setText("Wartortle LV 9, press space to BATTLE");
			 battling = true;
		 }
		 
		 
		 if(x > 650 && x < 750 && y > 50 && y < 150) {
			 move = 0;
			 text.setText("Charmeleon LV 14, press space to BATTLE");
			 battling = true;
		 }
		 
		 if(x > 200 && x < 300 && y > 400 && y < 500) {
			 move = 0;
			 text.setText("Venusaur LV 19, press space to BATTLE");
			 battling = true;
		 }
		 
		 if(x > 700 && x < 800 && y > 450 && y < 550) {
			 move = 0;
			 text.setText("Blastoise LV 34, press space to BATTLE");
			 battling = true;
		 }
		 
		 if(x > 800 && x < 900 && y > 685 && y < 785) {
			 move = 0;
			 text.setText("Charizard LV 49, press space to BATTLE");
			 battling = true;
		 }
		 
		 if(x > 1325 && x < 1375 && y > 625 && y < 725) {
			 move = 0;
			 text.setText("MewTwo LV 65, FINAL BOSS!!");
			 battling = true;
		 }
		 
		 
	 }
	 
	//  Checks if you win or lose
	 public void fight() {
		 if (bulbasaurLv <= lv && x > 1100 && x < 1200 && y > 250 && y < 350) {
			 move= 15;
			 text.setText("You Win!");
			 battling = false;
			 y=y-100;
			 lv++;
			 currentLv.setText("Level: " + lv); 
			 evolve();
			} 
		 
		 if (squirtleLv <= lv && x > 800 && x <900 && y > 220 && y <320 ) {
			 move= 15;
			 text.setText("You Win!");
			 battling = false;
			 y=y-100;
			 lv++;
			 currentLv.setText("Level: " + lv); 
			 evolve();
			} 
		 if (squirtleLv > lv && x > 800 && x <900 && y > 220 && y <320 ) {
			 move= 15;
			 text.setText("You LOSE!");
			 battling = false;
			 y=y-100;
		 }
		 
		 if (charmanderLv <= lv && x > 1100 && x <1200 && y > 10 && y <110 ) {
			 move= 15;
			 text.setText("You WIN!");
			 battling = false;
			 y=y+100;
			 lv++;
			 currentLv.setText("Level: " + lv); 
			 evolve();
		 }
		 if (charmanderLv > lv && x > 1100 && x <1200 && y > 10 && y <110 ) {
			 move= 15;
			 text.setText("You LOSE!");
			 battling = false;
			 y=y+100;
		 }
		 
		 if (ivysaurLv <= lv && x > 300 && x < 400 && y > 175 && y < 275) {
			 move= 15;
			 text.setText("You WIN!");
			 battling = false;
			 y=y+100;
			 lv+=5;
			 currentLv.setText("Level: " + lv); 
			 evolve();
		 }
		 if (ivysaurLv > lv && x > 300 && x < 400 && y > 175 && y < 275) {
			 move= 15;
			 text.setText("You LOSE!");
			 battling = false;
			 y=y+100;
		 }
		 
		 
		 if (wartortleLv > lv &&x > 425 && x < 525 && y > 550 && y < 650) {
			 move= 15;
			 text.setText("You LOSE!");
			 battling = false;
			 y=y-100;
		 }
		 if (wartortleLv <= lv && x > 425 && x < 525 && y > 550 && y < 650) {
			 move= 15;
			 text.setText("You WIN!");
			 battling = false;
			 y=y-100;
			 lv+=5;
			 currentLv.setText("Level: " + lv); 
			 evolve();
		 }
		 
		 if (charmeleonLv > lv && x > 650 && x < 750 && y > 50 && y < 150) {
			 move= 15;
			 text.setText("You LOSE!");
			 battling = false;
			 y=y+100;
		 }
		 if (charmeleonLv <= lv && x > 650 && x < 750 && y > 50 && y < 150) {
			 move= 15;
			 text.setText("You WIN!");
			 battling = false;
			 y=y+100;
			 lv+=5;
			 currentLv.setText("Level: " + lv); 
			 evolve();
		 }
		 
		 if (venusaurLv > lv && x > 200 && x < 300 && y > 400 && y < 500) {
			 move= 15;
			 text.setText("You LOSE!");
			 battling = false;
			 y=y+100;
		 }
		 if (venusaurLv <= lv && x > 200 && x < 300 && y > 400 && y < 500) {
			 move= 15;
			 text.setText("You WIN!");
			 battling = false;
			 y=y+100;
			 lv+=15;
			 currentLv.setText("Level: " + lv); 
			 evolve();
		 }
		 
		 if (blastoiseLv <= lv && x > 700 && x < 800 && y > 450 && y < 550) {
			 move= 15;
			 text.setText("You WIN!");
			 battling = false;
			 x=x-100;
			 lv+=15;
			 currentLv.setText("Level: " + lv); 
			 evolve();
		 }
		 if (blastoiseLv > lv && x > 700 && x < 800 && y > 450 && y < 550) {
			 move= 15;
			 text.setText("You LOSE!");
			 battling = false;
			 x=x-100;
		 }
		
		 if (charizardLv <= lv && x > 800 && x < 900 && y > 685 && y < 785) {
			 move= 15;
			 text.setText("You WIN!");
			 battling = false;
			 x=x-100;
			 lv+=15;
			 currentLv.setText("Level: " + lv); 
			 evolve();
			 bx=1325;
			 by=575;
		 }
		 if (charizardLv > lv && x > 800 && x < 900 && y > 685 && y < 785) {
			 move= 15;
			 text.setText("You LOSE!");
			 battling = false;
			 x=x-100;
		 }
		 
		 if (mewTwoLv <= lv && x > 1325 && x < 1375 && y > 625 && y < 725) {
			 move= 15;
			 text.setText("You beat the game CONGRATS!!!");
			 battling = false;
			 y=y+100;
			 lv+=30;
			 currentLv.setText("Level: " + lv); 
			 evolve();
		 }
		 if (mewTwoLv > lv && x > 1325 && x < 1375 && y > 625 && y < 725) {
			 move= 15;
			 text.setText("You LOSE!");
			 battling = false;
			 y=y+100;
		 }
	 }
	 
	 // Checks if pokemon can evolve
	 public void evolve() {
		 if (lv > 3 && evolve1==false) {
			 stage = 2;
			 pokemon = pikachuD;
			 evolve1=true;
			 text.setText("You WIN and Pichu EVOLVED to Pikachu!!");
		 }
		 if (lv > 14 && evolve2==false) {
			 stage = 3;
			 pokemon = raichuD;
			 evolve2=true;
			 text.setText("You WIN and Pikachu EVOLVED to Raichu!!");
		 }
	 }
}
// pokemon sprites from https://www.spriters-resource.com/ds_dsi/pokemonheartgoldsoulsilver/
// trainer sprite form https://www.deviantart.com/pkmntrainerspriterc/art/Sprite-Request-TheGoku7729-Hoenn-Ash-618721570
// map from https://www.serebii.net/pokearth/kanto/safarizone.shtml