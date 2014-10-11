/**  @(#)View1.java  Dec 7, 2005
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
 * ��ͼ1
 * 
 * @author ����ѩ jianglx@sc.mdcl.com.cn
 * @version ��ϰ
 */

public class View1 extends JPanel implements IObserver {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3256446919122237495L;

	Model model;

	public View1() {
	}

	public View1(Model model) {
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
		for (int i = 0; i < model.size(); i++) {
			Data data = model.getData(i);
			g.drawString(data.name, x, y);
			y += h;
			g.drawString(String.valueOf(data.value), x, y);
			y += h;
		}
	}

	// ��ģ�����ݷ����ı�ʱ�����Զ����ô˷�����ˢ��ͼ��
	public void dataUpdate(Model model) {
		/** @todo: Implement this Observer method */
		this.model = model;
		repaint();
	}
}
