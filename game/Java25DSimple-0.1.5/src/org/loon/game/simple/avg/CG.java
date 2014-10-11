package org.loon.game.simple.avg;

import java.awt.Image;

import org.loon.game.simple.j25d.command.ArrayMap;
import org.loon.game.simple.j25d.resource.CacheResource;


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
public class CG {

	private Image backgroundCG;

	public Image getBackgroundCG() {
		return backgroundCG;
	}

	public void setBackgroundCG(Image backgroundCG) {
		this.backgroundCG = backgroundCG;
	}

	public void addChara(String file, Chara role) {
		CacheResource.ADV_CHARAS.put(file.replaceAll(" ", "").toLowerCase(),
				role);
	}

	public void addImage(String file, int x, int y) {
		String keyName = file.replaceAll(" ", "").toLowerCase();
		Chara chara = (Chara) CacheResource.ADV_CHARAS.get(keyName);
		if (chara == null) {
			CacheResource.ADV_CHARAS.put(keyName, new Chara(file, x, y));
		} else {
			chara.setX(x);
			chara.setY(y);
		}
	}

	public Chara removeImage(String file) {
		return (Chara) CacheResource.ADV_CHARAS.remove(file.replaceAll(" ", "")
				.toLowerCase());
	}

	public void clear() {
		CacheResource.ADV_CHARAS.clear();
	}

	public ArrayMap getCharas() {
		return CacheResource.ADV_CHARAS;
	}

}
