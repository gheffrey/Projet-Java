package gestion.inscription.entities;

public class AC  extends User{

    public AC(String login, String mdp, String nomComplet) {
        super(login, mdp, nomComplet);
       role = Role.AC;
    }
    
}
