package fr.florent59.plugin;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
	
	@Override
	public void onEnable(){
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new TeamEvent(), this);
		this.getCommand("jointeam").setExecutor(new JoinTeamCommandExecutor());
	}
	
	public static Plugin plugin() {
		return Plugin.getPlugin(Plugin.class);
	}

}
