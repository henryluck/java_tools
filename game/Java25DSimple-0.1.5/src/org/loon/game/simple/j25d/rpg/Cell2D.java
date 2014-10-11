package org.loon.game.simple.j25d.rpg;

import java.io.Serializable;

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
public class Cell2D implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int x;

	private int y;

	public Cell2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(Object o) {
		if (o instanceof Cell2D) {
			Cell2D p = (Cell2D) o;
			return p.x == x && p.y == y;
		}
		return false;
	}

	public int hashCode() {
		return x + y;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

}
