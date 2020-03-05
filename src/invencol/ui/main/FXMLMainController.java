/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invencol.ui.main;

import invencol.jdbc.DAO.UsuarioDAO;
import invencol.model.Usuario;
import invencol.ui.home.FXMLHomeController;

import invencol.util.ToolBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author vinicius
 */
public class FXMLMainController implements Initializable {

    @FXML
    private TextField tfUserName;

    @FXML
    private Button button;

    @FXML
    private PasswordField pfPasswrd;

    @FXML
    private void handleButtonEntrar(ActionEvent event) {
        if (new UsuarioDAO().verifyPasswrd(new Usuario(tfUserName.getText(), pfPasswrd.getText()))) {
            System.out.println("Usuário autenticado!");

            try {
                ToolBox.trocarTela(FXMLHomeController.class, (Node) (event.getSource()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Usuário inválido!");
        }
    }

    @FXML
    private void handleButtonCancelar(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfUserName.setText("rufino");
        pfPasswrd.setText("asterisk");
    }

}
