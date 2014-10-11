package org.loon.game.simple.j25d;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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
public abstract class SimpleControl implements IControl {

	protected static IControl currentControl;

	protected static boolean isControlOfNull;

	public static Point mouse = new Point(0, 0);

	public static Random rand = new Random();

	public static boolean left_click;

	public static boolean right_click;

	public static boolean fg_click;

	public SimpleControl() {
	}
	

	public synchronized void setCurrentControl(final IControl control) {
		SimpleControl.currentControl = control;
		SimpleControl.isControlOfNull = (SimpleControl.currentControl == null);
	}

	public synchronized void removeCurrentControl() {
		SimpleControl.currentControl = null;
		SimpleControl.isControlOfNull = true;
	}

	public static synchronized boolean validControl() {
		return !SimpleControl.isControlOfNull;
	}
	
	public void next() {
	}

	public void setFrame(int i) {
	}
	
	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 1) {
			SimpleControl.left_click = true;
		}
		if (e.getButton() == 3) {
			SimpleControl.right_click = true;
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == 1) {
			SimpleControl.left_click = false;
		}
		if (e.getButton() == 3) {
			SimpleControl.right_click = false;
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		mouse = e.getPoint();
	}

	public void mouseMoved(MouseEvent e) {
		mouse = e.getPoint();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {
	}



}
