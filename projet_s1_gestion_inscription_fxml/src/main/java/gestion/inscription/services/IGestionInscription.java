package gestion.inscription.services;

import java.util.List;

import gestion.inscription.entities.Classe;
import gestion.inscription.entities.Etudiant;
import gestion.inscription.entities.Inscription;
import gestion.inscription.entities.Prof;
import gestion.inscription.entities.User;

public interface IGestionInscription {

    public List<Etudiant> lister_etudiant();

    public Classe findByLibelle(String l);
    public Prof findByNCI(String nci);
    public Etudiant findByMatricule(String matricule);

    public Classe creerClasse(Classe c);

    public List<Classe> lister_classe();

    public Prof ajouterProf(Prof p);

    public List<Prof> lister_prof();
    public List<Inscription> lister_inscription();
    public List<Inscription> filtreParAn(String an);
    
    public void affecterClasseProf(Prof p, Classe c);

    public Etudiant creerEtudiant(Etudiant e);

    public Inscription inscription(Etudiant e,Classe c, Inscription i);

    public List<Classe> classeUnProf(String nci);
    public List<Classe> classeUnEtudiant(String matricule);

    public List<Classe> classeFiltreParAn(List<Inscription> lstInscription ,String libelle);

    public User seConnecter(String login,String password);

}
