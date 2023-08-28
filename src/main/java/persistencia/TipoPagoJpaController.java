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
import modelosBD.Pacientes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelosBD.TipoPago;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author NICOLAS
 */
public class TipoPagoJpaController implements Serializable {

    public TipoPagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public TipoPagoJpaController(){
        emf = Persistence.createEntityManagerFactory("com.mycompany_ClinicaOdontologica_war_1.0-SNAPSHOTPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoPago tipoPago) {
        if (tipoPago.getPacientesLista() == null) {
            tipoPago.setPacientesLista(new ArrayList<Pacientes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pacientes> attachedPacientesLista = new ArrayList<Pacientes>();
            for (Pacientes pacientesListaPacientesToAttach : tipoPago.getPacientesLista()) {
                pacientesListaPacientesToAttach = em.getReference(pacientesListaPacientesToAttach.getClass(), pacientesListaPacientesToAttach.getRutPaciente());
                attachedPacientesLista.add(pacientesListaPacientesToAttach);
            }
            tipoPago.setPacientesLista(attachedPacientesLista);
            em.persist(tipoPago);
            for (Pacientes pacientesListaPacientes : tipoPago.getPacientesLista()) {
                TipoPago oldIdTipoPagoFKOfPacientesListaPacientes = pacientesListaPacientes.getIdTipoPagoFK();
                pacientesListaPacientes.setIdTipoPagoFK(tipoPago);
                pacientesListaPacientes = em.merge(pacientesListaPacientes);
                if (oldIdTipoPagoFKOfPacientesListaPacientes != null) {
                    oldIdTipoPagoFKOfPacientesListaPacientes.getPacientesLista().remove(pacientesListaPacientes);
                    oldIdTipoPagoFKOfPacientesListaPacientes = em.merge(oldIdTipoPagoFKOfPacientesListaPacientes);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoPago tipoPago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoPago persistentTipoPago = em.find(TipoPago.class, tipoPago.getIdTipoPago());
            List<Pacientes> pacientesListaOld = persistentTipoPago.getPacientesLista();
            List<Pacientes> pacientesListaNew = tipoPago.getPacientesLista();
            List<Pacientes> attachedPacientesListaNew = new ArrayList<Pacientes>();
            for (Pacientes pacientesListaNewPacientesToAttach : pacientesListaNew) {
                pacientesListaNewPacientesToAttach = em.getReference(pacientesListaNewPacientesToAttach.getClass(), pacientesListaNewPacientesToAttach.getRutPaciente());
                attachedPacientesListaNew.add(pacientesListaNewPacientesToAttach);
            }
            pacientesListaNew = attachedPacientesListaNew;
            tipoPago.setPacientesLista(pacientesListaNew);
            tipoPago = em.merge(tipoPago);
            for (Pacientes pacientesListaOldPacientes : pacientesListaOld) {
                if (!pacientesListaNew.contains(pacientesListaOldPacientes)) {
                    pacientesListaOldPacientes.setIdTipoPagoFK(null);
                    pacientesListaOldPacientes = em.merge(pacientesListaOldPacientes);
                }
            }
            for (Pacientes pacientesListaNewPacientes : pacientesListaNew) {
                if (!pacientesListaOld.contains(pacientesListaNewPacientes)) {
                    TipoPago oldIdTipoPagoFKOfPacientesListaNewPacientes = pacientesListaNewPacientes.getIdTipoPagoFK();
                    pacientesListaNewPacientes.setIdTipoPagoFK(tipoPago);
                    pacientesListaNewPacientes = em.merge(pacientesListaNewPacientes);
                    if (oldIdTipoPagoFKOfPacientesListaNewPacientes != null && !oldIdTipoPagoFKOfPacientesListaNewPacientes.equals(tipoPago)) {
                        oldIdTipoPagoFKOfPacientesListaNewPacientes.getPacientesLista().remove(pacientesListaNewPacientes);
                        oldIdTipoPagoFKOfPacientesListaNewPacientes = em.merge(oldIdTipoPagoFKOfPacientesListaNewPacientes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = tipoPago.getIdTipoPago();
                if (findTipoPago(id) == null) {
                    throw new NonexistentEntityException("The tipoPago with id " + id + " no longer exists.");
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
            TipoPago tipoPago;
            try {
                tipoPago = em.getReference(TipoPago.class, id);
                tipoPago.getIdTipoPago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoPago with id " + id + " no longer exists.", enfe);
            }
            List<Pacientes> pacientesLista = tipoPago.getPacientesLista();
            for (Pacientes pacientesListaPacientes : pacientesLista) {
                pacientesListaPacientes.setIdTipoPagoFK(null);
                pacientesListaPacientes = em.merge(pacientesListaPacientes);
            }
            em.remove(tipoPago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoPago> findTipoPagoEntities() {
        return findTipoPagoEntities(true, -1, -1);
    }

    public List<TipoPago> findTipoPagoEntities(int maxResults, int firstResult) {
        return findTipoPagoEntities(false, maxResults, firstResult);
    }

    private List<TipoPago> findTipoPagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoPago.class));
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

    public TipoPago findTipoPago(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoPago.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoPagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoPago> rt = cq.from(TipoPago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
