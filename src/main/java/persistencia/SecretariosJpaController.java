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
import modelosBD.Login;
import modelosBD.Secretarios;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author NICOLAS
 */
public class SecretariosJpaController implements Serializable {

    public SecretariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public SecretariosJpaController(){
        emf = Persistence.createEntityManagerFactory("com.mycompany_ClinicaOdontologica_war_1.0-SNAPSHOTPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Secretarios secretarios) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Login idLoginFK = secretarios.getIdLoginFK();
            if (idLoginFK != null) {
                idLoginFK = em.getReference(idLoginFK.getClass(), idLoginFK.getIdLogin());
                secretarios.setIdLoginFK(idLoginFK);
            }
            em.persist(secretarios);
            if (idLoginFK != null) {
                idLoginFK.getSecretarioLista().add(secretarios);
                idLoginFK = em.merge(idLoginFK);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSecretarios(secretarios.getRutSecretario()) != null) {
                throw new PreexistingEntityException("Secretarios " + secretarios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Secretarios secretarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Secretarios persistentSecretarios = em.find(Secretarios.class, secretarios.getRutSecretario());
            Login idLoginFKOld = persistentSecretarios.getIdLoginFK();
            Login idLoginFKNew = secretarios.getIdLoginFK();
            if (idLoginFKNew != null) {
                idLoginFKNew = em.getReference(idLoginFKNew.getClass(), idLoginFKNew.getIdLogin());
                secretarios.setIdLoginFK(idLoginFKNew);
            }
            secretarios = em.merge(secretarios);
            if (idLoginFKOld != null && !idLoginFKOld.equals(idLoginFKNew)) {
                idLoginFKOld.getSecretarioLista().remove(secretarios);
                idLoginFKOld = em.merge(idLoginFKOld);
            }
            if (idLoginFKNew != null && !idLoginFKNew.equals(idLoginFKOld)) {
                idLoginFKNew.getSecretarioLista().add(secretarios);
                idLoginFKNew = em.merge(idLoginFKNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = secretarios.getRutSecretario();
                if (findSecretarios(id) == null) {
                    throw new NonexistentEntityException("The secretarios with id " + id + " no longer exists.");
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
            Secretarios secretarios;
            try {
                secretarios = em.getReference(Secretarios.class, id);
                secretarios.getRutSecretario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The secretarios with id " + id + " no longer exists.", enfe);
            }
            Login idLoginFK = secretarios.getIdLoginFK();
            if (idLoginFK != null) {
                idLoginFK.getSecretarioLista().remove(secretarios);
                idLoginFK = em.merge(idLoginFK);
            }
            em.remove(secretarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Secretarios> findSecretariosEntities() {
        return findSecretariosEntities(true, -1, -1);
    }

    public List<Secretarios> findSecretariosEntities(int maxResults, int firstResult) {
        return findSecretariosEntities(false, maxResults, firstResult);
    }

    private List<Secretarios> findSecretariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Secretarios.class));
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

    public Secretarios findSecretarios(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Secretarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getSecretariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Secretarios> rt = cq.from(Secretarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
