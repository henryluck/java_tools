package org.loon.game.simple.j25d.rpg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.loon.game.simple.j25d.GraphicsUtils;

import org.loon.game.simple.j25d.LSystem;
import org.loon.game.simple.j25d.command.Command;
import org.loon.game.simple.j25d.resource.BackgroundResource;
import org.loon.game.simple.j25d.resource.RpgResource;
import org.loon.game.simple.j25d.rpg.role.Role;
import org.loon.game.simple.j25d.rpg.role.Roles;

public class RpgMap implements Config {

	private ImageMapFactory imageMap;

	private Roles roles;

	private int row, col, width, height;

	private Image miniMapImage;

	private Image[][] mapImages, mapTransImages;

	private int[][] mapTypes;

	private boolean showGrid;

	private boolean initFlag, isTransparency;

	private Field2D map2d;

	private int firstTileX;

	private int firstTileY;

	private int lastTileX;

	private int lastTileY;

	private int mSize = ImageMapFactory.mImageWidth, mFlagSize = 5;

	private int miniatureX = LSystem.WIDTH - mSize - mFlagSize;

	private int i, j, minX, minY;

	private List transferEvents = new ArrayList(10);

	private String imageFile, mapFile, transFile, scriptFile;

	public RpgMap(String imageFile, String mapFile, String scriptFile) {
		this(imageFile, mapFile, null, scriptFile);
	}

	public RpgMap(String imageFile, String mapFile, String transFile,
			String scriptFile) {
		this.imageFile = imageFile;
		this.mapFile = mapFile;
		this.transFile = transFile;
		this.scriptFile = scriptFile;
	}

