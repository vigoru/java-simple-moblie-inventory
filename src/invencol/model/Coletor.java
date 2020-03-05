/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invencol.model;

/**
 *
 * @author vinicius
 */
public class Coletor extends AbstractModel {

    private Long ID;
    private Long CG_LOJA;
    private Long PATRIMONIO;
    private String SERIAL_NUMBER;
    private String APP_NAME;

    /**
     * @return the id
     */
    public Long getID() {
        return ID;
    }

    /**
     * @param ID the id to set
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    /**
     * @return the cgLoja
     */
    public Long getCG_LOJA() {
        return CG_LOJA;
    }

    /**
     * @param CG_LOJA the cgLoja to set
     */
    public void setCG_LOJA(Long CG_LOJA) {
        this.CG_LOJA = CG_LOJA;
    }

    /**
     * @return the patrimonio
     */
    public Long getPATRIMONIO() {
        return PATRIMONIO;
    }

    /**
     * @param PATRIMONIO the patrimonio to set
     */
    public void setPATRIMONIO(Long PATRIMONIO) {
        this.PATRIMONIO = PATRIMONIO;
    }

    /**
     * @return the serialNumber
     */
    public String getSERIAL_NUMBER() {
        return SERIAL_NUMBER;
    }

    /**
     * @param SERIAL_NUMBER the serialNumber to set
     */
    public void setSERIAL_NUMBER(String SERIAL_NUMBER) {
        this.SERIAL_NUMBER = SERIAL_NUMBER;
    }

    /**
     * @return the appName
     */
    public String getAPP_NAME() {
        return APP_NAME;
    }

    /**
     * @param APP_NAME the appName to set
     */
    public void setAPP_NAME(String APP_NAME) {
        this.APP_NAME = APP_NAME;
    }

    @Override
    public String toString() {
        return "Coletor{ cgLoja=" + CG_LOJA + ", patrimonio=" + PATRIMONIO + ", serialNumber=" + SERIAL_NUMBER + ", appName=" + APP_NAME + '}';
    }

    @Override
    public String getTableName() {
        return "inventario_coletor";
    }
}
