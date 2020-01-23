package chiculatormain;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ChiculatorMain extends Application {
   TableView<Chisquare> table;
   TableColumn<Chisquare, Double> obs;
   TableColumn<Chisquare, Integer> co;
   TableColumn<Chisquare, Double> exp;
   TableColumn<Chisquare, Double> oe;
   TableColumn<Chisquare, Double> oE;
   TableColumn<Chisquare, Double> fin;
   MenuBar menuBar;
   Menu menu;
   Menu exit;
   MenuItem calcItem;
   MenuItem gpItem;
   Menu anova;
   MenuItem oAnova;
   Menu chiItem;
   RadioMenuItem auto;
   RadioMenuItem unauto;
   ToggleGroup tg;
   Label label1;
   Label label2;
   BorderPane bpane;
   VBox vboxnLabel;
   Scene sceneMain;

   public void start(Stage ps) {
      this.menuBar = new MenuBar();
      this.menu = new Menu();
      Menu regression = new Menu("Regression");
      this.calcItem = new MenuItem();
      this.gpItem = new MenuItem();
      this.chiItem = new Menu();
      this.auto = new RadioMenuItem();
      this.unauto = new RadioMenuItem();
      this.tg = new ToggleGroup();
      this.exit = new Menu();
      this.anova = new Menu("Anova");
      this.oAnova = new MenuItem("One-Way");
      this.label1 = new Label();
      this.label2 = new Label();
      this.label1.setText("CalPro");
      this.label2.setText("v_1.0.0");
      this.label1.setFont(new Font("cooper black", 50.0D));
      this.label2.setFont(new Font("Sans Serrif", 11.0D));
      this.menuBar.setMaxWidth(300.0D);
      this.auto.setToggleGroup(this.tg);
      this.unauto.setToggleGroup(this.tg);
      this.auto.setOnAction((e) -> {
         ps.setScene((new Chisquare3()).chiGraph(ps, this.sceneMain));
      });
      this.unauto.setOnAction((e) -> {
         ps.setScene((new Chisquare3()).chiGraph2(ps, this.sceneMain));
      });
      this.oAnova.setOnAction((e) -> {
         ps.setScene((new Anova2()).anovaGraph(ps, this.sceneMain));
      });
      this.calcItem.setOnAction((e) -> {
         ps.setScene((new LinearRegression2()).RegressionScene(ps, this.sceneMain));
      });
      this.gpItem.setOnAction((e) -> {
         ps.setScene((new GradePoint2()).firstScene(ps, this.sceneMain));
      });
      regression.getItems().add(this.calcItem);
      this.calcItem.setText("_Linear Regression");
      this.gpItem.setText("_GP Calculator");
      this.chiItem.setText("Chi-Square _Analysis");
      this.anova.getItems().add(this.oAnova);
      this.chiItem.getItems().addAll(new MenuItem[]{this.auto, this.unauto});
      this.menu.getItems().addAll(new MenuItem[]{this.gpItem, new SeparatorMenuItem(), regression, new SeparatorMenuItem(), this.chiItem, new SeparatorMenuItem(), this.anova});
      this.menu.setText("_Main Menu");
      this.auto.setText("_Autogenerate");
      Button bn = new Button("Exit");
      Region r = new Region();
      VBox.setVgrow(r, Priority.ALWAYS);
      bn.setAlignment(Pos.BOTTOM_LEFT);
      ps.setOnCloseRequest((e) -> {
         e.consume();
         Boolean b = Reconfirmation.closeBox("Are you sure you want to exit?", "Exit Program");
         if (b) {
            ps.close();
         }

      });
      bn.setOnAction((e) -> {
         Boolean b = Reconfirmation.closeBox("Are you sure you want to exit?", "Exit Program");
         if (b) {
            ps.close();
         }

      });
      this.unauto.setText("_Input");
      this.menuBar.getMenus().addAll(new Menu[]{this.menu});
      MenuBar menubar = new MenuBar(new Menu[]{this.exit});
      HBox hbox = new HBox();
      hbox.getChildren().addAll(new Node[]{this.menuBar, menubar});
      this.menuBar.prefWidthProperty().bind(ps.widthProperty());
      menubar.prefWidthProperty().bind(ps.widthProperty());
      hbox.setAlignment(Pos.TOP_LEFT);
      hbox.setPadding(new Insets(0.0D, 0.0D, 150.0D, 0.0D));
      new Tooltip("Click here to access the app");
      Tooltip.install(hbox, new Tooltip("Click Menu to access the features of the app and exit to quit"));
      this.vboxnLabel = new VBox();
      Label mz = new Label("Ziord™");
      mz.setFont(new Font("Franklin Gothic Demi", 12.0D));
      VBox vlabel = new VBox(new Node[]{this.label1, this.label2});
      vlabel.setAlignment(Pos.CENTER);
      Region rr = new Region();
      VBox.setMargin(bn, new Insets(222.0D, 0.0D, 0.0D, 5.0D));
      HBox hbb = new HBox(new Node[]{bn, rr, mz});
      HBox.setHgrow(rr, Priority.ALWAYS);
      hbb.setPadding(new Insets(0.0D, 5.0D, 3.0D, 5.0D));
      this.vboxnLabel.getChildren().addAll(new Node[]{hbox, vlabel, r, hbb});
      this.sceneMain = new Scene(this.vboxnLabel, 525.0D, 500.0D);
      ps.getIcons().add(new Image(ChiculatorMain.class.getResourceAsStream("calproCaptureTest4.png")));
      ps.setTitle("");
      ps.setScene(this.sceneMain);
      ps.setTitle("CalPro");
      ps.show();
   }

   public static void main(String[] args) {
      launch(args);
   }
}
