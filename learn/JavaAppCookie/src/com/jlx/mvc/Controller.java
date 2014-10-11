/**  @(#)Controller.java  Dec 7, 2005
 * 
 * Copyright (c) 2001-2008 MDCL-FRONTLINE, Inc. 
 * All rights reserved. 
 * */
package com.jlx.mvc;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * 控制器
 * 
 * @author 蒋林雪 jianglx@sc.mdcl.com.cn
 * @version 练习
 */

public class Controller extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3906650808249561911L;

	Model model = new Model();

	View1 view1 = new View1(model);

	View2 view2 = new View2(model);

	JScrollPane jScrollPane1 = new JScrollPane();

	JButton jButton1 = new JButton();

	JTextField jTextField1 = new JTextField();

	JTextField jTextField2 = new JTextField();

	JLabel jLabel1 = new JLabel();

	JLabel jLabel2 = new JLabel();

	JLabel jLabel3 = new JLabel();

	public Controller() {
		try {
			jbInit();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void jbInit() throws Exception {
		Data[] data = new Data[2];
		data[0] = new Data();
		data[1] = new Data();
		data[0].name = "Ted";
		data[0].value = 20;
		data[1].name = "Joy";
		data[1].value = 14;
		model.addData(data[0]);
		model.addData(data[1]);
		// 注意下面两行：向模型中登记它的观察者View1和View2.
		model.registerObserver(view1);
		model.registerObserver(view2);

		this.getContentPane().setLayout(null);
		jScrollPane1.setBounds(new Rectangle(0, 0, 3, 3));
		jButton1.setBounds(new Rectangle(309, 259, 101, 27));
		jButton1.setText("Update");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton1_actionPerformed(e);
			}
		});
		jTextField1.setText("20");
		jTextField1.setBounds(new Rectangle(80, 254, 52, 30));
		jTextField2.setText("14");
		jTextField2.setBounds(new Rectangle(178, 255, 50, 31));
		jLabel1.setText("Age:");
		jLabel1.setBounds(new Rectangle(41, 226, 47, 23));
		jLabel2.setText("Ted");
		jLabel2.setBounds(new Rectangle(42, 252, 35, 33));
		jLabel3.setText("Joy");
		jLabel3.setBounds(new Rectangle(144, 255, 31, 31));
		view1.setBounds(new Rectangle(7, 5, 225, 20));
		view2.setBounds(new Rectangle(234, 4, 219, 209));
		this.getContentPane().add(jScrollPane1, null);
		this.getContentPane().add(jTextField2, null);
		this.getContentPane().add(jTextField1, null);
		this.getContentPane().add(jLabel2, null);
		this.getContentPane().add(jLabel3, null);
		this.getContentPane().add(jLabel1, null);
		this.getContentPane().add(jButton1, null);
		this.getContentPane().add(view1, null);
		this.getContentPane().add(view2, null);
	}

	// 按下Update按钮，通知Model数据发生改变.
	void jButton1_actionPerformed(ActionEvent e) {
		Data[] data = new Data[2];
		data[0] = new Data();
		data[1] = new Data();
		data[0].name = jLabel1.getText();
		data[0].value = Integer.parseInt(jTextField1.getText());
		data[1].name = jLabel2.getText();
		data[1].value = Integer.parseInt(jTextField2.getText());
		Model m = new Model(data);
		this.model.changeModel(m);
	}

	public static void main(String[] args) {
		Controller c = new Controller();
		c.setSize(475, 310);
		c.setVisible(true);
	}
}
