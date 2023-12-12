package tn.esprit.eventsproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.entities.Tache;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.List;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;


 class EventServicesImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private LogisticsRepository logisticsRepository;

    @InjectMocks
    private EventServicesImpl eventServices;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAffectEvenParticipant() {
        // Create a participant for the test
        Participant participant = new Participant();
        participant.setIdPart(1);
        participant.setEvents(new HashSet<>());

        // Create an event for the test
        Event event = new Event();
        event.setIdEvent(1);

        // Simulate the behavior of the participant repository
        when(participantRepository.findById(1)).thenReturn(Optional.of(participant));

        // Simulate the behavior of the event repository
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        // Call the method to test
        Event result = eventServices.addAffectEvenParticipant(event, 1);

        // Verify that the save method of the repository has been called
        verify(eventRepository, times(1)).save(any(Event.class));

        // Verify that the event has been added to the list of events for the participant
        assertEquals(1, participant.getEvents().size());
        assertTrue(participant.getEvents().contains(result));
    }

    @Test
    void testAddAffectEvenParticipantWithSet() {
        // Create participants for the test
        Participant participant1 = new Participant();
        participant1.setIdPart(1);

        Participant participant2 = new Participant();
        participant2.setIdPart(2);

        Set<Participant> participants = new HashSet<>();
        participants.add(participant1);
        participants.add(participant2);

        // Create an event for the test
        Event event = new Event();
        event.setParticipants(participants);

        // Simulate the behavior of the participant repository
        when(participantRepository.findById(1)).thenReturn(Optional.of(participant1));
        when(participantRepository.findById(2)).thenReturn(Optional.of(participant2));

        // Simulate the behavior of the event repository
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        // Call the method to test
        Event result = eventServices.addAffectEvenParticipant(event);

        // Verify that the save method of the repository has been called
        verify(eventRepository, times(1)).save(any(Event.class));

        // Add additional assertions if needed
    }

    @Test
    void testAddAffectLog() {
        // Create an event for the test
        Event event = new Event();
        event.setDescription("Test Event");
        event.setLogistics(new HashSet<>());

        // Create a Logistics object for the test
        Logistics logistics = new Logistics();
        logistics.setIdLog(1);
        logistics.setReserve(true);

        // Simulate the behavior of the event repository
        when(eventRepository.findByDescription("Test Event")).thenReturn(event);

        // Simulate the behavior of the repositories
        when(eventRepository.save(any(Event.class))).thenReturn(event);
        when(logisticsRepository.save(any(Logistics.class))).thenReturn(logistics);

        // Call the method to test
        Logistics result = eventServices.addAffectLog(logistics, "Test Event");

        // Verify that the save methods have been called
        verify(eventRepository, times(1)).save(any(Event.class));
        verify(logisticsRepository, times(1)).save(any(Logistics.class));

        // Verify that the logistics has been added to the list of logistics for the event
        assertEquals(1, event.getLogistics().size());
        assertTrue(event.getLogistics().contains(result));
    }

    @Test
    void testGetLogisticsDates() {
        // Create events for the test
        Event event1 = new Event();
        event1.setLogistics(new HashSet<>());

        Event event2 = new Event();
        event2.setLogistics(new HashSet<>());

        List<Event> events = Arrays.asList(event1, event2);

        // Simulate the behavior of the event repository
        when(eventRepository.findByDateDebutBetween(any(LocalDate.class), any(LocalDate.class))).thenReturn(events);

        // Call the method to test
        List<Logistics> result = eventServices.getLogisticsDates(LocalDate.now(), LocalDate.now().plusDays(1));

          assertNotNull(result, "Result should not be null");

    // Verify that the result contains the expected number of elements
    assertEquals(0, result.size(), "Result should have two logistics");

    }
    @Test
    void testCalculCout() {
        // Create events for the test
        Event event1 = new Event();
        event1.setDescription("Event 1");
        event1.setLogistics(new HashSet<>());
    
        Event event2 = new Event();
        event2.setDescription("Event 2");
        event2.setLogistics(new HashSet<>());
    
        List<Event> events = Arrays.asList(event1, event2);
    
        // Simulate the behavior of the event repository
        when(eventRepository.findByParticipantsNomAndParticipantsPrenomAndParticipantsTache(
                ("Tounsi"), ("Ahmed"), (Tache.ORGANISATEUR))).thenReturn(events);
    
        // Simulate the behavior of the event repository and logistics
        // Use lenient mode for unnecessary stubbing
        lenient().when(eventRepository.save(any(Event.class)))
                .thenReturn(event1)
                .then(returnsFirstArg())
                .then(returnsFirstArg())
                .then(returnsFirstArg())
                .then(returnsFirstArg())
                .then(returnsFirstArg())
                .then(returnsFirstArg())
                .then(returnsFirstArg());
    
        when(logisticsRepository.save(any(Logistics.class))).thenReturn(new Logistics());
    
        // Call the method to test
        eventServices.calculCout();
    
        verify(eventRepository, times(2)).save(any(Event.class));

    // Verify that the logistics have been saved to the repository
    verify(logisticsRepository, times(2)).save(any(Logistics.class));

    }
    
}
