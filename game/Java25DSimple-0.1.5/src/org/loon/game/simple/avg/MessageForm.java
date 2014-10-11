package org.loon.game.simple.avg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import org.loon.game.simple.j25d.GraphicsUtils;
import org.loon.game.simple.j25d.LSystem;
import org.loon.game.simple.j25d.SimpleControl;
import org.loon.game.simple.j25d.resource.BaleResource;

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
public class MessageForm extends SimpleControl {

	private int messageSize = 30;

	private Image screenImage;

	private String[] messages;

	private int messageCount;

	private int messageIndex;

	private int next;

	private boolean initFlag;

	private StringBuffer messageBuffer = new StringBuffer(messageSize);

	private int messageLeft = 0;

	private int messageTop = 0;

	private boolean initDraw = false;

	final private Font font;

	public MessageForm(final int effectIndex, final Image screen,
			final String[] strContent) {

		this.font = GraphicsUtils.getFont("����songheiti黑体", 0, 20);

		this.messageTop = 50;

		this.messages = new String[messageSize];

		this.next = strContent[0].length();

		int length = strContent.length;

		System.arraycopy(strContent, 0, messages, 0, length);

		screenImage = screen;

	}

	private void clear() {
		messageBuffer.delete(0, messageBuffer.length());
		if (messageIndex > next) {
			messageIndex = 0;
		}

		messageCount = 0;
		messageIndex++;
		if (messages[messageIndex] != null) {
			next = messages[messageIndex].length();
		} else {
			next = 0;
		}
	}

	public void draw(Graphics g) {
		g.drawImage(screenImage, 0, 0, null);
		if (!initDraw) {
			initDraw = true;
		} else {
			Font superFont = g.getFont();
			g.setFont(font);
			String showMessage = messageBuffer.toString();
			int fontSize = g.getFont().getSize();
			int fontHeight = g.getFontMetrics().getHeight();
			messageLeft = (LSystem.WIDTH / 2 - (fontSize * messageSize) / 2) - 10;
			char[] messages = showMessage.toCharArray();
			int size = messages.length;
			int index = 0;
			int offset = 0;
			for (int i = 0; i < messages.length; i++) {
				boolean newLine = false;
				if (messages[i] == '\n') {
					index = 0;
					offset++;
					newLine = true;
				} else if (index > messageSize) {
					index = 0;
					offset++;
					newLine = false;
				}
				int fontLeft = messageLeft + (fontSize * index);
				GraphicsUtils.setAntialias(g, true);
				if (i != size - 1) {
					g.setColor(Color.white);
					GraphicsUtils.drawStyleString(g, String
							.valueOf(messages[i]), fontLeft,
							(offset * fontHeight) + messageTop, Color.black,
							Color.white);
				} else if (!newLine) {
					g.setColor(Color.red);
					g.drawImage(BaleResource.creeseIcon, fontLeft,
							(offset * fontHeight) + messageTop, null);
				}
				GraphicsUtils.setAntialias(g, false);
				index++;

			}
			g.setFont(superFont);
			if (messageBuffer.length() > 1) {
				messageBuffer.delete(messageBuffer.length() - 1, messageBuffer
						.length());
			}
		}
	}

	public void next() {
		if (initFlag) {
			char charMessage = '\0';
			if (messageCount == next) {
				messages[messageIndex] = messages[messageIndex].substring(0,
						messageCount);
				clear();
			}
			if (next == 0) {
				// ControlResource.updateControlEvent(eventIndex);
				return;
			}
			charMessage = messages[messageIndex].charAt(messageCount);
			messageBuffer.append(charMessage);
			messageBuffer.append("_");
			messageCount++;
			GraphicsUtils.wait(150);
		} else {
			initFlag = true;
		}
	}

}
