package org.hinoob.ccleaner.modules.impl;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.hinoob.ccleaner.CCleaner;
import org.hinoob.ccleaner.modules.Module;
import org.hinoob.ccleaner.util.EntityUtils;

// time to test, prob buggy
public class MergeNearItems extends Module {

    @Override
    public int getTimer() {
        return 3;
    }

    @Override
    public String getName() {
        return "mergeNearItems";
    }

    @Override
    public void run(CCleaner instance) {
        for(World world : Bukkit.getWorlds()) {
            for(Entity entity : world.getEntities()) {
                if(EntityUtils.isItem(entity)) {
                    Item parent = (Item) entity;

                    for(Entity near : entity.getNearbyEntities(2.0,2.0,2.0)) {
                        if(EntityUtils.isItem(near)) {
                            Item item = (Item) entity;
                            if(item.getItemStack().isSimilar(parent.getItemStack())) {
                                parent.getItemStack().setAmount(parent.getItemStack().getAmount() + item.getItemStack().getAmount());
                                item.remove();
                            }
                        }
                    }
                }

            }
        }
    }
}
