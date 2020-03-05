/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invencol.jdbc.DAO;


import invencol.model.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vinicius
 */
public class UsuarioDAO extends AbstractDAO<Usuario>{

    @Override
    protected HashMap<String, Object> getValues(Usuario entity) {
        HashMap<String, Object> values = new HashMap<>();
        
        if(entity.getId() != null){
            values.put("ID", entity.getId());
        }
        values.put("NAME", entity.getName());
        values.put("PASSWRD", entity.getPasswrd());
        values.put("EMAIL", entity.getEmail());
        
        return values;
    }

    @Override
    protected Usuario getEntity(ResultSet values) {
        Usuario user = null;
        try {
            if(values.next()){
                user = new Usuario();
                user.setId(values.getLong("ID"));
                user.setName(values.getString("NAME"));
                user.setPasswrd(values.getString("PASSWRD"));
                user.setEmail(values.getString("EMAIL"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, ex.getMessage());
            return null;
        }    
        return user;        
    }

    @Override
    protected String getTableName() {
        return "user";
    }
    
    public boolean verifyPasswrd(Usuario user){                
        try {
            String sql = "SELECT * FROM " + getTableName()+ " WHERE NAME = '" + user.getName() + "' AND PASSWRD = '"+ user.getPasswrd() + "';";            
            return getItemFromDB(sql) != null;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }        
    }    
}
