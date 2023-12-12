package tn.esprit.eventsproject.dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventDTO {
   private String description;
   private LocalDate dateDebut;
   private LocalDate dateFin;
   private float cout;
}
