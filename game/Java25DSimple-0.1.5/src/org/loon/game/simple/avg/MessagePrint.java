package org.loon.game.simple.avg;

import java.awt.Color;
import java.awt.Graphics;

import org.loon.game.simple.j25d.GraphicsUtils;
import org.loon.game.simple.j25d.LSystem;
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
public class MessagePrint {

	private char[] showMessages = new char[] { '\0' };

	private Color fontColor = Color.white;

	private int interceptMaxString = 0;

	private int interceptCount = 0;

	private int messageSize = 26;

	private String messages;

	private int messageCount;

	private boolean onComplete;

	private int next;

	private StringBuffer messageBuffer = new StringBuffer(messageSize);

	private int messageLeft;

	private int messageTop;

	private int nowLeft;

	public MessagePrint(int left, int top) {
		this("",left,top);
	}
	
	public MessagePrint(String context, int left, int top) {
		this.setMessage(context);
		this.messageLeft = left;
		this.messageTop = top;
	}

	public void setMessage(String context) {
		this.messages = context;
		this.next = context.length();
		this.onComplete = false;
		this.messageCount = 0;
		this.messageBuffer.delete(0, messageBuffer.length());
	}

	public String getMessage() {
		return messages;
	}

	private Color getColor(char flagName) {
		if ('r' == flagName || 'R' == flagName) {
			return Color.red;
		}
		if ('b' == flagName || 'B' == flagName) {
			return Color.black;
		}
		if ('l' == flagName || 'L' == flagName) {
			return Color.blue;
		}
		if ('g' == flagName || 'G' == flagName) {
			return Color.green;
		}
		if ('o' == flagName || 'O' == flagName) {
			return Color.orange;
		}
		if ('y' == flagName || 'Y' == flagName) {
			return Color.yellow;
		} else {
			return null;
		}
	}

	public void draw(Graphics g) {
		int fontSize = g.getFont().getSize();
		int fontHeight = g.getFontMetrics().getHeight();
		nowLeft = ((LSystem.WIDTH - messageLeft) / 2 - (fontSize * messageSize) / 2) - 10;
		int size = showMessages.length;
		int index = 0;
		int offset = 0;
		boolean newLine = false;
		for (int i = 0; i < size; i++) {
			if (interceptCount < interceptMaxString) {
				interceptCount++;
				g.setColor(fontColor);
				continue;
			} else {
				interceptMaxString = 0;
				interceptCount = 0;
			}
			if (showMessages[i] == '\n') {
				index = 0;
				offset++;
				newLine = true;
			} else if (showMessages[i] == '<') {
				Color color = getColor(showMessages[i < size - 1 ? i + 1 : i]);
				if (color != null) {
					interceptMaxString = 1;
					fontColor = color;
				}
				continue;
			} else if (showMessages[i] == '/') {
				if (showMessages[i < size - 1 ? i + 1 : i] == '>') {
					interceptMaxString = 1;
					fontColor = Color.white;
				}
				continue;
			} else if (index > messageSize) {
				index = 0;
				offset++;
				newLine = false;
			}
			int fontLeft = nowLeft + (fontSize * index);
			if (i != size - 1) {
				GraphicsUtils.setAntialias(g, true);
				g.drawString(String.valueOf(showMessages[i]), messageLeft
						+ fontLeft, (offset * fontHeight) + messageTop);
				GraphicsUtils.setAntialias(g, false);
			} else if (!newLine) {
				g.drawImage(BaleResource.creeseIcon, messageLeft + fontLeft,
						(offset * fontHeight) + messageTop, null);
			}
			index++;
		}
		if (messageCount == next) {
			onComplete = true;
		}
		if (!onComplete) {
			if (messageBuffer.length() > 1) {
				messageBuffer.delete(messageBuffer.length() - 1, messageBuffer
						.length());
			}
			GraphicsUtils.wait(20);
		}

	}

	public boolean next() {
		if (!onComplete) {
			char charMessage = '\0';
			if (messageCount == next) {
				onComplete = true;
				return false;
			}
			charMessage = messages.charAt(messageCount);
			messageBuffer.append(charMessage);
			messageBuffer.append("_");
			showMessages = messageBuffer.toString().toCharArray();
			messageCount++;
		} else {
			return false;
		}
		return true;
	}

	public int getMessageLeft() {
		return messageLeft;
	}

	public void setMessageLeft(int messageLeft) {
		this.messageLeft = messageLeft;
	}

	public int getMessageTop() {
		return messageTop;
	}

	public void setMessageTop(int messageTop) {
		this.messageTop = messageTop;
	}
}
