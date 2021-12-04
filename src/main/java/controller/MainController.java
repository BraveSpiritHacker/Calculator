package controller;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.Operation;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    Operation operation = new Operation();

    Alert alert = new Alert(Alert.AlertType.ERROR);

    @FXML
    private Button buttonClearAll, buttonDivision, buttonMultiply, buttonClearOneSymbol,
            buttonNumber7, buttonNumber8, buttonNumber9, buttonSub,
            buttonNumber4, buttonNumber5, buttonNumber6, buttonAdd,
            buttonNumber1, buttonNumber2, buttonNumber3, buttonRoot,
            buttonPow, buttonNumber0, buttonComma, buttonEquals;

    @FXML
    private TextField textField;

    @FXML
    private ImageView iconCalc;

    @FXML
    private ImageView iconExite;

    private boolean pressedOperation = false;

    public void init(Stage stage){
        iconExite.setOnMouseClicked(event -> stage.close());
    }

    @FXML
    void buttonClearAllOnAction(ActionEvent event) {
        pressedOperation = false;
        operation.setValue1(Double.NaN);
        operation.setValue2(Double.NaN);
        textField.clear();
    }

    @FXML
    void buttonClearOneSymbolOnAction(ActionEvent event) {
        if (!textField.getText().isEmpty()) {
            if(textField.getText().indexOf('-') == textField.getText().length()-2 && textField.getText().length() != 1){
                textField.deleteText(textField.getText().length()-2,textField.getText().length());
            }else if(textField.getText().indexOf('E') == textField.getText().length()-2 && textField.getText().length() != 1){
                textField.deleteText(textField.getText().length()-2,textField.getText().length());
            } else {
                textField.deletePreviousChar();
                if (!pressedOperation && !Double.isNaN(operation.getValue1()) && !Objects.equals(operation.getOp(), "=")) {
                    if (!textField.getText().isEmpty())
                        operation.setValue2(Double.parseDouble(textField.getText()));
                    else
                        operation.setValue2(Double.NaN);
                } else {
                    operation.setValue1(Double.NaN);
                    pressedOperation = false;
                }
            }
        }else{
            buttonClearAllOnAction(event);
        }
    }

    @FXML
    void buttonAddOnAction(ActionEvent event) {
        alg();
        operation.setOp("+");
        textField.setText(operation.getValue1String() + "+");
        textField.positionCaret(textField.getCaretPosition() + textField.getText().length());
    }

    @FXML
    void buttonCommaOnAction(ActionEvent event) {
        if(Objects.equals(operation.getOp(), "=")){
            operation.setOp("");
        }
        textField.setText(textField.getText() + ".");
        textField.positionCaret(textField.getCaretPosition() + textField.getText().length());
    }

    @FXML
    void buttonDivisionOnAction(ActionEvent event) {
        alg();
        operation.setOp("/");
        textField.setText(operation.getValue1String() + "÷");
        textField.positionCaret(textField.getCaretPosition() + textField.getText().length());
    }

    @FXML
    void buttonEqualsOnAction(ActionEvent event) {
        if(!Objects.equals(operation.getOp(), "=")){
            alg();
            pressedOperation = false;
            operation.setOp("=");
            textField.setText(operation.getValue1String());
            textField.positionCaret(textField.getCaretPosition() + textField.getText().length());
            operation.setValue1(Double.NaN);
        }
    }

    @FXML
    void buttonMultiplyOnAction(ActionEvent event) {
        alg();
        operation.setOp("*");
        textField.setText(operation.getValue1String() + "×");
        textField.positionCaret(textField.getCaretPosition() + textField.getText().length());
    }

    @FXML
    void buttonRootOnAction(ActionEvent event) {
        if(!pressedOperation && Double.isNaN(operation.getValue1()) && !textField.getText().isEmpty()){
            operation.setValue1(Double.parseDouble(textField.getText()));
            operation.setOp("√");
            operation.setValue1(operation.calculate());
            if(Double.isNaN(operation.getValue1()) ){
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Корень из отрицательного числа!");
                alert.showAndWait();
                textField.setText("");
            }
            operation.setValue2(Double.NaN);
            textField.setText(operation.getValue1String());
            textField.positionCaret(textField.getCaretPosition() + textField.getText().length());
            operation.setValue1(Double.NaN);
        }
    }

    @FXML
    void buttonPowOnAction(ActionEvent event) {
        alg();
        operation.setOp("n");
        textField.setText(operation.getValue1String() + "^");
        textField.positionCaret(textField.getCaretPosition() + textField.getText().length());
    }

    @FXML
    void buttonSubOnAction(ActionEvent event) {
        alg();
        operation.setOp("-");
        textField.setText(operation.getValue1String() + "-");
        textField.positionCaret(textField.getCaretPosition() + textField.getText().length());
    }

    @FXML
    void buttonNumber0_OnAction(ActionEvent event) {
        clearTextField();
        textField.appendText("0");
    }

    @FXML
    void buttonNumber1_OnAction(ActionEvent event) {
        clearTextField();
        textField.appendText("1");
    }

    @FXML
    void buttonNumber2_OnAction(ActionEvent event) {
        clearTextField();
        textField.appendText("2");
    }

    @FXML
    void buttonNumber3_OnAction(ActionEvent event) {
        clearTextField();
        textField.appendText("3");
    }

    @FXML
    void buttonNumber4_OnAction(ActionEvent event) {
        clearTextField();
        textField.appendText("4");
    }

    @FXML
    void buttonNumber5_OnAction(ActionEvent event) {
        clearTextField();
        textField.appendText("5");
    }

    @FXML
    void buttonNumber6_OnAction(ActionEvent event) {
        clearTextField();
        textField.appendText("6");
    }

    @FXML
    void buttonNumber7_OnAction(ActionEvent event) {
        clearTextField();
        textField.appendText("7");
    }

    @FXML
    void buttonNumber8_OnAction(ActionEvent event) {
        clearTextField();
        textField.appendText("8");
    }

    @FXML
    void buttonNumber9_OnAction(ActionEvent event) {
        clearTextField();
        textField.appendText("9");
    }

    private void clearTextField(){
        if(pressedOperation) {
            textField.clear();
        }else if(Objects.equals(operation.getOp(), "=")){
            textField.clear();
            operation.setOp("");
        }
        pressedOperation = false;
    }

    private void alg(){
        if(!textField.getText().isEmpty()) {
            if (pressedOperation) {
                textField.setText(textField.getText().substring(0,textField.getText().length()-1));
            }else {
                if (Double.isNaN(operation.getValue1()))
                    operation.setValue1(Double.parseDouble(textField.getText()));
                else if (Double.isNaN(operation.getValue2()))
                    operation.setValue2(Double.parseDouble(textField.getText()));
                if (!Double.isNaN(operation.getValue1()) && !Double.isNaN(operation.getValue2())) {
                    operation.setValue1(operation.calculate());
                    if(Double.isNaN(operation.getValue1()) ){
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        if(Objects.equals(operation.getOp(), "/")){
                            alert.setContentText("Деление на 0!");
                        }else if(Objects.equals(operation.getOp(), "n")){
                            alert.setContentText("Корень из отрицательного числа!");
                        }
                        alert.showAndWait();
                        textField.clear();
                    }
                    operation.setValue2(Double.NaN);
                }
            }
            pressedOperation = true;
        }
    }

    @FXML
    void initialize() {

        textField.textProperty().addListener((observableValue, s, t1) -> {
        if (!t1.isEmpty()) {
            if (t1.matches("-?[0-9]+\\.?([0-9]+)?([eE][-+]?[0-9]+)?\\^?((?<=[0-9])(\\+|\\-|\\*|\\/|\\%|\\×|\\÷))?")) {
                textField.setText(t1);
            } else {
                textField.setText(s);
            }
        }});

        textField.setOnKeyPressed(ke -> {
            if(ke.getCode()== KeyCode.BACK_SPACE){
                ke.consume();
                buttonClearOneSymbol.fire();
            }
        });

        textField.setOnKeyTyped(ke -> {
                switch (ke.getCharacter()) {
                    case "+":
                        ke.consume();
                        buttonAdd.fire();
                        break;
                    case "-":
                        ke.consume();
                        buttonSub.fire();
                        break;
                    case "*":
                        ke.consume();
                        buttonMultiply.fire();
                        break;
                    case "/":
                        ke.consume();
                        buttonDivision.fire();
                        break;
                    case "^":
                        ke.consume();
                        buttonPow.fire();
                        break;
                    case ".":
                        ke.consume();
                        buttonComma.fire();
                        break;
                    case "=":
                    case "\r":
                        ke.consume();
                        buttonEquals.fire();
                        break;
                    case "0":
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6":
                    case "7":
                    case "8":
                    case "9":
                        clearTextField();
                        break;
                }
        });
    }
}
