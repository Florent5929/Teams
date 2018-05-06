package fr.florent59.plugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class TeamEvent implements Listener {
	
	public Map<String, String> players = new HashMap<String, String>();
	// sert à stocker celui qui invite (première String) et celui qui reçoit (deuxième String)

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {

		Player player = e.getPlayer();
		ItemStack customhoe = new ItemStack(Material.DIAMOND_HOE, 1);
		ItemMeta customM = customhoe.getItemMeta();
		customM.setDisplayName("§dHoue de Cupidon");
		customM.setLore(
				Arrays.asList("Cliquez sur un joueur avec la houe de cupidon", "et un amour passionné vous attendra."));
		customM.addEnchant(Enchantment.KNOCKBACK, 1, true);
		customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		customhoe.setItemMeta(customM);
		player.getInventory().setItem(8, customhoe);
		player.updateInventory();
	}

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		
		e.getPlayer().sendMessage("tata" +e.getRightClicked().toString());

		if (e.getRightClicked() instanceof CraftPlayer || e.getRightClicked() instanceof Player) {
			Player player = e.getPlayer();
			players.put(player.getName(), e.getRightClicked().getName());
			// on sauvegarde dans la hashmap celui qui invite et celui qui reçoit.
			
			ItemStack item = player.getInventory().getItemInMainHand();
			ItemMeta meta = item.getItemMeta();

			if (meta.getLore().contains("houe de cupidon")) {

				Inventory inv = Bukkit.createInventory(null, 27, "§8Menu des Teams");
				int[] bytes = { 10, 14, 5, 11 };
				int[] places = { 12, 10, 14, 16 };
				String[] couleurs = { "violets", "rouges", "verts", "bleus" };

				for (int i = 0; i < couleurs.length; i++) {
					item = new ItemStack(Material.WOOL, 1, (byte) bytes[i]);
					meta = item.getItemMeta();
					meta.setDisplayName("Cliquez pour rejoindre l'équipe des " + couleurs[i] + ".");
					item.setItemMeta(meta);
					inv.setItem(places[i], item);
				}
				player.openInventory(inv);

			}
		}

	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
	    ItemStack item = e.getCurrentItem();
	    String[] couleurs = { "violets", "rouges", "verts", "bleus" };
		
		if(item != null 
				&& inv.getName().equalsIgnoreCase("§8Menu des Teams") 
				&& item.getType().equals(Material.WOOL) 
				&& item.hasItemMeta() 
				&& item.getItemMeta().hasDisplayName()){
			
			for(int i=0; i<couleurs.length;i++){
				
				if(item.getItemMeta().getDisplayName().contains(couleurs[i])){
					player.closeInventory();
					player.sendMessage("§5Vous voulez rejoindre l'équipe des " + couleurs[i] +".");
					String info = "§e" + player.getName() + " §bt'invite à rejoindre l'équipe des §d"+couleurs[i]+"§b! §6Clique ici pour la rejoindre !";
					TextComponent message = new TextComponent(info);
					message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/help"));
					Player cible = Bukkit.getPlayer(players.get(player.getName()));
					cible.spigot().sendMessage(message);
	                break;
				}
			}
			
		}

	}

}
