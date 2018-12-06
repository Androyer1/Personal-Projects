package com.myfirstgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen implements Screen, Initialize{

	final DesertBattle game;
	private SpriteBatch batch;
	private static final int FRAME_COLS_T = 1, FRAME_ROWS_T = 2;
	private static final int FRAME_COLS_F = 3, FRAME_ROWS_F = 1;
	private static int TShotNum = 0;
	private static int FShotNum = 0;
	private static int THealth = 3;
	private static int FHealth = 3;
	private static float positionxT = 320;
	private static float positionyT = 180;
	private static float positionxF = 960;
	private static float positionyF = 540;
	private static float positionTShotx = positionxT;
	private static float positionTShoty = positionyT;
	private static float positionFShotx = positionxF;
	private static float positionFShoty = positionyF;
	private static boolean TShot = false;
	private static boolean FShot = false;
	private static boolean Game = true;
	private float elapsedTime;
	private String TigreText;
	private String FlibbetsText;
	private Animation TigreAnimation;
	private Animation FlibbetAnimation;
	private Texture tempTexture;
	private Texture MapTexture;
	private Texture TigreTexture;
	private Texture FlibbetTexture;
	private Texture TigreShotTexture;
	private Texture FlibbetShotTexture;
	private Texture THealth3Texture;
	private Texture THealth2Texture;
	private Texture THealth1Texture;
	private Texture FHealth3Texture;
	private Texture FHealth2Texture;
	private Texture FHealth1Texture;
	private Texture NoHealthTexture;
	private Sprite tempSprite;
	private Sprite Map;
	private Sprite TigreShot;
	private Sprite FlibbetShot;
	private Sprite THealth3;
	private Sprite THealth2;
	private Sprite THealth1;
	private Sprite FHealth3;
	private Sprite FHealth2;
	private Sprite FHealth1;
	private Sprite NoHealth;
	private Rectangle Tigre;
	private Rectangle Flibbet;
	private Rectangle TShotRectangle;
	private Rectangle FShotRectangle;
	private BitmapFont tempFont;
	private BitmapFont TFont;
	private BitmapFont FFont;

	public GameScreen(final DesertBattle game) {
		this.game = game;
		
			//loads map
		MapTexture = newTexture("Map_Game_2.PNG");
		Map = newSprite(MapTexture);
		Map.setPosition(0, 0);
		
			//loads fonts
		TFont = newFont("TigreFont.fnt");
		FFont = newFont("FlibbetsFont.fnt");
		
			//creates text used for names
		TigreText = "KITSUNO TIGRE";
		FlibbetsText = "FROBISHER FLIBBETS";
		
			//creates character textures
		TigreTexture = newTexture("SpriteSheet_KitsunoTigre.png");
		FlibbetTexture = newTexture("SpriteSheet_FrobisherFlibbet.png");
		
			//creates projectile textures
		TigreShotTexture = newTexture("Red_Skill_Shot_4.png");
		FlibbetShotTexture = newTexture("Blue_Skill_Shot_4.png");
		
			//creates health bar textures
		THealth3Texture = newTexture("Tigre_FullHP.png");
		THealth2Texture = newTexture("Tigre_2hp.png");
		THealth1Texture = newTexture("Tigre_1hp.png");

		FHealth3Texture = newTexture("Frog_Full_Hp_1.png");
		FHealth2Texture = newTexture("Frog_2hp.png");
		FHealth1Texture = newTexture("Frog_1hp.png");

		NoHealthTexture = newTexture("Dead.png");
		
			//creates projectile sprites
		TigreShot = newSprite(TigreShotTexture);
		FlibbetShot = newSprite(FlibbetShotTexture);
		
			//creates heal bar sprites
		THealth3 = newSprite(THealth3Texture);
		THealth2 = newSprite(THealth2Texture);
		THealth1 = newSprite(THealth1Texture);

		FHealth3 = newSprite(FHealth3Texture);
		FHealth2 = newSprite(FHealth2Texture);
		FHealth1 = newSprite(FHealth1Texture);

		NoHealth = newSprite(NoHealthTexture);
		
			//creates first location of projectile (to be relocated later)
		TigreShot.setPosition(positionxT, positionyT);
		FlibbetShot.setPosition(positionxF, positionyF);
		
			//creates location of health bars
		THealth3.setPosition(110, 630);
		THealth2.setPosition(110, 630);
		THealth1.setPosition(110, 630);

		FHealth3.setPosition(1277, 630);
		FHealth2.setPosition(1277, 630);
		FHealth1.setPosition(1277, 630);
		
			//creates a texture region for each of the characters
		TextureRegion[][] TigreRegion = TextureRegion.split(TigreTexture, TigreTexture.getWidth() / FRAME_COLS_T,
				TigreTexture.getHeight() / FRAME_ROWS_T);
		TextureRegion[][] FlibbetRegion = TextureRegion.split(FlibbetTexture, FlibbetTexture.getWidth() / FRAME_COLS_F,
				FlibbetTexture.getHeight() / FRAME_ROWS_F);

		TextureRegion[] TigreFrames = new TextureRegion[FRAME_COLS_T * FRAME_ROWS_T];
		TextureRegion[] FlibbetFrames = new TextureRegion[FRAME_COLS_F * FRAME_ROWS_F];
		
			//creates the seperate frames used for animation
		int indexTigre = 0;
		int indexFrog = 0;
		int index = 0;
		while (index < FRAME_ROWS_T) {
			for (int j = 0; j < FRAME_COLS_T; j++) {
				TigreFrames[indexTigre++] = TigreRegion[index][j];
			}
			index++;
		}
		for (int i = 0; i < FRAME_ROWS_F; i++) {
			for (int j = 0; j < FRAME_COLS_F; j++) {
				FlibbetFrames[indexFrog++] = FlibbetRegion[i][j];
			}
		}
		
			//creates the animation of the characters
		TigreAnimation = new Animation(0.5f, TigreFrames);
		FlibbetAnimation = new Animation(0.5f, FlibbetFrames);
		
			//creates hitboxes for the two characters and their projectiles
		Tigre = new Rectangle();
		Tigre.x = positionxT + 20;
		Tigre.y = positionyT + 5;
		Tigre.width = 20;
		Tigre.height = 35;

		Flibbet = new Rectangle();
		Flibbet.x = positionxF + 20;
		Flibbet.y = positionyF + 5;
		Flibbet.width = 20;
		Flibbet.height = 40;

		TShotRectangle = new Rectangle();
		TShotRectangle.x = positionTShotx + 20;
		TShotRectangle.y = positionTShoty + 10;
		TShotRectangle.width = 20;
		TShotRectangle.height = 32;

		FShotRectangle = new Rectangle();
		FShotRectangle.x = positionFShotx + 20;
		FShotRectangle.y = positionFShoty + 10;
		FShotRectangle.width = 20;
		FShotRectangle.height = 32;
		
			//creates the time used for animation
		elapsedTime = 0f;

		batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		batch.begin();
		
			//checks if the game is running
		if (Game) {
			
				//clears background and makes it black
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
				//increases time
			elapsedTime += Gdx.graphics.getDeltaTime();
			
				//corrects the hitboxes of each character and projectile to move with their sprites
			Tigre.x = positionxT + 20;
			Tigre.y = positionyT + 5;
			Flibbet.x = positionxF + 20;
			Flibbet.y = positionyF + 5;
			TShotRectangle.x = positionTShotx + 20;
			TShotRectangle.y = positionTShoty + 10;
			FShotRectangle.x = positionFShotx + 20;
			FShotRectangle.y = positionFShoty + 10;
			
				//creates the current frame of each animation
			TextureRegion currentTigreFrame = TigreAnimation.getKeyFrame(elapsedTime, true);
			TextureRegion currentFlibbetFrame = FlibbetAnimation.getKeyFrame(elapsedTime, true);
			
				//draws map
			batch.draw(Map, Map.getX(), Map.getY());
			
				//checks input of each character for movement and if they are shooting as well as setting bounds for where they can move
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				if (positionxF <= Gdx.graphics.getWidth() / 2 + 12) {
				} else
					positionxF -= 5;
			}

			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				if (positionxF >= 1280 - 80) {
				} else
					positionxF += 5;
			}

			if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				if (positionyF >= 720 - 64) {
				} else
					positionyF += 5;
			}

			if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				if (positionyF <= 64) {
				} else
					positionyF -= 5;
			}

			if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_RIGHT)) {
				if (FShot == false) {		//makes it so the projectile can only appear once
					FShot = true;
					positionFShotx = positionxF;
					positionFShoty = positionyF;
					FShotNum++;		//increases shot counter (used for stats)
				}
			}

			if (Gdx.input.isKeyPressed(Input.Keys.A)) {
				if (positionxT <= 32) {
				} else
					positionxT -= 5;
			}

			if (Gdx.input.isKeyPressed(Input.Keys.D)) {
				if (positionxT >= 1280 - Gdx.graphics.getWidth() / 2 - 64) {
				} else
					positionxT += 5;
			}

			if (Gdx.input.isKeyPressed(Input.Keys.W)) {
				if (positionyT >= 720 - 64) {
				} else
					positionyT += 5;
			}

			if (Gdx.input.isKeyPressed(Input.Keys.S)) {
				if (positionyT <= 64) {
				} else
					positionyT -= 5;
			}

			if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
				if (TShot == false) {		//makes it so the projectile can only appear once
					TShot = true;
					positionTShotx = positionxT;
					positionTShoty = positionyT;
					TShotNum++;		//increases shot counter (used for stats)
				}
			}

				//draws characters
			batch.draw(currentTigreFrame, positionxT, positionyT);
			batch.draw(currentFlibbetFrame, positionxF, positionyF);

				//draws the character names
			TFont.getData().setScale(1.5f);
			TFont.draw(batch, TigreText, 10, 710);
			FFont.getData().setScale(1.5f);
			FFont.draw(batch, FlibbetsText, 850, 710);

				//checks if the shot is off of the screen and if it hit the other character
			if (TShot) {
				if (positionTShotx <= 1280) {
					batch.draw(TigreShot, positionTShotx, positionTShoty);
					positionTShotx += 30;
				} else
					TShot = false;
				if (TShotRectangle.overlaps(Flibbet)) {
					positionTShotx = 1400;
					FHealth--;		//decreases health of other character
				}
			}

				//checks if the shot is off of the screen and if it hit the other character
			if (FShot) {
				if (positionFShotx >= -FlibbetShot.getWidth()) {
					batch.draw(FlibbetShot, positionFShotx, positionFShoty);
					positionFShotx -= 30;
				} else
					FShot = false;
				if (FShotRectangle.overlaps(Tigre)) {
					positionFShotx = -FlibbetShot.getWidth();
					THealth--;		//decreases health of other character
				}
			}

				//draws the health bar for Frobisher Flibbet depending on health
			if (FHealth == 3) {
				batch.draw(FHealth3, FHealth3.getX(), FHealth3.getY(), FHealth3.getWidth(), FHealth3.getHeight(),
						FHealth3.getWidth(), FHealth3.getHeight(), 3, 3, 0);
			} else if (FHealth == 2) {
				batch.draw(FHealth2, FHealth2.getX(), FHealth2.getY(), FHealth2.getWidth(), FHealth2.getHeight(),
						FHealth2.getWidth(), FHealth2.getHeight(), 3, 3, 0);
			} else if (FHealth == 1) {
				batch.draw(FHealth1, FHealth1.getX(), FHealth1.getY(), FHealth1.getWidth(), FHealth1.getHeight(),
						FHealth1.getWidth(), FHealth1.getHeight(), 3, 3, 0);
			}
			
				//draws the health bar for Kitsuno Tigre depending on health
			if (THealth == 3) {
				batch.draw(THealth3, THealth3.getX(), THealth3.getY(), THealth3.getWidth(), THealth3.getHeight(),
						THealth3.getWidth(), THealth3.getHeight(), 3, 3, 0);
			} else if (THealth == 2) {
				batch.draw(THealth2, THealth2.getX(), THealth2.getY(), THealth2.getWidth(), THealth2.getHeight(),
						THealth2.getWidth(), THealth2.getHeight(), 3, 3, 0);
			} else if (THealth == 1) {
				batch.draw(THealth1, THealth1.getX(), THealth1.getY(), THealth1.getWidth(), THealth1.getHeight(),
						THealth1.getWidth(), THealth1.getHeight(), 3, 3, 0);
			}
			
				//ends the game if one or both characters have no health
			if (FHealth <= 0 && THealth <= 0 || FHealth <= 0 || THealth <= 0) {
				// if the game is over, end the game
				Game = false;
			}

			//if the game is not running
		} else {
			if (FHealth <= 0 && THealth <= 0) {
				
					// gameover for both, draws both health bars at 0, displays end screen
				batch.draw(NoHealth, FHealth1.getX(), FHealth1.getY(), NoHealth.getWidth(), NoHealth.getHeight(),
						NoHealth.getWidth(), NoHealth.getHeight(), 3, 3, 0);
				batch.draw(NoHealth, THealth1.getX(), THealth1.getY(), NoHealth.getWidth(), NoHealth.getHeight(),
						NoHealth.getWidth(), NoHealth.getHeight(), 3, 3, 0);
				game.font.draw(batch, "GAME OVER\n" + "You both suck!\n" + "Press ENTER to play again!",
						Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 4,
						Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 8);
			} else if (FHealth <= 0) {
				
					// Tigre wins, Flibbets loses, draws Flibbets' health bar at 0, displays end screen
				batch.draw(NoHealth, FHealth1.getX(), FHealth1.getY(), NoHealth.getWidth(), NoHealth.getHeight(),
						NoHealth.getWidth(), NoHealth.getHeight(), 3, 3, 0);
				game.font.draw(batch,
						"Kitsuno Tigre WINS!\n" + "There were " + (TShotNum + FShotNum) + " shots fired that round!\n"
								+ "Press ENTER to play again!",
						Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 4,
						Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 8);
			} else if (THealth <= 0) {
					
					// Flibbets wins, Tigre loses, draws Tigre's health bar at 0, displays end screen
				batch.draw(NoHealth, THealth1.getX(), THealth1.getY(), NoHealth.getWidth(), NoHealth.getHeight(),
						NoHealth.getWidth(), NoHealth.getHeight(), 3, 3, 0);
				game.font.draw(batch,
						"Frobisher Flibbets WINS!\n" + "There were " + (TShotNum + FShotNum)
								+ " shots fired that round!\n" + "Press ENTER to play again!",
						Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 4,
						Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 8);
			}
				
				//restarts the game if ENTER is pressed, resets character and projectile positions as well as health
			if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
				FHealth = 3;
				THealth = 3;
				TShotNum = 0;
				FShotNum = 0;
				positionTShotx = 1400;
				positionFShotx = -FlibbetShot.getWidth();
				positionxT = 320;
				positionyT = 180;
				positionxF = 960;
				positionyF = 540;
				Game = true;
			}
		}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		batch.dispose();
		tempTexture.dispose();
		TigreTexture.dispose();
		FlibbetTexture.dispose();
		TigreShotTexture.dispose();
		FlibbetShotTexture.dispose();
		THealth3Texture.dispose();
		THealth2Texture.dispose();
		THealth1Texture.dispose();
		FHealth3Texture.dispose();
		FHealth2Texture.dispose();
		FHealth1Texture.dispose();
		NoHealthTexture.dispose();
		tempFont.dispose();
		FFont.dispose();
		TFont.dispose();
	}

	@Override
	public Texture newTexture(String Name) {		//creates a new texture
		tempTexture = new Texture(Gdx.files.internal(Name));
		return tempTexture;
	}
	
	@Override
	public Sprite newSprite(Texture txt) {		//creates a new sprite
		tempSprite = new Sprite(txt);
		return tempSprite;
	}

	@Override
	public BitmapFont newFont(String Font) {		//creates a new font
		tempFont = new BitmapFont(Gdx.files.internal(Font));
		return tempFont;
	}

}