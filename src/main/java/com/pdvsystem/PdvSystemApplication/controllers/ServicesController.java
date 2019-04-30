package com.pdvsystem.PdvSystemApplication.controllers;

import com.pdvsystem.PdvSystemApplication.entitys.PdvDTO;
import com.pdvsystem.PdvSystemApplication.repositories.PdvRepository;
import com.pdvsystem.PdvSystemApplication.services.Haversine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServicesController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ServicesController.class);

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
    final PdvDTO pdvDTO = pdvRepository.findById(1);
    return Haversine.distance(latitude, longitude, pdvDTO.getAddress().getCoordinates()[0],
      pdvDTO.getAddress().getCoordinates()[1]);
//    return null;
  }

  @PostMapping("/pdv")
  @ResponseStatus(HttpStatus.CREATED)
  public void createPdv(@RequestBody final PdvDTO pdv) {
    pdvRepository.save(pdv);
  }

}
