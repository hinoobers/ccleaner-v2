package org.hinoob.ccleaner.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.persistence.PersistentDataType;
import org.hinoob.ccleaner.CCleaner;
import org.hinoob.ccleaner.modules.impl.MobStacker;
import org.hinoob.ccleaner.util.EntityUtils;

public class StackedListener implements Listener {


    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if(event.getEntity().getType() == EntityType.PLAYER || EntityUtils.isTamed(event.getEntity())) return; // We don't care, it's not stacked

        NamespacedKey key = new NamespacedKey(CCleaner.getInstance(), "stacked");

        if(!event.getEntity().getPersistentDataContainer().has(key, PersistentDataType.INTEGER)) {
            return;
        }

        int stacked = event.getEntity().getPersistentDataContainer().get(key, PersistentDataType.INTEGER);
        stacked -= 1;

        if(stacked > 0) {
            LivingEntity entity = (LivingEntity) event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), event.getEntityType());
            entity.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, stacked);

            MobStacker stacker = (MobStacker) CCleaner.getInstance().getModuleManager().getModule(MobStacker.class);
            if(stacker.isCustomName()) {
                entity.setCustomName("x" + stacked + " " + entity.getType().name());
            }
        }
    }
}
