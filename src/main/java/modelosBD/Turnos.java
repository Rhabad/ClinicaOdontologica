/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelosBD;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author NICOLAS
 */
@Entity(name = "turnos")
public class Turnos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idTurnos;
    @Column(name = "fechaAtencion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAtencion;
    
    @ManyToOne
    @JoinColumn(name = "rutOdontologoFK")
    private Odontologos rutOdontologoFK;
    @ManyToOne
    @JoinColumn(name = "rutPacienteFK")
    private Pacientes rutPacienteFK;

    public Turnos() {
    }

    public Turnos(long idTurnos, Date fechaAtencion, Odontologos rutOdontologoFK, Pacientes rutPacienteFK) {
        this.idTurnos = idTurnos;
        this.fechaAtencion = fechaAtencion;
        this.rutOdontologoFK = rutOdontologoFK;
        this.rutPacienteFK = rutPacienteFK;
    }

    public long getIdTurnos() {
        return idTurnos;
    }

    public void setIdTurnos(long idTurnos) {
        this.idTurnos = idTurnos;
    }

    public Date getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(Date fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public Odontologos getRutOdontologoFK() {
        return rutOdontologoFK;
    }

    public void setRutOdontologoFK(Odontologos rutOdontologoFK) {
        this.rutOdontologoFK = rutOdontologoFK;
    }

    public Pacientes getRutPacienteFK() {
        return rutPacienteFK;
    }

    public void setRutPacienteFK(Pacientes rutPacienteFK) {
        this.rutPacienteFK = rutPacienteFK;
    }
    
    
    
}
