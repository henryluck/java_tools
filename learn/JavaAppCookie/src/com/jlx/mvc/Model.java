/**  @(#)Model.java  Dec 7, 2005
 * 
 * Copyright (c) 2001-2008 MDCL-FRONTLINE, Inc. 
 * All rights reserved. 
 * */
package com.jlx.mvc;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * ����ҵ���߼������ݵĶ�д����
 * 
 * @author ����ѩ jianglx@sc.mdcl.com.cn
 * @version ��ϰ
 */
public class Model {

	ArrayList data = new ArrayList();

	ArrayList observer = new ArrayList();

	public Model() {
		super();
	}

	public Model(int[] value, String[] name) {
		for (int i = 0; i < value.length; i++) {
			addData(value[i], name[i]);
		}
	}

	public Model(Data[] data) {
		for (int i = 0; i < data.length; i++) {
			addData(data[i]);
		}
	}

	@SuppressWarnings("unchecked")
	public void addData(int value, String name) {
		Data data = new Data();
		data.value = value;
		data.name = name;
		this.data.add(data);
	}

	@SuppressWarnings("unchecked")
	public void addData(Data data) {
		this.data.add(data);
	}

	public Data getData(int idx) {
		return (Data) (data.get(idx));
	}

	public int size() {
		return data.size();
	}

	// ������ģ���еǼǹ۲���.
	@SuppressWarnings("unchecked")
	public void registerObserver(IObserver o) {
		observer.add(o);
	}

	public void removeObserver(IObserver o) {
		observer.remove(o);
	}

	// �����ݸı�ʱ����Controller���ô˷�����֪ͨ����Observer,ˢ����ͼ.
	public void changeModel(Model model) {
		data.clear();
		for (int i = 0; i < model.size(); i++) {
			this.addData(model.getData(i));
		}
		dataUpdate();
	}

	private void dataUpdate() {
		for (Iterator i = observer.iterator(); i.hasNext();) {
			IObserver o = (IObserver) (i.next());
			o.dataUpdate(this);
		}
	}

}
