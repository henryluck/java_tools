package org.loon.game.simple.j25d.rpg;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import org.loon.game.simple.j25d.GraphicsUtils;

public class ImageMapFactory implements Config {

	final protected static int mImageWidth = 150, mImageHeight = 120;

	private int[][] maps = null;

	private Image[][] images = null;

	private Image[][] transImages = null;

	private static int CS = 32;

	private int imageWidth = 0;

	private int ImageHeight = 0;

	private int mapWidth = 0;

	private int mapHeight = 0;

	final private boolean isTransparency;

	final private Image backgroundImage;

	final private Image transparenceImage;

	private boolean isDeflate;

	private Image deflateImage;
	
	private double deflateX;

	private double deflateY;
	
	private int cost = 1;

	public ImageMapFactory(String imageFile, String mapFile, String transFile)
			throws IOException {
		this(GraphicsUtils.loadImage(imageFile), MapConfig
				.loadAthwartArray(mapFile), GraphicsUtils.loadImage(transFile));
	}

	public ImageMapFactory(Image imgBack, int[][] map, Image imgTrans) {
		maps = map;
		backgroundImage = imgBack;
		if (imgTrans != null) {
			transparenceImage = imgTrans;
			transImages = GraphicsUtils.getSplit2Images(transparenceImage, CS,
					CS, false);
			isTransparency = true;
		} else {
			transparenceImage = null;
			isTransparency = false;
		}
		imageWidth = imgBack.getWidth(null);
		ImageHeight = imgBack.getHeight(null);
		mapWidth = imageWidth / CS;
		mapHeight = ImageHeight / CS;
		images = GraphicsUtils.getSplit2Images(backgroundImage, CS, CS, false);
	}
	
	public boolean isExists(String fileName){
		return new File(fileName).exists();
	}

	public boolean isTransparency() {
		return isTransparency;
	}

	public Image getTransparenceImage() {
		return transparenceImage;
	}

	public Image[][] getTransImages() {
		return transImages;
	}
	
	public double getDeflateX(){
		return deflateX;
	}
	
	public double getDeflateY(){
		return deflateY;
	}
	
	public Image getDeflateImage() {
		if (!isDeflate) {
			deflateImage = GraphicsUtils.getResize(backgroundImage,
					mImageWidth, mImageHeight);
			isDeflate = true;
			deflateX = (double)mImageWidth/(double)getImageWidth();
			deflateY = (double)mImageHeight/(double)getImageHeight();
			
		}
		return deflateImage;
	}

	public Image getImage() {
		return backgroundImage;
	}

	public Image[][] getImages() {
		return images;
	}

	public static Point toPoint(Point local) {
		Point p = new Point();
		p.x = local.x * CS + CS / 2;
		p.y = (local.y + 1) * CS + CS;
		return p;
	}

	public void draw(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, null);
	}

	public int[][] getMap() {
		return maps;
	}

	public Point getPoint(int x, int y) {
		Point p = new Point();
		p.x = x;
		p.y = y;
		return toPoint(p);
	}

	public int getImageHeight() {
		return ImageHeight;
	}

	public void setImageHeight(int height) {
		ImageHeight = height;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int width) {
		imageWidth = width;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(int height) {
		mapHeight = height;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(int width) {
		mapWidth = width;
	}

	public int getAlternation() {
		return CS;
	}

	public int getCost(Point p) {
		return cost;
	}

	public void setCost(Point p) {
		this.cost = 1;
	}

}
