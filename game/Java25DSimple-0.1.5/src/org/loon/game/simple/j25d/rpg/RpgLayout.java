package org.loon.game.simple.j25d.rpg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.loon.game.simple.avg.AVGScript;
import org.loon.game.simple.j25d.GraphicsUtils;
import org.loon.game.simple.j25d.LSystem;
import org.loon.game.simple.j25d.MouseUtils;
import org.loon.game.simple.j25d.SimpleControl;
import org.loon.game.simple.j25d.rpg.role.Role;

public class RpgLayout extends SimpleControl implements Config {

	private BufferedImage screen = GraphicsUtils.createImage(LSystem.WIDTH,
			LSystem.HEIGHT, false);

	private Graphics2D graphics2D = screen.createGraphics();

	private AVGScript avgScript = new AVGScript();

	private RpgMap rpgMap;

	private Role hero;

	private InputKey leftKey;

	private InputKey rightKey;

	private InputKey upKey;

	private InputKey downKey;

	private InputKey spaceKey;

	private Random rand = new Random();

	private List startPath;

	private boolean isVisible;

	private int offsetX, offsetY;

	private int nx, ny, rx, ry;

	public RpgLayout(RpgMap rpgMap) {
		this.leftKey = new InputKey();
		this.rightKey = new InputKey();
		this.upKey = new InputKey();
		this.downKey = new InputKey();
		this.spaceKey = new InputKey(InputKey.DETECT_INITIAL_PRESS_ONLY);
		this.rpgMap = rpgMap;
		this.hero = rpgMap.getHero();
	}

	/**
	 * 移动事件
	 */
	public synchronized void next() {
		if (!avgScript.isVisible()) {
			setupWindowCheckInput();
			if (startPath != null && !startPath.isEmpty()) {
				if (!hero.isMoving()) {
					hero.autoDirection(startPath);
					hero.setMoving(true);
				}
			}
			heroMove();
			charaMove();
		}
	}

	/**
	 * 绘制rpg模式所有界面
	 */
	public synchronized void draw(Graphics g) {
		isVisible = avgScript.isVisible();
		offsetX = LSystem.WIDTH / 2 - hero.getPx();
		offsetX = Math.min(offsetX, 0);
		offsetX = Math.max(offsetX, LSystem.WIDTH - rpgMap.getWidth());
		offsetY = LSystem.HEIGHT / 2 - hero.getPy();
		offsetY = Math.min(offsetY, 0);
		offsetY = Math.max(offsetY, LSystem.HEIGHT - rpgMap.getHeight());
		if (isVisible) {
			rpgMap.draw(graphics2D, offsetX, offsetY);
			g.drawImage(screen, 0, 0, LSystem.WIDTH, LSystem.HEIGHT, null);
		} else {
			drawMap(g, offsetX, offsetY);
		}
		if (isVisible) {
			avgScript.setBackgroundCG(screen);
			avgScript.draw(g);
		}
	}

	/**
	 * 绘制地图显示部分
	 * 
	 * @param g
	 * @param offsetX
	 * @param offsetY
	 */
	private synchronized void drawMap(Graphics g, int offsetX, int offsetY) {
		rpgMap.draw(g, offsetX, offsetY);
		if (startPath != null) {
			for (int i = 0; i < startPath.size(); i++) {
				Cell2D cell = (Cell2D) startPath.get(i);
				g.setColor(Color.red);
				GraphicsUtils.setAlpha(g, 0.5d);
				g.drawOval(CS * cell.x() + offsetX, CS * cell.y() + offsetY,
						CS - 2, CS - 2);
				g.fillOval(CS * cell.x() + offsetX, CS * cell.y() + offsetY,
						CS - 2, CS - 2);
				GraphicsUtils.setAlpha(g, 1.0d);
			}
		}
	}

