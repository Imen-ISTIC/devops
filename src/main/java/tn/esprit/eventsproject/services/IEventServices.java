package tn.esprit.eventsproject.services;

import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;

import java.time.LocalDate;
import java.util.List;

public interface IEventServices {
    Participant addParticipant(Participant participant);

    Event addAffectEvenParticipant(Event event, int idParticipant);

    Event addAffectEvenParticipant(Event event);

    Logistics addAffectLog(Logistics logistics, String descriptionEvent);

    List<Logistics> getLogisticsDates(LocalDate date_debut, LocalDate date_fin);

    void calculCout();
}
