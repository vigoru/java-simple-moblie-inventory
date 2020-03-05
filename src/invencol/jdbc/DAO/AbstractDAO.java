/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invencol.jdbc.DAO;

import invencol.jdbc.connection.ConnectionFactory;
import invencol.model.AbstractModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author vinicius
 */
public abstract class AbstractDAO<T extends AbstractModel> extends AbstractDAOUtil<T>{
    
    protected abstract HashMap<String, Object> getValues(T entity);
    protected abstract T getEntity(ResultSet values);
    protected abstract String getTableName();
    
    public void insert(T entity)throws SQLException{
        execute(buildInsert(entity));        
    }
    
    public void update(T entity, String whereField, String whereValue)throws SQLException{
        execute(buildUpdate(entity, whereField, whereValue));
    }
    
    public void deleteById(Long id) throws SQLException{
        String stmt = "DELETE FROM " + getTableName() + " WHERE ID = " + id.toString() + ";";
        execute(stmt);         
    }
    
    public T getItemFromDB(String sql) throws SQLException{
        return getEntity(executeWithResult(sql));
    }    
    
    private String getOnlyValues(T entity){
        StringBuilder sb = new StringBuilder();
        Set<Entry<String,Object>> entrySet = getValues(entity).entrySet();
        
        for(Map.Entry<String,Object> entry : getValues(entity).entrySet()){            
            sb.append("'").append(entry.getValue()).append("',");
        }
        
        String result = sb.toString();
        
        return result.substring(0, result.length() - 1);
    }
    
    protected void execute(String sql) throws SQLException{
        System.out.println(sql);
        Connection connection = new ConnectionFactory().getConnection();
        connection.prepareStatement(sql).execute();  
        connection.close();
    }
    
    protected ResultSet executeWithResult(String sql) throws SQLException{
        System.out.println(sql);
        Connection connection = new ConnectionFactory().getConnection();
        ResultSet rs = connection.prepareStatement(sql).executeQuery();  
        connection.close();
        
        return rs;
    }
}
