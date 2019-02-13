package controller;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Pet;

public class PetsListHelper {

	static EntityManagerFactory emfactory =	
		Persistence.createEntityManagerFactory("WebPetsList");
	
	public void insertItem(Pet toInsert) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(toInsert);
		em.getTransaction().commit();
		em.close();
	}
	
	public	List<Pet> showAllPets() {
		EntityManager	em	=	emfactory.createEntityManager();
		List<Pet> allItems = em.createQuery("SELECT p FROM Pet p").getResultList();
		return	allItems;
		}

	public void deleteItem(Pet toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Pet> typedQuery = em.createQuery("select p from " +
			"Pet p where p.type = :selectedType and p.name = " +
			":selectedName and p.owner = :selectedOwner", Pet.class);
		
		//Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedType", toDelete.getType());
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedOwner", toDelete.getOwner());
		
		//we only want one result
		typedQuery.setMaxResults(1);
		
		//get the result and save it into a new list item
		Pet result = typedQuery.getSingleResult();
		
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public Pet searchForPetById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Pet found = em.find(Pet.class, idToEdit);
		em.close();
		return found;
	}

	public void updatePet(Pet toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	public List<Pet> searchForPetByType(String type) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Pet> typedQuery = em.createQuery("select p from " +
			"Pet p where p.type = :selectedType",	Pet.class);
		typedQuery.setParameter("selectedType", type);
		
		List<Pet> foundPets = typedQuery.getResultList();
		em.close();
		return foundPets;
	}

	public List<Pet> searchForPetByName(String name) {
		EntityManager em	=	emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Pet>	typedQuery	=	em.createQuery("select p from " +
		"Pet p where p.name = :selectedName", Pet.class);
		typedQuery.setParameter("selectedName", name);
		
		List<Pet> foundPets = typedQuery.getResultList();
		em.close();
		return foundPets;
	}
	
	public List<Pet> searchForPetByOwner(String owner) {
		EntityManager em	=	emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Pet>	typedQuery	=	em.createQuery("select p from " +
		"Pet p where p.owner = :selectedOwner", Pet.class);
		typedQuery.setParameter("selectedOwner", owner);
		
		List<Pet> foundPets = typedQuery.getResultList();
		em.close();
		return foundPets;
	}
	
	public void cleanUp(){
		emfactory.close();
	}
	
}
