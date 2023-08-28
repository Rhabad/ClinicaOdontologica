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
import modelosBD.Secretarios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelosBD.Login;
import modelosBD.Odontologos;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author NICOLAS
 */
public class LoginJpaController implements Serializable {

    public LoginJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public LoginJpaController(){
        emf = Persistence.createEntityManagerFactory("com.mycompany_ClinicaOdontologica_war_1.0-SNAPSHOTPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Login login) {
        if (login.getSecretarioLista() == null) {
            login.setSecretarioLista(new ArrayList<Secretarios>());
        }
        if (login.getOdontologoLista() == null) {
            login.setOdontologoLista(new ArrayList<Odontologos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Secretarios> attachedSecretarioLista = new ArrayList<Secretarios>();
            for (Secretarios secretarioListaSecretariosToAttach : login.getSecretarioLista()) {
                secretarioListaSecretariosToAttach = em.getReference(secretarioListaSecretariosToAttach.getClass(), secretarioListaSecretariosToAttach.getRutSecretario());
                attachedSecretarioLista.add(secretarioListaSecretariosToAttach);
            }
            login.setSecretarioLista(attachedSecretarioLista);
            List<Odontologos> attachedOdontologoLista = new ArrayList<Odontologos>();
            for (Odontologos odontologoListaOdontologosToAttach : login.getOdontologoLista()) {
                odontologoListaOdontologosToAttach = em.getReference(odontologoListaOdontologosToAttach.getClass(), odontologoListaOdontologosToAttach.getRutOdontologo());
                attachedOdontologoLista.add(odontologoListaOdontologosToAttach);
            }
            login.setOdontologoLista(attachedOdontologoLista);
            em.persist(login);
            for (Secretarios secretarioListaSecretarios : login.getSecretarioLista()) {
                Login oldIdLoginFKOfSecretarioListaSecretarios = secretarioListaSecretarios.getIdLoginFK();
                secretarioListaSecretarios.setIdLoginFK(login);
                secretarioListaSecretarios = em.merge(secretarioListaSecretarios);
                if (oldIdLoginFKOfSecretarioListaSecretarios != null) {
                    oldIdLoginFKOfSecretarioListaSecretarios.getSecretarioLista().remove(secretarioListaSecretarios);
                    oldIdLoginFKOfSecretarioListaSecretarios = em.merge(oldIdLoginFKOfSecretarioListaSecretarios);
                }
            }
            for (Odontologos odontologoListaOdontologos : login.getOdontologoLista()) {
                Login oldIdLoginFKOfOdontologoListaOdontologos = odontologoListaOdontologos.getIdLoginFK();
                odontologoListaOdontologos.setIdLoginFK(login);
                odontologoListaOdontologos = em.merge(odontologoListaOdontologos);
                if (oldIdLoginFKOfOdontologoListaOdontologos != null) {
                    oldIdLoginFKOfOdontologoListaOdontologos.getOdontologoLista().remove(odontologoListaOdontologos);
                    oldIdLoginFKOfOdontologoListaOdontologos = em.merge(oldIdLoginFKOfOdontologoListaOdontologos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Login login) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Login persistentLogin = em.find(Login.class, login.getIdLogin());
            List<Secretarios> secretarioListaOld = persistentLogin.getSecretarioLista();
            List<Secretarios> secretarioListaNew = login.getSecretarioLista();
            List<Odontologos> odontologoListaOld = persistentLogin.getOdontologoLista();
            List<Odontologos> odontologoListaNew = login.getOdontologoLista();
            List<Secretarios> attachedSecretarioListaNew = new ArrayList<Secretarios>();
            for (Secretarios secretarioListaNewSecretariosToAttach : secretarioListaNew) {
                secretarioListaNewSecretariosToAttach = em.getReference(secretarioListaNewSecretariosToAttach.getClass(), secretarioListaNewSecretariosToAttach.getRutSecretario());
                attachedSecretarioListaNew.add(secretarioListaNewSecretariosToAttach);
            }
            secretarioListaNew = attachedSecretarioListaNew;
            login.setSecretarioLista(secretarioListaNew);
            List<Odontologos> attachedOdontologoListaNew = new ArrayList<Odontologos>();
            for (Odontologos odontologoListaNewOdontologosToAttach : odontologoListaNew) {
                odontologoListaNewOdontologosToAttach = em.getReference(odontologoListaNewOdontologosToAttach.getClass(), odontologoListaNewOdontologosToAttach.getRutOdontologo());
                attachedOdontologoListaNew.add(odontologoListaNewOdontologosToAttach);
            }
            odontologoListaNew = attachedOdontologoListaNew;
            login.setOdontologoLista(odontologoListaNew);
            login = em.merge(login);
            for (Secretarios secretarioListaOldSecretarios : secretarioListaOld) {
                if (!secretarioListaNew.contains(secretarioListaOldSecretarios)) {
                    secretarioListaOldSecretarios.setIdLoginFK(null);
                    secretarioListaOldSecretarios = em.merge(secretarioListaOldSecretarios);
                }
            }
            for (Secretarios secretarioListaNewSecretarios : secretarioListaNew) {
                if (!secretarioListaOld.contains(secretarioListaNewSecretarios)) {
                    Login oldIdLoginFKOfSecretarioListaNewSecretarios = secretarioListaNewSecretarios.getIdLoginFK();
                    secretarioListaNewSecretarios.setIdLoginFK(login);
                    secretarioListaNewSecretarios = em.merge(secretarioListaNewSecretarios);
                    if (oldIdLoginFKOfSecretarioListaNewSecretarios != null && !oldIdLoginFKOfSecretarioListaNewSecretarios.equals(login)) {
                        oldIdLoginFKOfSecretarioListaNewSecretarios.getSecretarioLista().remove(secretarioListaNewSecretarios);
                        oldIdLoginFKOfSecretarioListaNewSecretarios = em.merge(oldIdLoginFKOfSecretarioListaNewSecretarios);
                    }
                }
            }
            for (Odontologos odontologoListaOldOdontologos : odontologoListaOld) {
                if (!odontologoListaNew.contains(odontologoListaOldOdontologos)) {
                    odontologoListaOldOdontologos.setIdLoginFK(null);
                    odontologoListaOldOdontologos = em.merge(odontologoListaOldOdontologos);
                }
            }
            for (Odontologos odontologoListaNewOdontologos : odontologoListaNew) {
                if (!odontologoListaOld.contains(odontologoListaNewOdontologos)) {
                    Login oldIdLoginFKOfOdontologoListaNewOdontologos = odontologoListaNewOdontologos.getIdLoginFK();
                    odontologoListaNewOdontologos.setIdLoginFK(login);
                    odontologoListaNewOdontologos = em.merge(odontologoListaNewOdontologos);
                    if (oldIdLoginFKOfOdontologoListaNewOdontologos != null && !oldIdLoginFKOfOdontologoListaNewOdontologos.equals(login)) {
                        oldIdLoginFKOfOdontologoListaNewOdontologos.getOdontologoLista().remove(odontologoListaNewOdontologos);
                        oldIdLoginFKOfOdontologoListaNewOdontologos = em.merge(oldIdLoginFKOfOdontologoListaNewOdontologos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = login.getIdLogin();
                if (findLogin(id) == null) {
                    throw new NonexistentEntityException("The login with id " + id + " no longer exists.");
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
            Login login;
            try {
                login = em.getReference(Login.class, id);
                login.getIdLogin();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The login with id " + id + " no longer exists.", enfe);
            }
            List<Secretarios> secretarioLista = login.getSecretarioLista();
            for (Secretarios secretarioListaSecretarios : secretarioLista) {
                secretarioListaSecretarios.setIdLoginFK(null);
                secretarioListaSecretarios = em.merge(secretarioListaSecretarios);
            }
            List<Odontologos> odontologoLista = login.getOdontologoLista();
            for (Odontologos odontologoListaOdontologos : odontologoLista) {
                odontologoListaOdontologos.setIdLoginFK(null);
                odontologoListaOdontologos = em.merge(odontologoListaOdontologos);
            }
            em.remove(login);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Login> findLoginEntities() {
        return findLoginEntities(true, -1, -1);
    }

    public List<Login> findLoginEntities(int maxResults, int firstResult) {
        return findLoginEntities(false, maxResults, firstResult);
    }

    private List<Login> findLoginEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Login.class));
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

    public Login findLogin(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Login.class, id);
        } finally {
            em.close();
        }
    }

    public int getLoginCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Login> rt = cq.from(Login.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
