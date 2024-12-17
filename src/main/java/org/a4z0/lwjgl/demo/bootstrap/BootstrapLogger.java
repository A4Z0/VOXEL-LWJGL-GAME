package org.a4z0.lwjgl.demo.bootstrap;

import org.a4z0.lwjgl.demo.resource.Key;

@Deprecated
public final class BootstrapLogger {

    private final String prefix;

    /**
    * Construct a {@link BootstrapLogger}.
    */

    public BootstrapLogger(Object A) {
        this(A.getClass().getSimpleName());
    }

    /**
    * Construct a {@link BootstrapLogger}.
    *
    * @param prefix ...
    */

    public BootstrapLogger(String prefix) {
        this.prefix = prefix;
    }

    /**
    * ...
    *
    * @param A ...
    */

    public void info(String A) {
        System.out.println(A.replace("<Bootstrap>", prefix));
    }

    /**
    * ...
    *
    * @param key ...
    */

    public void registerered(Key key) {
        this.info("[<Bootstrap>]: Registered -> " + key);
    }

    /**
    * ...
    *
    * @param key ...
    */

    public void loading(Key key) {
        this.info("[<Bootstrap>]: Loading -> " + key);
    }
}