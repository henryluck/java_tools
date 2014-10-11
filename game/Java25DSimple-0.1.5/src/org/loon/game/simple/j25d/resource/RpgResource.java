package org.loon.game.simple.j25d.resource;

import java.util.HashMap;
import java.util.Map;

import org.loon.game.simple.j25d.rpg.Config;
import org.loon.game.simple.j25d.rpg.RpgMap;
import org.loon.game.simple.j25d.rpg.role.Role;

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
 * @email：ceponline@yahoo.com.cn
 * @version 0.1
 */
public class RpgResource {

	public static RpgMap dgInstance = new RpgMap("image/map/ydg1.jpg",
			"image/map/ydg1.map", "image/map/ydg1_m.png", "script/map1.script");

	public static RpgMap sdInstance = new RpgMap("image/map/maze.jpg",
			"image/map/maze.map", "script/map2.script");

	public static Map roles = new HashMap(3);

	static {
		// 创建主角
		Role hero = new Role("image/role/gm.png", Config.DOWN, 0);
		// 创建NPC1
		Role npc1 = new Role("image/role/assassin.png", Config.LEFT, 1);
		// //创建NPC2
		Role npc2 = new Role("image/role/rogue.png", Config.LEFT, 1);
		hero.setName("鹏凌三千(传说中的怪蜀黍)");
		hero.setPartyName("曾经的北S商人");
		hero.setMaxHP(50);
		hero.setHp(45);
		hero.setMaxSP(100);
		hero.setSp(20);
		hero.setShowName(true);
		hero.setShowState(true);
		npc1.setScriptFile("script/lyh.txt");
		npc1.setName("炼妖狐（爆刺、爆刺、一拍即死）");
		npc1.setPartyName("此人已死，有事烧纸");
		npc2.setScriptFile("script/mm.txt");
		npc2.setName("猫猫（巴帽小偷）");
		npc2.setPartyName("病猫不发威你还拿我当老虎了");

		roles.put("鹏凌三千", hero);
		roles.put("炼妖狐", npc1);
		roles.put("猫猫", npc2);
	}

}
