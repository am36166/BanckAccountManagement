package com.fsac.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue(value = "CE")
public class CompteEpargne extends Compte{
	private double taux ;

	public CompteEpargne() {
		super();
	}

	public CompteEpargne(String code , Date dateCreation, double solde ,double taux) {
		super(code , dateCreation,solde);
		this.taux = taux;
	}
	
	

}