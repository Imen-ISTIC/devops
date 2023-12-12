package tn.esprit.eventsproject.dto;

import tn.esprit.eventsproject.entities.Event;

public class EventConverter {

    private EventConverter() {
        throw new IllegalStateException("Utility class");
      }

    public static EventDTO convertToDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setDescription(event.getDescription());
        eventDTO.setDateDebut(event.getDateDebut());
        eventDTO.setDateFin(event.getDateFin());
        eventDTO.setCout(event.getCout());
        // You may add additional mappings as needed
        return eventDTO;
    }

    public static Event convertToEntity(EventDTO eventDTO) {
        Event event = new Event();
        event.setDescription(eventDTO.getDescription());
        event.setDateDebut(eventDTO.getDateDebut());
        event.setDateFin(eventDTO.getDateFin());
        event.setCout(eventDTO.getCout());
        // You may add additional mappings as needed
        return event;
    }
}
