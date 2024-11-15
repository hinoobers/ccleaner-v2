package org.hinoob.ccleaner.modules.impl;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.hinoob.ccleaner.CCleaner;
import org.hinoob.ccleaner.modules.Module;
import org.hinoob.ccleaner.util.EntityUtils;

public class MobStacker extends Module {

    @Override
    public String getName() {
        return "mobstacker";
    }

    @Override
    public int getTimer() {
        return 5;
    }

    @Override
    public void run(CCleaner instance) {
        for(World w : Bukkit.getWorlds()) {
            for(LivingEntity entity : w.getLivingEntities()) {
                if(EntityUtils.isTamed(entity) || EntityUtils.isItem(entity) || entity instanceof Player) continue;

                for(Entity nearby : entity.getNearbyEntities(4.0,4.0,4.0)) {
                    if(nearby instanceof LivingEntity && nearby.getType() == entity.getType()) {
                        entity.remove();

                        int stacked = nearby.getPersistentDataContainer().get(new NamespacedKey(instance, "stacked"), PersistentDataType.INTEGER);
                        nearby.getPersistentDataContainer().set(new NamespacedKey(instance, "stacked"), PersistentDataType.INTEGER, stacked+1);

                    }
                }
            }
        }
    }
}
