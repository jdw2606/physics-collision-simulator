package app;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import physics.Body;
import physics.PhysicsWorld;
import ui.HudOverlay;

public class GameLoop extends AnimationTimer {

    private static final double FIXED_DT = 1.0 / 120.0;

    private final PhysicsWorld world;
    private final Canvas canvas;
    private final GraphicsContext gc;

    private long lastTime = 0;
    private double accumulator = 0.0;

    private final HudOverlay hud;

    public GameLoop(PhysicsWorld world, Canvas canvas, HudOverlay hud) {
        this.world = world;
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.hud = hud;
    }

    @Override
    public void handle(long now) {
        if (lastTime == 0) {
            lastTime = now;
            return;
        }

        double frameTime = (now - lastTime) / 1_000_000_000.0;
        lastTime = now;

        frameTime = Math.min(frameTime, 0.25);
        accumulator += frameTime;

        while (accumulator >= FIXED_DT) {
            world.step(FIXED_DT);
            accumulator -= FIXED_DT;
        }

        render();
    }

    private void render() {
        double w = canvas.getWidth();
        double h = canvas.getHeight();

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, w, h);

        gc.setFill(Color.CORNFLOWERBLUE);
        for (Body b : world.getBodies()) {
            double x = b.position.x - b.radius;
            double y = b.position.y - b.radius;
            double d = b.radius * 2.0;

            gc.fillOval(x, y, d, d);
        }
    }
}
