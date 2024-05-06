package com.fsac.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue(value = "CC")
public class CompteCourant extends Compte{
	private double decouvert ;

	public CompteCourant() {
		super();
	}

	public CompteCourant(String code , Date dateCreation, double solde , double decouvert) {
		super(code,dateCreation, solde);
		// TODO Auto-generated constructor stub
		this.decouvert = decouvert ;
	}
		

}
