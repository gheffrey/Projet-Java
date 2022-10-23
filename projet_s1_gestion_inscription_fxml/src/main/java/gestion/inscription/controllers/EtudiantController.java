package gestion.inscription.controllers;

import java.net.URL;
import java.util.ResourceBundle;


import gestion.inscription.core.Fabrique;
import gestion.inscription.core.Validator;
import gestion.inscription.entities.Classe;
import gestion.inscription.entities.Etudiant;
import gestion.inscription.entities.Inscription;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class EtudiantController implements Initializable {

    @FXML
    TextField  txtMatricule, txtNomComplet, txtTuteur, txtLibelle, txtDateInscription;
    @FXML
    Pane pnlEtudiant,pnlMatricule;
    @FXML
    Button btnInscription;
    @FXML
    Text lblErrorDate;
    
    @FXML
    TableView<Classe> tblvClasse ;
    @FXML
    TableColumn<Classe,String> tblcLibelle;
    
    ObservableList obClasseEtudiant ;
    private ObservableList obEtudiant = FXCollections.observableList(Fabrique.getService().lister_etudiant());

    SimpleBooleanProperty smpl=new SimpleBooleanProperty(false);


    Etudiant etudiant;
    Classe classe;
    Inscription inscription;

    private void LoadClasseUnEtudiant(String matricule){
        //Conversion List-> ObservableList
        obClasseEtudiant=FXCollections.observableList(Fabrique.getService().classeUnEtudiant(matricule));
        //Abonnement
        tblvClasse.setItems(obClasseEtudiant);
        //Construction des colonnes
        tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
    }

    public void handleSearchEtudiant(){
        String matricule = txtMatricule.getText().trim();
        etudiant = Fabrique.getService().findByMatricule(matricule);

        if(etudiant!=null){
            pnlEtudiant.setDisable(true);
            txtNomComplet.setText(etudiant.getNom_complet());
            txtTuteur.setText(etudiant.getTuteur());

                //On charge les classes de l'etudiant existant
            LoadClasseUnEtudiant( matricule);
            
        }else{ 
            //Sinon on active les champs 
            //et on récupère les données
            pnlEtudiant.setDisable(false);
        }
    }

    public void handleAddEtudiant(){
          //récupération des données
          String tuteur = txtTuteur.getText().trim();
          String nom_complet = txtNomComplet.getText().trim();

          if(etudiant==null){
            //on elève le matricule de la vue car il est généré
            pnlEtudiant.setDisable(true);       
            //on créé et insère la nouvele classe
            
          Etudiant e = Fabrique.getService().creerEtudiant(new Etudiant(nom_complet, tuteur));
  
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("Gestion d'inscription ISM");
          alert.setContentText("Etudiant ajouté avec succès!");
          alert.show();
  
          //MAJ des données et de l'affichage
          obEtudiant.add(e);
  
          //On vide les champ de text
          txtLibelle.clear();
          txtMatricule.clear();
          txtTuteur.clear();
          txtNomComplet.clear();
          }
          
    }

    public void handleAffectation(){

        String date_inscription ;

        if(etudiant==null){
            String nom_complet =txtNomComplet.getText().trim();
            String tuteur =txtTuteur.getText().trim();
            String libelle =txtLibelle.getText().trim();
            date_inscription = txtDateInscription.getText().trim();
            
            etudiant =  Fabrique.getService().creerEtudiant(etudiant);
            classe = Fabrique.getService().findByLibelle(libelle);
           
            if(classe==null){

                //App.setRoot("prof");
                
                Alert alert=new Alert(AlertType.INFORMATION);
                alert.setTitle("Gestion Bancaire");
                alert.setContentText("ATTENTION ! Cette classe n'existe pas !");
                alert.showAndWait();
                clearFields();
             }else{
                etudiant.setClasse(classe);
                inscription = new Inscription(libelle, etudiant, classe);
                Fabrique.getService().inscription(etudiant, classe, inscription);
             }
         }

         Classe c = Fabrique.getService().findByLibelle(txtLibelle.getText().trim());
         date_inscription = txtDateInscription.getText().trim();

         if(c==null){

            //App.setRoot("prof");
            
            Alert alert=new Alert(AlertType.INFORMATION);
            alert.setTitle("Gestion Bancaire");
            alert.setContentText("Attention ! Cette classe n'existe pas !");
            alert.showAndWait();
            clearFields();
         }else{
            if(date_inscription==""){
                Alert alert=new Alert(AlertType.INFORMATION);
                alert.setTitle("Gestion Bancaire");
                alert.setContentText("Entrez une date valide !");
                alert.showAndWait();
                clearFields();
            }else{
                etudiant.setClasse(c);
                inscription = new Inscription(date_inscription, etudiant, c);
              //  inscription.setClasse(c);
              //  inscription.setEtudiant(etudiant);
                Fabrique.getService().inscription(etudiant, c, inscription);
       
                Alert alert=new Alert(AlertType.INFORMATION);
                   alert.setTitle("Gestion Bancaire");
                   alert.setContentText("Etudiant incscrit avec succès !");
                   alert.showAndWait();
                   clearFields();
            }
         }

         

         

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lblErrorDate.setVisible(false);
        
        txtDateInscription.textProperty().addListener((obv,old,newV)->{
            if(newV.isEmpty()){
                lblErrorDate.setVisible(true);
            }else{
                  smpl.set(!Validator.isDate(txtDateInscription.getText()));
                 lblErrorDate.setVisible(false);  
            }
        });
        //on active le bouton si les champs sont remplis;
        btnInscription.disableProperty().bind(smpl);
    }

    private void clearFields(){
        txtTuteur.clear();
        txtMatricule.clear();
        txtNomComplet.clear();
        pnlEtudiant.setDisable(true);
     }
}
