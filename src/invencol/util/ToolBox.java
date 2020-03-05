/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invencol.util;

import invencol.controller.AbstractFXMLController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author vinicius
 */
public class ToolBox {

    public static void sobreporTela(Class<?> clazz, Node telaAtual) throws IOException {
        sobreporTela(clazz, telaAtual, null);
    }

    public static void sobreporTela(Class<?> clazz, Node telaAtual, Object entity) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader(clazz.getResource(clazz.getSimpleName().replace("Controller", ".fxml")));
        Parent root = fXMLLoader.load();

        if (fXMLLoader.getController() instanceof AbstractFXMLController) {
            ((AbstractFXMLController) fXMLLoader.getController()).buildScreen(entity);
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        if (telaAtual != null) {
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(getStage(telaAtual));
        }
        stage.show();
    }

    public static void trocarTela(Class<?> clazz, Node telaAtual) throws IOException {
        Parent root = FXMLLoader.load(clazz.getResource(clazz.getSimpleName().replace("Controller", ".fxml")));

        getStage(telaAtual).setScene(new Scene(root));
    }

    private static Stage getStage(Node node) {
        return (Stage) node.getScene().getWindow();
    }
}
