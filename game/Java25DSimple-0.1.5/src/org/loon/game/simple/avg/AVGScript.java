package org.loon.game.simple.avg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.List;

import org.loon.game.simple.j25d.GraphicsUtils;
import org.loon.game.simple.j25d.LSystem;
import org.loon.game.simple.j25d.SimpleControl;
import org.loon.game.simple.j25d.command.Command;

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
 * @email：ceponline@yahoo.com.cn
 * @version 0.1
 */
public class AVGScript extends SimpleControl {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1982090123122834814L;

	private Color color;

	private int sleep;

	private int sleepMax;

	private boolean isMessage;

	private boolean isSelectMessage;

	private String scriptName;

	private int stringMaxLine = 8;

	private int selectFlag = 0;

	private boolean printNext;
	
	private boolean visible;

	private boolean isClick;

	private boolean moving;

	private int shakeNumber;

	private String roleName;

	private CG cg = new CG();

	private Pause pause = new Pause();

	private Command command;

	private MessageDialog dialog = new MessageDialog();

	private MessagePrint mesPrint = new MessagePrint(0, 320);

	private String[] selectMessages;

	private int[] flags;

	public AVGScript() {
	}

	public AVGScript(final String initscript) {
		format(initscript);
	}

	public void format(final String initscript) {
		if (initscript == null) {
			return;
		}
		pause.start();
		initialize(initscript);
		Command.resetCache();
		runScript(scriptName);
	}

	private void initialize(final String initscript) {
		scriptName = initscript;
		shakeNumber = 0;
		sleep = 0;
		selectFlag = 0;
		shakeNumber = -1;
		isClick = false;
		isSelectMessage = false;
		isMessage = false;
		selectMessages = new String[stringMaxLine];
		flags = new int[stringMaxLine];
		pause.intermit();
		dialog.initialize();
	}

	public void finalize() {
		flush();
	}

	public void flush() {
		cg = null;
		dialog = null;
		mesPrint = null;
		selectMessages = null;
		flags = null;
		pause.stop();
		pause = null;
	}

	public boolean nextMessages() {
		return mesPrint.next();
	}

	public void setBackgroundCG(Image image) {
		cg.setBackgroundCG(image);
	}


	/**
	 * 绘制游戏界面
	 */
	public synchronized void draw(final Graphics g) {
		if (sleep == 0) {
			if (cg.getBackgroundCG() != null) {
				if (shakeNumber > 0) {
					g.drawImage(cg.getBackgroundCG(), shakeNumber / 2
							- SimpleControl.rand.nextInt(shakeNumber),
							shakeNumber / 2
									- SimpleControl.rand.nextInt(shakeNumber),
							null);
				} else {
					g.drawImage(cg.getBackgroundCG(), 0, 0, null);
				}
			}
			int moveCount = 0;
			for (int i = 0; i < cg.getCharas().size(); i++) {
				Chara chara = (Chara) cg.getCharas().get(i);
				float value = 1.0f;
				if (chara.next()) {
					value = chara.getNextAlpha();
					moveCount++;
				}
				GraphicsUtils.setAlpha(g, value);
				chara.draw(g);
				GraphicsUtils.setAlpha(g, 1.0f);
			}
			moving = (moveCount != 0);
			if (isMessage && selectMessages != null
					&& selectMessages.length > 0) {
				printNext = false;
				int size = 20;
				pause.go();
				dialog.showDialog(g);
				Font font = g.getFont();
				g.setFont(GraphicsUtils.getFont("黑体", 0, size));
				GraphicsUtils.setAntialias(g, true);
				g.setColor(Color.white);
				dialog.showRoleName(g, roleName);
				if (isSelectMessage) {
					char[] meschars;
					int sizeWidth = -(size * 2);
					int left = dialog.getMESSAGE_LINE_X() + 2;
					int top = dialog.getMESSAGE_LINE_Y() + 20;
					for (int i = 0; i < stringMaxLine; i++) {
						meschars = selectMessages[i].toCharArray();
						for (int j = 0; j < meschars.length; j++) {
							g
									.drawString(String.valueOf(meschars[j]),
											(size * j) + left - sizeWidth, top
													+ i * 20);
						}
						if (flags[selectFlag] != -1) {
							dialog.showDialog(g, selectFlag, size,
									LSystem.FONT_SIZE);
						}
					}
				} else {
					printNext = mesPrint.next();
					mesPrint.draw(g);
				}
				if (!printNext && !moving) {
					pause.draw(g, dialog.getMESSAGE_LINE_X2()
							- dialog.getMESSAGE_LINE_X1() - 30, dialog
							.getMESSAGE_LINE_Y2() - 60);
				}
				GraphicsUtils.setAntialias(g, false);
				g.setFont(font);
			} else {
				pause.intermit();
			}

		} else {
			sleep--;
			if (color != null) {
				double alpha = (double) (sleepMax - sleep) / sleepMax;
				if (alpha < 0.7) {
					if (cg.getBackgroundCG() != null) {
						g.drawImage(cg.getBackgroundCG(), 0, 0, null);
					}
					GraphicsUtils.setAlpha(g, alpha);
					g.setColor(color);
					g.fillRect(0, 0, LSystem.WIDTH, LSystem.HEIGHT);
					GraphicsUtils.setAlpha(g, 1.0d);
				}
				GraphicsUtils.wait(20);
			}
		}
	}

