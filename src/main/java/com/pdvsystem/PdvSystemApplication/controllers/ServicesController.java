package com.pdvsystem.PdvSystemApplication.controllers;

import com.pdvsystem.PdvSystemApplication.entitys.PdvDTO;
import com.pdvsystem.PdvSystemApplication.repositories.PdvRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServicesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServicesController.class);

    @Autowired
    private PdvRepository pdvRepository;

    @GetMapping("/pdv/{id}")
    public PdvDTO consultPdv(@PathVariable("id") final int id) {
        LOGGER.info("Getting PDV with ID: " + id);
        return pdvRepository.findById(id);
    }

    @PostMapping("/pdv")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void createPdv(@RequestBody final PdvDTO pdvDTO) {
        pdvRepository.save(pdvDTO);
    }


}
