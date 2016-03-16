package net.hycrafthd.teambattle;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.hycrafthd.teambattle.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;

public class TeambattleReference {

	public static final String modid = "teambattle";
	public static final String name = "Teambattle Mod";
	public static final String version = "0.1";

	public static final String resource = "teambattle:";

	@Instance(modid)
	public static TeambattleMod instance = new TeambattleMod();

	@SidedProxy(serverSide = "net.hycrafthd.teambattle.proxy.CommonProxy", clientSide = "net.hycrafthd.teambattle.proxy.ClientProxy", modId = modid)
	public static CommonProxy proxy = new CommonProxy();

	public static final Logger log = LogManager.getLogger(name);

	public static void printExeption(Throwable th) {
		printModInfo();
		for (StackTraceElement element : th.getStackTrace()) {
			log.error(element);
		}
	}

	public static void printExeption(String th) {
		printModInfo();
		log.error(th);
	}

	private static void printModInfo() {
		log.error("--------------------------------------------------------------------------");
		log.error("TeambattleMod by HyCraftHD!");
		log.error("An error occured!");
		log.error("Please report the following stacktrace to the modauthor on github: http://github.hycrafthd.de");
		log.error("--------------------------------------------------------------------------");
	}

}
