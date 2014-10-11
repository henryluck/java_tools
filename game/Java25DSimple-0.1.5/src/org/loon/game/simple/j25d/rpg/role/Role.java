package org.loon.game.simple.j25d.rpg.role;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import org.loon.game.simple.j25d.GraphicsUtils;
import org.loon.game.simple.j25d.rpg.Cell2D;
import org.loon.game.simple.j25d.rpg.Config;
import org.loon.game.simple.j25d.rpg.Field2D;
import org.loon.game.simple.j25d.rpg.RpgMap;

public class Role implements Config {

	final private static int SPEED = 4;

	final public static double PROB_MOVE = 0.02;

	final private static int STATE_MAX_SIZE = 70;

	final private static int STATE_HEIGHT = 4;

	final private static int FONT_SIZE = 20;

	private boolean showName;

	private boolean showState;

	private int nx, ny;

	private int mx, my;

	private int stateX, stateY;

	private boolean isFontInit;

	private int fontHeight;

	private int nameFontWidth;

	private int maxHP, maxSP;

	private int hp, sp;

	private int x, y;

	private int px, py;

	private int direction, spriteSize;

	private int count;

	private boolean isMoving;

	private Role chara;

	private int movingLength, moveType;

	private int aspect, nhp, nsp;

	private String message;

	private Thread threadAnime;

	private RpgSprite sprite;

	private RpgMap map;

	private String scriptFile;

	private String name;

	private String partyName;

	private int ioffsetX, ioffsetY, rWidth, rHeight;

	private boolean autoFinder, isHero, isLoop;

	private Image[][] spriteImages;

	private Image shadowImage;

	public Role(String fileName, int direction, int moveType) {
		sprite = new RpgSprite(fileName);
		this.spriteImages = sprite.getImages();
		this.shadowImage = sprite.makeShadowImage();
		this.showName = false;
		this.showState = false;
		this.rWidth = sprite.getImageWidth();
		this.rHeight = sprite.getImageHeight();
		ioffsetX = rWidth - CS;
		ioffsetY = rHeight - CS;
		this.direction = direction;
		this.spriteSize = sprite.getSize();
		this.count = 0;
		this.moveType = moveType;
		if (moveType == 0) {
			this.setHero(true);
		}
	}

	public void setupMap(RpgMap map) {
		this.map = map;
	}

	public void startLoop() {
		isLoop = true;
		threadAnime = new Thread(new AnimationThread());
		threadAnime.setPriority(isHero ? Thread.NORM_PRIORITY
				: Thread.MIN_PRIORITY);
		threadAnime.start();
	}

	public void stopLoop() {
		isLoop = false;
		threadAnime = null;
	}

	public void setXandY(Cell2D cell) {
		setXandY(cell.x(), cell.y());
	}

	public void setXandY(int x, int y) {
		this.x = x;
		this.y = y;
		px = x * CS;
		py = y * CS;
	}

	public Cell2D getCell2D() {
		return new Cell2D(x, y);
	}

	/**
	 * 获得当前hp长度
	 * 
	 * @return
	 */
	protected int getNowHPSize() {
		return (int) ((STATE_MAX_SIZE - 1) * ((double) getHp() / (double) getMaxHP()));
	}

	/**
	 * 获得当前sp长度
	 * 
	 * @return
	 */
	protected int getNowSPSize() {
		return (int) ((STATE_MAX_SIZE - 1) * ((double) getSp() / (double) getMaxSP()));
	}

	public int autoAspect() {
		if (autoFinder) {
			move();
		}
		if (x < 0 || px < 0) {
			px = 0;
			x = 0;
		}
		if (y < 0 || py < 0) {
			py = 0;
			y = 0;
		}
		if (x > map.getRow() || px > map.getWidth() - CS) {
			px = map.getWidth() - CS;
			x = map.getRow() - 1;
		}
		if (y > map.getCol() || py > map.getHeight() - CS) {
			py = map.getHeight() - CS;
			y = map.getCol() - 1;
		}
		switch (direction) {
		case UP:
			return RpgSprite.UPPER_RIGHT;
		case DOWN:
			return RpgSprite.LOWER_LEFT;
		case LEFT:
			return RpgSprite.UPPER_LEFT;
		case RIGHT:
			return RpgSprite.LOWER_RIGHT;
		case TUP:
			return RpgSprite.UP;
		case TDOWN:
			return RpgSprite.DOWN;
		case TLEFT:
			return RpgSprite.LEFT;
		case TRIGHT:
			return RpgSprite.RIGHT;
		}
		return 0;
	}

