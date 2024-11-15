package org.hinoob.ccleaner.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.hinoob.ccleaner.CCleaner;
import org.hinoob.ccleaner.modules.Module;

public class ModulesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!sender.hasPermission("ccleaner.modules")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to view modules.");
            return true;
        }

        for(Module module : CCleaner.getInstance().getModuleManager().getModules()) {
            sender.sendMessage(ChatColor.GREEN + module.getName() + " - runs every " + module.getTimer() + " second(s)");
        }

        return true;
    }
}
