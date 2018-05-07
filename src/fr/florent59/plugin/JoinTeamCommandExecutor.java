package fr.florent59.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinTeamCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player && args.length == 1){
			Player player = (Player) sender;
			Player friend = Bukkit.getPlayer(TeamEvent.players.get(player.getName()));
			Plugin.scoreboard.getTeam(args[0]).addEntry(player.getName());
			Plugin.scoreboard.getTeam(args[0]).addEntry(friend.getName());
			player.sendMessage("§2Vous avez été ajouté à l'équipe des " + args[0] + " ! "
					+ "Voici la liste des membres actuels : " + 
					Plugin.scoreboard.getTeam(args[0]).getEntries());
			friend.sendMessage("§2Vous avez été ajouté à l'équipe des " + args[0] + " ! "
					+ "Voici la liste des membres actuels : " + 
					Plugin.scoreboard.getTeam(args[0]).getEntries());
			player.setPlayerListName(TeamEvent.getPrefix(args[0])+player.getName());
			friend.setPlayerListName(TeamEvent.getPrefix(args[0])+friend.getName());
			
			return true;
		}
		
		return false;
	}

}
