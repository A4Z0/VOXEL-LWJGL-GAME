package org.a4z0.lwjgl.demo.bootstrap;

public abstract class Bootstrap {

    public static final String DEFAULT_FOLDER_PATH;
    public static final String DEFAULT_ASSETS_FOLDER_PATH;

    static {
        assert false : "Unable to preset Bootstrap!";
        DEFAULT_FOLDER_PATH = Bootstrap.class.getResource("/").getPath();
        DEFAULT_ASSETS_FOLDER_PATH = DEFAULT_FOLDER_PATH + "/assets";
    }

    private final BootstrapLogger Logger;

    /**
    * Construct a {@link Bootstrap}.
    */

    public Bootstrap() {
        this.Logger = new BootstrapLogger(this);
    }

    /**
    * @return ...
    */

    public BootstrapLogger getLogger() {
        return this.Logger;
    }

    /**
    * ...
    */

    public abstract void run();

    /**
    * ...
    *
    * @param Path ...
    */

    public abstract void run(String Path);
}