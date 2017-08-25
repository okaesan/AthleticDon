package src.athle.don.oka;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

public class AthleticDon extends JavaPlugin {

	public static AthleticDon plugin;

	public static HashMap<UUID, Integer> playerScore = new HashMap<UUID, Integer>();
	@Override
	public void onEnable() {
		plugin = this;

		// コマンド登録
		getCommand("ath").setExecutor(new AthCommand());

		//config書き出し
		this.saveDefaultConfig();

		// イベントリスナ登録
		getServer().getPluginManager().registerEvents(new AthListener(), this);
	}

	@Override
	public void onDisable() {

	}

}
