/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelosBD.ResponsablePaciente;
import modelosBD.TipoPago;
import modelosBD.Descripcion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelosBD.Pacientes;
import modelosBD.Turnos;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author NICOLAS
 */
public class PacientesJpaController implements Serializable {

    public PacientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public PacientesJpaController(){
        emf = Persistence.createEntityManagerFactory("com.mycompany_ClinicaOdontologica_war_1.0-SNAPSHOTPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pacientes pacientes) throws PreexistingEntityException, Exception {
        if (pacientes.getDescripcionLista() == null) {
            pacientes.setDescripcionLista(new ArrayList<Descripcion>());
        }
        if (pacientes.getTurnosLista() == null) {
            pacientes.setTurnosLista(new ArrayList<Turnos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ResponsablePaciente rutResponsableFK = pacientes.getRutResponsableFK();
            if (rutResponsableFK != null) {
                rutResponsableFK = em.getReference(rutResponsableFK.getClass(), rutResponsableFK.getRutResponsable());
                pacientes.setRutResponsableFK(rutResponsableFK);
            }
            TipoPago idTipoPagoFK = pacientes.getIdTipoPagoFK();
            if (idTipoPagoFK != null) {
                idTipoPagoFK = em.getReference(idTipoPagoFK.getClass(), idTipoPagoFK.getIdTipoPago());
                pacientes.setIdTipoPagoFK(idTipoPagoFK);
            }
            List<Descripcion> attachedDescripcionLista = new ArrayList<Descripcion>();
            for (Descripcion descripcionListaDescripcionToAttach : pacientes.getDescripcionLista()) {
                descripcionListaDescripcionToAttach = em.getReference(descripcionListaDescripcionToAttach.getClass(), descripcionListaDescripcionToAttach.getIdDescripcion());
                attachedDescripcionLista.add(descripcionListaDescripcionToAttach);
            }
            pacientes.setDescripcionLista(attachedDescripcionLista);
            List<Turnos> attachedTurnosLista = new ArrayList<Turnos>();
            for (Turnos turnosListaTurnosToAttach : pacientes.getTurnosLista()) {
                turnosListaTurnosToAttach = em.getReference(turnosListaTurnosToAttach.getClass(), turnosListaTurnosToAttach.getIdTurnos());
                attachedTurnosLista.add(turnosListaTurnosToAttach);
            }
            pacientes.setTurnosLista(attachedTurnosLista);
            em.persist(pacientes);
            if (rutResponsableFK != null) {
                rutResponsableFK.getPacientesLista().add(pacientes);
                rutResponsableFK = em.merge(rutResponsableFK);
            }
            if (idTipoPagoFK != null) {
                idTipoPagoFK.getPacientesLista().add(pacientes);
                idTipoPagoFK = em.merge(idTipoPagoFK);
            }
            for (Descripcion descripcionListaDescripcion : pacientes.getDescripcionLista()) {
                Pacientes oldRutPacienteFKOfDescripcionListaDescripcion = descripcionListaDescripcion.getRutPacienteFK();
                descripcionListaDescripcion.setRutPacienteFK(pacientes);
                descripcionListaDescripcion = em.merge(descripcionListaDescripcion);
                if (oldRutPacienteFKOfDescripcionListaDescripcion != null) {
                    oldRutPacienteFKOfDescripcionListaDescripcion.getDescripcionLista().remove(descripcionListaDescripcion);
                    oldRutPacienteFKOfDescripcionListaDescripcion = em.merge(oldRutPacienteFKOfDescripcionListaDescripcion);
                }
            }
            for (Turnos turnosListaTurnos : pacientes.getTurnosLista()) {
                Pacientes oldRutPacienteFKOfTurnosListaTurnos = turnosListaTurnos.getRutPacienteFK();
                turnosListaTurnos.setRutPacienteFK(pacientes);
                turnosListaTurnos = em.merge(turnosListaTurnos);
                if (oldRutPacienteFKOfTurnosListaTurnos != null) {
                    oldRutPacienteFKOfTurnosListaTurnos.getTurnosLista().remove(turnosListaTurnos);
                    oldRutPacienteFKOfTurnosListaTurnos = em.merge(oldRutPacienteFKOfTurnosListaTurnos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPacientes(pacientes.getRutPaciente()) != null) {
                throw new PreexistingEntityException("Pacientes " + pacientes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pacientes pacientes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pacientes persistentPacientes = em.find(Pacientes.class, pacientes.getRutPaciente());
            ResponsablePaciente rutResponsableFKOld = persistentPacientes.getRutResponsableFK();
            ResponsablePaciente rutResponsableFKNew = pacientes.getRutResponsableFK();
            TipoPago idTipoPagoFKOld = persistentPacientes.getIdTipoPagoFK();
            TipoPago idTipoPagoFKNew = pacientes.getIdTipoPagoFK();
            List<Descripcion> descripcionListaOld = persistentPacientes.getDescripcionLista();
            List<Descripcion> descripcionListaNew = pacientes.getDescripcionLista();
            List<Turnos> turnosListaOld = persistentPacientes.getTurnosLista();
            List<Turnos> turnosListaNew = pacientes.getTurnosLista();
            if (rutResponsableFKNew != null) {
                rutResponsableFKNew = em.getReference(rutResponsableFKNew.getClass(), rutResponsableFKNew.getRutResponsable());
                pacientes.setRutResponsableFK(rutResponsableFKNew);
            }
            if (idTipoPagoFKNew != null) {
                idTipoPagoFKNew = em.getReference(idTipoPagoFKNew.getClass(), idTipoPagoFKNew.getIdTipoPago());
                pacientes.setIdTipoPagoFK(idTipoPagoFKNew);
            }
            List<Descripcion> attachedDescripcionListaNew = new ArrayList<Descripcion>();
            for (Descripcion descripcionListaNewDescripcionToAttach : descripcionListaNew) {
                descripcionListaNewDescripcionToAttach = em.getReference(descripcionListaNewDescripcionToAttach.getClass(), descripcionListaNewDescripcionToAttach.getIdDescripcion());
                attachedDescripcionListaNew.add(descripcionListaNewDescripcionToAttach);
            }
            descripcionListaNew = attachedDescripcionListaNew;
            pacientes.setDescripcionLista(descripcionListaNew);
            List<Turnos> attachedTurnosListaNew = new ArrayList<Turnos>();
            for (Turnos turnosListaNewTurnosToAttach : turnosListaNew) {
                turnosListaNewTurnosToAttach = em.getReference(turnosListaNewTurnosToAttach.getClass(), turnosListaNewTurnosToAttach.getIdTurnos());
                attachedTurnosListaNew.add(turnosListaNewTurnosToAttach);
            }
            turnosListaNew = attachedTurnosListaNew;
            pacientes.setTurnosLista(turnosListaNew);
            pacientes = em.merge(pacientes);
            if (rutResponsableFKOld != null && !rutResponsableFKOld.equals(rutResponsableFKNew)) {
                rutResponsableFKOld.getPacientesLista().remove(pacientes);
                rutResponsableFKOld = em.merge(rutResponsableFKOld);
            }
            if (rutResponsableFKNew != null && !rutResponsableFKNew.equals(rutResponsableFKOld)) {
                rutResponsableFKNew.getPacientesLista().add(pacientes);
                rutResponsableFKNew = em.merge(rutResponsableFKNew);
            }
            if (idTipoPagoFKOld != null && !idTipoPagoFKOld.equals(idTipoPagoFKNew)) {
                idTipoPagoFKOld.getPacientesLista().remove(pacientes);
                idTipoPagoFKOld = em.merge(idTipoPagoFKOld);
            }
            if (idTipoPagoFKNew != null && !idTipoPagoFKNew.equals(idTipoPagoFKOld)) {
                idTipoPagoFKNew.getPacientesLista().add(pacientes);
                idTipoPagoFKNew = em.merge(idTipoPagoFKNew);
            }
            for (Descripcion descripcionListaOldDescripcion : descripcionListaOld) {
                if (!descripcionListaNew.contains(descripcionListaOldDescripcion)) {
                    descripcionListaOldDescripcion.setRutPacienteFK(null);
                    descripcionListaOldDescripcion = em.merge(descripcionListaOldDescripcion);
                }
            }
            for (Descripcion descripcionListaNewDescripcion : descripcionListaNew) {
                if (!descripcionListaOld.contains(descripcionListaNewDescripcion)) {
                    Pacientes oldRutPacienteFKOfDescripcionListaNewDescripcion = descripcionListaNewDescripcion.getRutPacienteFK();
                    descripcionListaNewDescripcion.setRutPacienteFK(pacientes);
                    descripcionListaNewDescripcion = em.merge(descripcionListaNewDescripcion);
                    if (oldRutPacienteFKOfDescripcionListaNewDescripcion != null && !oldRutPacienteFKOfDescripcionListaNewDescripcion.equals(pacientes)) {
                        oldRutPacienteFKOfDescripcionListaNewDescripcion.getDescripcionLista().remove(descripcionListaNewDescripcion);
                        oldRutPacienteFKOfDescripcionListaNewDescripcion = em.merge(oldRutPacienteFKOfDescripcionListaNewDescripcion);
                    }
                }
            }
            for (Turnos turnosListaOldTurnos : turnosListaOld) {
                if (!turnosListaNew.contains(turnosListaOldTurnos)) {
                    turnosListaOldTurnos.setRutPacienteFK(null);
                    turnosListaOldTurnos = em.merge(turnosListaOldTurnos);
                }
            }
            for (Turnos turnosListaNewTurnos : turnosListaNew) {
                if (!turnosListaOld.contains(turnosListaNewTurnos)) {
                    Pacientes oldRutPacienteFKOfTurnosListaNewTurnos = turnosListaNewTurnos.getRutPacienteFK();
                    turnosListaNewTurnos.setRutPacienteFK(pacientes);
                    turnosListaNewTurnos = em.merge(turnosListaNewTurnos);
                    if (oldRutPacienteFKOfTurnosListaNewTurnos != null && !oldRutPacienteFKOfTurnosListaNewTurnos.equals(pacientes)) {
                        oldRutPacienteFKOfTurnosListaNewTurnos.getTurnosLista().remove(turnosListaNewTurnos);
                        oldRutPacienteFKOfTurnosListaNewTurnos = em.merge(oldRutPacienteFKOfTurnosListaNewTurnos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = pacientes.getRutPaciente();
                if (findPacientes(id) == null) {
                    throw new NonexistentEntityException("The pacientes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pacientes pacientes;
            try {
                pacientes = em.getReference(Pacientes.class, id);
                pacientes.getRutPaciente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pacientes with id " + id + " no longer exists.", enfe);
            }
            ResponsablePaciente rutResponsableFK = pacientes.getRutResponsableFK();
            if (rutResponsableFK != null) {
                rutResponsableFK.getPacientesLista().remove(pacientes);
                rutResponsableFK = em.merge(rutResponsableFK);
            }
            TipoPago idTipoPagoFK = pacientes.getIdTipoPagoFK();
            if (idTipoPagoFK != null) {
                idTipoPagoFK.getPacientesLista().remove(pacientes);
                idTipoPagoFK = em.merge(idTipoPagoFK);
            }
            List<Descripcion> descripcionLista = pacientes.getDescripcionLista();
            for (Descripcion descripcionListaDescripcion : descripcionLista) {
                descripcionListaDescripcion.setRutPacienteFK(null);
                descripcionListaDescripcion = em.merge(descripcionListaDescripcion);
            }
            List<Turnos> turnosLista = pacientes.getTurnosLista();
            for (Turnos turnosListaTurnos : turnosLista) {
                turnosListaTurnos.setRutPacienteFK(null);
                turnosListaTurnos = em.merge(turnosListaTurnos);
            }
            em.remove(pacientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pacientes> findPacientesEntities() {
        return findPacientesEntities(true, -1, -1);
    }

    public List<Pacientes> findPacientesEntities(int maxResults, int firstResult) {
        return findPacientesEntities(false, maxResults, firstResult);
    }

    private List<Pacientes> findPacientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pacientes.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pacientes findPacientes(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pacientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getPacientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pacientes> rt = cq.from(Pacientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
