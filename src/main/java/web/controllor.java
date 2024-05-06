package web;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fsac.entities.Client;
import com.fsac.entities.Compte;
import com.fsac.entities.CompteCourant;
import com.fsac.entities.CompteEpargne;
import com.fsac.entities.Operation;

import metier.Imetier;

@Controller
public class controllor {
	
	@Autowired
    private Imetier metier;


    @RequestMapping(value = "/index.do", method = RequestMethod.GET)
    public String test() {
        return "comptes";
    }

    @RequestMapping(value = "/consultercompte.do" , method= RequestMethod.GET)
    public String consulter(@RequestParam("codeCompte") String codeCompte, Model model) {
        try {
            if (metier != null) {
                Compte compte = metier.ConsulterCompte(codeCompte);
                String nomClasse = compte != null ? compte.getClass().getSimpleName() : "";
                long id = compte.getClient().getCode() ;
                Client client = metier.ConsulterClient(id);
                List<Operation> operations = metier.ConsulterOperations(codeCompte);
                model.addAttribute("operations",operations);
                model.addAttribute("client",client);
                model.addAttribute("nomClasse", nomClasse);
                model.addAttribute("compte", compte);
            } else {
                model.addAttribute("erreur", "Le DAO n'est pas initialisé.");
            }
        } catch (Exception e) {
            model.addAttribute("erreur", "Une erreur s'est produite lors de la consultation du compte : " + e.getMessage());
        }
        return "comptes";
    }

    @RequestMapping(value = "/effectuerTransaction.do" , method=RequestMethod.POST)
    public String transaction(Model model, @RequestParam String typeTransaction, 
                              @RequestParam double montant, @RequestParam(required=false) String compteCible,
                              @RequestParam String codeCompte) {
        String message = "Transaction effectuee avec succes."; 

        try {
            if ("retrait".equals(typeTransaction)) {
                metier.retirer(codeCompte, montant);
            } else if ("virement".equals(typeTransaction)) {
                if (compteCible == null || compteCible.isEmpty()) {
                    model.addAttribute("erreur1", "Le compte cible est requis pour effectuer un virement.");
                }
                metier.virement(codeCompte, compteCible, montant);
                model.addAttribute("message", message);
            } else if ("versement".equals(typeTransaction)) {
                metier.verser(codeCompte, montant);
                model.addAttribute("message", message);
            } 

        } catch (Exception e) {
            return "redirect:/consultercompte.do?codeCompte=" + codeCompte + "&error=" + e.getMessage();
        }
        
        return "redirect:/consultercompte.do?codeCompte=" + codeCompte + "&message=" + message;
    }

    @RequestMapping(value = "/service.do", method = RequestMethod.GET)
    public String afficherService(Model model) {
        return "devenirclient"; 
    }
    
    @RequestMapping(value = "/propos.do", method = RequestMethod.GET)
    public String afficherPropos(Model model) {
        return "propos"; 
    }
    
    @RequestMapping(value = "/creerCompte.do", method = RequestMethod.GET)
    public String Compte(Model model) {
        return "creerCompte"; 
    }
    
    @RequestMapping(value = "enregistrerClient.do" , method= RequestMethod.POST)
    public String EnregistrerCompte(Model model,@RequestParam String email,@RequestParam String nom) {
    	
    	    String message = "Votre compte a ete Bien cree";
    	     metier.AjouterClient(new Client(nom,email));
        return "redirect:/service.do?message=" + message;
    }
    
    @RequestMapping(value = "enregistrerCompte.do", method = RequestMethod.POST)
    public String enregistrerCompteClient(Model model, @RequestParam Long codeclient, @RequestParam String Compte,
            @RequestParam String typeCompte, @RequestParam(required = false) String tauxStr,
            @RequestParam(required = false) String decouvertStr, @RequestParam String soldeStr) {
        String message = "Votre compte a ete cree avec succes";
        try {
            // Conversion des valeurs en double
            double solde = Double.parseDouble(soldeStr);
            double taux = (tauxStr != null && !tauxStr.isEmpty()) ? Double.parseDouble(tauxStr) : 0.0;
            double decouvert = (decouvertStr != null && !decouvertStr.isEmpty()) ? Double.parseDouble(decouvertStr) : 0.0;

            // Création du compte en fonction du type
            if ("courant".equals(typeCompte)) {
                Compte cpte = new CompteCourant(Compte, new Date(), solde, decouvert);
                metier.AjouterCompte(codeclient, cpte);
            } else if ("epargne".equals(typeCompte)) {
                Compte cpte = new CompteEpargne(Compte, new Date(), solde, taux);
                metier.AjouterCompte(codeclient, cpte);
            }

        } catch (NumberFormatException e) {
            return "redirect:/creerCompte.do?erreur=Veuillez saisir des valeurs numeriques valides pour les champs solde, taux et decouvert.";
        } catch (Exception e) {
            return "redirect:/creerCompte.do?erreur=" + e.getMessage();
        }
        return "redirect:/creerCompte.do?message=" + message;
    }

}

