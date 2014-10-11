/**  @(#)View2.java  Dec 7, 2005
 * 
 * Copyright (c) 2001-2008 MDCL-FRONTLINE, Inc. 
 * All rights reserved. 
 * */
package com.jlx.mvc;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * 视图2
 * 
 * @author 蒋林雪 jianglx@sc.mdcl.com.cn
 * @version 练习
 */

public class View2 extends JPanel implements IObserver {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3258409521624855859L;

	Model model;

	public View2() {
	}

	public View2(Model model) {
		try {
			this.model = model;
			jbInit();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		this.setBackground(Color.white);
		this.setBorder(new TitledBorder(BorderFactory.createLineBorder(
				Color.black, 1), "View1"));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(model == null)
			return;
		int x = 20, y = 50;
		int h = g.getFontMetrics().getHeight();
		int width = this.getWidth();
		int height = this.getHeight();
		int sy = height / model.size();
		int sx = width / 2;
		for (int i = 0; i < model.size(); i++) {
			Data data = model.getData(i);
			int value = data.value;
			int dx = 3;
			int r = 3;
			Color c = new Color((int) (255 * Math.random()), (int) (255 * Math
					.random()), (int) (255 * Math.random()));
			int cx = sx;
			int cy = y + i * sy;
			for (int j = 0; j < value; j++) {
				g.setColor(c);
				g.drawOval(cx, cy, r, r);
				r += dx;
			}
			g.drawString(data.name, 25, cy);
		}
	} // 当模型数据发生改变时，会自动调用此方法来刷新图形

	public void dataUpdate(Model model) {
		/** @todo: Implement this Observer method */
		this.model = model;
		repaint();
	}
}
