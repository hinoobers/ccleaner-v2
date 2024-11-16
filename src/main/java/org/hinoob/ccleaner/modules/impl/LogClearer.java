package org.hinoob.ccleaner.modules.impl;

import org.bukkit.Bukkit;
import org.hinoob.ccleaner.CCleaner;
import org.hinoob.ccleaner.modules.Module;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;

public class LogClearer extends Module {

    private boolean enabled;

    public LogClearer(CCleaner instance) {
        this.enabled = instance.getConfig().getBoolean("modules.logclearer.enabled");
    }

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
        if(!enabled) return;
        getLogger().info("Clearing logs...");
        for(File f : new File(Bukkit.getWorldContainer(), "logs").listFiles()) {
            if(f.getName().equals("latest.log")) continue;

            String name = f.getName().replaceAll(".log.gz", "");
            String[] split = name.split("-");
            LocalDate date = LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));

            LocalDate now = LocalDate.now();
            // Delete logs older than 7 days
            if(date.isBefore(now.minusDays(7))) {
                f.delete();
            }
        }
    }
}
