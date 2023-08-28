/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelosBD;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 *
 * @author NICOLAS
 */
@Entity(name = "odontologos")
public class Odontologos implements Serializable {
    @Id
    @Column(name = "rutOdontologo")
    private String rutOdontologo;
    @Column(name = "nombreOdontologo")
    private String nombreOdontologo;
    @Column(name = "apellidoOdontologo")
    private String apellidoOdontologo;
    @Column(name = "edadOdontologo")
    private int edadOdontologo;
    @Column(name = "fechaNacimientoOdontologo")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimientoOdontologo;
    @Column(name = "especialidad")
    private String especialidad;
    @Column(name = "horarioDias")
    private String horarioDias;
    @Column(name = "horarioHoras")
    @Temporal(TemporalType.TIME)
    private Time horarioHoras;
    
    @ManyToOne
    @JoinColumn(name = "idLoginFK")
    private Login idLoginFK;
    
    @OneToMany(mappedBy = "rutOdontologoFK")
    private List<Turnos> turnosLista;

    public Odontologos() {
    }

    public Odontologos(String rutOdontologo, String nombreOdontologo, String apellidoOdontogo, int edadOdontologo, Date fechaNacimientoOdontologo, String especialidad, String horarioDias, Time horarioHoras, Login idLoginFK) {
        this.rutOdontologo = rutOdontologo;
        this.nombreOdontologo = nombreOdontologo;
        this.apellidoOdontologo = apellidoOdontogo;
        this.edadOdontologo = edadOdontologo;
        this.fechaNacimientoOdontologo = fechaNacimientoOdontologo;
        this.especialidad = especialidad;
        this.horarioDias = horarioDias;
        this.horarioHoras = horarioHoras;
        this.idLoginFK = idLoginFK;
    }

    public Odontologos(String rutOdontologo, String nombreOdontologo, String apellidoOdontogo, int edadOdontologo, Date fechaNacimientoOdontologo, String especialidad, String horarioDias, Time horarioHoras, Login idLoginFK, List<Turnos> turnosLista) {
        this.rutOdontologo = rutOdontologo;
        this.nombreOdontologo = nombreOdontologo;
        this.apellidoOdontologo = apellidoOdontogo;
        this.edadOdontologo = edadOdontologo;
        this.fechaNacimientoOdontologo = fechaNacimientoOdontologo;
        this.especialidad = especialidad;
        this.horarioDias = horarioDias;
        this.horarioHoras = horarioHoras;
        this.idLoginFK = idLoginFK;
        this.turnosLista = turnosLista;
    }
    
    

    public String getRutOdontologo() {
        return rutOdontologo;
    }

    public void setRutOdontologo(String rutOdontologo) {
        this.rutOdontologo = rutOdontologo;
    }

    public String getNombreOdontologo() {
        return nombreOdontologo;
    }

    public void setNombreOdontologo(String nombreOdontologo) {
        this.nombreOdontologo = nombreOdontologo;
    }

    public String getApellidoOdontologo() {
        return apellidoOdontologo;
    }

    public void setApellidoOdontologo(String apellidoOdontogo) {
        this.apellidoOdontologo = apellidoOdontogo;
    }

    public int getEdadOdontologo() {
        return edadOdontologo;
    }

    public void setEdadOdontologo(int edadOdontologo) {
        this.edadOdontologo = edadOdontologo;
    }

    public Date getFechaNacimientoOdontologo() {
        return fechaNacimientoOdontologo;
    }

    public void setFechaNacimientoOdontologo(Date fechaNacimientoOdontologo) {
        this.fechaNacimientoOdontologo = fechaNacimientoOdontologo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getHorarioDias() {
        return horarioDias;
    }

    public void setHorarioDias(String horarioDias) {
        this.horarioDias = horarioDias;
    }

    public Time getHorarioHoras() {
        return horarioHoras;
    }

    public void setHorarioHoras(Time horarioHoras) {
        this.horarioHoras = horarioHoras;
    }

    public Login getIdLoginFK() {
        return idLoginFK;
    }

    public void setIdLoginFK(Login idLoginFK) {
        this.idLoginFK = idLoginFK;
    }

    public List<Turnos> getTurnosLista() {
        return turnosLista;
    }

    public void setTurnosLista(List<Turnos> turnosLista) {
        this.turnosLista = turnosLista;
    }
    
    
    
}
