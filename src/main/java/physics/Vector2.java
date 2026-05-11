package physics;

public class Vector2 {
    public double x;
    public double y;

    public Vector2() {
        this(0.0, 0.0);
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }
    

    public Vector2 add(Vector2 other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    public Vector2 sub(Vector2 other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    public Vector2 mul(double scalar) {
        return new Vector2(this.x * scalar, this.y * scalar);
    }
    public Vector2 div(double scalar) {
        if (scalar == 0.0) throw new IllegalArgumentException("Cannot divide by zero");
        return new Vector2(this.x / scalar, this.y / scalar);
    }


    // vector math

    public double dot(Vector2 other) {
        return this.x * other.x + this.y * other.y;
    }

    public double lengthSquared() {
        return this.x * this.x + this.y * this.y;
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vector2 normalized() {
        double len = length();
        if (len == 0.0) return new Vector2(0.0, 0.0);
        return this.div(len);
    }




    public static Vector2 zero() {
        return new Vector2(0.0, 0.0);
    }

    @Override
    public String toString() {
        return "Vector2(" + x + ", " + y + ")";
    }
}
