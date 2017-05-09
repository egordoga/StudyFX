package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Person;

public class EditDialogController {

    @FXML
    private Label lblName;
    @FXML
    private Label lblPhone;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPhone;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;

    private Person person;

    public void setPerson(Person person) {
        this.person = person;

        txtName.setText(person.getName());
        txtPhone.setText(person.getPhone());
    }

    public Person getPerson(){return person;}


    public void actionClose(ActionEvent actionEvent) {

        Node sourse = (Node) actionEvent.getSource();
        Stage stage = (Stage) sourse.getScene().getWindow();
        stage.hide();
    }

    public void actionSave(ActionEvent actionEvent){
        person.setPhone(txtPhone.getText());
        person.setName(txtName.getText());
        actionClose(actionEvent);
    }
}
