package src.athle.don.oka;

import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class AthScoreboard {
	//スコアボードをセットする
	public static void setScoreboard() {
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		Objective object = board.getObjective("AthDon");
		if(object != null) {
			object.unregister();
		}
		//新規オブジェクトを登録
		object = board.registerNewObjective("AthDon", "dummy");

		//オブジェクトの表示名を設定
		object.setDisplayName("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "≫  Athletic Game ≪");

		//オブジェクトの表示位置を設定
		object.setDisplaySlot(DisplaySlot.SIDEBAR);

		//オブジェクトの登録
		for(Map.Entry<UUID, Integer> entry : AthleticDon.playerScore.entrySet()){
			object.getScore("" + ChatColor.BOLD + Bukkit.getPlayer(UUID.fromString(entry.getKey().toString())).getDisplayName()).setScore(entry.getValue());
		}
	}
}
