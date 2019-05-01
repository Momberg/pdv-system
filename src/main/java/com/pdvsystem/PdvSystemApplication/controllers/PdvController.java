package com.pdvsystem.PdvSystemApplication.controllers;

import com.mapbox.services.api.utils.turf.TurfMeasurement;
import com.mapbox.services.commons.geojson.MultiPolygon;
import com.mapbox.services.commons.geojson.Point;
import com.mapbox.services.commons.models.Position;
import com.pdvsystem.PdvSystemApplication.entitys.PdvDTO;
import com.pdvsystem.PdvSystemApplication.repositories.PdvRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PdvController {

  private static final Logger LOGGER = LoggerFactory.getLogger(PdvController.class);

  @Autowired
  private PdvRepository pdvRepository;

  @GetMapping("/pdv/{id}")
  public PdvDTO consultPdv(@PathVariable("id") final int id) {
    LOGGER.info("Getting PDV with ID: {}", id);
    return pdvRepository.findById(id);
  }

  @GetMapping("/pdv")
  public Double getCloserPdv(@RequestParam("latitude") final double latitude,
    @RequestParam("longitude") final double longitude) {
    final List<PdvDTO> pdvs = (List<PdvDTO>) pdvRepository.findAll();
    final TurfMeasurement turfMeasurement = new TurfMeasurement();
    double area = turfMeasurement.lineDistance(MultiPolygon.fromCoordinates(pdvs.get(0).getCoverageArea().getCoordinates()),
            "kilometers");
    double distance = turfMeasurement.distance(Point.fromCoordinates(Position.fromCoordinates(longitude, latitude)),
            Point.fromCoordinates(Position.fromCoordinates(pdvs.get(0).getAddress().getCoordinates()[1], pdvs.get(0).getAddress().getCoordinates()[0])));
    return area - distance;
  }

  @PostMapping("/pdv")
  @ResponseStatus(HttpStatus.CREATED)
  public void createPdv(@RequestBody final PdvDTO pdv) {
    pdvRepository.save(pdv);
  }

}