	public void draw(Graphics g, int offsetX, int offsetY) {

		aspect = autoAspect();

		nhp = getNowHPSize();

		nsp = getNowSPSize();

		nx = px + offsetX - (ioffsetX / 2);
		ny = py + offsetY - ioffsetY;

		g.drawImage(shadowImage, nx + 10, ((ny + rHeight) - (rHeight / 4)),
				null);
		g.drawImage(spriteImages[aspect][count], nx, ny, null);

		if (!isFontInit) {
			fontHeight = g.getFontMetrics().getHeight();
			nameFontWidth = g.getFontMetrics().stringWidth(name);
			isFontInit = true;
		}

		if (showState) {
			stateX = (nx + ioffsetX - 5) - STATE_MAX_SIZE / 2;
			stateY = (ny + ioffsetY) + 28;
			GraphicsUtils.rectFill(g, stateX - 1, stateY - 1,
					STATE_MAX_SIZE + 1, (STATE_HEIGHT * 2) + 1, Color.black);
			GraphicsUtils.rectFill(g, stateX, stateY, nhp, STATE_HEIGHT - 1,
					Color.green);
			GraphicsUtils.rectFill(g, stateX, stateY + STATE_HEIGHT, nsp,
					STATE_HEIGHT - 1, Color.blue);
		}

		if (showName) {
			mx = (nx + ioffsetX - (nameFontWidth / 2) - 5);
			my = ny + ioffsetY + FONT_SIZE + fontHeight;
			GraphicsUtils.drawStyleString(g, name, mx, my += FONT_SIZE,
					Color.black, Color.white);
			GraphicsUtils.drawStyleString(g, partyName, mx, my += FONT_SIZE,
					Color.black, Color.white);
		}
	}

	public void autoDirection(List startPath) {
		Cell2D cell1 = (Cell2D) startPath.get(0);
		try {
			if (startPath.size() > 1) {
				Cell2D cell2 = (Cell2D) startPath.get(1);
				int sx = cell2.x() - cell1.x();
				int sy = cell2.y() - cell1.y();
				direction = Field2D.getDirection(sx, sy);
			}
		} finally {
			startPath.remove(0);
		}
	}

	public boolean move() {
		switch (direction) {
		case LEFT:
			if (moveLowerLeft()) {
				return true;
			}
			break;
		case RIGHT:
			if (moveLowerRight()) {
				return true;
			}
			break;
		case UP:
			if (moveUpperRight()) {
				return true;
			}
			break;
		case DOWN:
			if (moveUpperLeft()) {
				return true;
			}
			break;
		case TLEFT:
			if (moveLeft()) {
				return true;
			}
			break;
		case TRIGHT:
			if (moveRight()) {
				return true;
			}
			break;
		case TUP:
			if (moveUp()) {
				return true;
			}
			break;
		case TDOWN:
			if (moveDown()) {
				return true;
			}
			break;
		}
		return false;
	}

	protected boolean moveLeft() {
		int nextX = x - 1;
		int nextY = y;
		if (nextX < 0) {
			nextX = 0;
		}
		if (!map.isHit(nextX, nextY)) {
			px -= Role.SPEED;
			movingLength += Role.SPEED;
			if (movingLength >= CS) {
				x--;
				px = x * CS;
				isMoving = false;
				return true;
			}
		} else {
			isMoving = false;
			px = x * CS;
			py = y * CS;
		}
		return false;
	}

