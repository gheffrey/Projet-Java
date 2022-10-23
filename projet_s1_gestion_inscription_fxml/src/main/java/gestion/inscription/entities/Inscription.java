package gestion.inscription.entities;

public class Inscription {

    private int id;
    private String date_inscription;

    private Etudiant etudiant;
    private Classe classe;

    
    public Inscription() {
    }

    public Inscription(int id, String date_inscription, int etudiant_id, int classe_id) {
        this.id = id;
        this.date_inscription = date_inscription;
    }

   

    public Inscription(int id, String date_inscription) {
        this.id = id;
        this.date_inscription = date_inscription;
    }

    //pour insérer
    public Inscription(String date_inscription, Etudiant etudiant, Classe classe) {
        this.date_inscription = date_inscription;
        this.etudiant = etudiant;
        this.classe = classe;
    }


    //pour récupérer
    public Inscription(int id, String date_inscription, Etudiant etudiant, Classe classe) {
        this.id = id;
        this.date_inscription = date_inscription;
        this.etudiant = etudiant;
        this.classe = classe;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getDate_inscription() {
        return date_inscription;
    }


    public void setDate_inscription(String date_inscription) {
        this.date_inscription = date_inscription;
    }



    public Etudiant getEtudiant() {
        return etudiant;
    }


    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }


    public Classe getClasse() {
        return classe;
    }


    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    @Override
    public String toString() {
        return "Inscription [ date_inscription=" + date_inscription + ", etudiant=" + etudiant
                + ", classe=" + classe + "]";
    }


    
}