	public void addCG(String fileName) {
		addCG(fileName, 0, 0);
	}

	public void addCG(String fileName, int x, int y) {
		cg.addImage(fileName, x, y);
	}

	public synchronized void select(int type) {
		if (command != null) {
			command.select(type);
			isSelectMessage = false;
		}
	}

	public synchronized String getSelect() {
		if (command != null) {
			return command.getSelect();
		}
		return null;
	}

	public synchronized void resetFlag() {
		if (!isMessage) {
			return;
		}
		if (selectMessages != null) {
			int count = (SimpleControl.mouse.y - dialog.getMESSAGE_LINE_Y()) / 25;
			if (count < 0) {
				count = 0;
				return;
			}
			if (count >= stringMaxLine) {
				count = 0;
				return;
			}
			if (flags[count] != -1) {
				isClick = true;
			}
			int maxSize = 0;
			for (; maxSize < selectMessages.length; maxSize++) {
				if (selectMessages[maxSize].length() == 0) {
					break;
				}
			}
			maxSize -= 1;
			if (maxSize > 0 && count > maxSize) {
				count = maxSize;
			}
			selectFlag = count;
		}
	}

	public boolean isVisible() {
		return visible;
	}

	public void mouseMoved(MouseEvent e) {
		if (printNext) {
			return;
		}
		super.mouseMoved(e);
		resetFlag();
	}

	public void mousePressed(MouseEvent e) {
		if (printNext) {
			return;
		}
		super.mousePressed(e);
		resetFlag();
		boolean isNext = false;
		if (moving) {
			return;
		}
		if (!isSelectMessage && SimpleControl.left_click && sleep <= 0) {
			if (!isMessage) {
				isMessage = true;
			}
			isNext = true;

		} else if (isMessage && isClick && SimpleControl.left_click) {
			if (flags[selectFlag] != -1) {
				// 变更选择变量
				select(flags[selectFlag]);
				isNext = true;
				isSelectMessage = false;
			}
		}

		if (isNext && !isSelectMessage) {
			// 逐行解释执行脚本
			nextScript();
		}
		GraphicsUtils.wait(10);
	}

