package physics.collision;

import physics.Body;
import physics.Vector2;

public class CollisionResolver {
    public static void resolve(Manifold m) {
        Body a = m.a;
        Body b = m.b;
        
        if (a.isStatic() && b.isStatic()) return;

        Vector2 rv = b.velocity.sub(a.velocity);

        double velAlongNormal = rv.dot(m.normal);

        if (velAlongNormal > 0.0) return;

        double e = Math.min(a.restitution, b.restitution);

        double j = -(1.0 + e) * velAlongNormal;
        double invMassSum = a.invMass + b.invMass;

        if (invMassSum == 0.0) return;

        j /= invMassSum;

        Vector2 impulse = m.normal.mul(j);
        a.applyImpulse(impulse.mul(-1.0));
        b.applyImpulse(impulse);

        positionalCorrection(m);
    }

    private static void positionalCorrection(Manifold m) {
        Body a = m.a;
        Body b = m.b;

        final double percent = 0.8;
        final double slop = 0.01;

        double invMassSum = a.invMass + b.invMass;
        if (invMassSum == 0.0) return;

        double correctionMag = Math.max(m.penetration - slop, 0.0) / invMassSum * percent;

        Vector2 correction = m.normal.mul(correctionMag);

        if (!a.isStatic()) {
            a.position = a.position.sub(correction.mul(a.invMass));
        }
        if (!b.isStatic()) {
            b.position = b.position.add(correction.mul(b.invMass));
        }
    }
}
