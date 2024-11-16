package org.hinoob.ccleaner;

import org.bukkit.plugin.java.JavaPlugin;
import org.hinoob.ccleaner.commands.HelpCommand;
import org.hinoob.ccleaner.commands.ModulesCommand;
import org.hinoob.ccleaner.commands.RunModuleCommand;
import org.hinoob.ccleaner.listener.StackedListener;
import org.hinoob.ccleaner.modules.Module;
import org.hinoob.ccleaner.modules.impl.LogClearer;
import org.hinoob.ccleaner.modules.impl.MobDeleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CCleaner extends JavaPlugin {

    private static CCleaner instance;
    private ModuleManager moduleManager;

    private int timerIndex;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("CCleaner has been enabled!");

        saveDefaultConfig();
        moduleManager = new ModuleManager(this);

        getCommand("crunmodule").setExecutor(new RunModuleCommand());
        getCommand("cmodules").setExecutor(new ModulesCommand());
        getCommand("chelp").setExecutor(new HelpCommand());

        getServer().getPluginManager().registerEvents(new StackedListener(), this);

        getLogger().info("Running modules...");
        moduleManager.getModules().forEach(module -> {
            module.run(this);
        });


        getServer().getScheduler().runTaskTimer(this, () -> {
            moduleManager.getModules().forEach(module -> {
                if(module.getTimer() < 0) return;

                if(timerIndex % (module.getTimer()*20) == 0) {
                    module.run(this);
                }
            });
            timerIndex++;
        }, 20L, 20L);
    }

    @Override
    public void onDisable() {

    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public static CCleaner getInstance() {
        return instance;
    }

}
