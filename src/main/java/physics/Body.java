package physics;

public class Body {
    public Vector2 position;
    public Vector2 velocity;

    public double radius;

    public double mass;
    public double invMass;

    public double restitution;


    public Body(Vector2 position, Vector2 velocity, double radius, double mass, double restitution) {
        this.position = position;
        this.radius = radius;
        this.velocity = velocity;

        setMass(mass);
        this.restitution = restitution;
    }

    public void setMass(double mass) {
        this.mass = mass;

        if (mass <= 0.0) {
            this.invMass = 0.0;
        } else {
            this.invMass = 1.0 / mass;
        }
    }

    public boolean isStatic() {
        return invMass == 0.0;
    }

    public void applyImpulse(Vector2 impulse) {
        if (isStatic()) return;

        this.velocity = this.velocity.add(impulse.mul(invMass));
    }


}
