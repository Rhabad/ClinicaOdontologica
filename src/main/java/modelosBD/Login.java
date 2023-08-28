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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author NICOLAS
 */
@Entity (name = "login")
public class Login implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idLogin")
    private long idLogin;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "email")
    private String email;
    @Column(name = "contrasenia")
    private String contrasenia;
    
    @OneToMany(mappedBy = "idLoginFK")
    private List<Secretarios> secretarioLista;
    @OneToMany(mappedBy = "idLoginFK")
    private List<Odontologos> odontologoLista;

    public Login() {
    }

    public Login(long idLogin, String usuario, String nombre, String apellido, Date fechaNacimiento, String email, String contrasenia) {
        this.idLogin = idLogin;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public Login(long idLogin, String usuario, String nombre, String apellido, Date fechaNacimiento, String email, String contrasenia, List<Secretarios> secretarioLista, List<Odontologos> odontologoLista) {
        this.idLogin = idLogin;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.contrasenia = contrasenia;
        this.secretarioLista = secretarioLista;
        this.odontologoLista = odontologoLista;
    }
    
    

    public long getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(long idLogin) {
        this.idLogin = idLogin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public List<Secretarios> getSecretarioLista() {
        return secretarioLista;
    }

    public void setSecretarioLista(List<Secretarios> secretarioLista) {
        this.secretarioLista = secretarioLista;
    }

    public List<Odontologos> getOdontologoLista() {
        return odontologoLista;
    }

    public void setOdontologoLista(List<Odontologos> odontologoLista) {
        this.odontologoLista = odontologoLista;
    }
    
    
}
