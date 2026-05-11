package app;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import physics.Body;
import physics.PhysicsWorld;
import physics.Vector2;
import ui.ControlPanel;
import ui.HudOverlay;


public class GameApp {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private PhysicsWorld world;
    private Canvas canvas;

    private Vector2 dragStart = null; 

    public void start(Stage stage) {
        canvas = new Canvas(WIDTH, HEIGHT);

        world = new PhysicsWorld(WIDTH, HEIGHT);

        world.addBody(new Body(new Vector2(200, 100), new Vector2(150, 0),15,1.0,0.8));

        world.addBody(new Body(new Vector2(400, 50), new Vector2(-100, 0),20,1.5,0.7));

        HudOverlay hud = new HudOverlay();

        StackPane center = new StackPane(canvas, hud);
        StackPane.setAlignment(hud, Pos.TOP_LEFT);

        BorderPane root = new BorderPane();
        root.setCenter(center);

        ControlPanel panel = new ControlPanel(world);
        root.setRight(panel);

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        scene.setOnMousePressed(e -> {
            dragStart = new Vector2(e.getX(), e.getY());
        });
        
        scene.setOnMouseReleased(e -> {
            if (dragStart == null) return;
        
            Vector2 dragEnd = new Vector2(e.getX(), e.getY());
        
            // Velocity = drag vector (scaled)
            Vector2 velocity = dragStart.sub(dragEnd).mul(3.0);
        
            Body b = new Body(
                    dragStart,
                    velocity,
                    12,     // radius
                    1.0,    // mass
                    0.8     // restitution
            );
        
            world.addBody(b);
            dragStart = null;
        });

        stage.setTitle("2D Collision Sandbox");
        stage.setScene(scene);
        stage.show();

        GameLoop loop = new GameLoop(world, canvas, hud);
        loop.start();
    }
}
