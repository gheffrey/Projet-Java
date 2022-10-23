package gestion.inscription.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import gestion.inscription.App;
import gestion.inscription.core.Fabrique;
import gestion.inscription.entities.Classe;
import gestion.inscription.entities.Prof;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProfController implements Initializable{

    @FXML
    TextField txtNci, txtNomComplet, txtGrade, txtLibelle;
    @FXML
    Pane pnlProf;

    @FXML
    TableView<Classe> tblvClasse ;
    @FXML
    TableColumn<Classe,String> tblcLibelle;


    private ObservableList obProf = FXCollections.observableList(Fabrique.getService().lister_prof());
    
    ObservableList obClasseProf ;

    Prof prof;
    Classe classe;

    private void LoadClasseUnProf(String nci){
        //Conversion List-> ObservableList
        obClasseProf=FXCollections.observableList(Fabrique.getService().classeUnProf(nci));
        //Abonnement
        tblvClasse.setItems(obClasseProf);
        //Construction des colonnes
        tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));


    }

    
    public void handleSearchProf(){
        String nci = txtNci.getText().trim();
        prof = Fabrique.getService().findByNCI(nci);


        if(prof!=null){
            //si le prof existe, on désactive les champs
            //et on affiche ses informations
            pnlProf.setDisable(true);
            
            txtNomComplet.setText(prof.getNom_complet());
            txtGrade.setText(prof.getGrade());
                //On charge les classes du prof existant
            LoadClasseUnProf(nci);
        }else{ 
            //Sinon on active les champs 
            //et on récupère les données
            pnlProf.setDisable(false);
        }
    }

    public void handleAddProf(){
          //récupération des données
          String nci = txtNci.getText().trim();
          String grade = txtGrade.getText().trim();
          String nom_complet = txtNomComplet.getText().trim();

          if(prof==null){
            //on créé et insère la nouvele classe
          Prof professeur = Fabrique.getService().ajouterProf(new Prof(nom_complet, nci, grade));
  
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("Gestion d'inscription ISM");
          alert.setContentText("Professeur ajouté avec succès!");
          alert.show();
  
          //MAJ des données et de l'affichage
          obProf.add(professeur);
  
          //On vide les champ de text
          txtLibelle.clear();
          txtGrade.clear();
          txtNci.clear();
          txtNomComplet.clear();
          }
          
    }
    public void handleAffectation(){

        if(prof==null){
            String nci =txtNci.getText().trim();
            String nom_complet =txtNomComplet.getText().trim();
            String grade =txtGrade.getText().trim();
            String libelle =txtLibelle.getText().trim();
            
            prof =  Fabrique.getService().ajouterProf(new Prof(nom_complet, nci, grade));
            classe = Fabrique.getService().findByLibelle(libelle);

            if(classe==null){

                //App.setRoot("prof");
                
                Alert alert=new Alert(AlertType.INFORMATION);
                alert.setTitle("Gestion Bancaire");
                alert.setContentText("ATTENTION ! Cette classe n'existe pas !");
                alert.showAndWait();
                clearFields();
             }else{
                prof.setClasse(classe);
                Fabrique.getService().affecterClasseProf(prof, classe);
             }
         }

         Classe c = Fabrique.getService().findByLibelle(txtLibelle.getText().trim());

         if(c==null){

            //App.setRoot("prof");
            
            Alert alert=new Alert(AlertType.INFORMATION);
            alert.setTitle("Gestion Bancaire");
            alert.setContentText("Cette classe n'existe pas !");
            alert.showAndWait();
            clearFields();
         }
         prof.setClasse(c);
         Fabrique.getService().affecterClasseProf(prof, c);

         Alert alert=new Alert(AlertType.INFORMATION);
            alert.setTitle("Gestion Bancaire");
            alert.setContentText("Professeur affecté avec succès !");
            alert.showAndWait();
            clearFields();

        

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         
        
    }


        private void clearFields(){
            txtNci.clear();
            txtGrade.clear();
            txtNomComplet.clear();
            pnlProf.setDisable(true);
         }
    


}
