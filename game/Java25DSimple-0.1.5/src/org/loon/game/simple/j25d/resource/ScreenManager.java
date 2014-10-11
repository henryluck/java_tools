package org.loon.game.simple.j25d.resource;

import java.awt.DisplayMode;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import org.loon.game.simple.j25d.LSystem;



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
public class ScreenManager {

	final public static GraphicsEnvironment environment = GraphicsEnvironment
			.getLocalGraphicsEnvironment();

	final public static GraphicsDevice graphicsDevice = environment
			.getDefaultScreenDevice();

	final public static GraphicsConfiguration graphicsConfiguration = graphicsDevice
			.getDefaultConfiguration();
	
	public static DisplayMode searchFullScreenModeDisplay() {
		return searchFullScreenModeDisplay(graphicsDevice);
	}
	
	public static DisplayMode searchFullScreenModeDisplay(GraphicsDevice device) {
		DisplayMode displayModes[] = device.getDisplayModes();
		int currentDisplayPoint = 0;
		DisplayMode fullScreenMode = null;
		DisplayMode normalMode = device.getDisplayMode();
		DisplayMode adisplaymode[] = displayModes;
		int i = 0;
		for (int j = adisplaymode.length; i < j; i++) {
			DisplayMode mode = adisplaymode[i];
			if (mode.getWidth() == LSystem.WIDTH
					&& mode.getHeight() == LSystem.HEIGHT) {
				int point = 0;
				if (normalMode.getBitDepth() == mode.getBitDepth())
					point += 40;
				else
					point += mode.getBitDepth();
				if (normalMode.getRefreshRate() == mode.getRefreshRate())
					point += 5;
				if (currentDisplayPoint < point) {
					fullScreenMode = mode;
					currentDisplayPoint = point;
				}
			}
		}

		return fullScreenMode;
	}
}
