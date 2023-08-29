/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author NICOLAS
 */
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.*;
import modelosBD.*;
import persistencia.exceptions.NonexistentEntityException;


public class ControladorPersistencia {
    DescripcionJpaController desJPA = new DescripcionJpaController();
    LoginJpaController logJPA = new LoginJpaController();
    OdontologosJpaController odoJPA = new OdontologosJpaController();
    PacientesJpaController pacJPA = new PacientesJpaController();
    ResponsablePacienteJpaController resJPA = new ResponsablePacienteJpaController();
    SecretariosJpaController secJPA = new SecretariosJpaController();
    TipoPagoJpaController tipJPA = new TipoPagoJpaController();
    TurnosJpaController turJPA = new TurnosJpaController();
    
    //Operaciones CRUD para cada tabla, ya que las utilizare luego...
    
    // ============ LOGIN ============
    public void crearLogin(Login login){
        logJPA.create(login);
    }
    public List<Login> mostrarLogin(){
        List<Login> mostrarLogins = logJPA.findLoginEntities();
        return mostrarLogins;
    }
    public Login traerLogin(long id){
        Login log = logJPA.findLogin(id);
        return log;
    }
    public void editarLogin(Login login){
        try {
            logJPA.edit(login);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminarLogin(long id){
        try {
            logJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // ============ SECRETARIOS ============
    public void crearSecretarios(Secretarios secretarios){
        try {
            secJPA.create(secretarios);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Secretarios> mostrarSecretarios(){
        List<Secretarios> secre = secJPA.findSecretariosEntities();
        return secre;
    }
    public Secretarios traerSecretario(String id){
        Secretarios secre = secJPA.findSecretarios(id);
        return secre;
    }
    public void editarSecretario(Secretarios secretarios){
        try {
            secJPA.edit(secretarios);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminarSecretario(String id){
        try {
            secJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // ============ ODONTOLOGOS ============
    public void crearOdontologos(Odontologos odontologos){
        try {
            odoJPA.create(odontologos);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Odontologos> mostrarOdontologos(){
        List<Odontologos> odon = odoJPA.findOdontologosEntities();
        return odon;
    }
    public Odontologos traerOdontologo(String id){
        Odontologos odon = odoJPA.findOdontologos(id);
        return odon;
    }
    public void editarOdontologo(Odontologos odontologos){
        try {
            odoJPA.edit(odontologos);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminarOdontologo(String id){
        try {
            odoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // ============ RESPONSABLEPACIENTE ============
    public void crearResponsable(ResponsablePaciente responsable){
        try {
            resJPA.create(responsable);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<ResponsablePaciente> mostrarResponsable(){
        List<ResponsablePaciente> respo = resJPA.findResponsablePacienteEntities();
        return respo;
    }
    public ResponsablePaciente traerResponsable(String id){
        ResponsablePaciente respo = resJPA.findResponsablePaciente(id);
        return respo;
    }
    public void editarResponsable(ResponsablePaciente responsablePaciente){
        try {
            resJPA.edit(responsablePaciente);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminarResponsable(String id){
        try {
            resJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // ============ TIPOPAGO ============
    public void crearTipoPago(TipoPago tipopago){
        tipJPA.create(tipopago);
    }
    public List<TipoPago> mostrarTipoPago(){
        List<TipoPago> tipo = tipJPA.findTipoPagoEntities();
        return tipo;
    }
    public TipoPago traerTipoPago(long id){
        TipoPago tip = tipJPA.findTipoPago(id);
        return tip;
    }
    public void editarTipoPago(TipoPago tipopago){
        try {
            tipJPA.edit(tipopago);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminarTipoPago(long id){
        try {
            tipJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // ============ PACIENTES ============
    public void crearTipoPago(Pacientes paciente){
        try {
            pacJPA.create(paciente);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Pacientes> mostrarPaciente(){
        List<Pacientes> paci = pacJPA.findPacientesEntities();
        return paci;
    }
    public Pacientes traerPaciente(String id){
        Pacientes paci = pacJPA.findPacientes(id);
        return paci;
    }
    public void editarPaciente(Pacientes pacientes){
        try {
            pacJPA.edit(pacientes);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminarPaciente(String id){
        try {
            pacJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // ============ DESCRIPCION ============
    public void crearDescripcion(Descripcion descripcion){
        desJPA.create(descripcion);
    }
    public List<Descripcion> mostrarDescripcion(){
        List<Descripcion> descri = desJPA.findDescripcionEntities();
        return descri;
    }
    public Descripcion traerDescripcion(long id){
        Descripcion descri = desJPA.findDescripcion(id);
        return descri;
    }
    public void editarDescripcion(Descripcion descripcion){
        try {
            desJPA.edit(descripcion);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminarDescripcion(long id){
        try {
            desJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // ============ TURNOS ============
    public void crearTurno(Turnos turnos){
        turJPA.create(turnos);
    }
    public List<Turnos> mostrarTurnos(){
        List<Turnos> tur = turJPA.findTurnosEntities();
        return tur;
    }
    public Turnos traerTurnos(long id){
        Turnos tur = turJPA.findTurnos(id);
        return tur;
    }
    public void editarTurnos(Turnos turnos){
        try {
            turJPA.edit(turnos);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminarTurnos(long id){
        try {
            turJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
