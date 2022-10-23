package gestion.inscription.repositories;

import java.util.List;

import gestion.inscription.entities.Classe;
import gestion.inscription.entities.Etudiant;
import gestion.inscription.entities.Inscription;
import gestion.inscription.entities.Prof;

public interface IClasseRepository {
    
    public Classe insert(Classe c);
    public List<Classe> lister();
    public Classe findByLibelle(String l);
    public Classe findById(int id);
    public List<Classe> classeUnProf(String nci);
    public List<Classe> classeUnEtudiant(String matricule);
    public List<Classe> classeFiltreParAn(List<Inscription>  lstInscription, String libelle);
     
}