	private synchronized void nextScript() {
		isMessage = false;
		isClick = false;
		int count = 0;
		for (int i = 0; i < stringMaxLine; i++) {
			selectMessages[i] = "";
			flags[i] = -1;
		}
		if (command != null) {
			for (; visible = command.next();) {
				// 返回本行命令执行结果
				String result = command.doExecute();
				if (result == null) {
					nextScript();
					break;
				}

				// 分解命令
				List commands = Command.splitToList(result, " ");
				int size = commands.size();
				String cmdFlag = (String) commands.get(0);

				String mesFlag = null, orderFlag = null, lastFlag = null;
				if (size == 2) {
					mesFlag = (String) commands.get(1);
				} else if (size == 3) {
					mesFlag = (String) commands.get(1);
					orderFlag = (String) commands.get(2);
				} else if (size == 4) {
					mesFlag = (String) commands.get(1);
					orderFlag = (String) commands.get(2);
					lastFlag = (String) commands.get(3);
				}
				if (cmdFlag.equalsIgnoreCase("wait")) {
					isMessage = true;
					break;
				}
				if (cmdFlag.equalsIgnoreCase("mes")) {
					roleName = null;
					isMessage = true;
					roleName = Command.getNameTag(mesFlag, "{", "}");
					String nMessage = null;
					if (roleName != null) {
						int nameLength = roleName.length() + 2;
						nMessage = mesFlag.substring(nameLength, mesFlag
								.length());
					} else {
						nMessage = mesFlag;
					}
					mesPrint.setMessage(nMessage);
					flags[count] = -1;
					if (++count == stringMaxLine) {
						break;
					}
					break;
				}
				if (cmdFlag.equalsIgnoreCase("selects")) {
					isMessage = true;
					isSelectMessage = true;
					String[] selects = command.getReads();
					for (int i = 0; i < selects.length; i++) {
						selectMessages[i] = selects[i];
						flags[i] = i;
					}
					break;
				}
				if (cmdFlag.equalsIgnoreCase("fname")) {
					roleName = null;
					break;
				}
				if (cmdFlag.equalsIgnoreCase("shake")) {
					shakeNumber = Integer.valueOf(mesFlag).intValue();
					continue;
				}
				if (cmdFlag.equalsIgnoreCase("cgwait")) {
					isMessage = false;
					break;
				}
				if (cmdFlag.equalsIgnoreCase("sleep")) {
					sleep = Integer.valueOf(mesFlag).intValue();
					sleepMax = Integer.valueOf(mesFlag).intValue();
					isMessage = false;
					break;
				}
				if (cmdFlag.equalsIgnoreCase("flash")) {
					String[] colors = mesFlag.split(",");
					if (color == null && colors != null && colors.length == 3) {
						color = new Color(
								Integer.valueOf(colors[0]).intValue(), Integer
										.valueOf(colors[1]).intValue(), Integer
										.valueOf(colors[2]).intValue());
						sleep = 20;
						sleepMax = sleep;
						isMessage = false;
					} else {
						color = null;
					}
					break;
				}
				if (cmdFlag.equalsIgnoreCase("gb")) {
					if (mesFlag == null) {
						return;
					}
					if (mesFlag.equalsIgnoreCase("none")) {
						cg.setBackgroundCG(null);
					} else {
						cg.setBackgroundCG(GraphicsUtils.loadImage(mesFlag));
					}
					continue;
				}
				if (cmdFlag.equalsIgnoreCase("cg")) {
					if (mesFlag == null) {
						return;
					}
					// 删除
					if (mesFlag.equalsIgnoreCase("del")) {
						if (orderFlag != null) {
							cg.removeImage(orderFlag);
						} else {
							cg.clear();
						}
					} else if (lastFlag != null
							&& "to".equalsIgnoreCase(orderFlag)) {
						Chara chara = cg.removeImage(mesFlag);
						if (chara != null) {
							int x = chara.getX();
							int y = chara.getY();
							chara = new Chara(lastFlag, 0, 0);
							chara.setMove(false);
							chara.setX(x);
							chara.setY(y);
							cg.addChara(lastFlag, chara);
						}
					} else {
						// 移动
						int x = 0, y = 0;
						if (orderFlag != null) {
							x = Integer.parseInt(orderFlag);
						}
						if (size >= 4) {
							y = Integer.parseInt((String) commands.get(4));
						}
						cg.addImage(mesFlag, x, y);
					}
					continue;
				}

			}
		}
	}

	private synchronized void runScript(final String fileName) {
		if (fileName == null) {
			return;
		}
		if (command == null) {
			command = new Command(fileName);
			// 刷新脚本缓存
			Command.resetCache();
		} else {
			command.formatCommand(fileName);
		}
		nextScript();
	}

}
