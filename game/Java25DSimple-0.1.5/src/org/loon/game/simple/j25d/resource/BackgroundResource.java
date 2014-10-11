package org.loon.game.simple.j25d.resource;

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
public abstract class BackgroundResource {

	final static public Image windowsBackgroundCanvas;
	
	final static public Image windowsCanvas;

	final static public Image[] pauseImage;

	
	static {
		windowsBackgroundCanvas =  GraphicsUtils.loadImage("image/system/back.png");
		windowsCanvas = GraphicsUtils.loadImage("image/system/window.png");
		pauseImage = GraphicsUtils.getSplitImages("image/system/pause.png",37,55);
	}

	private BackgroundResource() {
	}

}
