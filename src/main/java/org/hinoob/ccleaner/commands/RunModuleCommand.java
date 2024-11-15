package org.hinoob.ccleaner.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.hinoob.ccleaner.CCleaner;
import org.hinoob.ccleaner.modules.Module;

public class RunModuleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player && !((Player)sender).hasPermission("ccleaner.runmodule")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to run modules.");
            return true;
        }

        if(args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /runmodule <module>");
            return true;
        }

        Module module = CCleaner.getInstance().getModuleManager().getModules().stream().filter(p -> p.getName().equals(args[0])).findFirst().orElse(null);
        if(module == null) {
            sender.sendMessage(ChatColor.RED + "Module not found.");
            return true;
        }

        module.run(CCleaner.getInstance());
        sender.sendMessage(ChatColor.GREEN + "Module executed.");
        return true;
    }
}
