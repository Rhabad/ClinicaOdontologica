/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelosBD.Odontologos;
import modelosBD.Pacientes;
import modelosBD.Turnos;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author NICOLAS
 */
public class TurnosJpaController implements Serializable {

    public TurnosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public TurnosJpaController(){
        emf = Persistence.createEntityManagerFactory("com.mycompany_ClinicaOdontologica_war_1.0-SNAPSHOTPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Turnos turnos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Odontologos rutOdontologoFK = turnos.getRutOdontologoFK();
            if (rutOdontologoFK != null) {
                rutOdontologoFK = em.getReference(rutOdontologoFK.getClass(), rutOdontologoFK.getRutOdontologo());
                turnos.setRutOdontologoFK(rutOdontologoFK);
            }
            Pacientes rutPacienteFK = turnos.getRutPacienteFK();
            if (rutPacienteFK != null) {
                rutPacienteFK = em.getReference(rutPacienteFK.getClass(), rutPacienteFK.getRutPaciente());
                turnos.setRutPacienteFK(rutPacienteFK);
            }
            em.persist(turnos);
            if (rutOdontologoFK != null) {
                rutOdontologoFK.getTurnosLista().add(turnos);
                rutOdontologoFK = em.merge(rutOdontologoFK);
            }
            if (rutPacienteFK != null) {
                rutPacienteFK.getTurnosLista().add(turnos);
                rutPacienteFK = em.merge(rutPacienteFK);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Turnos turnos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turnos persistentTurnos = em.find(Turnos.class, turnos.getIdTurnos());
            Odontologos rutOdontologoFKOld = persistentTurnos.getRutOdontologoFK();
            Odontologos rutOdontologoFKNew = turnos.getRutOdontologoFK();
            Pacientes rutPacienteFKOld = persistentTurnos.getRutPacienteFK();
            Pacientes rutPacienteFKNew = turnos.getRutPacienteFK();
            if (rutOdontologoFKNew != null) {
                rutOdontologoFKNew = em.getReference(rutOdontologoFKNew.getClass(), rutOdontologoFKNew.getRutOdontologo());
                turnos.setRutOdontologoFK(rutOdontologoFKNew);
            }
            if (rutPacienteFKNew != null) {
                rutPacienteFKNew = em.getReference(rutPacienteFKNew.getClass(), rutPacienteFKNew.getRutPaciente());
                turnos.setRutPacienteFK(rutPacienteFKNew);
            }
            turnos = em.merge(turnos);
            if (rutOdontologoFKOld != null && !rutOdontologoFKOld.equals(rutOdontologoFKNew)) {
                rutOdontologoFKOld.getTurnosLista().remove(turnos);
                rutOdontologoFKOld = em.merge(rutOdontologoFKOld);
            }
            if (rutOdontologoFKNew != null && !rutOdontologoFKNew.equals(rutOdontologoFKOld)) {
                rutOdontologoFKNew.getTurnosLista().add(turnos);
                rutOdontologoFKNew = em.merge(rutOdontologoFKNew);
            }
            if (rutPacienteFKOld != null && !rutPacienteFKOld.equals(rutPacienteFKNew)) {
                rutPacienteFKOld.getTurnosLista().remove(turnos);
                rutPacienteFKOld = em.merge(rutPacienteFKOld);
            }
            if (rutPacienteFKNew != null && !rutPacienteFKNew.equals(rutPacienteFKOld)) {
                rutPacienteFKNew.getTurnosLista().add(turnos);
                rutPacienteFKNew = em.merge(rutPacienteFKNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = turnos.getIdTurnos();
                if (findTurnos(id) == null) {
                    throw new NonexistentEntityException("The turnos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turnos turnos;
            try {
                turnos = em.getReference(Turnos.class, id);
                turnos.getIdTurnos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The turnos with id " + id + " no longer exists.", enfe);
            }
            Odontologos rutOdontologoFK = turnos.getRutOdontologoFK();
            if (rutOdontologoFK != null) {
                rutOdontologoFK.getTurnosLista().remove(turnos);
                rutOdontologoFK = em.merge(rutOdontologoFK);
            }
            Pacientes rutPacienteFK = turnos.getRutPacienteFK();
            if (rutPacienteFK != null) {
                rutPacienteFK.getTurnosLista().remove(turnos);
                rutPacienteFK = em.merge(rutPacienteFK);
            }
            em.remove(turnos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Turnos> findTurnosEntities() {
        return findTurnosEntities(true, -1, -1);
    }

    public List<Turnos> findTurnosEntities(int maxResults, int firstResult) {
        return findTurnosEntities(false, maxResults, firstResult);
    }

    private List<Turnos> findTurnosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Turnos.class));
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

    public Turnos findTurnos(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Turnos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTurnosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Turnos> rt = cq.from(Turnos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
