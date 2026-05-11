package util;

import physics.Vector2;

public final class Config {
    private Config() {}

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static final Vector2 GRAVITY = new Vector2(0, 800); // px/s^2
    public static final double MAX_DT = 0.05;

    public static final double DEFAULT_RESTITUTION = 0.8;
}
