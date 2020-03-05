/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invencol.jdbc.DAO;

import invencol.jdbc.DAO.annotations.Column;
import invencol.model.AbstractModel;
//import invencol.jdbc.DAO.annotations.Table;
import invencol.model.Coletor;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vinicius
 */
abstract class AbstractDAOUtil<T extends AbstractModel> {

    public String buildInsert(T entity) {
        StringBuilder sb = new StringBuilder();

        if (entity.getTableName() == null) {
            return "";
        }

        List<String> fields = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (Field f : entity.getClass().getDeclaredFields()) {
            boolean isAccessible = f.isAccessible();
            f.setAccessible(true);

            Object obj;
            try {
                obj = f.get(entity);
                if (obj == null) {
                    continue;
                }

                if (!f.getName().startsWith("ID")) {
                    fields.add(f.getName());
                    if (obj instanceof Long) {
                        values.add(obj.toString());
                    } else if (obj instanceof String) {
                        values.add("'" + obj.toString() + "'");
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                ex.printStackTrace();
            } finally {
                f.setAccessible(isAccessible);
            }
        }

        sb.append("insert into ");
        sb.append(entity.getTableName());
        sb.append(" (");
        sb.append(implode(fields, ","));
        sb.append(") values (");
        sb.append(implode(values, ","));
        sb.append(")");

        return sb.toString();
    }

    private String implode(List<String> list, String separator) {
        StringBuilder sb = new StringBuilder();

        if (list.size() > 0) {
            sb.append(list.get(0));
            for (int i = 1; i < list.size(); i++) {
                sb.append(separator).append(list.get(i));
            }
        }

        return sb.toString();
    }

    public String buildUpdate(T entity, String whereField, String whereValue) {
        StringBuilder sb = new StringBuilder();

        if (entity.getTableName() == null) {
            return "";
        }

        List<String> fields = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (Field f : entity.getClass().getDeclaredFields()) {
            boolean isAccessible = f.isAccessible();
            f.setAccessible(true);

            Object obj;
            try {
                obj = f.get(entity);
                if (obj == null) {
                    continue;
                }

                if (!f.getName().startsWith("ID")) {
                    fields.add(f.getName());
                    if (obj instanceof Long) {
                        values.add(obj.toString());
                    } else if (obj instanceof String) {
                        values.add("'" + obj.toString() + "'");
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                ex.printStackTrace();
            } finally {
                f.setAccessible(isAccessible);
            }
        }

        sb.append("update ");
        sb.append(entity.getTableName());
        sb.append(" set ");
        
        sb.append(fields.get(0)).append(" = ").append(values.get(0));;
        
        for(int i = 1; i < fields.size(); i++){
            sb.append(",").append(fields.get(i)).append(" = ").append(values.get(i));
        }
        
        sb.append(" where ").append(whereField).append(" = ").append(whereValue);
       
        return sb.toString();
    }

}
