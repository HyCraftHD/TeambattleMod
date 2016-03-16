package net.hycrafthd.teambattle;

import net.hycrafthd.teambattle.entity.EntityHangGlider;
import net.hycrafthd.teambattle.util.CommonRegistryUtil;

public class TEntitys {

	public TEntitys() {
		register();
	}

	private void register() {
		CommonRegistryUtil.registerEntity(EntityHangGlider.class, "hangglider", 0, 64, 1, true, 0, 0, false);
	}

}
