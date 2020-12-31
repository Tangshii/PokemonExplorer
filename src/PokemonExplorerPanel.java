import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PokemonExplorerPanel extends JPanel {
	// Variables
	private ImageIcon current, map, textBorder, pokemon;
	private ImageIcon right1, right2, left1, left2, up1, up2, down1, down2;
	private ImageIcon pichuR, pichuL, pichuU, pichuD;
	private ImageIcon pikachuR, pikachuL, pikachuU, pikachuD;
	private ImageIcon raichuR, raichuL, raichuU, raichuD;
	private ImageIcon bulbasaur, squirtle, charmander, ivysaur, wartortle, charmeleon, venusaur, blastoise, charizard,
			mewTwo;
	private int stage = 1, move = 15, lv = 1;
	private int x = 870, y = 220;
	private int px = x, py = y;
	private int bx = -100, by = -100;
	int bulbasaurLv = 1, squirtleLv = 2, charmanderLv = 3, ivysaurLv = 4, wartortleLv = 9, charmeleonLv = 14,
			venusaurLv = 19, blastoiseLv = 34, charizardLv = 49, mewTwoLv = 65;
	JLabel label, text, currentLv;
	boolean battling, evolve1, evolve2;

	// Constructor
	public PokemonExplorerPanel() {
		setLayout(null);
		addKeyListener(new DirectionListener());
		setPreferredSize(new Dimension(1030, 700));

		String imgFolder = "src/PokemonExplorerImages/";

		map = new ImageIcon(imgFolder + "map.png");
		textBorder = new ImageIcon(imgFolder + "textBorder.png");

		// Initializes trainer motion images
		right1 = new ImageIcon(imgFolder + "right1.png");
		right2 = new ImageIcon(imgFolder + "right2.png");
		left1 = new ImageIcon(imgFolder + "left1.png");
		left2 = new ImageIcon(imgFolder + "left2.png");
		up1 = new ImageIcon(imgFolder + "up1.png");
		up2 = new ImageIcon(imgFolder + "up2.png");
		down1 = new ImageIcon(imgFolder + "down1.png");
		down2 = new ImageIcon(imgFolder + "down2.png");

		// Initializes pokemon Pichu motion images
		pichuR = new ImageIcon(imgFolder + "pichuR.png");
		pichuL = new ImageIcon(imgFolder + "pichuL.png");
		pichuU = new ImageIcon(imgFolder + "pichuU.png");
		pichuD = new ImageIcon(imgFolder + "pichuD.png");

		// Initializes pokemon Pikachu motion images
		pikachuR = new ImageIcon(imgFolder + "pikachuR.png");
		pikachuL = new ImageIcon(imgFolder + "pikachuL.png");
		pikachuU = new ImageIcon(imgFolder + "pikachuU.png");
		pikachuD = new ImageIcon(imgFolder + "pikachuD.png");

		// Initializes pokemon Raichu motion images
		raichuR = new ImageIcon(imgFolder + "raichuR.png");
		raichuL = new ImageIcon(imgFolder + "raichuL.png");
		raichuU = new ImageIcon(imgFolder + "raichuU.png");
		raichuD = new ImageIcon(imgFolder + "raichuD.png");

		pokemon = new ImageIcon(imgFolder + "pichuD.png");

		current = down1;

		// Initializes enemy pokemon motion images
		bulbasaur = new ImageIcon(imgFolder + "bulbasaur.png");
		squirtle = new ImageIcon(imgFolder + "squirtle.png");
		charmander = new ImageIcon(imgFolder + "charmander.png");

		ivysaur = new ImageIcon(imgFolder + "ivysaur.png");
		wartortle = new ImageIcon(imgFolder + "wartortle.png");
		charmeleon = new ImageIcon(imgFolder + "charmeleon.png");

		venusaur = new ImageIcon(imgFolder + "venusaur.png");
		blastoise = new ImageIcon(imgFolder + "blastoise.png");
		charizard = new ImageIcon(imgFolder + "charizard.png");

		mewTwo = new ImageIcon(imgFolder + "mewTwo.png");

		// label for coordinates
		label = new JLabel();
		label.setBounds(0, 0, 300, 50);
		add(label);
		label.setFont(new Font("Times New", Font.BOLD, 40));
		label.setForeground(Color.white);

		// label for game text
		text = new JLabel("Welcome to the world of POKEMON!!");
		text.setBounds(25, 600, 425, 100);
		text.setFont(new Font("Times New", Font.BOLD, 20));
		add(text);

		// label shows your current level
		currentLv = new JLabel("Level: " + lv);
		currentLv.setBounds(25, 515, 500, 200);
		add(currentLv);
		currentLv.setFont(new Font("Times New", Font.BOLD, 30));
		currentLv.setBackground(Color.black);
		currentLv.setForeground(Color.black);

		setFocusable(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		map.paintIcon(this, g, 0, 0);
		// paints your pokemon
		pokemon.paintIcon(this, g, px + 10, py - 55);
		// paints trainer
		current.paintIcon(this, g, x, y);

		// paints enemies
		bulbasaur.paintIcon(this, g, 740, 240);
		squirtle.paintIcon(this, g, 520, 180);
		charmander.paintIcon(this, g, 830, 60);

		ivysaur.paintIcon(this, g, 230, 180);
		wartortle.paintIcon(this, g, 350, 400);
		charmeleon.paintIcon(this, g, 450, 60);

		venusaur.paintIcon(this, g, 110, 295);
		blastoise.paintIcon(this, g, 540, 330);
		charizard.paintIcon(this, g, 600, 495);

		mewTwo.paintIcon(this, g, bx, by);

		textBorder.paintIcon(this, g, 0, 570);
	}

	// Key events and controls
	private class DirectionListener implements KeyListener {
		public void keyPressed(KeyEvent event) {
			switch (event.getKeyCode()) {
			case KeyEvent.VK_UP:
				y -= move;
				px = x - 5;
				py = y + 120;
				// changes trainer walking image
				if (y % 2 == 1)
					current = up1;
				if (y % 2 == 0)
					current = up2;
				// checks evolution
				if (stage == 1)
					pokemon = pichuU;
				if (stage == 2)
					pokemon = pikachuU;
				if (stage == 3) {
					pokemon = raichuU;
					px = x - 10;
					py = y + 110;
				}
				label.setText("x:" + x + " " + "y:" + y);
				battle();
				break;

			case KeyEvent.VK_DOWN:
				y += move;
				px = x - 5;
				py = y;
				if (y % 2 == 1)
					current = down1;
				if (y % 2 == 0)
					current = down2;

				if (stage == 1)
					pokemon = pichuD;
				if (stage == 2)
					pokemon = pikachuD;
				if (stage == 3) {
					pokemon = raichuD;
					px = x - 33;
					py = y - 10;

				}
				label.setText("x:" + x + " " + "y:" + y);
				battle();
				break;

			case KeyEvent.VK_LEFT:
				x -= move;
				px = x + 55;
				py = y + 65;
				if (x % 2 == 1)
					current = left1;
				if (x % 2 == 0)
					current = left2;

				if (stage == 1)
					pokemon = pichuL;
				if (stage == 2)
					pokemon = pikachuL;
				if (stage == 3) {
					pokemon = raichuL;

					py = y + 60;
				}
				label.setText("x:" + x + " " + "y:" + y);
				battle();
				break;

			case KeyEvent.VK_RIGHT:
				x += move;
				px = x - 65;
				py = y + 65;
				if (x % 2 == 1)
					current = right1;
				if (x % 2 == 0)
					current = right2;

				if (stage == 1)
					pokemon = pichuR;
				if (stage == 2)
					pokemon = pikachuR;
				if (stage == 3) {
					pokemon = raichuR;
					px = x - 100;
					py = y + 60;
				}
				label.setText("x:" + x + " " + "y:" + y);
				battle();
				break;

			// cheat codes
			case KeyEvent.VK_1:
				stage = 1;
				break;
			case KeyEvent.VK_2:
				stage = 2;
				break;
			case KeyEvent.VK_3:
				stage = 3;
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

		public void keyTyped(KeyEvent event) {
		}

		public void keyReleased(KeyEvent event) {
		}
	}

	// Checks of trainer is near an enemy and starts battle
	public void battle() {

		if (x > 750 && x < 825 && y > 190 && y < 265) {
			move = 0;
			text.setText("Bulbasaur LV 1, press space to BATTLE");
			battling = true;
		}

		if (x > 525 && x < 600 && y > 130 && y < 190) {
			move = 0;
			text.setText("Squirtle LV 2, press space to BATTLE");
			battling = true;
		}
		if (x > 750 && x < 795 && y > 10 && y < 85) {
			move = 0;
			text.setText("Charmander LV 3, press space to BATTLE");
			battling = true;
		}

		if (x > 255 && x < 330 && y > 130 && y < 190) {
			move = 0;
			text.setText("Ivysaur LV 4, press space to BATTLE");
			battling = true;
		}

		if (x > 270 && x < 345 && y > 370 && y < 430) {
			move = 0;
			text.setText("Wartortle LV 9, press space to BATTLE");
			battling = true;
		}

		if (x > 465 && x < 540 && y > 25 && y < 85) {
			move = 0;
			text.setText("Charmeleon LV 14, press space to BATTLE");
			battling = true;
		}

		if (x > 150 && x < 210 && y > 265 && y < 325) {
			move = 0;
			text.setText("Venusaur LV 19, press space to BATTLE");
			battling = true;
		}

		if (x > 450 && x < 525 && y > 295 && y < 355) {
			move = 0;
			text.setText("Blastoise LV 34, press space to BATTLE");
			battling = true;
		}

		if (x > 510 && x < 600 && y > 475 && y < 535) {
			move = 0;
			text.setText("Charizard LV 49, press space to BATTLE");
			battling = true;
		}

		if (x > 870 && x < 960 && y > 445 && y < 565) {
			move = 0;
			text.setText("MewTwo LV 65, FINAL BOSS!!");
			battling = true;
		}

	}

	// Checks if you win or lose
	public void fight() {
		if (bulbasaurLv <= lv && x > 750 && x < 825 && y > 190 && y < 265) {
			move = 15;
			text.setText("You Win!");
			battling = false;
			y = y - 90;
			lv++;
			currentLv.setText("Level: " + lv);
			evolve();
		}

		if (squirtleLv <= lv && x > 525 && x < 600 && y > 130 && y < 190) {
			move = 15;
			text.setText("You Win!");
			battling = false;
			x = x + 75;
			lv++;
			currentLv.setText("Level: " + lv);
			evolve();
		}
		if (squirtleLv > lv && x > 525 && x < 600 && y > 130 && y < 190) {
			move = 15;
			text.setText("You LOSE!");
			battling = false;
			x = x + 75;
		}

		if (charmanderLv <= lv && x > 750 && x < 820 && y > 20 && y < 100) {
			move = 15;
			text.setText("You WIN!");
			battling = false;
			y = y + 90;
			lv++;
			currentLv.setText("Level: " + lv);
			evolve();
		}
		if (charmanderLv > lv && x > 750 && x < 795 && y > 10 && y < 85) {
			move = 15;
			text.setText("You LOSE!");
			battling = false;
			y = y + 90;
		}

		if (ivysaurLv <= lv && x > 255 && x < 330 && y > 130 && y < 190) {
			move = 15;
			text.setText("You WIN!");
			battling = false;
			y = y + 90;
			lv += 5;
			currentLv.setText("Level: " + lv);
			evolve();
		}
		if (ivysaurLv > lv && x > 255 && x < 330 && y > 130 && y < 190) {
			move = 15;
			text.setText("You LOSE!");
			battling = false;
			y = y + 90;
		}

		if (wartortleLv > lv && x > 270 && x < 345 && y > 370 && y < 430) {
			move = 15;
			text.setText("You LOSE!");
			battling = false;
			y = y - 90;
		}
		if (wartortleLv <= lv && x > 270 && x < 345 && y > 370 && y < 430) {
			move = 15;
			text.setText("You WIN!");
			battling = false;
			y = y - 90;
			lv += 5;
			currentLv.setText("Level: " + lv);
			evolve();
		}

		if (charmeleonLv > lv && x > 465 && x < 540 && y > 25 && y < 85) {
			move = 15;
			text.setText("You LOSE!");
			battling = false;
			x = x + 90;
		}
		if (charmeleonLv <= lv && x > 465 && x < 540 && y > 25 && y < 85) {
			move = 15;
			text.setText("You WIN!");
			battling = false;
			x = x + 90;
			lv += 5;
			currentLv.setText("Level: " + lv);
			evolve();
		}

		if (venusaurLv > lv && x > 150 && x < 210 && y > 265 && y < 325) {
			move = 15;
			text.setText("You LOSE!");
			battling = false;
			x = x + 90;
		}
		if (venusaurLv <= lv && x > 150 && x < 210 && y > 265 && y < 325) {
			move = 15;
			text.setText("You WIN!");
			battling = false;
			x = x + 90;
			lv += 15;
			currentLv.setText("Level: " + lv);
			evolve();
		}

		if (blastoiseLv <= lv && x > 450 && x < 525 && y > 295 && y < 355) {
			move = 15;
			text.setText("You WIN!");
			battling = false;
			x = x - 90;
			lv += 15;
			currentLv.setText("Level: " + lv);
			evolve();
		}
		if (blastoiseLv > lv && x > 450 && x < 525 && y > 295 && y < 355) {
			move = 15;
			text.setText("You LOSE!");
			battling = false;
			x = x - 90;
		}

		if (charizardLv <= lv && x > 510 && x < 600 && y > 475 && y < 535) {
			move = 15;
			text.setText("You WIN!");
			battling = false;
			y = y - 90;
			lv += 15;
			currentLv.setText("Level: " + lv);
			evolve();
			bx = 900;
			by = 400;
		}
		if (charizardLv > lv && x > 510 && x < 600 && y > 475 && y < 535) {
			move = 15;
			text.setText("You LOSE!");
			battling = false;
			y = y - 90;
		}

		if (mewTwoLv <= lv && x > 870 && x < 960 && y > 445 && y < 565) {
			move = 15;
			text.setText("You beat the game CONGRATS!!!");
			battling = false;
			x = x - 100;
			lv += 30;
			currentLv.setText("Level: " + lv);
			evolve();
		}
		if (mewTwoLv > lv && x > 870 && x < 960 && y > 445 && y < 565) {
			move = 15;
			text.setText("You LOSE!");
			battling = false;
			x = x - 100;
		}
	}

	// Checks if pokemon can evolve
	public void evolve() {
		if (lv > 3 && evolve1 == false) {
			stage = 2;
			pokemon = pikachuD;
			evolve1 = true;
			text.setText("You WIN and Pichu EVOLVED to Pikachu!!");
		}
		if (lv > 14 && evolve2 == false) {
			stage = 3;
			pokemon = raichuD;
			evolve2 = true;
			text.setText("You WIN and Pikachu EVOLVED to Raichu!!");
		}
	}
}
// pokemon sprites from https://www.spriters-resource.com/ds_dsi/pokemonheartgoldsoulsilver/
// trainer sprite form https://www.deviantart.com/pkmntrainerspriterc/art/Sprite-Request-TheGoku7729-Hoenn-Ash-618721570
// map from https://www.serebii.net/pokearth/kanto/safarizone.shtml