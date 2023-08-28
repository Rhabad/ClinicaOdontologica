/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelosBD;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author NICOLAS
 */
@Entity
public class Descripcion implements Serializable {
    @Id
    @Column(name = "idDescripcion")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idDescripcion;
    @Column(name = "descripcion")
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name = "rutPacienteFK")
    private Pacientes rutPacienteFK;

    public Descripcion() {
    }

    public Descripcion(long idDescripcion, String descripcion, Pacientes rutPacienteFK) {
        this.idDescripcion = idDescripcion;
        this.descripcion = descripcion;
        this.rutPacienteFK = rutPacienteFK;
    }

    public long getIdDescripcion() {
        return idDescripcion;
    }

    public void setIdDescripcion(long idDescripcion) {
        this.idDescripcion = idDescripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Pacientes getRutPacienteFK() {
        return rutPacienteFK;
    }

    public void setRutPacienteFK(Pacientes rutPacienteFK) {
        this.rutPacienteFK = rutPacienteFK;
    }
    
    
}
