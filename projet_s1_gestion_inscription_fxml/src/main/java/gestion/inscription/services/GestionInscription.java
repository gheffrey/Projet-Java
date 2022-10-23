package gestion.inscription.services;

import java.util.List;

import gestion.inscription.entities.Classe;
import gestion.inscription.entities.Etudiant;
import gestion.inscription.entities.Inscription;
import gestion.inscription.entities.Prof;
import gestion.inscription.entities.User;
import gestion.inscription.repositories.IClasseRepository;
import gestion.inscription.repositories.IEtudiantRepository;
import gestion.inscription.repositories.IInscriptionRepository;
import gestion.inscription.repositories.IProfRepository;
import gestion.inscription.repositories.IUserRepository;

public class GestionInscription implements IGestionInscription{

    private IUserRepository userR;
    private IProfRepository profR;
    private IClasseRepository classeR ;
    private IEtudiantRepository etudiantR;
    private IInscriptionRepository inscriptionR;

    public GestionInscription(IClasseRepository classeR, IProfRepository profR,IEtudiantRepository etudiantR, IInscriptionRepository inscriptionR,IUserRepository userR) {
        this.classeR = classeR;
        this.profR = profR;
        this.etudiantR= etudiantR;  
        this.inscriptionR=inscriptionR;
        this.userR = userR;

    }

    @Override
    public Classe creerClasse(Classe c) {
        return classeR.insert(c);
    }

    @Override
    public List<Classe> lister_classe() {
        return classeR.lister();
    }

    @Override
    public Prof ajouterProf(Prof p) {
        return profR.insert(p);
    }

    @Override
    public void affecterClasseProf(Prof p, Classe c) {
         profR.affecterClasseProf(p,c);
    }

    @Override
    public List<Prof> lister_prof() {
        return profR.lister();
    }

    

    @Override
    public Etudiant creerEtudiant(Etudiant e) {
        return etudiantR.insert(e);
    }

    @Override
    public Classe findByLibelle(String l) {
        return classeR.findByLibelle(l);
    }

    @Override
    public List<Classe> classeUnProf(String nci) {
        return classeR.classeUnProf(nci);
    }

    @Override
    public Prof findByNCI(String nci) {
        return profR.findByNci(nci);
    }
    @Override
    public Etudiant findByMatricule(String matricule) {
        return etudiantR.findByMatricule(matricule);
    }

    @Override
    public Inscription inscription(Etudiant e, Classe c, Inscription i) {
        return inscriptionR.inscription(e, c, i);
    }

    @Override
    public List<Inscription> lister_inscription() {
        return inscriptionR.lister();
    }

    @Override
    public List<Inscription> filtreParAn(String an) {
        return inscriptionR.findByAn(an);
    }

    @Override
    public List<Classe> classeFiltreParAn(List<Inscription> lstInscription, String libelle) {
        return classeR.classeFiltreParAn(lstInscription,libelle);
    }

    @Override
    public User seConnecter(String login, String password) {
        return userR.findUserByLoginAndPassword(login, password);
    }

    @Override
    public List<Etudiant> lister_etudiant() {
        return etudiantR.lister();
    }

    @Override
    public List<Classe> classeUnEtudiant(String matricule) {
       return classeR.classeUnEtudiant(matricule);
    }

   

   

          
    

}
    
    

