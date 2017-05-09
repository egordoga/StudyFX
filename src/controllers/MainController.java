package controllers;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.CollectionAddressBook;
import main.Person;


import java.io.IOException;

public class MainController {

    private CollectionAddressBook addressBookImpl = new CollectionAddressBook();
    private Stage mainStage;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnFind;
    @FXML
    private TextField textFind;
    @FXML
    private TableView tblAdress;
    @FXML
    private Label lblCount;
    @FXML
    private TableColumn<Person, String> columnName;
    @FXML
    private TableColumn<Person, String> columnPhone;

    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditDialogController editDialogController;
    private Stage editDialogStage;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    private void initialize(){

        //tblAdress.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        columnName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));

        initListeners();
        fillData();
        initLoader();

    }

    private void fillData() {
        addressBookImpl.initBase();
        tblAdress.setItems(addressBookImpl.getPersonList());
    }

    private void initLoader(){
        try {
            fxmlLoader.setLocation(getClass().getResource("../fxml/edit.fxml"));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initListeners(){
        addressBookImpl.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateCountLable();
            }
        });

        tblAdress.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2){
                    editDialogController.setPerson((Person) tblAdress.getSelectionModel().getSelectedItem());
                    showDialog();
                }
            }
        });
    }

    private void updateCountLable(){
        lblCount.setText("Количество записей: " + addressBookImpl.getPersonList().size());
    }

    public void actionButtonPressed(ActionEvent actionEvent){
        Object source = actionEvent.getSource();
        if (!(source instanceof Button)){
            return;
        }

        Button clickedButton = (Button) source;
        Person selectedPerson = (Person) tblAdress.getSelectionModel().getSelectedItem();
        Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
        editDialogController.setPerson(selectedPerson);

        switch (clickedButton.getId()) {
            case "btnAdd":
                editDialogController.setPerson(new Person());
                showDialog();
                addressBookImpl.add(editDialogController.getPerson());
                break;
            case "btnEdit":
                showDialog();
                break;
            case "btnDelete":
                addressBookImpl.remove((Person) tblAdress.getSelectionModel().getSelectedItem());
                break;
        }
    }


    @FXML
    private void showDialog() {

        if (editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Редактирование записи");
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
        }
            editDialogStage.showAndWait();

    }




}
