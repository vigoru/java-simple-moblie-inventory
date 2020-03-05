/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invencol.ui.coletor;

import invencol.controller.AbstractFXMLController;
import invencol.jdbc.DAO.ColetorDAO;
import invencol.model.Coletor;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author vinicius
 */
public class FXMLColetorController extends AbstractFXMLController<Coletor>{

    @FXML
    private TextField tfCgLoja;
    @FXML
    private TextField tfPatrimonio;
    @FXML
    private TextField tfSerialNumber;
    @FXML
    private ComboBox<String> cbAppName;
    @FXML
    private Button btnDelete;

    private ColetorDAO cDAO;
    private Coletor coletor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cDAO = new ColetorDAO();
        cbAppName.setItems(FXCollections.observableArrayList("Quiosque", "DeRemarcação"));
    }

    @FXML
    private void handleButtonSalvar(ActionEvent event) {
        if (coletor == null){
            coletor = new Coletor();
        }
        
        coletor.setAPP_NAME(cbAppName.getValue());
        coletor.setCG_LOJA(Long.parseLong(tfCgLoja.getText()));
        coletor.setPATRIMONIO(Long.parseLong(tfPatrimonio.getText()));
        coletor.setSERIAL_NUMBER(tfSerialNumber.getText());

        try {
            cDAO.insert(coletor);
            handleButtonCancelar(event);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLColetorController.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }

    @FXML
    private void handleButtonDeletar(ActionEvent event) {
        try {
            cDAO.deleteById(coletor.getID());
            handleButtonCancelar(event);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLColetorController.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }

    @FXML
    private void handleButtonCancelar(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void buildScreen(Coletor entity) {
        if (entity != null) {
            coletor = entity;
            tfCgLoja.setText(entity.getCG_LOJA().toString());
            tfPatrimonio.setText(entity.getPATRIMONIO().toString());
            tfSerialNumber.setText(entity.getSERIAL_NUMBER());
            cbAppName.getSelectionModel().select(entity.getAPP_NAME());
            
            btnDelete.setDisable(false);            
        } else {
            btnDelete.setDisable(true);
        }
    }
}
