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
    public Event addEventPart(@RequestBody Event event, @PathVariable("id") int idPart) {
        return eventServices.addAffectEvenParticipant(event, idPart);
    }

    @PutMapping("/addAffectLog/{description}")
    public Logistics addAffectLog(@RequestBody Logistics logistics, @PathVariable("description") String descriptionEvent) {
        return eventServices.addAffectLog(logistics, descriptionEvent);
    }

    @GetMapping("/getLogs/{d1}/{d2}")
    public List<Logistics> getLogistiquesDates(@PathVariable("d1") LocalDate datedebut, @PathVariable("d2") LocalDate datefin) {
        return eventServices.getLogisticsDates(datedebut, datefin);
    }
}
