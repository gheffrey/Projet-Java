package gestion.inscription.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gestion.inscription.App;
import gestion.inscription.entities.Role;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class HomeController implements Initializable {

    @FXML
    AnchorPane anchorContent;
    @FXML
    Button btnMenuInscription, btnMenuEtudiant, btnMenuClasse,btnMenuProf;

    public void handleDeconnexion() throws IOException{
        App.setRoot("connexion");
    }

    public void handleLoadViewClasse() throws IOException{
       this.loadView("classe");
    }
    public void handleLoadViewProf() throws IOException{
        this.loadView("prof");
    }
    public void handleLoadViewEtudiant() throws IOException{
        this.loadView("etudiant");
    }
    public void handleLoadViewInscription() throws IOException{
        this.loadView("inscription");
    }

    //pour charger la page par défaut lorsqu'un user se connecte
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            if(ConnexionController.user.getRole()==Role.RP){
                isRP();
            }else{
                isAC();
            }
            this.loadView("classe");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private void loadView(String fxml) throws IOException{
         //on récupère la vue classe.fxml
         AnchorPane root = (AnchorPane) App.loadFXML(fxml);
         //on la met sur les enfants de anchor home.fxml
         anchorContent.getChildren().clear();
         anchorContent.getChildren().add(root);
    
    }

    private void isAC(){
        btnMenuInscription.setDisable(false);
        btnMenuEtudiant.setDisable(false);
 
    }
    private void isRP(){
        btnMenuProf.setDisable(false);;
    }

    
}
