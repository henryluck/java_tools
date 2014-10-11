package org.loon.game.simple.j25d.rpg.role;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.loon.game.simple.j25d.rpg.Config;

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
public class Roles implements Config {

	private Role hero;

	private List roles = new ArrayList(20);

	private int rx, ry, count;

	final private static Comparator comparator = new Comparator() {
		public int compare(Object ro1, Object ro2) {
			Role role1 = (Role) ro1;
			Role role2 = (Role) ro2;
			return (role1.getPx() > role2.getPx() + 96)
					|| (role1.getPy() > role2.getPy()) ? 1 : 0;
		}
	};

	public Roles() {

	}

	public void mainHero(Role hero) {
		this.hero = hero;
		this.roles.add(count++, hero);
	}

	public boolean isHit(int x, int y) {
		for (Iterator it = roles.iterator(); it.hasNext();) {
			Role role = (Role) it.next();
			if (((role.getX() == x) || (role.getX() + role.getIoffsetX() == x))
					&& ((role.getY() == y) || (role.getY() + role.getIoffsetY() == y))) {
				return true;
			}
		}
		return false;
	}

	public synchronized void draw(Graphics g, int firstTileX, int firstTileY,
			int lastTileX, int lastTileY, int offsetX, int offsetY) {
		sortList();
		for (Iterator it = roles.iterator(); it.hasNext();) {
			Role role = (Role) it.next();
			if (role.isHero()) {
				role.draw(g, offsetX, offsetY);
				continue;
			}
			rx = role.getX();
			ry = role.getY();
			if (rx > firstTileX && ry > firstTileY && rx < lastTileX
					&& ry < lastTileY) {
				role.draw(g, offsetX, offsetY);
			} else {
				role.autoAspect();
			}
		}
	}

	public void sortList() {
		Collections.sort(roles, comparator);
	}

	public void addChara(Role chara) {
		roles.add(count++, chara);
	}

	public void removeChara(Role chara) {
		roles.remove(chara);
		count--;
	}

	public int getCount() {
		return count;
	}

	public Role getHero() {
		return hero;
	}

	public Role roleCheck(int x, int y) {
		for (Iterator it = roles.iterator(); it.hasNext();) {
			Role role = (Role) it.next();
			if (role.getX() == x && role.getY() == y) {
				return role;
			}
		}
		return null;
	}

	public List getRoles() {
		return roles;
	}

}
