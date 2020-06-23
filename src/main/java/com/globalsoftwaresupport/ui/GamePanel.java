package com.globalsoftwaresupport.ui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.globalsoftwaresupport.callbacks.GameEventListener;
import com.globalsoftwaresupport.constants.Constants;
import com.globalsoftwaresupport.image.Image;
import com.globalsoftwaresupport.image.ImageFactory;
import com.globalsoftwaresupport.model.Bomb;
import com.globalsoftwaresupport.model.EnemyShip;
import com.globalsoftwaresupport.model.Laser;
import com.globalsoftwaresupport.model.SpaceShip;
import com.globalsoftwaresupport.sound.SoundFactory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel {

	private List<EnemyShip> enemyShips;
	private List<Bomb> bombs;
	private SpaceShip spaceShip;
	private Laser laser;
	private int direction = -1;
	private int deaths = 0;
	private boolean inGame = true;
	private String message;
	private Timer timer;
	private ImageIcon backgroundImage;
	private Random generator;
	private SoundFactory soundFactory;
	private int score;
	private int shields = 3;

	public GamePanel() {
		initializeVariables();
		initializeBoard();
		initializeGame();
	}

	private void initializeVariables() {
		this.soundFactory = new SoundFactory();
		this.backgroundImage = ImageFactory.createImage(Image.BACKGROUND);
		this.generator = new Random();
		this.enemyShips = new ArrayList<>();
		this.bombs = new ArrayList<>();
		this.spaceShip = new SpaceShip();
		this.laser = new Laser();
	}

	private void initializeBoard() {
		setPreferredSize(new Dimension(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT));
		addKeyListener(new GameEventListener(this));
		setFocusable(true);
		
                // Questo è il game engine: ogni 10 millisecondi il metodo Gameloop actionPerformed() è chiamato
                // in questo modo teniamo aggiornate posizioni e refresh sul pannello di gioco
                // timer.start() inizia il timer per far iniziare banalmente il game
                
		timer = new Timer(Constants.GAME_SPEED, new GameLoop(this));
		timer.start();
	}

	private void initializeGame() {

		for (int i = 0; i < Constants.ENEMY_SHIPS_ROW; i++) {
			for (int j = 0; j < Constants.ENEMY_SHIPS_COLUMN; j++) {
				EnemyShip enemyShip = new EnemyShip(Constants.ENEMY_SHIP_INIT_X + 50 * j,
						Constants.ENEMY_SHIP_INIT_Y + 50 * i);
				enemyShips.add(enemyShip);
			}
		}
	}

	private void drawAliens(Graphics g) {

		for (EnemyShip enemyShip : enemyShips) {
			if (enemyShip.isVisible()) {
				g.drawImage(enemyShip.getImage(), enemyShip.getX(), enemyShip.getY(), this);
			}
		}
	}

	private void drawPlayer(Graphics g) {
		g.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), this);
	}

	private void drawShot(Graphics g) {

		if (!laser.isDead()) {
			g.drawImage(laser.getImage(), laser.getX(), laser.getY(), this);
		}
	}

	private void drawBombing(Graphics g) {

		for (Bomb b : this.bombs) {
			if (!b.isDead()) {
				g.drawImage(b.getImage(), b.getX(), b.getY(), this);
			}
		}
	}

	// mostra il valore delle vite che ci rimangono sottoforma di "scudi"
        // ovvero il numero di bombe che possono ancora colpirci prima di perdere
	private void drawScoreAndShield(Graphics g) {

		if (!inGame)
			return;

		Font font = new Font("Helvetica", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.GRAY);
		g.drawString("Punteggio: " + score, Constants.BOARD_WIDTH - 150, 50);
		g.drawString("Scudi: " + shields, 50, 50);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// disegna lo sfondo 
		g.drawImage(backgroundImage.getImage(), 0, 0, null);
		// disegna le componenti
		doDrawing(g);
	}

	private void doDrawing(Graphics g) {

		if (inGame) {
			drawScoreAndShield(g);
			drawAliens(g);
			drawPlayer(g);
			drawShot(g);
			drawBombing(g);
		} else {

			if (timer.isRunning()) {
				timer.stop();
			}

			gameOver(g);
		}

		Toolkit.getDefaultToolkit().sync();
	}

	private void gameOver(Graphics g) {

		g.drawImage(backgroundImage.getImage(), 0, 0, null);

		Font font = new Font("Helvetica", Font.BOLD, 50);
		FontMetrics fontMetrics = this.getFontMetrics(font);

		g.setColor(Color.white);
		g.setFont(font);
		g.drawString(message, (Constants.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2,
				Constants.BOARD_HEIGHT / 2 - 100);
	}

	private void update() {

		// Se la nave muore, il gioco finisce...mi dispiace
		if (spaceShip.isDead()) {
			// spaceShip.die();
			inGame = false;
			message = Constants.GAME_OVER;
		}

		// Se tutte le navi nemiche muoiono, il gioco finisce, urrà!
		if (deaths == enemyShips.size()) {
			inGame = false;
			message = Constants.WIN;
		}

		// giocatore 
		spaceShip.move();

		// se il laser è nello schermo, bisogna controllarne le collisioni (per istanziare poi la morte dei nemici)
		if (!laser.isDead()) {

			int shotX = laser.getX();
			int shotY = laser.getY();

			// controlla le collisioni dei nemici con il laser
			for (EnemyShip alien : enemyShips) {

				int alienX = alien.getX();
				int alienY = alien.getY();

				// restrizioni rispetto alla collisione
				if (alien.isVisible() && !laser.isDead()) {
					if (shotX >= (alienX) && shotX <= (alienX + Constants.ENEMY_SHIP_WIDTH) && shotY >= (alienY)
							&& shotY <= (alienY + Constants.ENEMY_SHIP_HEIGHT)) {
						// il risultato: sia il laser che il nemico muoiono (o meglio, il nemico diventa invisibile...)
						alien.setVisible(false);
						deaths++;
						score += 20;
						soundFactory.explosion();
						laser.die();
					}
				}
			}

			// movimento del laser
			laser.move();
		}

		// il movimento dei nemici, abbastanza complesso da realizzare
		for (EnemyShip enemyShip : enemyShips) {

			// và orizzontalmente e poi scende e cambia direzione
			if (enemyShip.getX() >= Constants.BOARD_WIDTH - 2 * Constants.BORDER_RIGHT && direction != -1
					|| enemyShip.getX() <= Constants.BORDER_LEFT && direction != 1) {

				direction *= -1;

				Iterator<EnemyShip> ufoIterator = enemyShips.iterator();

				while (ufoIterator.hasNext()) {
					EnemyShip ufo = ufoIterator.next();
					ufo.setY(ufo.getY() + Constants.GO_DOWN);
				}
			}
			
			// controlla se l'alieno tocca il giocatore
			// e muove i nemici ancora "visibili" verso di lui
			if (enemyShip.isVisible()) {

				// qui c'è la collisione tra nemico e spaceship
				if (enemyShip.getY() > Constants.GROUND - Constants.ENEMY_SHIP_HEIGHT) {
					enemyShip.die();
					inGame = false;
					message = "Invasione";
				}

				enemyShip.move(direction);
			}
		}

		// generatore di bombe
		for (EnemyShip enemy : enemyShips) {
			if (enemy.isVisible() && generator.nextDouble() < Constants.BOMB_DROPPING_PROBABILITY) {
				Bomb newBomb = new Bomb(enemy.getX(),enemy.getY());
				bombs.add(newBomb);
			}
		}

		// controlla la collisione tra la bomba ed il giocatore
		for (Bomb bomb : bombs) {

			int bombX = bomb.getX();
			int bombY = bomb.getY();
			int playerX = spaceShip.getX();
			int playerY = spaceShip.getY();

			if (!spaceShip.isDead() && !bomb.isDead()) {

				// condizioni della collisione
				if (bombX >= (playerX) && bombX <= (playerX + Constants.SPACESHIP_WIDTH) && bombY >= (playerY)
						&& bombY <= (playerY + Constants.SPACESHIP_HEIGHT)) {

					soundFactory.explosion();
					laser.die();
					shields--; //se finiscono gli scudi, abbiamo perso
					if (shields < 0)
						spaceShip.die();
					bomb.die();
				}
			}
			
			if (!bomb.isDead()) {
				bomb.move();
			}
		}
	}

	public void doOneLoop() {
		
                //fase 1: aggiorniamo la posizione di tutte le sprites (giocatore, nemici, bombe, laser)
		update();
		//fase 2: li disegniamo sul pannello di gioco
		repaint();
	}

	public void keyReleased(KeyEvent e) {
		spaceShip.keyReleased(e);
	}

	public void keyPressed(KeyEvent e) {

		spaceShip.keyPressed(e);

		int x = spaceShip.getX();
		int y = spaceShip.getY();

		int key = e.getKeyCode();

		// spariamo il laser quando viene premuto il tasto "space"
		if (key == KeyEvent.VK_SPACE) {

			if (inGame) {
				if (laser.isDead()) {
					soundFactory.laser();
					laser = new Laser(x, y);
				}
			}
		}
	}
}
