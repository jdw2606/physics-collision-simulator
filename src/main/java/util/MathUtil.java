package util;

public final class MathUtil {
    private MathUtil() {}

    public static double clamp(double v, double min, double max) {
        return Math.max(min, Math.min(max, v));
    }

    public static boolean nearZero(double v, double eps) {
        return Math.abs(v) < eps;
    }

    public static double lerp(double a, double b, double t) {
        return a + (b - a) * t;
    }
}
