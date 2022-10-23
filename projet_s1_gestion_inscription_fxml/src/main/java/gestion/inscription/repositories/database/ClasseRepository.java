package gestion.inscription.repositories.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gestion.inscription.core.MySqlDB;
import gestion.inscription.entities.Classe;
import gestion.inscription.entities.Etudiant;
import gestion.inscription.entities.Inscription;
import gestion.inscription.entities.Prof;
import gestion.inscription.repositories.IClasseRepository;
import gestion.inscription.repositories.IEtudiantRepository;
import gestion.inscription.repositories.IProfRepository;

public class ClasseRepository  extends MySqlDB implements IClasseRepository {

    private IProfRepository profRepository;
    private IEtudiantRepository etudiantR;

    private final String SQL_INSERT = "INSERT INTO `classe` (`libelle`) VALUES (?);";
    private final String SQL_SELECT_BY_LIBELLE = "SELECT * FROM `classe` WHERE `libelle` LIKE ?";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM `classe` WHERE `id`=?";
    private final String  SQL_SELECT_BY_PROF="SELECT classe_id FROM `classe_prof` WHERE `prof_id` = ? ";
    private final String  SQL_SELECT_BY_ETUDIANT="SELECT classe_id FROM `inscription` WHERE `user_id` = ? ";
    public ClasseRepository(IProfRepository profRepository, IEtudiantRepository etudiantR) {
        this.profRepository = profRepository;
        this.etudiantR = etudiantR;
    }

    // private final String SQL_SELECT_BY_ID ="SELECT * FROM `classe` WHERE id = ?";
    @Override
    public Classe insert(Classe c) {
        
        this.ouvrirConnexionBD();
        try {
            ps = conn.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, c.getLibelle());
            ps.executeUpdate(); //insertion
            
            ResultSet rs = ps.getGeneratedKeys(); //généré le dernier id inséré
            if(rs.next()){
                c.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
            this.fermerConnexionBD();
            return c;
    }

    @Override
    public List<Classe> lister() {

        List<Classe> liste_classe = new ArrayList<>();

        this.ouvrirConnexionBD();
        
        try {

         // 3 - Executer la requête
         Statement stm = conn.createStatement();
         //c'est une requête non préparée
 
 // 4 - Récupérer les résultats
         ResultSet rs = stm.executeQuery("select * from classe");
         while(rs.next()){
             //rs chaque ligne de la table ou un enregistrment ou un tuple
             //on doit transformer la ligne en objet agence
                 // si la col 1 est un int ALORS rs.getInt(1) ou rs.getInt("id")
                 //si la col 2 est une chaîne ALORS rs.String(2) ou rs.getString("numero")
                 Classe classe = new Classe (rs.getInt("id"),
                                            rs.getString("libelle"));
                 liste_classe.add(classe);
             //requête select : relation(BD) vers un objet(JAVA)

         }

        } catch (SQLException e) {
            e.printStackTrace();
        }

 // 5 - Fermer la connexion
    this.fermerConnexionBD();

    return liste_classe;
    }

    
    @Override

    public Classe findByLibelle(String libelle) {
          
        Classe classe = null;

        this.ouvrirConnexionBD();
        try {
           ps = conn.prepareStatement(SQL_SELECT_BY_LIBELLE);
           ps.setString(1, libelle);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
                classe = new Classe(rs.getInt("id"), 
                                    rs.getString("libelle"));
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
            this.fermerConnexionBD();
        return classe;
    }

    @Override
    public List<Classe> classeUnProf(String nci) {
        List<Classe>  liste_classe=new ArrayList<>();
        this.ouvrirConnexionBD();
        try {
              Prof prof = profRepository.findByNci(nci);
              ps= conn.prepareStatement(SQL_SELECT_BY_PROF);
              ps.setInt(1,prof.getId()); 
              ResultSet rs=ps.executeQuery();

              while(rs.next()){
                
                 Classe classe=findById(rs.getInt("classe_id"));
                    liste_classe.add(classe);
                 }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.fermerConnexionBD();
        return liste_classe;
    }

    @Override
    public Classe findById(int id) {
          
        Classe classe = null;

        this.ouvrirConnexionBD();
        try {
           ps = conn.prepareStatement(SQL_SELECT_BY_ID);
           ps.setInt(1, id);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
                classe = new Classe(rs.getInt("id"), 
                                    rs.getString("libelle"));
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
            this.fermerConnexionBD();
        return classe;
    }

    @Override
    public List<Classe> classeFiltreParAn(List<Inscription> lstInscription, String libelle) {
        
        List<Classe> lstClasse = new ArrayList<>();
        for (Inscription i : lstInscription) {
            if(i.getClasse().getLibelle().compareTo(libelle)==0){
                lstClasse.add(i.getClasse());
            }
        }

        return lstClasse;
    }



    @Override
    public List<Classe> classeUnEtudiant(String matricule) {

        List<Classe>  liste_classe=new ArrayList<>();
        this.ouvrirConnexionBD();
        try {
              Etudiant etudiant = etudiantR.findByMatricule(matricule);
              ps= conn.prepareStatement(SQL_SELECT_BY_ETUDIANT);
              ps.setInt(1,etudiant.getId()); 
              ResultSet rs=ps.executeQuery();

              while(rs.next()){
                
                 Classe classe=findById(rs.getInt("classe_id"));
                    liste_classe.add(classe);
                 }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.fermerConnexionBD();
        return liste_classe;
    }

    
    
}
