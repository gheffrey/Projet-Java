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
import gestion.inscription.repositories.IClasseProfRepository;
import gestion.inscription.repositories.IClasseRepository;
import gestion.inscription.repositories.IEtudiantRepository;
import gestion.inscription.repositories.IInscriptionRepository;
import gestion.inscription.repositories.IProfRepository;

public class InscriptionRepository extends MySqlDB implements IInscriptionRepository{

    private final String SQL_INSERT = "INSERT INTO `inscription` (`date_inscription`, `classe_id`, `user_id`) VALUES (?,?,?);";
    private final String SQL_SELECT_BY_AN = "SELECT * FROM `inscription` WHERE `date_inscription` LIKE ?";  //'%'?''
    
    IProfRepository profR = new ProfRepository();
    IEtudiantRepository etudiantR = new EtudiantRepository();
    IClasseRepository classeR = new ClasseRepository(profR,etudiantR);

    @Override
    public Inscription inscription(Etudiant e, Classe c,Inscription i) {
       
        this.ouvrirConnexionBD();
        try {
            ps = conn.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, i.getDate_inscription());
            ps.setInt(2,c.getId());          
            ps.setInt(3, e.getId());
            ps.executeUpdate(); //insertion
            
            ResultSet rs = ps.getGeneratedKeys(); //généré le dernier id inséré
            if(rs.next()){
                i.setId(rs.getInt(1));
            }

        } catch (SQLException x) {
            x.printStackTrace();
        }
        
            this.fermerConnexionBD();
            return i;
    }

    @Override
    public List<Inscription> findByAn(String a) {
        List<Inscription> inscriptionAn = new ArrayList<>();

        this.ouvrirConnexionBD();
        try {
           ps = conn.prepareStatement(SQL_SELECT_BY_AN);
           ps.setString(1, a+"%");
           ResultSet rs = ps.executeQuery();

           while(rs.next()){

            Inscription inscription = new Inscription(rs.getInt("id"),
           rs.getString("date_inscription"),
           etudiantR.findById(rs.getInt("user_id")),
           classeR.findById(rs.getInt("classe_id")));
           
           inscriptionAn.add(inscription);
       }
        } catch (SQLException e) {
        e.printStackTrace();
        }

            this.fermerConnexionBD();
        return inscriptionAn;
    }

    @Override
    public List<Inscription> lister() {
        List<Inscription> liste_i = new ArrayList<>();

        this.ouvrirConnexionBD();
        
        try {

         // 3 - Executer la requête
         Statement stm = conn.createStatement();
         //c'est une requête non préparée
 
 // 4 - Récupérer les résultats
         ResultSet rs = stm.executeQuery("select * from inscription");
         while(rs.next()){
             //rs chaque ligne de la table ou un enregistrment ou un tuple
             //on doit transformer la ligne en objet agence
                 // si la col 1 est un int ALORS rs.getInt(1) ou rs.getInt("id")
                 //si la col 2 est une chaîne ALORS rs.String(2) ou rs.getString("numero")
                 Inscription i = new Inscription(rs.getInt("id"),
                                        rs.getString("date_inscription"),
                                        etudiantR.findById(rs.getInt("user_id")),
                                        classeR.findById(rs.getInt("classe_id")) );
                 liste_i.add(i);
             //requête select : relation(BD) vers un objet(JAVA)

         }

        } catch (SQLException e) {
            e.printStackTrace();
        }

 // 5 - Fermer la connexion
    this.fermerConnexionBD();

    return liste_i;
    }

    
}
