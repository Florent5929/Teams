package fr.florent59.plugin;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HoueCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;
			ItemStack customhoe = new ItemStack(Material.DIAMOND_HOE, 1);
			ItemMeta customM = customhoe.getItemMeta();
			customM.setDisplayName("§dHoue de Cupidon");
			customM.setLore(
					Arrays.asList("Cliquez sur un joueur avec la houe de cupidon", "et un amour passionné vous attendra."));
			customM.addEnchant(Enchantment.KNOCKBACK, 1, true);
			customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			customhoe.setItemMeta(customM);
			player.getInventory().addItem(customhoe);
			player.sendMessage("Une houe du cupidon a été ajoutée à l'inventaire.");	
			return true;
		} else {
			sender.sendMessage("Player command only !");
			return false;
		}

	}

}
