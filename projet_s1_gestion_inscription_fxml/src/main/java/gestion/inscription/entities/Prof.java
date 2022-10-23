package gestion.inscription.entities;

public class Prof extends User{

    private String nci;
    private String grade;

    private Classe classe;


    public Prof(String nom_complet, String nci, String grade) {
        super(nom_complet);
        this.nci = nci;
        this.grade = grade;
        role = Role.Prof;
    }


    public Prof(int id,String nom_complet, String nci, String grade) {
        super( nom_complet);
       this.grade = grade;
       this.nci = nci;
       this.id = id;
       role = Role.Prof;
    }

    
    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }


    public String getNci() {
        return nci;
    }

    public void setNci(String nci) {
        this.nci = nci;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    @Override
    public String toString() {
        return "Professeur [id=" + id + ", nom complet=" + nom_complet +", grade ="+grade+", NCI ="+nci+", login ="+login+"]";
    }

    

}
