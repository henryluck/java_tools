package org.loon.game.simple.j25d;

import java.awt.Cursor;
import java.util.Random;


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
public class LSystem {

	final static public Random rand = new Random();

	final static public String encoding = "UTF-8";

	final static public String LS = System.getProperty("line.separator", "\n");

	final static public String FS = System.getProperty("file.separator", "\\");

	public static int FONT_TYPE = 15;

	public static int FONT_SIZE = 1;

	public static String FONT = "����";

	public static IGameHandler currentGameHandler;

	public static IGameCanvas currentCanvas;

	public static int WIDTH;

	public static int HEIGHT;

	public static void setSystemCursor(Cursor cursor) {
		if (currentCanvas != null) {
			currentCanvas.setCursor(cursor);
		}
	}

	public static String getExtension(String name) {
		int index = name.lastIndexOf(".");
		if (index == -1) {
			return "";
		} else {
			return name.substring(index + 1).toLowerCase();
		}
	}

	public static final String replaceIgnoreCase(String line, String oldString,
			String newString) {
		if (line == null)
			return null;
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			char line2[] = line.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = lcLine.indexOf(lcOldString, i)) > 0; j = i) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		} else {
			return line;
		}
	}

}
