package gestion.inscription.repositories;

import java.util.List;

import gestion.inscription.entities.Etudiant;

public interface IEtudiantRepository {
    
    public Etudiant insert(Etudiant e);
    public Etudiant findById(int id);
    public Etudiant findByMatricule(String m);
    public List<Etudiant> lister();
}
