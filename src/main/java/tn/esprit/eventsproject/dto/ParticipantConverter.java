package tn.esprit.eventsproject.dto;

import tn.esprit.eventsproject.entities.Participant;


public class ParticipantConverter {

    private ParticipantConverter() {
        throw new IllegalStateException("Utility class");
      }

    public static Participant convertToParticipant(ParticipantDTO participantDTO) {
        Participant participant = new Participant();
        participant.setNom(participantDTO.getNom());
        participant.setPrenom(participantDTO.getPrenom());
        participant.setTache(participantDTO.getTache());
        // Set other properties based on your needs
        return participant;
    }

    public static ParticipantDTO convertToParticipantDTO(Participant participant) {
        ParticipantDTO participantDTO = new ParticipantDTO();
        participantDTO.setNom(participant.getNom());
        participantDTO.setPrenom(participant.getPrenom());
        participantDTO.setTache(participant.getTache());
        // Set other properties based on your needs
        return participantDTO;
    }
}
