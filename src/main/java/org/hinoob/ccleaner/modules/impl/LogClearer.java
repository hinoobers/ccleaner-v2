package org.hinoob.ccleaner.modules.impl;

import org.bukkit.Bukkit;
import org.hinoob.ccleaner.CCleaner;
import org.hinoob.ccleaner.modules.Module;

import java.io.File;

public class LogClearer extends Module {

    @Override
    public String getName() {
        return "logcleaner";
    }

    @Override
    public int getTimer() {
        return 120; // 2 minutes
    }

    @Override
    public void run(CCleaner instance) {
        getLogger().info("Clearing logs...");
        for(File f : new File(Bukkit.getWorldContainer(), "logs").listFiles()) {
            if(f.getName().equals("latest.log")) continue;

            f.delete();
        }
    }
}
