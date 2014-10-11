package org.loon.game.simple.j25d;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;



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
public class GameHandler implements IGameHandler {

	public GameHandler() {

	}

	public synchronized void draw(final Graphics2D g) {
		if (SimpleControl.validControl()) {
			SimpleControl.currentControl.draw(g);
		}
	}

	public synchronized IControl getControl() {
		return SimpleControl.currentControl;
	}

	public synchronized void setControl(final IControl control) {
		SimpleControl.currentControl = control;
	}

	

	public void keyPressed(KeyEvent e) {
		if (SimpleControl.validControl())
			SimpleControl.currentControl.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		if (SimpleControl.validControl())
			SimpleControl.currentControl.keyReleased(e);
	}

	public void keyTyped(KeyEvent e) {
		if (SimpleControl.validControl())
			SimpleControl.currentControl.keyTyped(e);
	}

	public void mouseClicked(MouseEvent e) {
		if (SimpleControl.validControl())
			SimpleControl.currentControl.mouseClicked(e);
	}

	public void mouseEntered(MouseEvent e) {
		if (SimpleControl.validControl())
			SimpleControl.currentControl.mouseEntered(e);
	}

	public void mouseExited(MouseEvent e) {
		if (SimpleControl.validControl())
			SimpleControl.currentControl.mouseExited(e);
	}

	public void mousePressed(MouseEvent e) {
		if (SimpleControl.validControl())
			SimpleControl.currentControl.mousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		if (SimpleControl.validControl())
			SimpleControl.currentControl.mouseReleased(e);
	}

	public void mouseDragged(MouseEvent e) {
		if (SimpleControl.validControl())
			SimpleControl.currentControl.mouseDragged(e);
	}

	public void mouseMoved(MouseEvent e) {
		if (SimpleControl.validControl())
			SimpleControl.currentControl.mouseMoved(e);
	}

}
