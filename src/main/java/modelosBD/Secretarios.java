/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelosBD;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author NICOLAS
 */
@Entity(name = "secretarios")
public class Secretarios implements Serializable {
    @Id
    @Column(name = "rutSecretario")
    private String rutSecretario;
    @Column(name = "nombreSecretario")
    private String nombreSecretario;
    @Column(name = "apellidoSecretario")
    private String apellidoSecretario;
    @Column(name = "edadSecretario")
    private int edadSecretario;
    @Column(name = "fechaNacimientoSecretario")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimientoSecretario;
    
    @ManyToOne
    @JoinColumn(name = "idLoginFK")
    private Login idLoginFK;

    public Secretarios() {
    }

    public Secretarios(String rutSecretario, String nombreSecretario, String apellidoSecretario, int edadSecretario, Date fechaNacimientoSecretario, Login idLoginFK) {
        this.rutSecretario = rutSecretario;
        this.nombreSecretario = nombreSecretario;
        this.apellidoSecretario = apellidoSecretario;
        this.edadSecretario = edadSecretario;
        this.fechaNacimientoSecretario = fechaNacimientoSecretario;
        this.idLoginFK = idLoginFK;
    }

    public String getRutSecretario() {
        return rutSecretario;
    }

    public void setRutSecretario(String rutSecretario) {
        this.rutSecretario = rutSecretario;
    }

    public String getNombreSecretario() {
        return nombreSecretario;
    }

    public void setNombreSecretario(String nombreSecretario) {
        this.nombreSecretario = nombreSecretario;
    }

    public String getApellidoSecretario() {
        return apellidoSecretario;
    }

    public void setApellidoSecretario(String apellidoSecretario) {
        this.apellidoSecretario = apellidoSecretario;
    }

    public int getEdadSecretario() {
        return edadSecretario;
    }

    public void setEdadSecretario(int edadSecretario) {
        this.edadSecretario = edadSecretario;
    }

    public Date getFechaNacimientoSecretario() {
        return fechaNacimientoSecretario;
    }

    public void setFechaNacimientoSecretario(Date fechaNacimientoSecretario) {
        this.fechaNacimientoSecretario = fechaNacimientoSecretario;
    }

    public Login getIdLoginFK() {
        return idLoginFK;
    }

    public void setIdLoginFK(Login idLoginFK) {
        this.idLoginFK = idLoginFK;
    }
    
    
    
    
}
