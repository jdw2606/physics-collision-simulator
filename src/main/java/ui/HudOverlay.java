package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import physics.PhysicsWorld;

public class HudOverlay extends VBox {
    private final Label fpsLabel = new Label("FPS: --");
    private final Label bodiesLabel = new Label("Bodies: --");
    private final Label gravityLabel = new Label("Gravity: --");

    private long lastFpsTimeNs = 0;
    private int frames = 0;

    public HudOverlay() {
        setPadding(new Insets(8));
        setSpacing(4);

        fpsLabel.setTextFill(Color.WHITE);
        bodiesLabel.setTextFill(Color.WHITE);
        gravityLabel.setTextFill(Color.WHITE);

        getChildren().addAll(fpsLabel, bodiesLabel, gravityLabel);

        setMouseTransparent(true);
    }

    public void Update(PhysicsWorld world) {
        bodiesLabel.setText("Bodies: " + world.getBodies().size());
        gravityLabel.setText("Gravity: " + (world.gravityEnabled ? "ON" : "OFF") + " (" + (int) world.gravity.y + ")");

        long now = System.nanoTime();
        if (lastFpsTimeNs == 0) lastFpsTimeNs = now;

        frames ++;
        if (now - lastFpsTimeNs >= 1_000_000_000L) {
            fpsLabel.setText("FPS: " + frames);
            frames = 0;
            lastFpsTimeNs = now;
        }
    }
    
}
