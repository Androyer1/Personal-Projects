package com.myfirstgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class GameTest extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture MapTexture;
	private Texture TigreTexture;
	private Texture FlibbetTexture;
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
	private Texture TigreShotTexture;
	private Texture FlibbetShotTexture;
	private Texture THealth3Texture;
	private Texture THealth2Texture;
	private Texture THealth1Texture;
	private Texture FHealth3Texture;
	private Texture FHealth2Texture;
	private Texture FHealth1Texture;
	private Texture NoHealthTexture;
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
	private BitmapFont TFont;
	private BitmapFont FFont;

	@Override
	public void create() {
		MapTexture = new Texture("Map_Game_2.PNG");
		Map = new Sprite(MapTexture);
		Map.setPosition(0, 0);

		TFont = new BitmapFont(Gdx.files.internal("TigreFont.fnt"));
		FFont = new BitmapFont(Gdx.files.internal("FlibbetsFont.fnt"));

		TigreText = "KITSUNO TIGRE";
		FlibbetsText = "FROBISHER FLIBBETS";

		TigreTexture = new Texture(Gdx.files.internal("SpriteSheet_KitsunoTigre.png"));
		FlibbetTexture = new Texture(Gdx.files.internal("SpriteSheet_FrobisherFlibbet.png"));

		TigreShotTexture = new Texture(Gdx.files.internal("Red_Skill_Shot_4.png"));
		FlibbetShotTexture = new Texture(Gdx.files.internal("Blue_Skill_Shot_4.png"));

		THealth3Texture = new Texture(Gdx.files.internal("Tigre_FullHP.png"));
		THealth2Texture = new Texture(Gdx.files.internal("Tigre_2hp.png"));
		THealth1Texture = new Texture(Gdx.files.internal("Tigre_1hp.png"));

		FHealth3Texture = new Texture(Gdx.files.internal("Frog_Full_Hp_1.png"));
		FHealth2Texture = new Texture(Gdx.files.internal("Frog_2hp.png"));
		FHealth1Texture = new Texture(Gdx.files.internal("Frog_1hp.png"));

		NoHealthTexture = new Texture(Gdx.files.internal("Dead.png"));

		TigreShot = new Sprite(TigreShotTexture);
		FlibbetShot = new Sprite(FlibbetShotTexture);

		THealth3 = new Sprite(THealth3Texture);
		THealth2 = new Sprite(THealth2Texture);
		THealth1 = new Sprite(THealth1Texture);

		FHealth3 = new Sprite(FHealth3Texture);
		FHealth2 = new Sprite(FHealth2Texture);
		FHealth1 = new Sprite(FHealth1Texture);

		NoHealth = new Sprite(NoHealthTexture);

		TigreShot.setPosition(positionxT, positionyT);
		FlibbetShot.setPosition(positionxF, positionyF);

		THealth3.setPosition(110, 630);
		THealth2.setPosition(110, 630);
		THealth1.setPosition(110, 630);

		FHealth3.setPosition(1277, 630);
		FHealth2.setPosition(1277, 630);
		FHealth1.setPosition(1277, 630);

		TextureRegion[][] TigreRegion = TextureRegion.split(TigreTexture, TigreTexture.getWidth() / FRAME_COLS_T,
				TigreTexture.getHeight() / FRAME_ROWS_T);
		TextureRegion[][] FlibbetRegion = TextureRegion.split(FlibbetTexture, FlibbetTexture.getWidth() / FRAME_COLS_F,
				FlibbetTexture.getHeight() / FRAME_ROWS_F);

		TextureRegion[] TigreFrames = new TextureRegion[FRAME_COLS_T * FRAME_ROWS_T];
		TextureRegion[] FlibbetFrames = new TextureRegion[FRAME_COLS_F * FRAME_ROWS_F];

		int indexTigre = 0;
		int indexFrog = 0;
		for (int i = 0; i < FRAME_ROWS_T; i++) {
			for (int j = 0; j < FRAME_COLS_T; j++) {
				TigreFrames[indexTigre++] = TigreRegion[i][j];
			}
		}
		for (int i = 0; i < FRAME_ROWS_F; i++) {
			for (int j = 0; j < FRAME_COLS_F; j++) {
				FlibbetFrames[indexFrog++] = FlibbetRegion[i][j];
			}
		}

		TigreAnimation = new Animation(0.5f, TigreFrames);
		FlibbetAnimation = new Animation(0.5f, FlibbetFrames);

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

		elapsedTime = 0f;

		batch = new SpriteBatch();
	}

	@Override
	public void render() {
		batch.begin();

		if (Game) {

			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			elapsedTime += Gdx.graphics.getDeltaTime();

			Tigre.x = positionxT + 20;
			Tigre.y = positionyT + 5;
			Flibbet.x = positionxF + 20;
			Flibbet.y = positionyF + 5;
			TShotRectangle.x = positionTShotx + 20;
			TShotRectangle.y = positionTShoty + 10;
			FShotRectangle.x = positionFShotx + 20;
			FShotRectangle.y = positionFShoty + 10;

			TextureRegion currentTigreFrame = TigreAnimation.getKeyFrame(elapsedTime, true);
			TextureRegion currentFlibbetFrame = FlibbetAnimation.getKeyFrame(elapsedTime, true);

			batch.draw(Map, Map.getX(), Map.getY());

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
				if (FShot == false) {
					FShot = true;
					positionFShotx = positionxF;
					positionFShoty = positionyF;
					FShotNum++;
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
				if (TShot == false) {
					TShot = true;
					positionTShotx = positionxT;
					positionTShoty = positionyT;
					TShotNum++;
				}
			}

			batch.draw(currentTigreFrame, positionxT, positionyT);
			batch.draw(currentFlibbetFrame, positionxF, positionyF);

			TFont.getData().setScale(1.5f);
			TFont.draw(batch, TigreText, 10, 710);
			FFont.getData().setScale(1.5f);
			FFont.draw(batch, FlibbetsText, 850, 710);

			if (TShot) {
				if (positionTShotx <= 1280) {
					batch.draw(TigreShot, positionTShotx, positionTShoty);
					positionTShotx += 30;
				} else
					TShot = false;
				if (TShotRectangle.overlaps(Flibbet)) {
					positionTShotx = 1400;
					FHealth--;
				}
			}

			if (FShot) {
				if (positionFShotx >= -FlibbetShot.getWidth()) {
					batch.draw(FlibbetShot, positionFShotx, positionFShoty);
					positionFShotx -= 30;
				} else
					FShot = false;
				if (FShotRectangle.overlaps(Tigre)) {
					positionFShotx = -FlibbetShot.getWidth();
					THealth--;
				}
			}

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

			if (FHealth <= 0 && THealth <= 0) {
				// gameover for both
				Game = false;
			} else if (FHealth <= 0) {
				// Tigre wins, Flibbets loses
				Game = false;
			} else if (THealth <= 0) {
				// Flibbets wins, Tigre loses
				Game = false;
			}

		} else {
			// had to swich to TFont for font since GameTest is not associated with
			// DesertBattle
			if (FHealth <= 0 && THealth <= 0) {
				// gameover for both
				batch.draw(NoHealth, FHealth1.getX(), FHealth1.getY(), NoHealth.getWidth(), NoHealth.getHeight(),
						NoHealth.getWidth(), NoHealth.getHeight(), 3, 3, 0);
				batch.draw(NoHealth, THealth1.getX(), THealth1.getY(), NoHealth.getWidth(), NoHealth.getHeight(),
						NoHealth.getWidth(), NoHealth.getHeight(), 3, 3, 0);
				TFont.draw(batch, "GAME OVER\n + You both suck!\n + Press ENTER to play again!",
						Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 4,
						Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 8);
			} else if (FHealth <= 0) {
				// Tigre wins, Flibbets loses
				batch.draw(NoHealth, FHealth1.getX(), FHealth1.getY(), NoHealth.getWidth(), NoHealth.getHeight(),
						NoHealth.getWidth(), NoHealth.getHeight(), 3, 3, 0);
				TFont.draw(batch,
						"Kitsuno Tigre WINS!\n" + "There were " + (TShotNum + FShotNum) + " shots fired that round!\n"
								+ "Press ENTER to play again!",
						Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 4,
						Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 8);
			} else if (THealth <= 0) {
				// Flibbets wins, Tigre loses
				batch.draw(NoHealth, THealth1.getX(), THealth1.getY(), NoHealth.getWidth(), NoHealth.getHeight(),
						NoHealth.getWidth(), NoHealth.getHeight(), 3, 3, 0);
				TFont.draw(batch,
						"Frobisher Flibbets WINS!\n" + "There were " + (TShotNum + FShotNum)
								+ " shots fired that round!\n" + "Press ENTER to play again!",
						Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 4,
						Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 8);
			}
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
	public void dispose() {
		batch.dispose();
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
	}
}
