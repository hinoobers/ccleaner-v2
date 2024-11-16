package org.hinoob.ccleaner.modules.impl;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.hinoob.ccleaner.CCleaner;
import org.hinoob.ccleaner.modules.Module;
import org.hinoob.ccleaner.util.EntityUtils;

public class MobStacker extends Module {

    private boolean customName;
    private boolean enabled;

    public MobStacker(CCleaner instance) {
        this.enabled = instance.getConfig().getBoolean("modules.mobstacker.enabled");
        this.customName = instance.getConfig().getBoolean("modules.mobstacker.customname");
    }

    @Override
    public String getName() {
        return "mobstacker";
    }

    @Override
    public int getTimer() {
        return 5;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isCustomName() {
        return customName;
    }

    @Override
    public void run(CCleaner instance) {
        if(!enabled) return;

        for(World w : Bukkit.getWorlds()) {
            for(LivingEntity entity : w.getLivingEntities()) {
                if(EntityUtils.isTamed(entity) || EntityUtils.isItem(entity) || entity instanceof Player) continue;
                if(entity.getType() == EntityType.WITHER || entity.getType() == EntityType.ENDER_DRAGON) continue; // Better not to stack those ha

                for(Entity nearby : entity.getNearbyEntities(4.0,4.0,4.0)) {
                    if(!(nearby instanceof LivingEntity)) continue;
                    if(nearby.getEntityId() == entity.getEntityId()) continue;
                    if(nearby.getPersistentDataContainer().has(new NamespacedKey(instance, "stacked"), PersistentDataType.INTEGER)) continue;

                    if(EntityUtils.matching(entity, (LivingEntity) nearby)) {
                        nearby.remove();

                        NamespacedKey key = new NamespacedKey(instance, "stacked");
                        int stacked = 1;
                        if(entity.getPersistentDataContainer().has(key, PersistentDataType.INTEGER)) {
                            stacked = entity.getPersistentDataContainer().get(key, PersistentDataType.INTEGER);
                        }

                        entity.getPersistentDataContainer().set(new NamespacedKey(instance, "stacked"), PersistentDataType.INTEGER, stacked+1);
                        if(customName) {
                            entity.setCustomName("x" + (stacked+1) + " " + entity.getType().name());
                            entity.setCustomNameVisible(true);
                        }
                    }
                }
            }
        }
    }
}
