package org.loon.game.simple.j25d;

/**
 * 
 * Copyright 2008
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
public class LColor {

	public int R = 0;

	public int G = 0;

	public int B = 0;

	private LColor() {
	}

	/**
	 * �ж�����lcolor�Ƿ����
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean equals(final LColor a, final LColor b) {
		return (a.R == b.R) && (a.G == b.G) && (a.B == b.B);
	}

	/**
	 * ����ɫPixel��ֵ����ΪLColor
	 * 
	 * @param c
	 * @return
	 */
	public static LColor getLColor(int pixel) {
		LColor color = new LColor();
		color.R = (pixel & 0x00ff0000) >> 16;
		color.G = (pixel & 0x0000ff00) >> 8;
		color.B = pixel & 0x000000ff;
		return color;
	}

	/**
	 * ��color����Ϊ����
	 * 
	 * @param color
	 * @return
	 */
	public int getPixel(final LColor color) {
		return (color.R << 16) | (color.G << 8) | color.B;
	}

	public int getPixel() {
		return (R << 16) | (G << 8) | B;
	}

	/**
	 * ע��r,g,b��ֵ
	 * 
	 * @param r
	 * @param g
	 * @param b
	 */
	public LColor(final int r, final int g, final int b) {
		this.R = r;
		this.G = g;
		this.B = b;
	}

	public static LColor fromArgb(final int r, final int g, final int b) {
		return new LColor(r, g, b);
	}

}
