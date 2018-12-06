package com.myfirstgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public interface Initialize {

	public Texture newTexture(String Name);

	public Sprite newSprite(Texture txt);

	public BitmapFont newFont(String Font);

}
