package org.hinoob.ccleaner.modules.impl;

import org.bukkit.Bukkit;
import org.hinoob.ccleaner.CCleaner;
import org.hinoob.ccleaner.modules.Module;
import org.hinoob.ccleaner.util.FileUtil;

import java.io.File;

// Could probably be merged, doesn't save anything anyway, makes it more beautiful though
public class CacheClearer extends Module {


    @Override
    public String getName() {
        return "cacheclearer";
    }

    @Override
    public int getTimer() {
        return 120;
    }

    @Override
    public void run(CCleaner instance) {
        File cache = new File(Bukkit.getWorldContainer(), "cache");
        if(FileUtil.isDirectoryEmpty(cache)) {
            FileUtil.delete(cache);
        }

        File dotCache = new File(Bukkit.getWorldContainer(), ".cache");
        if(FileUtil.isDirectoryEmpty(dotCache)) {
            FileUtil.delete(dotCache);
        }
    }
}
