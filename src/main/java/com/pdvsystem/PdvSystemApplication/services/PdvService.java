package com.pdvsystem.PdvSystemApplication.services;

import com.mapbox.services.api.utils.turf.TurfMeasurement;
import com.mapbox.services.commons.geojson.MultiPolygon;
import com.mapbox.services.commons.geojson.Point;
import com.mapbox.services.commons.models.Position;
import com.pdvsystem.PdvSystemApplication.entitys.CustomerPoint;
import com.pdvsystem.PdvSystemApplication.entitys.PdvDTO;
import com.pdvsystem.PdvSystemApplication.exceptions.OutOfAreaException;
import com.pdvsystem.PdvSystemApplication.repositories.PdvRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PdvService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdvService.class);

    private static final String KM = "kilometers";

    @Autowired
    private PdvRepository pdvRepository;

    public PdvDTO getPdvById(final int id) {
        LOGGER.info("Getting PDV with ID: {}", id);
        return pdvRepository.findById(id);
    }

    public void savePdv(final PdvDTO pdv) {
        LOGGER.info("Saving PDV: {}", pdv.toString());
        pdvRepository.save(pdv);
    }

    public void saveListOfPdv(final List<PdvDTO> pdvs) {
        LOGGER.info("Saving PDV list");
        pdvRepository.saveAll(pdvs);
    }

    public PdvDTO getCloserPdv(final CustomerPoint customerPoint) throws OutOfAreaException {
        final List<PdvDTO> pdvs = (List<PdvDTO>) pdvRepository.findAll();
        final double lat = customerPoint.getLat();
        final double lng = customerPoint.getLng();
        final Map<PdvDTO, Double> info = new HashMap<>();
        pdvs.forEach(pdvDTO -> {
            double area = TurfMeasurement.lineDistance(MultiPolygon.fromCoordinates(pdvDTO.getCoverageArea()
                    .getCoordinates()), KM);
            double distance = TurfMeasurement.distance(Point.fromCoordinates(Position.fromCoordinates(lng, lat)),
                    Point.fromCoordinates(Position.fromCoordinates(pdvDTO.getAddress().getCoordinates()[1],
                            pdvDTO.getAddress().getCoordinates()[0])));
            if(area - distance >= 0 && distance <= area){
                info.put(pdvDTO, area - distance);
            }
        });
        return info.entrySet().stream().min(Map.Entry.comparingByValue()).orElseThrow(() ->
                new OutOfAreaException("No PDV for your area")).getKey();
    }

}
