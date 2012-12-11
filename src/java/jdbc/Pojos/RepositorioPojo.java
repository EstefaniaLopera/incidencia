/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.Pojos;

import java.util.Date;



/**
 *
 * @author vesprada
 */
public class RepositorioPojo{
    private static final long serialVersionUID = 1L;
   
    private Integer id;
   
    private String titulo;
    
    private String contenido;
    
    private int idUsuario;
    
    private int idLenguaje;
   
    private int idDocumento;
   
    private Date fecha;

    public RepositorioPojo() {
    }

    public RepositorioPojo(Integer id) {
        this.id = id;
    }

    public RepositorioPojo(Integer id, String titulo, String contenido, int idUsuario, int idLenguaje, int idDocumento, Date fecha) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.idUsuario = idUsuario;
        this.idLenguaje = idLenguaje;
        this.idDocumento = idDocumento;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdLenguaje() {
        return idLenguaje;
    }

    public void setIdLenguaje(int idLenguaje) {
        this.idLenguaje = idLenguaje;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        if (!(object instanceof RepositorioPojo)) {
            return false;
        }
        RepositorioPojo other = (RepositorioPojo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jdbc.entity.Repositorio[ id=" + id + " ]";
    }
    
}
