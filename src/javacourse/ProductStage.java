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

    private ObservableList<Part> associatedPartList = FXCollections.observableArrayList();

    void display(String type, Product product, Inventory inventory) {
        Stage productStage = new Stage();
        productStage.initModality(Modality.APPLICATION_MODAL);
        productStage.setTitle(type + " Product");

        // Buttons
        Button saveButton = new Button("Save");
        saveButton.setAlignment(Pos.CENTER_LEFT);
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
        ObservableList<Part> searchPartList = FXCollections.observableArrayList();
        TableView<Part> searchPartTableView = new TableView<>(searchPartList);
        TableColumn<Part, String> searchPartID = new TableColumn<>("Part ID");
        searchPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        searchPartTableView.getColumns().add(searchPartID);
        TableColumn<Part, String> searchPartName = new TableColumn<>("Part Name");
        searchPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        searchPartTableView.getColumns().add(searchPartName);
        TableColumn<Part, String> searchPartStock = new TableColumn<>("Inventory Level");
        searchPartStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        searchPartTableView.getColumns().add(searchPartStock);
        TableColumn<Part, String> searchPartPrice = new TableColumn<>("Price/Cost per Unit");
        searchPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        searchPartTableView.getColumns().add(searchPartPrice);
        searchPartTableView.setPrefWidth(450);
        searchPartTableView.setPrefHeight(300);

        // Populate fields of existing Product
        if (type.equals("Modify")) {
            idField.setText(Integer.toString(product.getId()));
            nameField.setText(product.getName());
            invField.setText(Integer.toString(product.getStock()));
            priceField.setText(Double.toString(product.getPrice()));
            maxField.setText(Integer.toString(product.getMax()));
            minField.setText(Integer.toString(product.getMin()));
            associatedPartList = product.getAllAssociatedParts();
        }

        // Associated Parts table for Product
        TableView<Part> partTableView = new TableView<>(associatedPartList);
        TableColumn<Part, String> partID = new TableColumn<>("Part ID");
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partTableView.getColumns().add(partID);
        TableColumn<Part, String> partName = new TableColumn<>("Part Name");
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partTableView.getColumns().add(partName);
        TableColumn<Part, String> partStock = new TableColumn<>("Inventory Level");
        partStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partTableView.getColumns().add(partStock);
        TableColumn<Part, String> partPrice = new TableColumn<>("Price/Cost per Unit");
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTableView.getColumns().add(partPrice);
        partTableView.setPrefWidth(450);
        partTableView.setPrefHeight(300);


        // Search Button
        searchPartButton.setOnAction(event -> {
            searchPartList.clear();
            searchPartList.addAll(inventory.lookupPart(searchPartField.getText()));
        });

        // Add Button
        addPartButton.setOnAction(event -> associatedPartList.add(searchPartTableView.getSelectionModel().getSelectedItem()));

        // Delete Button
        deletePartButton.setOnAction(event -> associatedPartList.remove(partTableView.getSelectionModel().getSelectedItem()));

        // Save Button
        saveButton.setOnAction(event -> {
            int id;
            if (type.equals("Modify")) {
                id = Integer.parseInt(idField.getText());
            } else {
                id = inventory.IDGenerator("Product");
            }
            String name = nameField.getText();
            int inv = Integer.parseInt(invField.getText());
            double price = Double.parseDouble(priceField.getText());
            int min = Integer.parseInt(minField.getText());
            int max = Integer.parseInt(maxField.getText());

            if (type.equals("Add")) {
                Product newProduct = new Product(id, name, price, inv, min, max);
                for (int i = 0; i < associatedPartList.size(); i++) {
                    newProduct.addAssociatedPart(associatedPartList.get(i));
                }
                inventory.addProduct(newProduct);
            } else if(type.equals("Modify")) {
                int index = inventory.getAllParts().indexOf(product);
                for (int i = 0; i < associatedPartList.size(); i++) {
                    product.addAssociatedPart(associatedPartList.get(i));
                }
                inventory.updateProduct(index, product);
            }
            productStage.close();
        });


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

