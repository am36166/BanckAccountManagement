package metier;

import java.util.List;

import com.fsac.dao.IDaoClient;
import com.fsac.entities.Client;
import com.fsac.entities.Compte;
import com.fsac.entities.Operation;

public class ImetierImp implements Imetier {
	private IDaoClient Dao ;
	
	

	public IDaoClient getDao() {
		return Dao;
	}

	public void setDao(IDaoClient dao) {
		Dao = dao;
	}

	@Override
	public void AjouterClient(Client client) {
		// TODO Auto-generated method stub
		Dao.AjouterClient(client);
		
	}

	@Override
	public void AjouterCompte(Long codeClient, Compte cpte) {
		// TODO Auto-generated method stub
		Dao.AjouterCompte(codeClient, cpte);
	}

	@Override
	public Compte ConsulterCompte(String codeCpte) {
		// TODO Auto-generated method stub
		
		return Dao.ConsulterCompte(codeCpte);
	}

	@Override
	public void verser(String codeCpte, double montant) {
		// TODO Auto-generated method stub
		Dao.verser(codeCpte, montant);
	}

	@Override
	public void retirer(String codeCpte, double montant) {
		// TODO Auto-generated method stub
		Dao.retirer(codeCpte, montant);
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
		// TODO Auto-generated method stub
		Dao.virement(codeCpte1, codeCpte2, montant);
		
	}

	@Override
	public Client ConsulterClient(Long id) {
		// TODO Auto-generated method stub
		return Dao.ConsulterClient(id);
	}

	@Override
	public List<Operation> ConsulterOperations(String codeCompte) {
		// TODO Auto-generated method stub
		return Dao.ConsulterOperations(codeCompte);
	}

}
