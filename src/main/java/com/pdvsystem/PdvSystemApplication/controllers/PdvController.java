package com.pdvsystem.PdvSystemApplication.controllers;

import com.pdvsystem.PdvSystemApplication.entitys.CustomerPoint;
import com.pdvsystem.PdvSystemApplication.entitys.PdvDTO;
import com.pdvsystem.PdvSystemApplication.exceptions.OutOfAreaException;
import com.pdvsystem.PdvSystemApplication.services.PdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PdvController {

  @Autowired
  private PdvService pdvService;

  @GetMapping("/pdv/{id}")
  public PdvDTO consultPdv(@PathVariable("id") final int id) {
    return pdvService.getPdvById(id);
  }

  @GetMapping("/pdv")
  public PdvDTO getCloserPdv(@RequestBody CustomerPoint customerPoint) throws OutOfAreaException {
    return pdvService.getCloserPdv(customerPoint);
  }

  @PostMapping("/pdv")
  @ResponseStatus(HttpStatus.CREATED)
  public void createPdv(@RequestBody final PdvDTO pdv) {
    pdvService.savePdv(pdv);
  }

  @PostMapping("/pdv/list")
  @ResponseStatus(HttpStatus.CREATED)
  public void createPdvFromList(@RequestBody final List<PdvDTO> pdvs) {
      pdvService.saveListOfPdv(pdvs);
  }

}
