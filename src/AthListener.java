package src.athle.don.oka;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class AthListener implements Listener {

	static AthleticDon plugin = AthleticDon.plugin;

	static List<Integer> location = new ArrayList<Integer>();

	@EventHandler
	public static void onClick(PlayerInteractEvent event){
		UUID uuid = event.getPlayer().getUniqueId();
		if(AthleticDon.playerScore.containsKey(uuid)){
			for(String checkNumber : plugin.getConfig().getConfigurationSection("CheckPoint").getKeys(false)){
				location = plugin.getConfig().getIntegerList("CheckPoint."+checkNumber);
				if(location.get(0)==event.getClickedBlock().getLocation().getBlockX()
						&& location.get(1)==event.getClickedBlock().getLocation().getBlockY()
						&& location.get(2)==event.getClickedBlock().getLocation().getBlockZ()){
					AthleticDon.playerScore.put(uuid, Integer.parseInt(checkNumber.replaceAll("Point", "")));
					AthScoreboard.setScoreboard();
				}
			}
		}
	}

	@EventHandler
	public static void onBreak(BlockBreakEvent event){
		if(AthCommand.check){
			location = new ArrayList<>();
			location.add(event.getBlock().getLocation().getBlockX());
			location.add(event.getBlock().getLocation().getBlockY());
			location.add(event.getBlock().getLocation().getBlockZ());
			plugin.getConfig().set("CheckPoint.Point"+Integer.toString(AthCommand.CheckCounter), location);
			plugin.saveConfig();
			event.getPlayer().sendMessage("CheckPoint"+AthCommand.CheckCounter+"が登録されました");
			AthCommand.CheckCounter++;

			event.setCancelled(true);
		}
	}

	@EventHandler
	public static void onJoin(PlayerLoginEvent event){
		new BukkitRunnable() {

			@Override
			public void run() {
				if(AthleticDon.playerScore.containsKey(event.getPlayer().getUniqueId())){
					AthScoreboard.setScoreboard();
				}else{
					AthleticDon.playerScore.put(event.getPlayer().getUniqueId(), 0);
					AthScoreboard.setScoreboard();
				}
			}
		}.runTaskLater(plugin, 20);
	}
}