	public void initialize() {
		try {
			imageMap = new ImageMapFactory(imageFile, mapFile, transFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		this.isTransparency = imageMap.isTransparency();
		this.mapTypes = imageMap.getMap();
		this.mapImages = imageMap.getImages();
		this.mapTransImages = imageMap.getTransImages();
		this.miniMapImage = imageMap.getDeflateImage();
		this.row = imageMap.getMapWidth();
		this.col = imageMap.getMapHeight();
		this.width = imageMap.getImageWidth();
		this.height = imageMap.getImageHeight();
		this.map2d = new Field2D(imageMap.getMap());
		this.addScript(scriptFile);
	}

	/**
	 * 注入场景脚本（本博文系列为示例，请自行完善）
	 * 
	 * @param fileName
	 */
	private void addScript(String fileName) {
		this.roles = new Roles();
		Command command = new Command(fileName);
		for (; command.next();) {
			String result = command.doExecute();
			if (result != null) {
				List commands = Command.splitToList(result, " ");
				if (commands.size() == 4) {
					String name = (String) commands.get(0);
					Role role = (Role) RpgResource.roles.get(commands.get(1));
					int x = Integer.parseInt(commands.get(2).toString());
					int y = Integer.parseInt(commands.get(3).toString());
					role.setXandY(x, y);
					if ("hero".equalsIgnoreCase(name)) {
						setupHero(role);
					} else if ("chara".equalsIgnoreCase(name)) {
						addRole(role);
					}
					role.setupMap(this);
					role.startLoop();
				}
			}
		}
	}

	public void startAllRole() {
		roleAll(true);
	}

	public void stopAllRole() {
		roleAll(false);
	}

	private void roleAll(boolean start) {
		Iterator it = roles.getRoles().iterator();
		for (; it.hasNext();) {
			Role role = (Role) it.next();
			if (start) {
				role.startLoop();
			} else {
				role.stopLoop();
			}
		}
	}

	public void addTransfer(RpgMap map, int x, int y) {
		transferEvents.add(new TransferEvent(map, x, y));
	}

	public List getTransfers() {
		return transferEvents;
	}

	public void addTransfer(TransferEvent transferEvent) {
		transferEvents.add(transferEvent);
	}

	public void addRole(Role role) {
		roles.addChara(role);
	}

	public Role getHero() {
		return roles.getHero();
	}

	public void setupHero(Role hero) {
		roles.mainHero(hero);
	}

	/**
	 * 地图及角色绘制
	 * 
	 * @param g
	 * @param offsetX
	 * @param offsetY
	 */
	public void draw(Graphics g, int offsetX, int offsetY) {
		// 基础背景
		if (!initFlag) {
			g.drawImage(BackgroundResource.windowsBackgroundCanvas, 0, 0, null);
			initFlag = true;
			return;
		}
		// 获得当前屏幕坐标
		firstTileX = pixelsToTiles(-offsetX);
		lastTileX = firstTileX + pixelsToTiles(LSystem.WIDTH) + 1;
		lastTileX = Math.min(lastTileX, row);
		firstTileY = pixelsToTiles(-offsetY);
		lastTileY = firstTileY + pixelsToTiles(LSystem.HEIGHT) + 1;
		lastTileY = Math.min(lastTileY, col);

		// 绘制背景图片
		for (i = lastTileX - 1; i > -1; --i) {
			for (j = lastTileY - 1; j > -1; --j) {
				g.drawImage(mapImages[i][j], tilesToPixels(i) + offsetX,
						tilesToPixels(j) + offsetY, null);
				if (!showGrid) {
					continue;
				}
				if (mapTypes[j][i] == 1) {
					g.setColor(Color.white);
					g.drawRect(tilesToPixels(i) + offsetX, tilesToPixels(j)
							+ offsetY, CS - 2, CS - 2);
					GraphicsUtils.setAlpha(g, 0.5d);
					g.fillRect(tilesToPixels(i) + offsetX, tilesToPixels(j)
							+ offsetY, CS - 2, CS - 2);
					GraphicsUtils.setAlpha(g, 1.0d);
				} else if (mapTypes[j][i] == -1) {
					g.setColor(Color.blue);
					g.drawRect(tilesToPixels(i) + offsetX, tilesToPixels(j)
							+ offsetY, CS - 2, CS - 2);
					GraphicsUtils.setAlpha(g, 0.3d);
					g.fillRect(tilesToPixels(i) + offsetX, tilesToPixels(j)
							+ offsetY, CS - 2, CS - 2);
					GraphicsUtils.setAlpha(g, 1.0d);
				}

			}
		}

		// 绘制角色群
		roles.draw(g, firstTileX, firstTileY, lastTileX, lastTileY, offsetX,
				offsetY);

		// 绘制透明区域
		if (isTransparency) {
			for (i = lastTileX - 1; i > -1; --i) {
				for (j = lastTileY - 1; j > -1; --j) {
					g.drawImage(mapTransImages[i][j], tilesToPixels(i)
							+ offsetX, tilesToPixels(j) + offsetY, null);
				}
			}
		}
		// 绘制缩略图
		drawMiniature(g);

	}

	// 显示缩略地图
	public void drawMiniature(Graphics g) {
		GraphicsUtils.setAlpha(g, 0.5d);
		g.drawImage(miniMapImage, miniatureX, mFlagSize, null);
		Iterator it = roles.getRoles().iterator();
		for (; it.hasNext();) {
			Role role = (Role) it.next();
			minX = (int) (imageMap.getDeflateX() * tilesToPixels(role.getX()));
			minY = (int) (imageMap.getDeflateY() * tilesToPixels(role.getY()));
			GraphicsUtils.drawSixStart(g, role.isHero() ? Color.blue
					: Color.red, ((LSystem.WIDTH - mSize) + minX) - mFlagSize,
					mFlagSize + minY, mFlagSize);
		}
		GraphicsUtils.setAlpha(g, 1.0d);
		GraphicsUtils.rectDraw(g, miniatureX, mFlagSize, 150, 120, Color.black);
	}

	public int getSelfFirstX() {
		return firstTileX;
	}

	public int getSelfFirstY() {
		return firstTileY;
	}

	public int getSelfLastX() {
		return lastTileX;
	}

	public int getSelfLastY() {
		return lastTileY;
	}

	public int getSelfFirstWidth() {
		return tilesToPixels(firstTileX);
	}

	public int getSelfFirstHeight() {
		return tilesToPixels(firstTileY);
	}

	public int getSelfLastWidth() {
		return tilesToPixels(lastTileX);
	}

	public int getSelfLastHeight() {
		return tilesToPixels(lastTileY);
	}

	public List findPath(Role hero, Cell2D goal) {
		return AStarFinder.find(map2d, hero.getCell2D(), goal);
	}

	public void showGrid(boolean show) {
		this.showGrid = show;
	}

	public ImageMapFactory getFactory() {
		return imageMap;
	}

	public boolean isHit(int x, int y) {
		try {
			if (mapTypes[y][x] == 1) {
				return true;
			}
			if (roles.isHit(x, y)) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static int pixelsToTiles(double pixels) {
		return (int) Math.floor(pixels / CS);
	}

	public static int tilesToPixels(int tiles) {
		return tiles * CS;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Roles getRoleList() {
		return roles;
	}

}
