/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelosBD;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author NICOLAS
 */
@Entity(name = "tipoPago")
public class TipoPago implements Serializable {
    @Id
    @Column(name = "idTipoPago")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idTipoPago;
    @Column(name = "tipoPago")
    private String tipoPago;
    
    @OneToMany(mappedBy = "idTipoPagoFK")
    private List<Pacientes> pacientesLista;

    public TipoPago() {
    }

    public TipoPago(long idTipoPago, String tipoPago) {
        this.idTipoPago = idTipoPago;
        this.tipoPago = tipoPago;
    }

    public TipoPago(long idTipoPago, String tipoPago, List<Pacientes> pacientesLista) {
        this.idTipoPago = idTipoPago;
        this.tipoPago = tipoPago;
        this.pacientesLista = pacientesLista;
    }

    public long getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(long idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public List<Pacientes> getPacientesLista() {
        return pacientesLista;
    }

    public void setPacientesLista(List<Pacientes> pacientesLista) {
        this.pacientesLista = pacientesLista;
    }
    
    
    
}
