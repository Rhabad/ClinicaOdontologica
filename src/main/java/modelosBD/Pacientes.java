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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author NICOLAS
 */
@Entity(name = "pacientes")
public class Pacientes implements Serializable {
    @Id
    @Column(name = "rutPaciente")
    private String rutPaciente;
    @Column(name = "nombrePaciente")
    private String nombrePaciente;
    @Column(name = "apellidoPaciente")
    private String apellidoPaciente;
    @Column(name = "edadPaciente")
    private int edadPaciente;
    @Column(name = "fechaNacimientoPaciente")
    private Date fechaNacimientoPaciente;
    
    @ManyToOne
    @JoinColumn(name = "rutResponsableFK")
    private ResponsablePaciente rutResponsableFK;
    @ManyToOne
    @JoinColumn(name = "idTipoPagoFK")
    private TipoPago idTipoPagoFK;
    
    @OneToMany(mappedBy = "rutPacienteFK")
    private List<Descripcion> descripcionLista;
    @OneToMany(mappedBy = "rutPacienteFK")
    private List<Turnos> turnosLista;

    public Pacientes() {
    }

    public Pacientes(String rutPaciente, String nombrePaciente, String apellidoPaciente, int edadPaciente, Date fechaNacimientoPaciente, ResponsablePaciente rutResponsableFK, TipoPago idTipoPagoFK) {
        this.rutPaciente = rutPaciente;
        this.nombrePaciente = nombrePaciente;
        this.apellidoPaciente = apellidoPaciente;
        this.edadPaciente = edadPaciente;
        this.fechaNacimientoPaciente = fechaNacimientoPaciente;
        this.rutResponsableFK = rutResponsableFK;
        this.idTipoPagoFK = idTipoPagoFK;
    }

    public Pacientes(String rutPaciente, String nombrePaciente, String apellidoPaciente, int edadPaciente, Date fechaNacimientoPaciente, ResponsablePaciente rutResponsableFK, TipoPago idTipoPagoFK, List<Descripcion> descripcionLista, List<Turnos> turnosLista) {
        this.rutPaciente = rutPaciente;
        this.nombrePaciente = nombrePaciente;
        this.apellidoPaciente = apellidoPaciente;
        this.edadPaciente = edadPaciente;
        this.fechaNacimientoPaciente = fechaNacimientoPaciente;
        this.rutResponsableFK = rutResponsableFK;
        this.idTipoPagoFK = idTipoPagoFK;
        this.descripcionLista = descripcionLista;
        this.turnosLista = turnosLista;
    }
    

    public String getRutPaciente() {
        return rutPaciente;
    }

    public void setRutPaciente(String rutPaciente) {
        this.rutPaciente = rutPaciente;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getApellidoPaciente() {
        return apellidoPaciente;
    }

    public void setApellidoPaciente(String apellidoPaciente) {
        this.apellidoPaciente = apellidoPaciente;
    }

    public int getEdadPaciente() {
        return edadPaciente;
    }

    public void setEdadPaciente(int edadPaciente) {
        this.edadPaciente = edadPaciente;
    }

    public Date getFechaNacimientoPaciente() {
        return fechaNacimientoPaciente;
    }

    public void setFechaNacimientoPaciente(Date fechaNacimientoPaciente) {
        this.fechaNacimientoPaciente = fechaNacimientoPaciente;
    }

    public ResponsablePaciente getRutResponsableFK() {
        return rutResponsableFK;
    }

    public void setRutResponsableFK(ResponsablePaciente rutResponsableFK) {
        this.rutResponsableFK = rutResponsableFK;
    }

    public TipoPago getIdTipoPagoFK() {
        return idTipoPagoFK;
    }

    public void setIdTipoPagoFK(TipoPago idTipoPagoFK) {
        this.idTipoPagoFK = idTipoPagoFK;
    }

    public List<Descripcion> getDescripcionLista() {
        return descripcionLista;
    }

    public void setDescripcionLista(List<Descripcion> descripcionLista) {
        this.descripcionLista = descripcionLista;
    }

    public List<Turnos> getTurnosLista() {
        return turnosLista;
    }

    public void setTurnosLista(List<Turnos> turnosLista) {
        this.turnosLista = turnosLista;
    }
    
    
    
}
