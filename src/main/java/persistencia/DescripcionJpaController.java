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
import modelosBD.Descripcion;
import modelosBD.Pacientes;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author NICOLAS
 */
public class DescripcionJpaController implements Serializable {

    public DescripcionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public DescripcionJpaController(){
        emf = Persistence.createEntityManagerFactory("com.mycompany_ClinicaOdontologica_war_1.0-SNAPSHOTPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Descripcion descripcion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pacientes rutPacienteFK = descripcion.getRutPacienteFK();
            if (rutPacienteFK != null) {
                rutPacienteFK = em.getReference(rutPacienteFK.getClass(), rutPacienteFK.getRutPaciente());
                descripcion.setRutPacienteFK(rutPacienteFK);
            }
            em.persist(descripcion);
            if (rutPacienteFK != null) {
                rutPacienteFK.getDescripcionLista().add(descripcion);
                rutPacienteFK = em.merge(rutPacienteFK);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Descripcion descripcion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Descripcion persistentDescripcion = em.find(Descripcion.class, descripcion.getIdDescripcion());
            Pacientes rutPacienteFKOld = persistentDescripcion.getRutPacienteFK();
            Pacientes rutPacienteFKNew = descripcion.getRutPacienteFK();
            if (rutPacienteFKNew != null) {
                rutPacienteFKNew = em.getReference(rutPacienteFKNew.getClass(), rutPacienteFKNew.getRutPaciente());
                descripcion.setRutPacienteFK(rutPacienteFKNew);
            }
            descripcion = em.merge(descripcion);
            if (rutPacienteFKOld != null && !rutPacienteFKOld.equals(rutPacienteFKNew)) {
                rutPacienteFKOld.getDescripcionLista().remove(descripcion);
                rutPacienteFKOld = em.merge(rutPacienteFKOld);
            }
            if (rutPacienteFKNew != null && !rutPacienteFKNew.equals(rutPacienteFKOld)) {
                rutPacienteFKNew.getDescripcionLista().add(descripcion);
                rutPacienteFKNew = em.merge(rutPacienteFKNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = descripcion.getIdDescripcion();
                if (findDescripcion(id) == null) {
                    throw new NonexistentEntityException("The descripcion with id " + id + " no longer exists.");
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
            Descripcion descripcion;
            try {
                descripcion = em.getReference(Descripcion.class, id);
                descripcion.getIdDescripcion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The descripcion with id " + id + " no longer exists.", enfe);
            }
            Pacientes rutPacienteFK = descripcion.getRutPacienteFK();
            if (rutPacienteFK != null) {
                rutPacienteFK.getDescripcionLista().remove(descripcion);
                rutPacienteFK = em.merge(rutPacienteFK);
            }
            em.remove(descripcion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Descripcion> findDescripcionEntities() {
        return findDescripcionEntities(true, -1, -1);
    }

    public List<Descripcion> findDescripcionEntities(int maxResults, int firstResult) {
        return findDescripcionEntities(false, maxResults, firstResult);
    }

    private List<Descripcion> findDescripcionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Descripcion.class));
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

    public Descripcion findDescripcion(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Descripcion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDescripcionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Descripcion> rt = cq.from(Descripcion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
