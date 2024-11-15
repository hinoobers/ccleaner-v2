package org.hinoob.ccleaner.util;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Tameable;

public class EntityUtils {

    public static boolean isTamed(Entity entity) {
        return entity instanceof Tameable && ((Tameable) entity).isTamed();
    }

    public static boolean isItem(Entity entity) {
        String type = entity.getType().name().toLowerCase().trim();
        return type.equals("item") || type.startsWith("dropped_");
    }
}