	private void setupWindowCheckInput() {
		if (leftKey.isPressed()) {
			if (!hero.isMoving()) {
				hero.setDirection(LEFT);
				hero.setMoving(true);
			}
		}
		if (rightKey.isPressed()) {
			if (!hero.isMoving()) {
				hero.setDirection(RIGHT);
				hero.setMoving(true);
			}
		}
		if (upKey.isPressed()) {
			if (!hero.isMoving()) {
				hero.setDirection(UP);
				hero.setMoving(true);
			}
		}
		if (downKey.isPressed()) {
			if (!hero.isMoving()) {
				hero.setDirection(DOWN);
				hero.setMoving(true);
			}
		}
		if (spaceKey.isPressed()) {
			if (hero.isMoving()) {
				return;
			}
			if (!avgScript.isVisible()) {
				Role role = hero.talkWith();
				if (role != null) {
					avgScript.format(role.getScriptFile());
					clearFindPath();
				}
			}
		}
		List transfers = rpgMap.getTransfers();
		if (transfers != null) {
			Iterator it = transfers.iterator();
			for (; it.hasNext();) {
				TransferEvent event = (TransferEvent) it.next();
				RpgMap result = event.send(hero);
				if (result != null) {
					rpgMap.stopAllRole();
					result.initialize();
					LSystem.currentGameHandler
							.setControl(new RpgLayout(result));
					return;
				}
			}
		}
	}

	private boolean heroMove() {
		if (hero.isMoving()) {
			return hero.move();
		}
		return false;
	}

	private void charaMove() {
		List roles = rpgMap.getRoleList().getRoles();
		for (int i = 0; i < roles.size(); i++) {
			Role role = (Role) roles.get(i);
			if (role.getMoveType() == 1) {
				if (role.isMoving()) {
					role.move();
				} else if (rand.nextDouble() < Role.PROB_MOVE) {
					role.setDirection(rand.nextInt(4));
					role.setMoving(true);
				}
			}
		}
	}

	public void mouseMoved(MouseEvent e) {
		if (avgScript.isVisible()) {
			avgScript.mouseMoved(e);
		}
	}

	public void mousePressed(MouseEvent e) {
		if (avgScript.isVisible()) {
			avgScript.mousePressed(e);
		}
	}

	public void mouseClicked(final MouseEvent e) {
		if (startPath != null) {
			clearFindPath();
		}
		if (!avgScript.isVisible()) {
			if (MouseUtils.isLeftMouse(e)) {
				Point point = e.getPoint();
				nx = (point.x / CS) + rpgMap.getSelfFirstX();
				ny = (point.y / CS) + rpgMap.getSelfFirstY();
				Cell2D goal = new Cell2D(nx, ny);
				Iterator it = rpgMap.getRoleList().getRoles().iterator();
				for (; it.hasNext();) {
					Role role = (Role) it.next();
					if (!role.isHero()) {
						rx = role.getX();
						ry = role.getY();
						if ((nx > rx && nx <= rx + 1 && ny >= ry - 1 && ny <= ry + 1)) {
							avgScript.format(role.getScriptFile());
							return;
						}
					}
				}
				startPath = rpgMap.findPath(hero, goal);
			}
		}
	}

	private void clearFindPath() {
		if (startPath != null) {
			startPath.clear();
			startPath = null;
		}
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT) {
			leftKey.press();
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			rightKey.press();
		}
		if (keyCode == KeyEvent.VK_UP) {
			upKey.press();
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			downKey.press();
		}
		if (keyCode == KeyEvent.VK_SPACE) {
			spaceKey.release();
		}
		clearFindPath();
		GraphicsUtils.wait(10);
	}

	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT) {
			leftKey.release();
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			rightKey.release();
		}
		if (keyCode == KeyEvent.VK_UP) {
			upKey.release();
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			downKey.release();
		}
		if (keyCode == KeyEvent.VK_SPACE) {
			spaceKey.press();
		}
		clearFindPath();
		GraphicsUtils.wait(10);
	}

	public void keyTyped(KeyEvent e) {
	}
}
