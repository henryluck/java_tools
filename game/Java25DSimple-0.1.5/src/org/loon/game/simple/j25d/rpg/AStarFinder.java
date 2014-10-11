package org.loon.game.simple.j25d.rpg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
public class AStarFinder {

	private Field2D map;

	private Cell2D goal;

	private ArrayList pathes;

	private Set visitedCache;

	public static List find(Field2D maps, Cell2D start, Cell2D goal) {
		AStarFinder astar = new AStarFinder();
		List path = astar.calc(maps, start, goal);
		return path;
	}

	public static List find(int[][] maps, Cell2D start, Cell2D goal) {
		return AStarFinder.find(new Field2D(maps), start, goal);
	}

	public List calc(Field2D map, Cell2D start, Cell2D goal) {
		return new AStarFinder().solve(map, start, goal);
	}

	private List solve(Field2D map, Cell2D start, Cell2D goal) {
		this.map = map;
		this.goal = goal;
		visitedCache = new HashSet();
		pathes = new ArrayList();
		visitedCache.add(start);
		ArrayList path = new ArrayList();
		path.add(start);
		ScoredPath spath = new ScoredPath(0, path);
		pathes.add(spath);
		return astar();
	}

	private List astar() {
		while (pathes.size() > 0) {
			ScoredPath spath = (ScoredPath) pathes.remove(0);
			Cell2D current = (Cell2D) spath.path.get(spath.path.size() - 1);
			if (current.equals(goal)) {
				return spath.path;
			}
			for (Iterator it = map.neighbors(current).iterator(); it.hasNext();) {
				Cell2D next = (Cell2D) it.next();
				if (visitedCache.contains(next)) {
					continue;
				}
				visitedCache.add(next);
				if (!map.isHit(next)) {
					continue;
				}
				ArrayList path = new ArrayList(spath.path);
				path.add(next);
				int score = spath.score + map.score(goal, next);
				insert(score, path);
			}
		}
		return null;
	}

	private void insert(int score, ArrayList path) {
		for (int i = 0; i < pathes.size(); i += 1) {
			ScoredPath spath = (ScoredPath) pathes.get(i);
			if (spath.score >= score) {
				pathes.add(i, new ScoredPath(score, path));
				return;
			}
		}
		pathes.add(new ScoredPath(score, path));
	}

	private class ScoredPath {
	
		int score;
		ArrayList path;

		ScoredPath(int score, ArrayList path) {
			this.score = score;
			this.path = path;
		}
	}
}
