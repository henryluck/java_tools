package org.loon.game.simple.avg;

import java.awt.Graphics;
import java.awt.Image;

import org.loon.game.simple.j25d.resource.BackgroundResource;

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
public class Pause implements Runnable {

	private Image[] myImage;

	private int sleepMax;

	private int sleep;

	private boolean isLoop;

	private boolean isRun;

	private Image nowImage;

	public Pause() {
		this(BackgroundResource.pauseImage);
	}

	public Pause(Image[] image) {
		myImage = image;
		sleepMax = myImage.length - 1;
		nowImage = myImage[0];
	}

	public void go() {
		isRun = true;
	}

	public void intermit() {
		isRun = false;
	}

	public void start() {
		go();
		isLoop = true;
		Thread thread = new Thread(this);
		thread.setPriority(Thread.MIN_PRIORITY);
		thread.start();
	}

	public void stop() {
		intermit();
		isLoop = false;
	}

	public void run() {
		for (; isLoop;) {
			if (isRun) {
				nowImage = myImage[sleep];
				if (sleep < sleepMax) {
					sleep++;
				} else {
					sleep = 0;
				}
			}
			try {
				Thread.sleep(500);
			} catch (Exception e) {
			}
		}
	}

	public void draw(Graphics g, int x, int y) {
		g.drawImage(nowImage, x, y, null);
	}

}
