package org.loon.game.simple.j25d.main;

import org.loon.game.simple.j25d.GameCursor;
import org.loon.game.simple.j25d.GameFrame;
import org.loon.game.simple.j25d.resource.RpgResource;
import org.loon.game.simple.j25d.rpg.RpgLayout;
import org.loon.game.simple.j25d.rpg.RpgMap;



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
public class Main {

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				GameFrame frame = new GameFrame(
						"Java 2.5D游戏开发中的角色对话实现<LoonFramework-Game>", 640, 480);
				// 设定游标
				frame.setCursor(GameCursor.getCursor("image/cursor.png"));
				
				RpgMap rpgMap1 = RpgResource.dgInstance;

				RpgMap rpgMap2 = RpgResource.sdInstance;
				
				//设定传送点
				rpgMap1.addTransfer(rpgMap2, 3, 31);
				rpgMap2.addTransfer(rpgMap1, 14, 22);
				rpgMap2.addTransfer(rpgMap1, 14, 23);
				rpgMap2.addTransfer(rpgMap1, 14, 21);
				//初始化地图1
				rpgMap1.initialize();
				frame.getGame().setControl(new RpgLayout(rpgMap1));
				// 游戏全屏
				// frame.updateFullScreen();
				frame.setFPS(true);
				frame.mainLoop();
				frame.showFrame();
			}
		});
	}

}
