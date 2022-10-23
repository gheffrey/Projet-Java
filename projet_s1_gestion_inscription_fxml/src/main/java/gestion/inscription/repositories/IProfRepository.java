package gestion.inscription.repositories;

import gestion.inscription.entities.Prof;

import java.util.List;

import gestion.inscription.entities.Classe;

public interface IProfRepository {

    public Prof insert(Prof p);
    public void affecterClasseProf(Prof p, Classe c);
    public Prof findByNci(String n);
    public List<Prof> lister();

}
