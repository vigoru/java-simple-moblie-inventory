/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invencol.jdbc.DAO.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author vinicius
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    public String name();
    public boolean isID() default false;    
}
