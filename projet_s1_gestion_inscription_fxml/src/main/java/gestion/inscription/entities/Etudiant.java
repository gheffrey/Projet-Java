package gestion.inscription.entities;

public class Etudiant extends User {

    
    private String matricule;
    private String tuteur;
    private static int nbr;

    private Classe classe;

    

    public Etudiant(int id, String nom_complet, String tuteur) {
        super(id, nom_complet);
        this.tuteur = tuteur;
        nbr++;
        id=nbr;
        matricule="ETUD_"+id;
    }

    public Etudiant(String nomComplet,String tuteur) {
        super(nomComplet);
        nbr++;
        id=nbr;
        matricule="ETUD_"+id;
        this.tuteur = tuteur ;
        role=Role.Etudiant;
    }
    
    /*** 
    public Etudiant(String matricule,String nomComplet,String tuteur) {
        super(nomComplet);
        this.tuteur = tuteur ;
        role=Role.Etudiant;
        this.matricule=matricule;
    }
*/

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getTuteur() {
        return tuteur;
    }

    public void setTuteur(String tuteur) {
        this.tuteur = tuteur;
    }

    public static int getNbr() {
        return nbr;
    }

    public static void setNbr(int nbr) {
        Etudiant.nbr = nbr;
    }
    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    @Override
    public String toString() {
        return "Matricule = " + matricule +"\n Nom complet = " +  nom_complet + "\n Tuteur = " + tuteur ;
    }

    

    
}
