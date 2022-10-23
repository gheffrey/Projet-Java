package gestion.inscription.core;

import gestion.inscription.repositories.IClasseProfRepository;
import gestion.inscription.repositories.IClasseRepository;
import gestion.inscription.repositories.IEtudiantRepository;
import gestion.inscription.repositories.IInscriptionRepository;
import gestion.inscription.repositories.IProfRepository;
import gestion.inscription.repositories.IUserRepository;
import gestion.inscription.repositories.database.ClasseRepository;
import gestion.inscription.repositories.database.EtudiantRepository;
import gestion.inscription.repositories.database.InscriptionRepository;
import gestion.inscription.repositories.database.ProfRepository;
import gestion.inscription.repositories.database.UserRepository;
import gestion.inscription.services.GestionInscription;
import gestion.inscription.services.IGestionInscription;

public class Fabrique {
    

    public  static IGestionInscription getService(){
         

         IProfRepository profR=new ProfRepository();
         IEtudiantRepository etudiantR =new EtudiantRepository();
         IClasseRepository classeR = new ClasseRepository(profR,etudiantR);
         IInscriptionRepository inscriptionR =new InscriptionRepository();
         IUserRepository userR = new UserRepository();
         return new GestionInscription(classeR,profR,etudiantR,inscriptionR,userR);
    }
}
