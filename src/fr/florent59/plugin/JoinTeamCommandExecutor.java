package fr.florent59.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinTeamCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player){
			Player player = (Player) sender;
			@SuppressWarnings("unused")
			Player friend = Bukkit.getPlayer(TeamEvent.players.get(player.getName()));
			Plugin.plugin().getServer().getScoreboardManager().getNewScoreboard();
			player.sendMessage("Tu as cliqué sur le message, la commande jointeam a été lancée !");
			
			return true;
		}
		
		return false;
	}

}
