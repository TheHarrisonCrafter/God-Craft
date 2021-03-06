package net.theharrisoncrafter.Commands;

import net.theharrisoncrafter.God;
import net.theharrisoncrafter.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Setmaxhealth implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){		
		if(sender instanceof Player){
			if(sender.hasPermission("GodCraft.Setmaxhealth")){
				int maxHealth = 0;
				Player targetPlayer = null;
				
				if(args.length > 2){
					return false;
				}
				else{
					if(args.length < 1){
						targetPlayer = (Player) sender;
						maxHealth = 20;
					}
					else if (args.length == 1 && Util.isParsableToInt(args[0])){
						targetPlayer = (Player) sender;
						maxHealth = Integer.parseInt(args[0]);
					}
					else if (args.length == 1 && !Util.isParsableToInt(args[0])){
						targetPlayer = Bukkit.getPlayer(args[0]);
						maxHealth = 20;
					}
					else if(args.length == 2 && !Util.isParsableToInt(args[0]) && Util.isParsableToInt(args[1])){
						targetPlayer = Bukkit.getPlayer(args[0]);
						maxHealth = Integer.parseInt(args[1]);
					}
					
					if(targetPlayer == null){
						sender.sendMessage(ChatColor.RED + "That player is not online!");
						return true;
					}
					
					if(maxHealth > God.config.getInt("maxhealth", 20)){
						sender.sendMessage(ChatColor.RED + "That is to much. Please use a number between 0 and " + God.config.getInt("maxhealth", 20));
						return true;
					}
				}
				
				targetPlayer.setMaxHealth(maxHealth);
				sender.sendMessage(ChatColor.YELLOW + "Max Health Set!");
				return true;
			}
			else{
				sender.sendMessage(ChatColor.RED + "You do not have permissions to do that!");
				return true;
			}
		}
		else{
			int maxHealth = 0;
			Player targetPlayer = null;
			
			if(args.length < 1 || args.length > 2){
				return false;
			}
			else{
				if (args.length == 1 && !Util.isParsableToInt(args[0])){
					targetPlayer = Bukkit.getPlayer(args[0]);
					maxHealth = 20;
				}
				else if(args.length == 2 && !Util.isParsableToInt(args[0]) && Util.isParsableToInt(args[1])){
					targetPlayer = Bukkit.getPlayer(args[0]);
					maxHealth = Integer.parseInt(args[1]);
				}
				
				if(targetPlayer == null){
					sender.sendMessage(ChatColor.RED + "That player is not online!");
					return true;
				}
			}
			
			targetPlayer.setMaxHealth(maxHealth);
			sender.sendMessage(ChatColor.YELLOW + "Max Health Set!");
			return true;
		}
	}
}
