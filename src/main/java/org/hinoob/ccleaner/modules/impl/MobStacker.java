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

import java.util.ArrayList;
import java.util.List;

public class MobStacker extends Module {

    private boolean customName;
    private boolean enabled;
    private List<String> blacklisted = new ArrayList<>();

    public MobStacker(CCleaner instance) {
        this.enabled = instance.getConfig().getBoolean("modules.mobstacker.enabled");
        this.customName = instance.getConfig().getBoolean("modules.mobstacker.customname");
        this.blacklisted = instance.getConfig().getStringList("modules.mobstacker.disallowed");
    }

    @Override
    public String getName() {
        return "mobstacker";
    }

    @Override
    public int getTimer() {
        return 5;
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
                if(entity.getCustomName() != null) continue; // Custom mob, or something weird, better not to stack
                if(blacklisted != null && blacklisted.contains(entity.getType().name())) continue;

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
