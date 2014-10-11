package org.loon.game.simple.j25d;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

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
public class GameCanvas extends Canvas implements IGameCanvas, Runnable {

	private static final long serialVersionUID = 1982278682597393958L;

	final static private Toolkit systemToolKit = GraphicsUtils.toolKit;

	private boolean start;

	private boolean isFPS;

	final static private Font fpsFont = GraphicsUtils.getFont("Dialog", 0, 20);

	final static private int fpsX = 5;

	final static private int fpsY = 20;

	private int fps = 0;

	private int frames = 0;

	private long totalTime = 0;

	private long curTime = System.currentTimeMillis();

	private long lastTime = curTime;

	private IGameHandler game;

	private Graphics canvasGraphics = null;

	private IControl control;

	private BufferStrategy bufferStrategy;

	private Thread mainLoop;

	public GameCanvas(IGameHandler handler, int width, int height) {
		format(handler, width, height);
	}

	public GameCanvas() {
		format(LSystem.currentGameHandler, LSystem.WIDTH, LSystem.HEIGHT);
	}

	public void format(IGameHandler handler, int width, int height) {
		LSystem.WIDTH = width;
		LSystem.HEIGHT = height;
		this.game = handler;
		this.start = false;
		this.setBackground(Color.black);
		this.setPreferredSize(new Dimension(width, height));
		this.setIgnoreRepaint(true);
		this.addKeyListener(handler);
		this.addMouseListener(handler);
		this.addMouseMotionListener(handler);
	}

	public void createBufferGraphics() {
		createBufferStrategy(2);
		bufferStrategy = getBufferStrategy();
	}

	public void run() {
		do {
			if (!start) {
				continue;
			}
			do {
				canvasGraphics = bufferStrategy.getDrawGraphics();
				control = game.getControl();
				control.next();
				control.draw(canvasGraphics);
				if (isFPS) {
					lastTime = curTime;
					curTime = System.currentTimeMillis();
					totalTime += curTime - lastTime;
					if (totalTime > 1000) {
						totalTime -= 1000;
						fps = frames;
						frames = 0;
					}
					++frames;
					canvasGraphics.setFont(fpsFont);
					canvasGraphics.setColor(Color.white);
					GraphicsUtils.setAntialias(canvasGraphics, true);
					canvasGraphics.drawString(("FPS:" + fps).intern(), fpsX,
							fpsY);
					GraphicsUtils.setAntialias(canvasGraphics, false);
				}
				bufferStrategy.show();
				canvasGraphics.dispose();
				systemToolKit.sync();
			} while (bufferStrategy.contentsLost());
			if (isFocusOwner()) {
				Thread.yield();
				continue;
			}
			GraphicsUtils.wait(30);
		} while (true);
	}

	public Thread getMainLoop() {
		return mainLoop;
	}

	public void mainLoop() {
		this.mainLoop = new Thread(this);
		this.mainLoop.setPriority(Thread.MIN_PRIORITY);
		this.mainLoop.start();
	}

	public void mainStop() {
		this.mainLoop = null;
	}

	public void startPaint() {
		this.start = true;
	}

	public void endPaint() {
		this.start = false;
	}

	public int getFPS() {
		return this.fps;
	}

	public void setShowFPS(boolean isFPS) {
		this.isFPS = isFPS;
	}

}
