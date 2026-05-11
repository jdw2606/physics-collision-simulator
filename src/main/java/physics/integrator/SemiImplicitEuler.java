package physics.integrator;

import physics.Body;
import physics.Vector2;

public class SemiImplicitEuler implements Integrator {

    @Override
    public void integrate(Body b, double dt, Vector2 gravity) {
        if (b.isStatic()) return;

        // v += g*dt
        b.velocity = b.velocity.add(gravity.mul(dt));

        // x += v*dt (using updated v)
        b.position = b.position.add(b.velocity.mul(dt));
    }
}