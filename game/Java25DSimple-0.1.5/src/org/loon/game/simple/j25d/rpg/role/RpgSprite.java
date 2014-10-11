package org.loon.game.simple.j25d.rpg.role;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import org.loon.game.simple.j25d.GraphicsUtils;

/**
 * Copyright 2008 - 2009
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * @project loonframework
 * @author chenpeng
 * @email��ceponline@yahoo.com.cn
 * @version 0.1
 */
public class RpgSprite {

	final static protected int DOWN = 0;

	final static protected int LEFT = 1;

	final static protected int RIGHT = 2;

	final static protected int UP = 3;

	final static protected int LOWER_LEFT = 4;

	final static protected int LOWER_RIGHT = 5;

	final static protected int UPPER_LEFT = 6;

	final static protected int UPPER_RIGHT = 7;

	final static private int rWidth = 70, rHeight = 124, rSize = 4;

	private Image shadowImage;

	private Image[][] images;

	private int imageWidth;

	private int imageHeight;

	private int size;

	public RpgSprite(String fileName) {
		this(fileName, rWidth, rHeight, rSize);
	}

	public RpgSprite(String fileName, int row, int col, int size) {
		this.size = size;
		this.imageWidth = row;
		this.imageHeight = col;
		this.images = GraphicsUtils.getSplit2Images(fileName, row, col, false);
		this.images = GraphicsUtils.getFlipHorizintalImage2D(images);
		this.imageWidth = row;
		this.imageHeight = col;
	}

	public Image makeShadowImage() {
		if (shadowImage == null) {
			shadowImage = GraphicsUtils.createImage(
					imageWidth - imageWidth / 3, imageWidth / 2, true);
			Graphics g = shadowImage.getGraphics();
			GraphicsUtils.setAlpha(g, 0.5d);
			GraphicsUtils.rectOval(g, 0, 0, imageWidth - imageWidth / 3,
					imageWidth / 3, Color.black);
			g.dispose();
		}
		return shadowImage;
	}

	public Image[][] getImages() {
		return images;
	}

	public Image[] getMove(int index) {
		return images[index];
	}

	public int getSize() {
		return size - 1;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

}
