package gestion.inscription.core;


import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Config {

    private static final String  file="src/main/resources/gestion/inscription/parametre.json";
    
    public static JsonNode loadJsonFile() throws StreamReadException, DatabindException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File(file);
        JsonNode jsonNode = mapper.readValue(jsonFile, JsonNode.class);

        return jsonNode;
    }


    public static int getSeqEtudiant() throws StreamReadException, DatabindException, IOException{
        JsonNode root= loadJsonFile();
        JsonNode sequenceNode = root.get("sequence");
         //Recuperation du Noeud Agence 
         JsonNode etudiantNode = sequenceNode.get("etudiant");
         return etudiantNode.asInt();
     }

     public static String getPrefixEtudiant() throws StreamReadException, DatabindException, IOException{
        JsonNode root= loadJsonFile();
        JsonNode sequenceNode = root.get("prefix");
         //Recuperation du Noeud Agence 
         JsonNode etudiantNode = sequenceNode.get("etudiant");
         return etudiantNode.asText();
     }




}
