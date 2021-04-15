package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class LogIn extends Application {
    Button button;
    Stage window;
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        window.setTitle("Hello World");
//        button = new Button("Click");
//        button.setOnAction(e->System.out.println("hello"));
//        StackPane layout = new StackPane();
//        layout.getChildren().add(button);
//        Scene scene = new Scene(layout, 300, 275);
//        primaryStage.setScene(scene);
        GridPane grid=new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setHgap(8);
        grid.setVgap(10);

        Label nameLabel = new Label("username : ");
        GridPane.setConstraints(nameLabel,0,0);

        TextField nameinput = new TextField();
        GridPane.setConstraints(nameinput,1,0);

        Label passwordLabel = new Label("Password : ");
        GridPane.setConstraints(passwordLabel,0,1);

        TextField passwordinput = new TextField();
        passwordinput.setPromptText("password");
        GridPane.setConstraints(passwordinput,1,1);

        Button loginbutton= new Button("Log In");
        GridPane.setConstraints(loginbutton,1,2);
        grid.getChildren().addAll(nameinput,nameLabel,passwordinput,passwordLabel,loginbutton);
        Scene scene=new Scene(grid,300,200);
        window.setScene(scene);
        window.show();
    }

}