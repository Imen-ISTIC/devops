package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.entities.Tache;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EventServicesImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private LogisticsRepository logisticsRepository;

    @InjectMocks
    private EventServicesImpl eventServices;

    @Test
    void testAddAffectEvenParticipant() {
        // Créer un participant fictif pour le test
        Participant participant = new Participant();
        participant.setIdPart(1);

        // Créer un événement fictif pour le test
        Event event = new Event();
        event.setIdEvent(1);

        // Simuler le comportement du repository participant
        when(participantRepository.findById(1)).thenReturn(java.util.Optional.of(participant));

        // Appeler la méthode à tester
        Event result = eventServices.addAffectEvenParticipant(event, 1);

        // Vérifier que la méthode save du repository a été appelée
        verify(eventRepository).save(event);

        // Vérifier que l'événement a été ajouté à la liste d'événements du participant
        assertTrue(participant.getEvents().contains(event));
    }

    @Test
    void testAddAffectLog() {
        // Créer un événement fictif pour le test
        Event event = new Event();
        event.setDescription("Test Event");

        // Créer un objet Logistics fictif pour le test
        Logistics logistics = new Logistics();
        logistics.setId(1);
        logistics.setReserve(true);

        // Simuler le comportement du repository event
        when(eventRepository.findByDescription("Test Event")).thenReturn(event);

        // Appeler la méthode à tester
        Logistics result = eventServices.addAffectLog(logistics, "Test Event");

        // Vérifier que la méthode save du repository logistics a été appelée
        verify(logisticsRepository).save(logistics);

        // Vérifier que la logistique a été ajoutée à la liste de logistiques de l'événement
        assertTrue(event.getLogistics().contains(logistics));
    }

    // Ajoutez d'autres tests selon les besoins
}
