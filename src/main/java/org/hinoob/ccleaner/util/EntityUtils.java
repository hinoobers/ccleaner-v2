package org.hinoob.ccleaner.util;

import org.bukkit.entity.*;

public class EntityUtils {

    public static boolean isTamed(Entity entity) {
        return entity instanceof Tameable && ((Tameable) entity).isTamed();
    }

    public static boolean isItem(Entity entity) {
        String type = entity.getType().name().toLowerCase().trim();
        return type.equals("item") || type.startsWith("dropped_");
    }

    public static boolean matching(LivingEntity a, LivingEntity b) {
        if(a.getType() != b.getType()) return false;

        if(a instanceof Ageable ab) {
            Ageable ageableB = (Ageable) b;

            if(ab.isAdult() != ageableB.isAdult()) return false;
        }

        return true;


    }
}