	protected boolean moveRight() {
		int nextX = x + 1;
		int nextY = y;
		if (nextX > map.getCol() - 1) {
			nextX = map.getCol() - 1;
		}
		if (!map.isHit(nextX, nextY)) {
			px += Role.SPEED;
			movingLength += Role.SPEED;
			if (movingLength >= CS) {
				x++;
				px = x * CS;
				isMoving = false;
				return true;
			}
		} else {
			isMoving = false;
			px = x * CS;
			py = y * CS;
		}

		return false;
	}

	protected boolean moveUp() {
		int nextX = x;
		int nextY = y - 1;
		if (nextY < 0) {
			nextY = 0;
		}
		if (!map.isHit(nextX, nextY)) {
			py -= Role.SPEED;
			movingLength += Role.SPEED;
			if (movingLength >= CS) {
				y--;
				py = y * CS;
				isMoving = false;
				return true;
			}
		} else {
			isMoving = false;
			px = x * CS;
			py = y * CS;
		}
		return false;
	}

	protected boolean moveDown() {
		int nextX = x;
		int nextY = y + 1;
		if (!map.isHit(nextX, nextY)) {
			py += Role.SPEED;
			movingLength += Role.SPEED;
			if (movingLength >= CS) {
				y++;
				py = y * CS;
				isMoving = false;
				return true;
			}
		} else {
			isMoving = false;
			px = x * CS;
			py = y * CS;
		}
		return false;
	}

	protected boolean moveLowerLeft() {
		int nextX = x - 1;
		int nextY = y - 1;
		if (nextX < 0) {
			nextX = 0;
		}
		if (nextY < 0) {
			nextY = 0;
		}
		if (!map.isHit(nextX, nextY)) {
			px -= Role.SPEED;
			py -= Role.SPEED;
			movingLength += Role.SPEED;
			if (movingLength >= CS) {
				x--;
				px = x * CS;
				y--;
				py = y * CS;
				isMoving = false;
				return true;
			}
		} else {
			isMoving = false;
			px = x * CS;
			py = y * CS;
		}
		return false;
	}

	protected boolean moveLowerRight() {
		int nextX = x + 1;
		int nextY = y + 1;
		if (nextX > map.getRow() - 1) {
			nextX = map.getRow() - 1;
		}
		if (nextY > map.getCol() - 1) {
			nextY = map.getCol() - 1;
		}
		if (!map.isHit(nextX, nextY)) {
			px += Role.SPEED;
			py += Role.SPEED;
			movingLength += Role.SPEED;
			if (movingLength >= CS) {
				x++;
				px = x * CS;
				y++;
				py = y * CS;
				isMoving = false;
				return true;
			}
		} else {
			isMoving = false;
			px = x * CS;
			py = y * CS;
		}
		return false;
	}

	protected boolean moveUpperLeft() {
		int nextX = x - 1;
		int nextY = y + 1;
		if (nextX < 0) {
			nextX = 0;
		}
		if (nextY > map.getCol() - 1) {
			nextY = map.getCol() - 1;
		}
		if (!map.isHit(nextX, nextY)) {
			px -= Role.SPEED;
			py += Role.SPEED;
			movingLength += Role.SPEED;
			if (movingLength >= CS) {
				x--;
				px = x * CS;
				y++;
				py = y * CS;
				isMoving = false;
				return true;
			}
		} else {
			isMoving = false;
			px = x * CS;
			py = y * CS;
		}
		return false;
	}

	protected boolean moveUpperRight() {
		int nextX = x + 1;
		int nextY = y - 1;
		if (nextX > map.getRow() - 1) {
			nextX = map.getRow() - 1;
		}
		if (nextY < 0) {
			nextY = 0;
		}
		if (!map.isHit(nextX, nextY)) {
			px += Role.SPEED;
			py -= Role.SPEED;
			movingLength += Role.SPEED;
			if (movingLength >= CS) {
				x++;
				px = x * CS;
				y--;
				py = y * CS;
				isMoving = false;
				return true;
			}
		} else {
			isMoving = false;
			px = x * CS;
			py = y * CS;
		}
		return false;
	}

