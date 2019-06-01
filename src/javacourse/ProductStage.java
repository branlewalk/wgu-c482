package javacourse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

class ProductStage {

    void display(String type, Product product, Inventory inventory) {
        Stage productStage = new Stage();
        productStage.initModality(Modality.APPLICATION_MODAL);
        productStage.setTitle(type + " Product");

        // Buttons
        Button saveButton = new Button("Save");
        saveButton.setAlignment(Pos.CENTER_LEFT);
        saveButton.setOnAction(event -> productStage.close());
        Button cancelButton = new Button("Cancel");
        cancelButton.setAlignment(Pos.CENTER_RIGHT);
        cancelButton.setOnAction(event -> productStage.close());
        Button searchPartButton = new Button("Search");
        Button addPartButton = new Button("Add");
        Button deletePartButton = new Button("Delete");

        // Labels for text fields
        Label productTitle = new Label(type + " Product");
        Label idLabel = new Label("ID:");
        Label nameLabel = new Label("Name:");
        Label invLabel = new Label("Inv:");
        Label priceLabel = new Label("Price/Cost");
        Label maxLabel = new Label("Max:");
        Label minLabel = new Label("Min:");

        // Text fields to extract data for products
        TextField idField = new TextField("Auto Gen - Disabled");
        idField.setDisable(true);
        TextField nameField = new TextField();
        nameField.setPromptText("Product Name");
        TextField invField = new TextField();
        invField.setPromptText("Inv");
        TextField priceField = new TextField();
        priceField.setPromptText("Price/Cost");
        TextField maxField = new TextField();
        maxField.setPromptText("Max");
        TextField minField = new TextField();
        minField.setPromptText("Min");
        TextField searchPartField = new TextField();
        searchPartField.setPromptText("Search Part");



        // Search Parts Table
        ObservableList searchPartList = FXCollections.observableArrayList();
        TableView<Part> searchPartTableView = new TableView<>(searchPartList);
        TableColumn<Part, String> id1 = new TableColumn<>("Part ID");
        id1.setCellValueFactory(new PropertyValueFactory<>("id"));
        searchPartTableView.getColumns().add(id1);
        TableColumn<Part, String> name1 = new TableColumn<>("Part Name");
        name1.setCellValueFactory(new PropertyValueFactory<>("name"));
        searchPartTableView.getColumns().add(name1);
        TableColumn<Part, String> stock1 = new TableColumn<>("Inventory Level");
        stock1.setCellValueFactory(new PropertyValueFactory<>("stock"));
        searchPartTableView.getColumns().add(stock1);
        TableColumn<Part, String> price1 = new TableColumn<>("Price/Cost per Unit");
        price1.setCellValueFactory(new PropertyValueFactory<>("price"));
        searchPartTableView.getColumns().add(price1);
        searchPartTableView.setPrefWidth(450);
        searchPartTableView.setPrefHeight(300);

//        // Search Button
//        searchPartButton.setOnAction(event -> searchPartList = inventory.lookupProduct(searchPartField.getText()));
//
//        // Add Button
//        addPartButton.setOnAction(event -> {
//
//        });


        ObservableList associatedPartList = FXCollections.observableArrayList();
        TableView<Part> partTableView = new TableView<>(associatedPartList);
        TableColumn<Part, String> id = new TableColumn<>("Part ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        partTableView.getColumns().add(id);
        TableColumn<Part, String> name = new TableColumn<>("Part Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        partTableView.getColumns().add(name);
        TableColumn<Part, String> stock = new TableColumn<>("Inventory Level");
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partTableView.getColumns().add(stock);
        TableColumn<Part, String> price = new TableColumn<>("Price/Cost per Unit");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTableView.getColumns().add(price);
        partTableView.setPrefWidth(450);
        partTableView.setPrefHeight(300);

        // Grid Layout for Label, Text Fields, Tables and Buttons
        GridPane productGrid = new GridPane();
        productGrid.setPadding(new Insets(25, 25 ,25 , 25));
        productGrid.setVgap(10);
        productGrid.setHgap(12);

        // Row 1
        productGrid.add(productTitle, 0, 0);
        productGrid.add(searchPartButton,2,0);
        productGrid.add(searchPartField,3,0, 2, 1);
        // Row 2
        productGrid.add(idLabel,0,1);
        productGrid.add(idField, 1,1);
        productGrid.add(searchPartTableView, 2,1, 4,2);
        // Row 3
        productGrid.add(nameLabel, 0,2);
        productGrid.add(nameField, 1,2);
        // Row 4
        productGrid.add(invLabel, 0,3);
        productGrid.add(invField, 1,3);
        productGrid.add(addPartButton, 5,3);
        // Row 5
        productGrid.add(priceLabel, 0,4);
        productGrid.add(priceField, 1,4);
        productGrid.add(partTableView, 2,4, 4,2);
        // Row 6
        productGrid.add(maxLabel, 0,5);
        productGrid.add(maxField, 1,5);
        // Row 7
        productGrid.add(minLabel, 0,6);
        productGrid.add(minField, 1,6);
        productGrid.add(deletePartButton, 5,6);
        // Row 8
        productGrid.add(saveButton, 3,7);
        productGrid.add(cancelButton, 4,7);
        Scene partScene = new Scene(productGrid, 650, 375);
        productStage.setScene(partScene);
        productStage.showAndWait();
    }

}

