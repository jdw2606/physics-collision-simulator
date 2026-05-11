package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import physics.PhysicsWorld;

public class ControlPanel extends VBox {
    public ControlPanel(PhysicsWorld world) {
        setPadding(new Insets(10));
        setSpacing(10);
        setPrefWidth(200);

        Label title = new Label("Physics Controls");

        CheckBox gravityToggle = new CheckBox("Enable Gravity");
        gravityToggle.setSelected(true);
        gravityToggle.setOnAction(e -> world.gravityEnabled = gravityToggle.isSelected());

        Label gravityLabel = new Label("Gravity Strength");
        Slider gravitySlider = new Slider(0, 1500, world.gravity.y);
        gravitySlider.valueProperty().addListener((obs, oldVal, newVal) -> world.gravity.y = newVal.doubleValue());

        Button clearButton = new Button("Clear Objects");
        clearButton.setOnAction(e -> world.clear());

        getChildren().addAll(title, gravityToggle, gravityLabel, gravitySlider, clearButton);
    }
}
