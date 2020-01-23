package chiculatormain;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Reconfirmation {
   static boolean clicked = false;

   public void messageBox(String message, String stageTitle) {
      Stage stage = new Stage();
      Button button = new Button("OK");
      Label label = new Label(message);
      stage.initModality(Modality.APPLICATION_MODAL);
      button.setOnAction((e) -> {
         stage.close();
      });
      VBox vbox = new VBox(10.0D, new Node[]{label, button});
      VBox.setMargin(button, new Insets(5.0D));
      vbox.setAlignment(Pos.CENTER);
      vbox.setPadding(new Insets(5.0D));
      Scene scene = new Scene(vbox, 400.0D, 150.0D);
      stage.setResizable(false);
      stage.setScene(scene);
      stage.setTitle(stageTitle);
      stage.showAndWait();
   }

   public static boolean clickYes(Stage stage) {
      stage.close();
      clicked = true;
      return clicked;
   }

   public static boolean clickNo(Stage stage) {
      stage.close();
      clicked = false;
      return clicked;
   }

   public static boolean closeBox(String message, String title) {
      Stage stage = new Stage();
      Button b1 = new Button("yes");
      b1.setOnAction((e) -> {
         clickYes(stage);
      });
      Button b2 = new Button("No");
      b2.setOnAction((e) -> {
         clickNo(stage);
      });
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.initStyle(StageStyle.DECORATED);
      Label label = new Label(message);
      HBox hbox = new HBox(15.0D, new Node[]{b1, b2});
      hbox.setAlignment(Pos.CENTER);
      VBox vbox = new VBox(new Node[]{label, hbox});
      vbox.setSpacing(15.0D);
      vbox.setAlignment(Pos.CENTER);
      Scene scene = new Scene(vbox, 350.0D, 70.0D);
      stage.setScene(scene);
      stage.setResizable(false);
      stage.setTitle(title);
      stage.showAndWait();
      return clicked;
   }
}
