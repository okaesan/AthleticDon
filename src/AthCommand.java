package src.athle.don.oka;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AthCommand implements CommandExecutor {

	static AthleticDon plugin = AthleticDon.plugin;

	//CheckPointの登録数
	public static int CheckCounter = 1;

	public static boolean check = false;

	@Override
	public boolean onCommand(CommandSender cmdSender, Command cmd, String label, String[] args) {
		if(!(cmdSender instanceof Player)){
			cmdSender.sendMessage("コマンドはゲーム内力してください");
			return false;
		}

		if(args.length == 0){
			return false;
		}

		Player sender = (Player) cmdSender;

		switch (args[0]){
		case "check":
			if(check){
				check = false;
				sender.sendMessage("チェックポイントの編集を終了しました");
			}else{
				check = true;
				sender.sendMessage("チェックポイントの編集を開始しました");
			}
			return true;

		case "reset":
			Bukkit.getScoreboardManager().getMainScoreboard().getObjective("AthDon").unregister();
			AthleticDon.playerScore.clear();
			return true;
		}

		return false;
	}

}
