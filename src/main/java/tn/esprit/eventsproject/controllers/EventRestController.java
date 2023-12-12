package tn.esprit.eventsproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.eventsproject.dto.*;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.services.IEventServices;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("event")
@RestController
public class EventRestController {
    private final IEventServices eventServices;

    @PostMapping("/addPart")
    public ParticipantDTO addParticipant(@RequestBody ParticipantDTO participantDTO) {
        Participant participant = ParticipantConverter.convertToParticipant(participantDTO);
        return ParticipantConverter.convertToParticipantDTO(eventServices.addParticipant(participant));
    }

    @PostMapping("/addEvent")
    public EventDTO addEvent(@RequestBody EventDTO eventDTO) {
        Event event = EventConverter.convertToEntity(eventDTO);
        return EventConverter.convertToDTO(eventServices.addAffectEvenParticipant(event));
    }

    @PostMapping("/addEvent/{id}")
public EventDTO addEventPart(@RequestBody EventDTO eventDTO, @PathVariable("id") int idPart) {
    // Convert EventDTO to Event entity
    Event event = EventConverter.convertToEntity(eventDTO);

    // Add the event and associate it with the participant
    Event addedEvent = eventServices.addAffectEvenParticipant(event, idPart);

    // Convert the added Event entity to EventDTO
    return EventConverter.convertToDTO(addedEvent);
}

@PutMapping("/addAffectLog/{description}")
public LogisticsDTO addAffectLog(@RequestBody LogisticsDTO logisticsDTO, @PathVariable("description") String descriptionEvent) {
    // Convert LogisticsDTO to Logistics entity
    Logistics logistics = LogisticsConverter.convertToEntity(logisticsDTO);

    // Call the service method with the Logistics entity
    Logistics addedLogistics = eventServices.addAffectLog(logistics, descriptionEvent);

    // Convert the added Logistics entity to LogisticsDTO
    return LogisticsConverter.convertToDTO(addedLogistics);
    
}

    @GetMapping("/getLogs/{d1}/{d2}")
    public List<Logistics> getLogistiquesDates(@PathVariable("d1") LocalDate datedebut, @PathVariable("d2") LocalDate datefin) {
        return eventServices.getLogisticsDates(datedebut, datefin);
    }
}
