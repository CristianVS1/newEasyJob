/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.entity.neweasyjob;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author user
 */
@Entity
@Table(name = "tbl_administrador")
@NamedQueries({
    @NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a")})
public class Administrador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "SucursalAdm")
    private String sucursalAdm;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "administrador", fetch = FetchType.LAZY)
    private Collection<Aprobacion> aprobacionCollection;
    @JoinColumns({
        @JoinColumn(name = "cedula", referencedColumnName = "cedula"),
        @JoinColumn(name = "cedula", referencedColumnName = "cedula")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    public Administrador() {
    }

    public Administrador(Integer id) {
        this.id = id;
    }

    public Administrador(Integer id, String sucursalAdm) {
        this.id = id;
        this.sucursalAdm = sucursalAdm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSucursalAdm() {
        return sucursalAdm;
    }

    public void setSucursalAdm(String sucursalAdm) {
        this.sucursalAdm = sucursalAdm;
    }

    public Collection<Aprobacion> getAprobacionCollection() {
        return aprobacionCollection;
    }

    public void setAprobacionCollection(Collection<Aprobacion> aprobacionCollection) {
        this.aprobacionCollection = aprobacionCollection;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof Administrador)) {
            return false;
        }
        Administrador other = (Administrador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entityeasyjob.Administrador[ id=" + id + " ]";
    }
    
}
