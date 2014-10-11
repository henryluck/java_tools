package org.loon.game.simple.j25d;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
public class GameFrame extends Frame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 198284399945549558L;

	private IGameHandler game;

	private String titleName;

	private IGameCanvas gameCanvas;

	private boolean isFullScreen;

	private DisplayMode normalMode;

	public GameFrame(String titleName, int width, int height) {
		this(new GameHandler(), titleName, width, height);
	}

	public GameFrame(IGameHandler game, String titleName, int width, int height) {
		super(titleName);
		this.game = game;
		this.titleName = titleName;
		this.addKeyListener(game);
		this.setPreferredSize(new Dimension(width + 5, height + 25));
		this.requestFocus();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.initCanvas(width, height);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setIgnoreRepaint(true);
		this.gameCanvas.createBufferGraphics();
		LSystem.currentGameHandler = game;
		LSystem.currentCanvas = gameCanvas;
	}

	public void setCursor(Cursor cursor) {
		LSystem.setSystemCursor(cursor);
	}

	public boolean isFullScreen() {
		return isFullScreen;
	}

	public synchronized void updateNormalScreen() {
		if (!isFullScreen) {
			return;
		} else {
			isFullScreen = false;
			this.setVisible(false);
			ScreenManager.graphicsDevice.setDisplayMode(normalMode);
			this.removeNotify();
			this.setUndecorated(false);
			ScreenManager.graphicsDevice.setFullScreenWindow(null);
			this.addNotify();
			this.pack();
			this.setVisible(true);
			this.updateDisplayMode();
			return;
		}
	}

	public synchronized void updateFullScreen() {
		if (isFullScreen) {
			return;
		}
		isFullScreen = true;
		try {
			DisplayMode useDisplayMode = ScreenManager
					.searchFullScreenModeDisplay();
			if (useDisplayMode == null) {
				return;
			}
			this.setVisible(false);
			this.removeNotify();
			this.setUndecorated(true);
			ScreenManager.graphicsDevice.setFullScreenWindow(this);
			ScreenManager.graphicsDevice.setDisplayMode(useDisplayMode);
			this.addNotify();
			this.setVisible(true);
			this.pack();
			this.updateDisplayMode();
			this.requestFocus();
		} catch (RuntimeException e) {
			this.updateNormalScreen();
		}
	}

	private synchronized void updateDisplayMode() {
		createBufferStrategy(3);
	}
	
	public void mainLoop(){
		gameCanvas.mainLoop();
	}

	public void setFPS(boolean isFPS) {
		gameCanvas.setShowFPS(isFPS);
	}

	public int getFPS() {
		return gameCanvas.getFPS();
	}

	public void showFrame() {
		this.setVisible(true);
	}

	public void hideFrame() {
		this.setVisible(false);
	}


	private void initCanvas(final int width, final int height) {
		GameCanvas gameCanvas = new GameCanvas(game, width, height);
		this.gameCanvas = gameCanvas;
		this.gameCanvas.startPaint();
		this.add(gameCanvas);
	}

	public IGameHandler getGame() {
		return this.game;
	}

	public String getTitleName() {
		return titleName;
	}

}
