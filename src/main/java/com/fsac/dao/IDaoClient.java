package com.fsac.dao;


import java.util.List;

import com.fsac.entities.Client;
import com.fsac.entities.Compte;
import com.fsac.entities.Operation;

public interface IDaoClient {
	
	public void AjouterClient(Client client);
	public void AjouterCompte(Long codeClient , Compte cpte);
	public Compte ConsulterCompte(String codeCpte);
	public void verser(String codeCpte , double montant);
	public void retirer(String codeCpte , double montant);
	public void virement(String codeCpte1 ,String codeCpte2 , double montant); 
	public Client ConsulterClient(Long id);
	public List<Operation> ConsulterOperations(String codeCompte);

}
