package sample;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.*;


public class Main extends Application {
    Stage window;
    TableView<Command> table;
    ObservableList<Command> products= FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;

        ////////////
        TableColumn<Command,String> codeColumn = new TableColumn<>("Code");
        codeColumn.setMinWidth(200);
        codeColumn.setCellValueFactory( new PropertyValueFactory<>("Code"));
////////////
        TableColumn<Command,String> commandColumn = new TableColumn<>("Command");
        commandColumn.setMinWidth(200);
        commandColumn.setCellValueFactory( new PropertyValueFactory<>("Command"));
/////////////////
        TableColumn<Command,String> reservationColumn = new TableColumn<>("reservation");
        reservationColumn.setMinWidth(200);
        reservationColumn.setCellValueFactory( new PropertyValueFactory<>("reservation"));
// /////////////
        TableColumn<Command,String> modificationColumn = new TableColumn<>("modification");
        modificationColumn.setMinWidth(200);
        modificationColumn.setCellValueFactory( new PropertyValueFactory<>("modification"));
// ///////
        TableColumn<Command,String> PriceColumn = new TableColumn<>("Price");
        PriceColumn.setMinWidth(200);
        PriceColumn.setCellValueFactory( new PropertyValueFactory<>("Price"));
// ///////

        Button DeleteButton = new Button("Delete");
        DeleteButton.setOnAction(e -> {
            try {
                deleteButtonclicked();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });


        Button  restartButton= new Button("refresh");
        restartButton.setOnAction(e-> {
            try {
                refreshtable();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });


        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(DeleteButton,restartButton);



        table=new TableView<>();
        table.getColumns().addAll(codeColumn,commandColumn,modificationColumn,reservationColumn,PriceColumn);
        table.setItems(refreshtable());


        table.setEditable(true);
        PriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        PriceColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Command, String>>() {
                    public void handle(TableColumn.CellEditEvent<Command, String> t) {
                        try {
                            Edit(t.getRowValue().getCode(),t.getNewValue());
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                    }
                }
        );


        VBox vBox = new VBox();
        vBox.getChildren().addAll(table,hBox);
        Scene scene2 =new Scene(vBox);
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

        TextField passwordinput = new PasswordField();
        passwordinput.setPromptText("password");
        GridPane.setConstraints(passwordinput,1,1);

        Button loginbutton= new Button("Log In");
        loginbutton.setOnAction(e->{
            ConnextionClass connectionClass = new ConnextionClass();
            Connection connection=connectionClass.connectdb();
            String nom=nameinput.getText();
            String password=passwordinput.getText();
            String sql="select * from USERS where nom = '"+nom+"' and password= '"+password+"';";
            Statement statement= null;
            try {
                statement = connection.createStatement();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            ResultSet resultSet= null;
            try {
                resultSet = statement.executeQuery(sql);
                if(resultSet.next()){
                    window.setScene(scene2);
                    window.setTitle("");
                    window.setTitle("All Commands");
                }else {
                    JOptionPane.showMessageDialog(null,"Login failed ! Please try again. ");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        GridPane.setConstraints(loginbutton,1,2);
        grid.getChildren().addAll(nameinput,nameLabel,passwordinput,passwordLabel,loginbutton);
        Scene scene1=new Scene(grid,300,200);
        window.setScene(scene1);
        window.show();
    }



    public ObservableList<Command> getProduct() throws SQLException {

        ConnextionClass connectionClass = new ConnextionClass();

        Connection connection=connectionClass.connectdb();
        String sql="select * from CLIENTS";
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);
        while (resultSet.next()){
            products.add(new Command(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)));
        }
        return products;
    }

    public void deleteButtonclicked() throws SQLException {
        ObservableList<Command> allcommands;
        ObservableList<Command> CommandSelected;

        allcommands =table.getItems();
        CommandSelected=table.getSelectionModel().getSelectedItems();
        String a=CommandSelected.get(0).getCode();
        CommandSelected.forEach(allcommands::remove);

        // Delete from database:
        ConnextionClass connectionClass = new ConnextionClass();
        Connection connection=connectionClass.connectdb();
        String sql="delete from CLIENTS where id="+a+";";
        Statement statement=connection.createStatement();
        statement.executeUpdate(sql);

    }

    public void Edit(String ID, String Price) throws SQLException {
        ConnextionClass connectionClass = new ConnextionClass();
        Connection connection=connectionClass.connectdb();
        String sql="update CLIENTS set Price="+Price+" where "+"id="+ID+";";
        Statement statement= null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public ObservableList<Command> refreshtable() throws SQLException {
        products.clear();
        return getProduct();
    }



}


