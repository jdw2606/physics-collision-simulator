package physics.collision;
//main.java.

import physics.Body;
import physics.Vector2;

public class Manifold {
    public final Body a;
    public final Body b;

    // Unit vector pointing from A -> B
    public final Vector2 normal;

    // How much A and B overlap
    public final double penetration;

    public Manifold(Body a, Body b, Vector2 normal, double penetration) {
        this.a = a;
        this.b = b;
        this.normal = normal;
        this.penetration = penetration;
    }

}
