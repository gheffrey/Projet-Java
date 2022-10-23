package gestion.inscription.entities;

public class User {

    protected int id;
    protected String login;
    protected String mdp;
    protected String nom_complet;
    protected Role role;

   

    public User(int id, String login, String mdp, String nom_complet, Role role) {
        this.id = id;
        this.login = login;
        this.mdp = mdp;
        this.nom_complet = nom_complet;
        this.role = role;
    }



    public User() {
    }



    public User(String nom_complet) {
        this.nom_complet = nom_complet;
    }



    public User(int id, String nom_complet) {
        this.id = id;
        this.nom_complet = nom_complet;
    }



    public User(String login, String mdp, String nom_complet) {
        this.login = login;
        this.mdp = mdp;
        this.nom_complet = nom_complet;
    }



    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }



    public String getLogin() {
        return login;
    }



    public void setLogin(String login) {
        this.login = login;
    }



    public String getMdp() {
        return mdp;
    }



    public void setMdp(String mdp) {
        this.mdp = mdp;
    }



    public String getNom_complet() {
        return nom_complet;
    }



    public void setNom_complet(String nom_complet) {
        this.nom_complet = nom_complet;
    }



    public Role getRole() {
        return role;
    }



    public void setRole(Role role) {
        this.role = role;
    }

    

}
