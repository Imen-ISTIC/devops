package tn.esprit.eventsproject.dto;

import lombok.Data;
import tn.esprit.eventsproject.entities.Tache;

@Data
public class ParticipantDTO {
    private String nom;
    private String prenom;
     private Tache tache;

    
}