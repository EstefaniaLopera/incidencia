/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.Pojos;


/**
 *
 * @author vesprada
 */
public class UsuarioPojo {
    
    
    private Integer id;
   
    private String nombre;
    
    private String ape1;
    
    private String ape2;
    
    private String telefono;
   
    private String email;
   
    private Integer idTipoUsuario;
   
    
    private String login;
    
    private String password;

    public UsuarioPojo() {
    }

    public UsuarioPojo(Integer id) {
        this.id = id;
    }

    public UsuarioPojo(Integer id, String nombre, String ape1, String ape2, String telefono, String email, Integer idTipoUsuario, String login, String password) {
        this.id = id;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.telefono = telefono;
        this.email = email;
        this.idTipoUsuario = idTipoUsuario;
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe1() {
        return ape1;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public String getApe2() {
        return ape2;
    }

    public void setApe2(String ape2) {
        this.ape2 = ape2;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(Integer idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioPojo)) {
            return false;
        }
        UsuarioPojo other = (UsuarioPojo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jdbc.entity.Usuario[ id=" + id + " ]";
    }
    
}
