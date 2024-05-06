package com.fsac.dao;

import java.util.Date;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.query.Query;

import com.fsac.entities.Client;
import com.fsac.entities.Compte;
import com.fsac.entities.Operation;
import com.fsac.entities.Retrait;
import com.fsac.entities.Versement;

import util.HibernateUtil;

public class IdaoClientImp implements IDaoClient{

	
	@Override
	public Compte ConsulterCompte(String codeCpte) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        Compte cpte = session.get(Compte.class, codeCpte);
	        if (cpte == null) throw new RuntimeException("Compte introuvable");
	        return cpte;
	    } catch (Exception e) {
	        session.getTransaction().rollback();
	        throw e;
	    } finally {
	        session.close(); // Assurez-vous de fermer la session
	    }
	}


	@Override
	public void verser(String codeCpte, double montant) {
		// TODO Auto-generated method stub
	    Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Compte cpte = ConsulterCompte(codeCpte);
		if(cpte != null) {
			cpte.setSolde(cpte.getSolde()+montant);
			Operation operation = new Versement(new Date(), montant);
			operation.setCompte(cpte);
			session.update(cpte);
			session.save(operation);
		}
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void retirer(String codeCpte, double montant) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    Compte cpte = ConsulterCompte(codeCpte);
	    if (cpte != null) {
	        if (cpte.getSolde() >= montant) {
	            cpte.setSolde(cpte.getSolde() - montant);
	            Operation operation = new Retrait(new Date(), montant);
				operation.setCompte(cpte);
				session.save(operation);
	            session.update(cpte);
	        } else {
	            session.getTransaction().rollback(); 
	            throw new RuntimeException("Le solde n'est pas suffisant pour effectuer cette opération");
	        }
	    }
	    session.getTransaction().commit();
	    session.close();
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    Compte cpte1 = ConsulterCompte(codeCpte1);
	    Compte cpte2 = ConsulterCompte(codeCpte2);
	    if (cpte1 != null && cpte2 != null) {
	        if (cpte1.getSolde() >= montant) {
	            cpte1.setSolde(cpte1.getSolde() - montant);
	            cpte2.setSolde(cpte2.getSolde() + montant);
	            Operation operationDebit = new Retrait(new Date(), -montant); // Opération de débit pour le compte 1
	            operationDebit.setCompte(cpte1);
	            session.save(operationDebit);
	            Operation operationCredit = new Versement(new Date(), montant); // Opération de crédit pour le compte 2
	            operationCredit.setCompte(cpte2);
	            session.save(operationCredit);
	            session.update(cpte1);
	            session.update(cpte2);
	        } else {
	            session.getTransaction().rollback(); 
	            throw new RuntimeException("Le solde du compte source n'est pas suffisant pour effectuer ce virement");
	        }
	    }
	    session.getTransaction().commit();
	    session.close();
	}

	@Override
	public void AjouterClient(Client client) {
		// TODO Auto-generated method stub
	    Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(client);
		session.getTransaction().commit();		
        session.close();

	}

	@Override
	public void AjouterCompte(Long codeClient, Compte cpte) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        Client client = ConsulterClient(codeClient);
	        cpte.setClient(client);
	        session.save(cpte);
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        session.getTransaction().rollback();
	        throw e;
	    } finally {
	        session.close();
	    }
	}

	@Override
	public Client ConsulterClient(Long id) {
		// TODO Auto-generated method stub
	    Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Client client = session.get(Client.class, id) ;
		if(client == null) throw new RuntimeException("le client est introuvable");
		session.getTransaction().commit();
        session.close();

		return client;
	}


	@Override
	public List<Operation> ConsulterOperations(String codeCompte) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    Query req = session.createQuery("select op from Operation op where op.compte.numCompte = :code");
	    req.setParameter("code", codeCompte);
	    List<Operation> operations = req.list();
	    session.getTransaction().commit();
	    session.close();
	    return operations;
	}




}
