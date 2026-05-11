package physics;

import java.util.ArrayList;
import java.util.List;

import physics.collision.CollisionDetector;
import physics.collision.CollisionResolver;
import physics.collision.Manifold;
import physics.integrator.Integrator;
import physics.integrator.SemiImplicitEuler;

public class PhysicsWorld {
    private final List<Body> bodies = new ArrayList<>();

    private final double width;
    private final double height;

    public Vector2 gravity = new Vector2(0.0, 800.0);
    public boolean gravityEnabled = true;

    // NEW: integrator
    private final Integrator integrator = new SemiImplicitEuler();

    // NEW: safety clamp for dt (seconds)
    private static final double MAX_DT = 0.05;

    public PhysicsWorld(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public List<Body> getBodies() {
        return bodies;
    }

    public void addBody(Body b) {
        bodies.add(b);
    }

    public void clear() {
        bodies.clear();
    }

    public void step(double dt) {
        // clamp dt so debugging/lag doesn't explode the sim
        if (dt > MAX_DT) dt = MAX_DT;
        if (dt <= 0) return;

        // 1) Integrate motion
        Vector2 g = gravityEnabled ? gravity : new Vector2(0.0, 0.0);
        for (Body b : bodies) {
            integrator.integrate(b, dt, g);
        }

        // 2) Solve wall collisions after integration
        for (Body b : bodies) {
            solveWallCollisions(b);
        }

        // 3) Solve body-body contacts
        List<Manifold> contacts = CollisionDetector.findContacts(bodies);
        for (Manifold m : contacts) {
            CollisionResolver.resolve(m);
        }
    }

    private void solveWallCollisions(Body b) {
        if (b.isStatic()) return;

        // Left wall
        if (b.position.x - b.radius < 0.0) {
            b.position.x = b.radius;
            b.velocity.x = -b.velocity.x * b.restitution;
        }

        // Right wall
        if (b.position.x + b.radius > width) {
            b.position.x = width - b.radius;
            b.velocity.x = -b.velocity.x * b.restitution;
        }

        // Top wall
        if (b.position.y - b.radius < 0.0) {
            b.position.y = b.radius;
            b.velocity.y = -b.velocity.y * b.restitution;
        }

        // Bottom wall
        if (b.position.y + b.radius > height) {
            b.position.y = height - b.radius;
            b.velocity.y = -b.velocity.y * b.restitution;
        }
        /*
        if (b.position.y + b.radius > height) {
            b.position.y = height - b.velocity.y * b.restitution; // <-- NO (see note)
        }
        */
        if (b.position.y + b.radius > height) {
            b.position.y = height - b.radius;
            b.velocity.y = -b.velocity.y * b.restitution;
        }
    }
}