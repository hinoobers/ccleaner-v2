package org.hinoob.ccleaner.modules;

import org.hinoob.ccleaner.CCleaner;

import java.util.logging.Logger;

public abstract class Module {

    public int ticks = 0;

    public Module() {
        this.ticks = getTimer();
    }

    protected Logger getLogger() {
        return CCleaner.getInstance().getLogger();
    }

    public abstract String getName();

    public abstract int getTimer(); // -1 = no timer

    public abstract void run(CCleaner instance);
}
