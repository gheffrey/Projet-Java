package gestion.inscription.entities;

public class RP extends User{

    public RP(String login, String mdp, String nomComplet) {
        super(login, mdp, nomComplet);
        
        role = Role.RP;
    }
    
}
