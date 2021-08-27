/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.entity.neweasyjob;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author user
 */
@Entity
@Table(name = "tbl_vistas_empleado")
@NamedQueries({
    @NamedQuery(name = "VistasEmpleado.findAll", query = "SELECT v FROM VistasEmpleado v")})
public class VistasEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vistas_empleado_id")
    private Integer vistasEmpleadoId;
    @Size(max = 45)
    @Column(name = "vis_emp_nombre")
    private String visEmpNombre;
    @Size(max = 150)
    @Column(name = "vis_emp_ruta")
    private String visEmpRuta;
    @Column(name = "vis_emp_estado")
    private Short visEmpEstado;
    @JoinColumn(name = "fk_vis_emp_cargo_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cargo fkVisEmpCargoId;

    public VistasEmpleado() {
    }

    public VistasEmpleado(Integer vistasEmpleadoId) {
        this.vistasEmpleadoId = vistasEmpleadoId;
    }

    public Integer getVistasEmpleadoId() {
        return vistasEmpleadoId;
    }

    public void setVistasEmpleadoId(Integer vistasEmpleadoId) {
        this.vistasEmpleadoId = vistasEmpleadoId;
    }

    public String getVisEmpNombre() {
        return visEmpNombre;
    }

    public void setVisEmpNombre(String visEmpNombre) {
        this.visEmpNombre = visEmpNombre;
    }

    public String getVisEmpRuta() {
        return visEmpRuta;
    }

    public void setVisEmpRuta(String visEmpRuta) {
        this.visEmpRuta = visEmpRuta;
    }

    public Short getVisEmpEstado() {
        return visEmpEstado;
    }

    public void setVisEmpEstado(Short visEmpEstado) {
        this.visEmpEstado = visEmpEstado;
    }

    public Cargo getFkVisEmpCargoId() {
        return fkVisEmpCargoId;
    }

    public void setFkVisEmpCargoId(Cargo fkVisEmpCargoId) {
        this.fkVisEmpCargoId = fkVisEmpCargoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vistasEmpleadoId != null ? vistasEmpleadoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VistasEmpleado)) {
            return false;
        }
        VistasEmpleado other = (VistasEmpleado) object;
        if ((this.vistasEmpleadoId == null && other.vistasEmpleadoId != null) || (this.vistasEmpleadoId != null && !this.vistasEmpleadoId.equals(other.vistasEmpleadoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entityeasyjob.VistasEmpleado[ vistasEmpleadoId=" + vistasEmpleadoId + " ]";
    }
    
}
