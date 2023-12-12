package tn.esprit.eventsproject.dto;
import lombok.Data;

@Data
public class LogisticsDTO {

    private String description;
   private boolean reserve;
   private float prixUnit;
   private int quantite;
    
}
