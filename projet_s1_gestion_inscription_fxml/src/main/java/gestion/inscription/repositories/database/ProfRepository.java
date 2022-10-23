package gestion.inscription.repositories.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gestion.inscription.core.MySqlDB;
import gestion.inscription.entities.Classe;
import gestion.inscription.entities.Prof;
import gestion.inscription.repositories.IProfRepository;

public class ProfRepository  extends MySqlDB implements IProfRepository{

    
        private final String SQL_INSERT = "INSERT INTO `user` (`nom_complet`, `role`, `nci`, `grade`) VALUES (?,?,?,?);";
        private final String SQL_AFFECTATION = "INSERT INTO `classe_prof` (`classe_id`, `prof_id`) VALUES (?,?);";
        private final String SQL_SELECT_BY_NCI = "SELECT * FROM `user` WHERE `nci` LIKE ?";

        @Override
        public Prof insert(Prof p) {
        
        this.ouvrirConnexionBD();
        try {
            ps = conn.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getNom_complet());
            ps.setString(2,p.getRole().name());          
            ps.setString(3, p.getNci());
            ps.setString( 4,p.getGrade());
            ps.executeUpdate(); //insertion
            
            ResultSet rs = ps.getGeneratedKeys(); //généré le dernier id inséré
            if(rs.next()){
                p.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
            this.fermerConnexionBD();
            return p;
    }

        //affecter une classe lors de la création du prof
        @Override
        public void affecterClasseProf(Prof p, Classe c) {       

            this.ouvrirConnexionBD();
            try {
                ps = conn.prepareStatement(SQL_AFFECTATION, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, c.getId());
                ps.setInt(2,p.getId()); 
                ps.executeUpdate(); //insertion
                
                ResultSet rs = ps.getGeneratedKeys(); //généré le dernier id inséré
                if(rs.next()){
                    p.setId(rs.getInt(1));
                }
    
            } catch (SQLException e) {
                e.printStackTrace();
            }
      
                this.fermerConnexionBD();
                     
    }

        @Override
        public Prof findByNci(String n) {
            Prof prof = null;

        this.ouvrirConnexionBD();
        try {
           ps = conn.prepareStatement(SQL_SELECT_BY_NCI);
           ps.setString(1, n);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
                prof = new Prof(rs.getInt("id"),
                                rs.getString("nom_complet"),
                                rs.getString("nci"),
                                rs.getString("grade"));
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
            this.fermerConnexionBD();
        return prof;
        }

        




        @Override
        public List<Prof> lister() {
            List<Prof> liste_prof = new ArrayList<>();

        this.ouvrirConnexionBD();
        
        try {

         // 3 - Executer la requête
         Statement stm = conn.createStatement();
         //c'est une requête non préparée
 
 // 4 - Récupérer les résultats
         ResultSet rs = stm.executeQuery("select * from user where role = 'Prof'");
         while(rs.next()){
             //rs chaque ligne de la table ou un enregistrment ou un tuple
             //on doit transformer la ligne en objet agence
                 // si la col 1 est un int ALORS rs.getInt(1) ou rs.getInt("id")
                 //si la col 2 est une chaîne ALORS rs.String(2) ou rs.getString("numero")
                 Prof prof = new Prof(rs.getInt("id"),
                                        rs.getString("nom_complet"),
                                        rs.getString("nci"),
                                        rs.getString("grade"));
                 liste_prof.add(prof);
             //requête select : relation(BD) vers un objet(JAVA)

         }

        } catch (SQLException e) {
            e.printStackTrace();
        }

 // 5 - Fermer la connexion
    this.fermerConnexionBD();

    return liste_prof;
        }     


}
