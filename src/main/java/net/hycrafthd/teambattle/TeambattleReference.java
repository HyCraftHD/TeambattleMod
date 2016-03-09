package net.hycrafthd.teambattle;

import net.hycrafthd.teambattle.proxy.CommonProxy;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.Instance;

public class TeambattleReference {

	public static final String modid = "teambattle";
	public static final String name = "Teambattle Mod";
	public static final String version = "0.1";

	public static final String resource = "teambattle:";

	@Instance(modid)
	public static TeamBattleMod instance = new TeamBattleMod();

	@SidedProxy(serverSide = "net.hycrafthd.teambattle.proxy.CommonProxy", clientSide = "net.hycrafthd.teambattle.proxy.ClientProxy", modId = modid)
	public static CommonProxy proxy = new CommonProxy();

}
