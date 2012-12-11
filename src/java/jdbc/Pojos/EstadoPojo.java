/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.Pojos;

/**
 *
 * @author vesprada
 */

public class EstadoPojo {
    private static final long serialVersionUID = 1L;
   
    private Integer id;
    
    private String nombre;

    public EstadoPojo() {
    }

    public EstadoPojo(Integer id) {
        this.id = id;
    }

    public EstadoPojo(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoPojo)) {
            return false;
        }
        EstadoPojo other = (EstadoPojo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jdbc.entity.Estado[ id=" + id + " ]";
    }
    
}
