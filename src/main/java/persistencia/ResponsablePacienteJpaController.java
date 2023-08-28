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
import modelosBD.ResponsablePaciente;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author NICOLAS
 */
public class ResponsablePacienteJpaController implements Serializable {

    public ResponsablePacienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public ResponsablePacienteJpaController(){
        emf = Persistence.createEntityManagerFactory("com.mycompany_ClinicaOdontologica_war_1.0-SNAPSHOTPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ResponsablePaciente responsablePaciente) throws PreexistingEntityException, Exception {
        if (responsablePaciente.getPacientesLista() == null) {
            responsablePaciente.setPacientesLista(new ArrayList<Pacientes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pacientes> attachedPacientesLista = new ArrayList<Pacientes>();
            for (Pacientes pacientesListaPacientesToAttach : responsablePaciente.getPacientesLista()) {
                pacientesListaPacientesToAttach = em.getReference(pacientesListaPacientesToAttach.getClass(), pacientesListaPacientesToAttach.getRutPaciente());
                attachedPacientesLista.add(pacientesListaPacientesToAttach);
            }
            responsablePaciente.setPacientesLista(attachedPacientesLista);
            em.persist(responsablePaciente);
            for (Pacientes pacientesListaPacientes : responsablePaciente.getPacientesLista()) {
                ResponsablePaciente oldRutResponsableFKOfPacientesListaPacientes = pacientesListaPacientes.getRutResponsableFK();
                pacientesListaPacientes.setRutResponsableFK(responsablePaciente);
                pacientesListaPacientes = em.merge(pacientesListaPacientes);
                if (oldRutResponsableFKOfPacientesListaPacientes != null) {
                    oldRutResponsableFKOfPacientesListaPacientes.getPacientesLista().remove(pacientesListaPacientes);
                    oldRutResponsableFKOfPacientesListaPacientes = em.merge(oldRutResponsableFKOfPacientesListaPacientes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findResponsablePaciente(responsablePaciente.getRutResponsable()) != null) {
                throw new PreexistingEntityException("ResponsablePaciente " + responsablePaciente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ResponsablePaciente responsablePaciente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ResponsablePaciente persistentResponsablePaciente = em.find(ResponsablePaciente.class, responsablePaciente.getRutResponsable());
            List<Pacientes> pacientesListaOld = persistentResponsablePaciente.getPacientesLista();
            List<Pacientes> pacientesListaNew = responsablePaciente.getPacientesLista();
            List<Pacientes> attachedPacientesListaNew = new ArrayList<Pacientes>();
            for (Pacientes pacientesListaNewPacientesToAttach : pacientesListaNew) {
                pacientesListaNewPacientesToAttach = em.getReference(pacientesListaNewPacientesToAttach.getClass(), pacientesListaNewPacientesToAttach.getRutPaciente());
                attachedPacientesListaNew.add(pacientesListaNewPacientesToAttach);
            }
            pacientesListaNew = attachedPacientesListaNew;
            responsablePaciente.setPacientesLista(pacientesListaNew);
            responsablePaciente = em.merge(responsablePaciente);
            for (Pacientes pacientesListaOldPacientes : pacientesListaOld) {
                if (!pacientesListaNew.contains(pacientesListaOldPacientes)) {
                    pacientesListaOldPacientes.setRutResponsableFK(null);
                    pacientesListaOldPacientes = em.merge(pacientesListaOldPacientes);
                }
            }
            for (Pacientes pacientesListaNewPacientes : pacientesListaNew) {
                if (!pacientesListaOld.contains(pacientesListaNewPacientes)) {
                    ResponsablePaciente oldRutResponsableFKOfPacientesListaNewPacientes = pacientesListaNewPacientes.getRutResponsableFK();
                    pacientesListaNewPacientes.setRutResponsableFK(responsablePaciente);
                    pacientesListaNewPacientes = em.merge(pacientesListaNewPacientes);
                    if (oldRutResponsableFKOfPacientesListaNewPacientes != null && !oldRutResponsableFKOfPacientesListaNewPacientes.equals(responsablePaciente)) {
                        oldRutResponsableFKOfPacientesListaNewPacientes.getPacientesLista().remove(pacientesListaNewPacientes);
                        oldRutResponsableFKOfPacientesListaNewPacientes = em.merge(oldRutResponsableFKOfPacientesListaNewPacientes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = responsablePaciente.getRutResponsable();
                if (findResponsablePaciente(id) == null) {
                    throw new NonexistentEntityException("The responsablePaciente with id " + id + " no longer exists.");
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
            ResponsablePaciente responsablePaciente;
            try {
                responsablePaciente = em.getReference(ResponsablePaciente.class, id);
                responsablePaciente.getRutResponsable();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The responsablePaciente with id " + id + " no longer exists.", enfe);
            }
            List<Pacientes> pacientesLista = responsablePaciente.getPacientesLista();
            for (Pacientes pacientesListaPacientes : pacientesLista) {
                pacientesListaPacientes.setRutResponsableFK(null);
                pacientesListaPacientes = em.merge(pacientesListaPacientes);
            }
            em.remove(responsablePaciente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ResponsablePaciente> findResponsablePacienteEntities() {
        return findResponsablePacienteEntities(true, -1, -1);
    }

    public List<ResponsablePaciente> findResponsablePacienteEntities(int maxResults, int firstResult) {
        return findResponsablePacienteEntities(false, maxResults, firstResult);
    }

    private List<ResponsablePaciente> findResponsablePacienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ResponsablePaciente.class));
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

    public ResponsablePaciente findResponsablePaciente(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ResponsablePaciente.class, id);
        } finally {
            em.close();
        }
    }

    public int getResponsablePacienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ResponsablePaciente> rt = cq.from(ResponsablePaciente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
