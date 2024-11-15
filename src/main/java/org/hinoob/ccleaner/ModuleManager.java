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

    public ModuleManager() {
        modules.add(new LogClearer());
        modules.add(new MobDeleter());
        modules.add(new CacheClearer());
        modules.add(new MergeNearItems());
        modules.add(new MobStacker());
    }

    public Collection<Module> getModules() {
        return Collections.unmodifiableCollection(modules);
    }
}
