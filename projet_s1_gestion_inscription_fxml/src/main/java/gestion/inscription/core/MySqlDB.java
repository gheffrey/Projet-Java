package gestion.inscription.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySqlDB implements IDatabase {
    
    protected Connection conn;
    protected PreparedStatement ps;


    @Override
    public void ouvrirConnexionBD() {
         // 1 - Charger le Driver
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            
        // 2 - Ouvrir la connexion
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_S1_gestion_inscription", "root", "");
               // System.out.println("Connexion à la BD réussie !");
        
        
            }catch(SQLException e){
                e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        
    }

    @Override
    public void fermerConnexionBD() {
        //si la connexion n'est pas vide, on la ferme
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }


    @Override
    public void initPS() {

        
    }
    
}
