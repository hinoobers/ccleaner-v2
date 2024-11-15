package org.hinoob.ccleaner.modules.impl;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.hinoob.ccleaner.CCleaner;
import org.hinoob.ccleaner.modules.Module;
import org.hinoob.ccleaner.util.EntityUtils;

public class MobDeleter extends Module {

    @Override
    public String getName() {
        return "mobdeleter";
    }

    @Override
    public int getTimer() {
        return 60*60; // 1hour
    }

    @Override
    public void run(CCleaner instance) {
        getLogger().info("Deleting entities...");
        int i = 0;
        for(World world : Bukkit.getWorlds()) {
            for(LivingEntity entity : world.getLivingEntities()) {
                if(entity.getLocation().getChunk().isForceLoaded()) {
                    continue;
                }
                if(EntityUtils.isTamed(entity)) {
                    continue;
                }

                if(entity instanceof Player) {
                    continue;
                }

                entity.remove();
                i++;
            }
        }

        getLogger().info("Deleted " + i + " entities.");
    }
}
