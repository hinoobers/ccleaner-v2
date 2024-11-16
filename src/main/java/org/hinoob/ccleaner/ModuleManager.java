package org.hinoob.ccleaner;

import com.google.common.cache.Cache;
import org.hinoob.ccleaner.modules.Module;
import org.hinoob.ccleaner.modules.impl.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ModuleManager {

    private final List<Module> modules = new ArrayList<>();

    public ModuleManager(CCleaner instance) {
        modules.add(new LogClearer(instance));
        modules.add(new MobDeleter());
        modules.add(new CacheClearer());
        modules.add(new MergeNearItems());
        modules.add(new MobStacker(instance));
    }

    public Collection<Module> getModules() {
        return Collections.unmodifiableCollection(modules);
    }

    public Module getModule(Class<?> clazz) {
        for(Module module : modules) {
            if(module.getClass().equals(clazz)) {
                return module;
            }
        }
        return null;
    }
}
