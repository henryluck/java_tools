package org.loon.game.simple.avg;

import java.awt.Color;
import java.awt.Graphics;

import org.loon.game.simple.avg.message.Message;
import org.loon.game.simple.j25d.GraphicsUtils;
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
public class MessageDialog {

	private int MESSAGE_LINE_X1 = 25;

	private int MESSAGE_LINE_Y1 = 290;

	private int MESSAGE_LINE_X2 = LSystem.WIDTH - MESSAGE_LINE_X1 + 5;

	private int MESSAGE_LINE_Y2 = LSystem.HEIGHT - 45;

	private int MESSAGE_LINE_X = 15;

	private int MESSAGE_LINE_Y = 298;

	private boolean MESSAGE_FRAME = true;

	private int LINE;

	public void initialize() {
		setLINE(MESSAGE_LINE_X2 - MESSAGE_LINE_X / 15);
	}

	public void showDialog(Graphics g, int select, int font, int fontSize) {
		Message.setWindowBuoyageMessage(g, (getMESSAGE_LINE_X() + font), select
				* (font + fontSize) + getMESSAGE_LINE_Y());
	}

	public int showRoleName(Graphics g, String name) {
		if (name != null && name.length() > 0) {
			int hSize = 10;
			int nameLength = name.length() + 2;
			int fontWidth = g.getFontMetrics().stringWidth(name);
			int fontHeight = g.getFontMetrics().getHeight();
			int nameX = this.getMESSAGE_LINE_X() + hSize;
			int nameY = this.getMESSAGE_LINE_Y() - 70;
			int width = fontWidth + 50;
			int height = 100;
			g.drawImage(Message.loadFrameWindow(width, LSystem.FONT_TYPE),
					nameX, nameY, null);
			GraphicsUtils.drawStyleString(g, name, nameX - 5
					+ (width / 2 - fontWidth / 2), nameY
					+ (height / 2 - fontHeight / 2), Color.blue, Color.white);
			return nameLength;
		}
		return 0;
	}

	public void showDialog(Graphics g) {
		if (MESSAGE_FRAME) {
			Message.setWindowFrame(g, MESSAGE_LINE_X1, MESSAGE_LINE_Y1,
					MESSAGE_LINE_X2, MESSAGE_LINE_Y2);
		}
	}

	public int getLINE() {
		return LINE;
	}

	public void setLINE(int line) {
		LINE = line;
	}

	public boolean isMESSAGE_FRAME() {
		return MESSAGE_FRAME;
	}

	public void setMESSAGE_FRAME(boolean message_frame) {
		MESSAGE_FRAME = message_frame;
	}

	public int getMESSAGE_LINE_X() {
		return MESSAGE_LINE_X;
	}

	public void setMESSAGE_LINE_X(int message_line_x) {
		MESSAGE_LINE_X = message_line_x;
	}

	public int getMESSAGE_LINE_X1() {
		return MESSAGE_LINE_X1;
	}

	public void setMESSAGE_LINE_X1(int message_line_x1) {
		MESSAGE_LINE_X1 = message_line_x1;
	}

	public int getMESSAGE_LINE_X2() {
		return MESSAGE_LINE_X2;
	}

	public void setMESSAGE_LINE_X2(int message_line_x2) {
		MESSAGE_LINE_X2 = message_line_x2;
	}

	public int getMESSAGE_LINE_Y() {
		return MESSAGE_LINE_Y;
	}

	public void setMESSAGE_LINE_Y(int message_line_y) {
		MESSAGE_LINE_Y = message_line_y;
	}

	public int getMESSAGE_LINE_Y1() {
		return MESSAGE_LINE_Y1;
	}

	public void setMESSAGE_LINE_Y1(int message_line_y1) {
		MESSAGE_LINE_Y1 = message_line_y1;
	}

	public int getMESSAGE_LINE_Y2() {
		return MESSAGE_LINE_Y2;
	}

	public void setMESSAGE_LINE_Y2(int message_line_y2) {
		MESSAGE_LINE_Y2 = message_line_y2;
	}
}
