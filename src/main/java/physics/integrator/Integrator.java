package physics.integrator;

import physics.Body;
import physics.Vector2;

public interface Integrator {
    
    void integrate(Body body, double dt, Vector2 gravity);
}
