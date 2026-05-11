package physics.collision;

import java.util.ArrayList;
import java.util.List;

import physics.Body;
import physics.Vector2;

public class CollisionDetector {
    // Check all pairs of bodies and return collision manifolds
    public static List<Manifold> findContacts(List<Body> bodies) {
        List<Manifold> contacts = new ArrayList<>();
    
        int count = bodies.size();
        for (int i = 0; i < count; i++) {
            Body a = bodies.get(i);
    
            for (int j = i + 1; j < count; j++) {
                Body b = bodies.get(j);
    
                Manifold m = circleVsCircle(a, b);
                if (m != null) {
                    contacts.add(m);
                }
            }
        }
    
        return contacts;
    }

    private static Manifold circleVsCircle(Body a, Body b) {
        Vector2 delta = b.position.sub(a.position);

        double radiusSum = a.radius + b.radius;
        double distSq = delta.lengthSquared();

        if (distSq >= radiusSum * radiusSum) {
            // No collision
            return null;
        }

        double distance = Math.sqrt(distSq);

        // Handle rare case where centers are exactly on top of each other
        Vector2 normal;
        if (distance == 0.0) {
            normal = new Vector2(1.0, 0.0);
        } else {
            normal = delta.div(distance); // normalize
        }

        double penetration = radiusSum - distance;

        return new Manifold(a, b, normal, penetration);
    }
}
