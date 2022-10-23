package gestion.inscription.repositories;

import java.util.List;

import gestion.inscription.entities.Classe;
import gestion.inscription.entities.Etudiant;
import gestion.inscription.entities.Inscription;

public interface IInscriptionRepository {

    public Inscription inscription(Etudiant e,Classe c,Inscription i);

    public List<Inscription> findByAn(String a); 
    public List<Inscription> lister();

}
