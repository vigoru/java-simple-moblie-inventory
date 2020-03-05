/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invencol.controller;

import javafx.fxml.Initializable;

/**
 *
 * @author vinicius
 */
public abstract class AbstractFXMLController<T> implements Initializable {

    public abstract void buildScreen(T entity);

}
