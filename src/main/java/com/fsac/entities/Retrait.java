package com.fsac.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue(value = "OR")
public class Retrait extends Operation{

	public Retrait() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Retrait(Date dateOperation, double montant) {
		super(dateOperation, montant);
		// TODO Auto-generated constructor stub
	}
	

}
