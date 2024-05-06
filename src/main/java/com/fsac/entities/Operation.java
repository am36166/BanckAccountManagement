package com.fsac.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public class Operation implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numOperation;
	private Date dateOperation;
	private double montant;
	@ManyToOne @JoinColumn(name = "CODE_CPTE")
	private Compte compte;
	
	public Operation() {
	}

	public Operation(Date dateOperation, double montant) {
		this.dateOperation = dateOperation;
		this.montant = montant;
	}

	public Long getNumOperation() {
		return numOperation;
	}

	public void setNumOperation(Long numOperation) {
		this.numOperation = numOperation;
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	
	
	

}
