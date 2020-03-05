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
public class Usuario extends AbstractModel{
    
    private Long id;    
    private String name;
    private String passwrd;
    private String email;    
    
    public Usuario(){
        
    }
    
    public Usuario(String name, String passwrd){
        this.name = name;
        this.passwrd = passwrd;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the passwrd
     */
    public String getPasswrd() {
        return passwrd;
    }

    /**
     * @param passwrd the passwrd to set
     */
    public void setPasswrd(String passwrd) {
        this.passwrd = passwrd;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getTableName() {
        return "user";
    }

    
}
