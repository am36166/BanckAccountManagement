package com.fsac.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public class Compte implements Serializable{
	@Id
	private String numCompte;
	private Date dateCreation;
	private double solde ;
	@OneToMany(mappedBy = "compte")
	private Collection<Operation> operations ;
	@ManyToOne @JoinColumn(name = "idClient")
	private Client client ;
	
	public Compte() {
	}

	public Compte(String numcpte , Date dateCreation, double solde) {
		this.dateCreation = dateCreation;
		this.solde = solde;
		this.numCompte = numcpte;
	}

	public String getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Collection<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
	
	

}
