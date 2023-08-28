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
import modelosBD.Login;
import modelosBD.Turnos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelosBD.Odontologos;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author NICOLAS
 */
public class OdontologosJpaController implements Serializable {

    public OdontologosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public OdontologosJpaController(){
        emf = Persistence.createEntityManagerFactory("com.mycompany_ClinicaOdontologica_war_1.0-SNAPSHOTPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Odontologos odontologos) throws PreexistingEntityException, Exception {
        if (odontologos.getTurnosLista() == null) {
            odontologos.setTurnosLista(new ArrayList<Turnos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Login idLoginFK = odontologos.getIdLoginFK();
            if (idLoginFK != null) {
                idLoginFK = em.getReference(idLoginFK.getClass(), idLoginFK.getIdLogin());
                odontologos.setIdLoginFK(idLoginFK);
            }
            List<Turnos> attachedTurnosLista = new ArrayList<Turnos>();
            for (Turnos turnosListaTurnosToAttach : odontologos.getTurnosLista()) {
                turnosListaTurnosToAttach = em.getReference(turnosListaTurnosToAttach.getClass(), turnosListaTurnosToAttach.getIdTurnos());
                attachedTurnosLista.add(turnosListaTurnosToAttach);
            }
            odontologos.setTurnosLista(attachedTurnosLista);
            em.persist(odontologos);
            if (idLoginFK != null) {
                idLoginFK.getOdontologoLista().add(odontologos);
                idLoginFK = em.merge(idLoginFK);
            }
            for (Turnos turnosListaTurnos : odontologos.getTurnosLista()) {
                Odontologos oldRutOdontologoFKOfTurnosListaTurnos = turnosListaTurnos.getRutOdontologoFK();
                turnosListaTurnos.setRutOdontologoFK(odontologos);
                turnosListaTurnos = em.merge(turnosListaTurnos);
                if (oldRutOdontologoFKOfTurnosListaTurnos != null) {
                    oldRutOdontologoFKOfTurnosListaTurnos.getTurnosLista().remove(turnosListaTurnos);
                    oldRutOdontologoFKOfTurnosListaTurnos = em.merge(oldRutOdontologoFKOfTurnosListaTurnos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOdontologos(odontologos.getRutOdontologo()) != null) {
                throw new PreexistingEntityException("Odontologos " + odontologos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Odontologos odontologos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Odontologos persistentOdontologos = em.find(Odontologos.class, odontologos.getRutOdontologo());
            Login idLoginFKOld = persistentOdontologos.getIdLoginFK();
            Login idLoginFKNew = odontologos.getIdLoginFK();
            List<Turnos> turnosListaOld = persistentOdontologos.getTurnosLista();
            List<Turnos> turnosListaNew = odontologos.getTurnosLista();
            if (idLoginFKNew != null) {
                idLoginFKNew = em.getReference(idLoginFKNew.getClass(), idLoginFKNew.getIdLogin());
                odontologos.setIdLoginFK(idLoginFKNew);
            }
            List<Turnos> attachedTurnosListaNew = new ArrayList<Turnos>();
            for (Turnos turnosListaNewTurnosToAttach : turnosListaNew) {
                turnosListaNewTurnosToAttach = em.getReference(turnosListaNewTurnosToAttach.getClass(), turnosListaNewTurnosToAttach.getIdTurnos());
                attachedTurnosListaNew.add(turnosListaNewTurnosToAttach);
            }
            turnosListaNew = attachedTurnosListaNew;
            odontologos.setTurnosLista(turnosListaNew);
            odontologos = em.merge(odontologos);
            if (idLoginFKOld != null && !idLoginFKOld.equals(idLoginFKNew)) {
                idLoginFKOld.getOdontologoLista().remove(odontologos);
                idLoginFKOld = em.merge(idLoginFKOld);
            }
            if (idLoginFKNew != null && !idLoginFKNew.equals(idLoginFKOld)) {
                idLoginFKNew.getOdontologoLista().add(odontologos);
                idLoginFKNew = em.merge(idLoginFKNew);
            }
            for (Turnos turnosListaOldTurnos : turnosListaOld) {
                if (!turnosListaNew.contains(turnosListaOldTurnos)) {
                    turnosListaOldTurnos.setRutOdontologoFK(null);
                    turnosListaOldTurnos = em.merge(turnosListaOldTurnos);
                }
            }
            for (Turnos turnosListaNewTurnos : turnosListaNew) {
                if (!turnosListaOld.contains(turnosListaNewTurnos)) {
                    Odontologos oldRutOdontologoFKOfTurnosListaNewTurnos = turnosListaNewTurnos.getRutOdontologoFK();
                    turnosListaNewTurnos.setRutOdontologoFK(odontologos);
                    turnosListaNewTurnos = em.merge(turnosListaNewTurnos);
                    if (oldRutOdontologoFKOfTurnosListaNewTurnos != null && !oldRutOdontologoFKOfTurnosListaNewTurnos.equals(odontologos)) {
                        oldRutOdontologoFKOfTurnosListaNewTurnos.getTurnosLista().remove(turnosListaNewTurnos);
                        oldRutOdontologoFKOfTurnosListaNewTurnos = em.merge(oldRutOdontologoFKOfTurnosListaNewTurnos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = odontologos.getRutOdontologo();
                if (findOdontologos(id) == null) {
                    throw new NonexistentEntityException("The odontologos with id " + id + " no longer exists.");
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
            Odontologos odontologos;
            try {
                odontologos = em.getReference(Odontologos.class, id);
                odontologos.getRutOdontologo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The odontologos with id " + id + " no longer exists.", enfe);
            }
            Login idLoginFK = odontologos.getIdLoginFK();
            if (idLoginFK != null) {
                idLoginFK.getOdontologoLista().remove(odontologos);
                idLoginFK = em.merge(idLoginFK);
            }
            List<Turnos> turnosLista = odontologos.getTurnosLista();
            for (Turnos turnosListaTurnos : turnosLista) {
                turnosListaTurnos.setRutOdontologoFK(null);
                turnosListaTurnos = em.merge(turnosListaTurnos);
            }
            em.remove(odontologos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Odontologos> findOdontologosEntities() {
        return findOdontologosEntities(true, -1, -1);
    }

    public List<Odontologos> findOdontologosEntities(int maxResults, int firstResult) {
        return findOdontologosEntities(false, maxResults, firstResult);
    }

    private List<Odontologos> findOdontologosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Odontologos.class));
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

    public Odontologos findOdontologos(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Odontologos.class, id);
        } finally {
            em.close();
        }
    }

    public int getOdontologosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Odontologos> rt = cq.from(Odontologos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
