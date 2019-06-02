package javacourse;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainStage extends Application {

    private String modify = "Modify";
    private String add = "Add";
    private String delete = "Delete";
    private Inventory inventory = new Inventory();

    @Override
    public void start(Stage primaryStage) {

        Stage inventoryMgmtSystem;
        Scene mainScene;
        inventoryMgmtSystem = primaryStage;

        //layout of main scene
        Label mainTitle = new Label("Inventory Management System");
        mainTitle.setPrefSize(200, 100);
        mainTitle.setAlignment(Pos.BASELINE_LEFT);
        VBox parts = PartDisplay();
        VBox products = ProductDisplay();
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> inventoryMgmtSystem.close());
        BorderPane mainLayout = new BorderPane();

        mainLayout.setTop(mainTitle);
        mainLayout.setLeft(parts);
        mainLayout.setRight(products);
        mainLayout.setBottom(exitButton);
        mainScene = new Scene(mainLayout, 1000, 500);

        inventoryMgmtSystem.setTitle("Inventory Management System");
        inventoryMgmtSystem.setScene(mainScene);
        inventoryMgmtSystem.show();
    }

    private VBox PartDisplay() {

        // Layout
        GridPane titleSearchBox = new GridPane();
        titleSearchBox.setPadding(new Insets(10,10,10,10));
        titleSearchBox.setVgap(12);
        titleSearchBox.setHgap(10);
        HBox buttonBox = new HBox();
        VBox partBox = new VBox();

        // Title
        Label partsTitle = new Label("Parts");
        partsTitle.setAlignment(Pos.BASELINE_LEFT);
        titleSearchBox.add(partsTitle, 0, 0);

        // Search Box
        Button searchPartButton = new Button("Search");
        titleSearchBox.add(searchPartButton, 2, 0);
        TextField searchPartField = new TextField();
        searchPartField.setPromptText("Search Parts");
        titleSearchBox.add(searchPartField, 3, 0);

        // Parts Table
        ObservableList<Part> partList = FXCollections.observableArrayList();
        TableView<Part> partTableView = new TableView<>(inventory.getAllParts());
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

        // Buttons
        Button addPartButton = new Button(add);
        addPartButton.setAlignment(Pos.BASELINE_LEFT);
        Button modifyPartButton = new Button(modify);
        modifyPartButton.setAlignment(Pos.BASELINE_CENTER);
        Button deletePartButton = new Button(delete);
        deletePartButton.setAlignment(Pos.BASELINE_RIGHT);
        addPartButton.setOnAction(event -> {
            PartStage partStage = new PartStage();
            partStage.display(add, null, inventory);
            partTableView.setItems(inventory.getAllParts());
        });
        modifyPartButton.setOnAction(event -> {
            PartStage partStage = new PartStage();
            partStage.display(modify, partTableView.getSelectionModel().getSelectedItem(), inventory);
            partTableView.setItems(inventory.getAllParts());
        });
        deletePartButton.setOnAction(event -> inventory.getAllParts().remove(partTableView.getSelectionModel().getSelectedItem()));

        // Search Button
        searchPartButton.setOnAction(event -> {
            partList.clear();
            partTableView.setItems(partList);
            partList.addAll(inventory.lookupPart(searchPartField.getText()));
        });

        // Initialize Layout
        buttonBox.getChildren().addAll(addPartButton, modifyPartButton, deletePartButton);
        partBox.getChildren().addAll(titleSearchBox, partTableView, buttonBox);

        return partBox;
    }

    private VBox ProductDisplay() {

        // Layout
        GridPane titleSearchGrid = new GridPane();
        titleSearchGrid.setPadding(new Insets(10,10,10,10));
        titleSearchGrid.setVgap(12);
        titleSearchGrid.setHgap(10);
        HBox buttonBox = new HBox();
        VBox productBox = new VBox();

        // Title
        Label productTitle = new Label("Products");
        productTitle.setAlignment(Pos.BASELINE_LEFT);
        titleSearchGrid.add(productTitle, 0, 0);

        // Search Box
        Button searchProductButton = new Button("Search");
        titleSearchGrid.add(searchProductButton, 2, 0);
        TextField searchProductField = new TextField();
        searchProductField.setPromptText("Search Products");
        titleSearchGrid.add(searchProductField, 3, 0);

        // Table for Products
        ObservableList<Product> productList = FXCollections.observableArrayList();
        TableView<Product> productTableView = new TableView<>(inventory.getAllProducts());
        TableColumn<Product, String> id = new TableColumn<>("Product ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        productTableView.getColumns().add(id);
        TableColumn<Product, String> name = new TableColumn<>("Product Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        productTableView.getColumns().add(name);
        TableColumn<Product, String> stock = new TableColumn<>("Inventory Level");
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productTableView.getColumns().add(stock);
        TableColumn<Product, String> price = new TableColumn<>("Price/Cost per Unit");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        productTableView.getColumns().add(price);
        productTableView.setPrefWidth(450);
        productTableView.setPrefHeight(300);

        // Buttons
        Button addProductButton = new Button(add);
        addProductButton.setAlignment(Pos.BASELINE_LEFT);
        Button modifyProductButton = new Button(modify);
        modifyProductButton.setAlignment(Pos.BASELINE_CENTER);
        Button deleteProductButton = new Button(delete);
        deleteProductButton.setAlignment(Pos.BASELINE_RIGHT);
        addProductButton.setOnAction(event -> {
            ProductStage productStage = new ProductStage();
            productStage.display(add, null, inventory);
            productTableView.setItems(inventory.getAllProducts());
        });
        modifyProductButton.setOnAction(event -> {
            ProductStage productStage = new ProductStage();
            productStage.display(modify, productTableView.getSelectionModel().getSelectedItem(), inventory);
            productTableView.setItems(inventory.getAllProducts());
        });
        deleteProductButton.setOnAction(event -> inventory.getAllParts().remove(productTableView.getSelectionModel().getSelectedItem()));

        // Search Button
        searchProductButton.setOnAction(event -> {
            productTableView.setItems(productList);
            productList.clear();
            productList.addAll(inventory.lookupProduct(searchProductField.getText()));
        });

        // Initialize Layout
        buttonBox.getChildren().addAll(addProductButton, modifyProductButton, deleteProductButton);
        productBox.getChildren().addAll(titleSearchGrid, productTableView, buttonBox);

        return productBox;

    }

    public static void main(String[] args) {
        launch(args);
    }
}
