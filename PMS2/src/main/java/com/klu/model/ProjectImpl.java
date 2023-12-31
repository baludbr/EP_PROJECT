package com.klu.model;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.klu.entity.Project;
@Stateless
@TransactionManagement(value=TransactionManagementType.BEAN)
public class ProjectImpl implements ProjectActions{

	@Override
	public String addProject(Project p) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("pms");
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return "Project Added Successfully";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getProjectDetails(String Email) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pms");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        String jpql = "SELECT u FROM Project u WHERE u.Email = :email";
        Query query = em.createQuery(jpql, Project.class);
        query.setParameter("email", Email);
        List<Project> resultList = query.getResultList();
        em.getTransaction().commit();
        em.close();
        emf.close();
        return resultList;
        }

	@Override
	public String approveProject(Project p) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("pms");
		EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Project r=em.find(Project.class,p.getProjectID());
        r.setStatus(p.getStatus());
        r.setApprovedby(p.getEmail());
        em.getTransaction().commit();
        em.close();
        emf.close();
        return "Approved Successful";
	}

	@Override
	public List<Project> getAllProjectDetails() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pms");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        String jpql = "SELECT u FROM Project u";
        Query query = em.createQuery(jpql, Project.class);
        List<Project> resultList = query.getResultList();
        em.getTransaction().commit();
        em.close();
        emf.close();
        return resultList;
	}
	public List<Project> getAllApprovedProjectDetails(String Email) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pms");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        String jpql = "SELECT u FROM Project u WHERE u.approvedby = :email";
        Query query = em.createQuery(jpql, Project.class);
        query.setParameter("email", Email);
        List<Project> resultList = query.getResultList();
        em.getTransaction().commit();
        em.close();
        emf.close();
        return resultList;
        }
	}

