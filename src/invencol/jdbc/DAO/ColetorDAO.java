/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invencol.jdbc.DAO;

import invencol.model.Coletor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author vinicius
 */
public class ColetorDAO extends AbstractDAO<Coletor> {

    @Override
    protected HashMap<String, Object> getValues(Coletor entity) {
        HashMap<String, Object> values = new HashMap<>();
        return values;
    }

    @Override
    protected Coletor getEntity(ResultSet values) {
        Coletor coletor = new Coletor();

        try {
            coletor.setID(values.getLong("ID"));
            coletor.setCG_LOJA(values.getLong("CG_LOJA"));
            coletor.setPATRIMONIO(values.getLong("PATRIMONIO"));
            coletor.setSERIAL_NUMBER(values.getString("SERIAL_NUMBER"));
            coletor.setAPP_NAME(values.getString("APP_NAME"));
            return coletor;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected String getTableName() {
        return "inventario_coletor";
    }

    public List<Coletor> getAll() {
        List<Coletor> list = new ArrayList<>();
        String sql = "SELECT * FROM " + getTableName();
        try {
            ResultSet rs = executeWithResult(sql);

            Coletor c = null;
            while (rs.next()) {
                c = getEntity(rs);
                if (c != null) {
                    list.add(c);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Coletor> findByField(String field, Object value) {
        List<Coletor> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ").append(getTableName()).append(" WHERE ").append(field);

        if (value instanceof String) {
            sql.append(" LIKE '%").append(value.toString()).append("%'");
        } else {
            sql.append(" = ").append("'").append(value.toString()).append("'");
        }

        try {
            ResultSet rs = executeWithResult(sql.toString());

            Coletor c = null;
            while (rs.next()) {
                c = getEntity(rs);
                if (c != null) {
                    list.add(c);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public void insert(Coletor coletor) throws SQLException {
        if (coletor.getID() == null || coletor.getID() == 0) {
            super.insert(coletor);
        } else {
            update(coletor, "ID", coletor.getID().toString());
        }
    }
}
