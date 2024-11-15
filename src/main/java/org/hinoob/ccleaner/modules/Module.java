package org.hinoob.ccleaner.modules;

import org.hinoob.ccleaner.CCleaner;

import java.util.logging.Logger;

public abstract class Module {

    protected Logger getLogger() {
        return CCleaner.getInstance().getLogger();
    }

    public abstract String getName();

    public abstract int getTimer(); // -1 = no timer

    public abstract void run(CCleaner instance);
}