	public Role talkWith() {
		int nextX = 0;
		int nextY = 0;
		if (isHero) {
			switch (direction) {
			case TLEFT:
				nextX = x - 1;
				nextY = y;
				break;
			case TRIGHT:
				nextX = x + 1;
				nextY = y;
				break;
			case TUP:
				nextX = x;
				nextY = y - 1;
				break;
			case TDOWN:
				nextX = x;
				nextY = y + 1;
				break;
			case UP:
				nextX = x + 1;
				nextY = y - 1;
				break;
			case DOWN:
				nextX = x - 1;
				nextY = y + 1;
				break;
			case LEFT:
				nextX = x - 1;
				nextY = y - 1;
				break;
			case RIGHT:
				nextX = x + 1;
				nextY = y + 1;
				break;
			}
			chara = map.getRoleList().roleCheck(nextX, nextY);
			if (chara != null) {
				switch (direction) {
				case TLEFT:
					chara.setDirection(TRIGHT);
					break;
				case TRIGHT:
					chara.setDirection(TLEFT);
					break;
				case TUP:
					chara.setDirection(TDOWN);
					break;
				case TDOWN:
					chara.setDirection(TUP);
					break;
				case UP:
					chara.setDirection(DOWN);
					break;
				case DOWN:
					chara.setDirection(UP);
					break;
				case LEFT:
					chara.setDirection(RIGHT);
					break;
				case RIGHT:
					chara.setDirection(LEFT);
					break;
				}
			}
		} else {
			switch (direction) {
			case UP:
				nextX = x + 1;
				nextY = y - 1;
				break;
			case DOWN:
				nextX = x - 1;
				nextY = y + 1;
				break;
			case LEFT:
				nextX = x - 1;
				nextY = y - 1;
				break;
			case RIGHT:
				nextX = x + 1;
				nextY = y + 1;
				break;
			}
			chara = map.getRoleList().roleCheck(nextX, nextY);
			if (chara != null) {
				switch (direction) {
				case UP:
					chara.setDirection(DOWN);
					break;
				case DOWN:
					chara.setDirection(UP);
					break;
				case LEFT:
					chara.setDirection(RIGHT);
					break;
				case RIGHT:
					chara.setDirection(LEFT);
					break;
				}
			}
		}
		return chara;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getPx() {
		return px;
	}

	public int getPy() {
		return py;
	}

	public void setDirection(int dir) {
		direction = dir;
	}

	public synchronized boolean isMoving() {
		return isMoving;
	}

	public synchronized void setMoving(boolean flag) {
		isMoving = flag;
		movingLength = 0;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getMoveType() {
		return moveType;
	}

	private class AnimationThread extends Thread {
		public void run() {
			for (; isLoop;) {
				if (count < spriteSize) {
					count++;
				} else {
					count = 0;
				}
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	public boolean isAutoFinder() {
		return autoFinder;
	}

	public void setAutoFinder(boolean autoFinder) {
		this.autoFinder = autoFinder;
	}

	public int getIoffsetX() {
		return ioffsetX;
	}

	public int getIoffsetY() {
		return ioffsetY;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getSp() {
		return sp;
	}

	public void setSp(int sp) {
		this.sp = sp;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getMaxSP() {
		return maxSP;
	}

	public void setMaxSP(int maxSP) {
		this.maxSP = maxSP;
	}

	public boolean isShowName() {
		return showName;
	}

	public void setShowName(boolean showName) {
		if (this.getName() != null) {
			this.showName = showName;
		} else {
			this.showName = false;
		}
	}

	public boolean isShowState() {
		return showState;
	}

	public void setShowState(boolean showState) {
		this.showState = showState;
	}

	public boolean isHero() {
		return isHero;
	}

	public void setHero(boolean isHero) {
		this.isHero = isHero;
	}

	public String getScriptFile() {
		return scriptFile;
	}

	public void setScriptFile(String scriptFile) {
		this.scriptFile = scriptFile;
	}

}
