/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelosBD;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author NICOLAS
 */
@Entity(name = "responsablePaciente")
public class ResponsablePaciente implements Serializable {
    @Id
    @Column(name = "rutResponsable")
    private String rutResponsable;
    @Column(name = "nombreResponsable")
    private String nombreResponsable;
    @Column(name = "apellidoResponsable")
    private String apellidoResponsable;
    @Column(name = "fechaNacimientoResponsable")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimientoResponsable;
    @Column(name = "edadResponsable")
    private int edadResponsable;
    
    @OneToMany(mappedBy = "rutResponsableFK")
    private List<Pacientes> pacientesLista;

    public ResponsablePaciente() {
    }

    public ResponsablePaciente(String rutResponsable, String nombreResponsable, String apellidoResponsable, Date fechaNacimientoResponsable, int edadResponsable) {
        this.rutResponsable = rutResponsable;
        this.nombreResponsable = nombreResponsable;
        this.apellidoResponsable = apellidoResponsable;
        this.fechaNacimientoResponsable = fechaNacimientoResponsable;
        this.edadResponsable = edadResponsable;
    }

    public ResponsablePaciente(String rutResponsable, String nombreResponsable, String apellidoResponsable, Date fechaNacimientoResponsable, int edadResponsable, List<Pacientes> pacientesLista) {
        this.rutResponsable = rutResponsable;
        this.nombreResponsable = nombreResponsable;
        this.apellidoResponsable = apellidoResponsable;
        this.fechaNacimientoResponsable = fechaNacimientoResponsable;
        this.edadResponsable = edadResponsable;
        this.pacientesLista = pacientesLista;
    }

    public String getRutResponsable() {
        return rutResponsable;
    }

    public void setRutResponsable(String rutResponsable) {
        this.rutResponsable = rutResponsable;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getApellidoResponsable() {
        return apellidoResponsable;
    }

    public void setApellidoResponsable(String apellidoResponsable) {
        this.apellidoResponsable = apellidoResponsable;
    }

    public Date getFechaNacimientoResponsable() {
        return fechaNacimientoResponsable;
    }

    public void setFechaNacimientoResponsable(Date fechaNacimientoResponsable) {
        this.fechaNacimientoResponsable = fechaNacimientoResponsable;
    }

    public int getEdadResponsable() {
        return edadResponsable;
    }

    public void setEdadResponsable(int edadResponsable) {
        this.edadResponsable = edadResponsable;
    }

    public List<Pacientes> getPacientesLista() {
        return pacientesLista;
    }

    public void setPacientesLista(List<Pacientes> pacientesLista) {
        this.pacientesLista = pacientesLista;
    }
    
    
    
    
}
