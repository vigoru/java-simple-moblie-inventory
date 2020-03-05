/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invencol.ui.home;

import invencol.jdbc.DAO.ColetorDAO;
import invencol.model.Coletor;
import invencol.ui.coletor.FXMLColetorController;
import invencol.util.ToolBox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author vinicius
 */
public class FXMLHomeController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    private ComboBox<String> cbFields;
    @FXML
    private TableView<Coletor> tvColetores;
    @FXML
    private TableColumn<Coletor, Long> colCgLoja;
    @FXML
    private TableColumn<Coletor, Long> colPatrimonio;
    @FXML
    private TableColumn<Coletor, String> colSerialNumber;
    @FXML
    private TableColumn<Coletor, String> colApp;
    
    
    String[] fields = {"ID", "APP_NAME", "CG_LOJA", "PATRIMONIO", "SERIAL_NUMBER"};
    String[] fieldNames = {"Selecione", "Aplicativo", "Código de Loja", "Número de Patrimônio", "Serial Number"};
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbFields.setItems(FXCollections.observableArrayList(fieldNames));
        
        colCgLoja.setCellValueFactory(new PropertyValueFactory<>("CG_LOJA"));
        colPatrimonio.setCellValueFactory(new PropertyValueFactory<>("PATRIMONIO"));
        colSerialNumber.setCellValueFactory(new PropertyValueFactory<>("SERIAL_NUMBER"));
        colApp.setCellValueFactory(new PropertyValueFactory<>("APP_NAME"));
    }

    @FXML
    private void handleButtonNovoColetor(ActionEvent event) {
        try {
            ToolBox.sobreporTela(FXMLColetorController.class, (Node) event.getSource());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleButtonEditarColetor(ActionEvent event) {
        Coletor c = tvColetores.getSelectionModel().getSelectedItem();

        if (c != null) {
            try {
                ToolBox.sobreporTela(FXMLColetorController.class, (Node) event.getSource(), c);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void handleButtonBuscar(ActionEvent event) {
        int index = cbFields.getSelectionModel().getSelectedIndex();
        
        List<Coletor> list = new ArrayList<>();
        
        if (index <= 0 ){
            list = new ColetorDAO().getAll();
        }else if (!tfSearch.getText().isEmpty()) {
            list = new ColetorDAO().findByField(fields[index], tfSearch.getText());
        }        
        
        ObservableList<Coletor> itens = FXCollections.observableArrayList(list);        
        tvColetores.setItems(itens);
    }

}
