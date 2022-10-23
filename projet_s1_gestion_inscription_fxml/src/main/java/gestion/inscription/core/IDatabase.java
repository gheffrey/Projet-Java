package gestion.inscription.core;


public interface IDatabase {
    public void ouvrirConnexionBD();
    public void fermerConnexionBD();
    public void initPS();

}
