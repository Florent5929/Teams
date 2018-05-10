package fr.florent59.plugin;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

public class Plugin extends JavaPlugin {

	public static Scoreboard scoreboard;

	@Override
	public void onEnable() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new TeamEvent(), this);
		this.getCommand("jointeam").setExecutor(new JoinTeamCommandExecutor());
		this.getCommand("houe").setExecutor(new HoueCommandExecutor());
		initScoreboard();
	}

	public static Plugin plugin() {
		return Plugin.getPlugin(Plugin.class);
	}

	public void initScoreboard() {
		scoreboard = this.getServer().getScoreboardManager().getNewScoreboard();

		for (int i = 0; i < TeamEvent.couleurs.length; i++) {
			scoreboard.registerNewTeam(TeamEvent.couleurs[i]);
			scoreboard.getTeam(TeamEvent.couleurs[i]).setColor(getChatColor(TeamEvent.couleurs[i]));
		}
	}

	public static ChatColor getChatColor(String color) {

		switch (color) {
		case "violets":
			return ChatColor.DARK_PURPLE;
		case "rouges":
			return ChatColor.RED;
		case "verts":
			return ChatColor.GREEN;
		case "bleus":
			return ChatColor.BLUE;
		default:
			return ChatColor.WHITE;
		}

	}

}
