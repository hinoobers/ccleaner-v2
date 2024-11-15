package org.hinoob.ccleaner.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!sender.hasPermission("ccleaner.help")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to view help.");
            return true;
        }
        sender.sendMessage("CCleaner - A simple plugin to speed/clean up your server.");
        sender.sendMessage("/cmodules - View all modules.");
        sender.sendMessage("/crunmodule <module> - Run a module.");
        return true;
    }
}
