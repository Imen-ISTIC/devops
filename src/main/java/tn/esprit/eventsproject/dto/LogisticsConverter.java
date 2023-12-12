package tn.esprit.eventsproject.dto;

import tn.esprit.eventsproject.entities.Logistics;

public class LogisticsConverter {

    private LogisticsConverter() {
        throw new IllegalStateException("Utility class");
      }

    public static Logistics convertToEntity(LogisticsDTO logisticsDTO) {
        Logistics logistics = new Logistics();
        logistics.setDescription(logisticsDTO.getDescription());
        logistics.setReserve(logisticsDTO.isReserve());
        logistics.setPrixUnit(logisticsDTO.getPrixUnit());
        logistics.setQuantite(logisticsDTO.getQuantite());
        return logistics;
    }

    public static LogisticsDTO convertToDTO(Logistics logistics) {
        LogisticsDTO logisticsDTO = new LogisticsDTO();
        logisticsDTO.setDescription(logistics.getDescription());
        logisticsDTO.setReserve(logistics.isReserve());
        logisticsDTO.setPrixUnit(logistics.getPrixUnit());
        logisticsDTO.setQuantite(logistics.getQuantite());
        return logisticsDTO;
    }
}
