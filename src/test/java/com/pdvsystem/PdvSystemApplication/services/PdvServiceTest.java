package com.pdvsystem.PdvSystemApplication.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.TypeRef;
import com.pdvsystem.PdvSystemApplication.entitys.CustomerPoint;
import com.pdvsystem.PdvSystemApplication.entitys.PdvDTO;
import com.pdvsystem.PdvSystemApplication.exceptions.OutOfAreaException;
import com.pdvsystem.PdvSystemApplication.repositories.PdvRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.core.StringContains.containsString;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PdvServiceTest {

    @Autowired
    private PdvRepository pdvRepository;

    @Autowired
    private PdvService pdvService;

    @Test
    public void getPdvById() throws IOException, URISyntaxException {
        final String json = readJsonFileAsString("mock/json/single-pdv.json");
        final ObjectMapper mapper = new ObjectMapper();
        final PdvDTO pdvDTO = mapper.readValue(json, PdvDTO.class);
        pdvService.savePdv(pdvDTO);
        final PdvDTO pdvSaved = pdvService.getPdvById(1);
        Assert.assertThat(pdvDTO.getDocument(), containsString(pdvSaved.getDocument()));
    }

    @Test
    public void getCloserPdv() throws IOException, URISyntaxException, OutOfAreaException {
        final String json = readJsonFileAsString("mock/json/list-pdv.json");
        final ObjectMapper mapper = new ObjectMapper();
        final List<PdvDTO> pdvDTO = mapper.readValue(json, new TypeReference<List<PdvDTO>>() {});
        pdvService.saveListOfPdv(pdvDTO);
        final PdvDTO pdvMoreClose = pdvService.getCloserPdv(getCustomerPoint());
        Assert.assertNotNull(pdvMoreClose);
    }

    private static String readJsonFileAsString(final String filename)
            throws URISyntaxException, IOException {
        return new String(Files.readAllBytes(
                Paths.get(
                        Objects.requireNonNull(PdvServiceTest.class.getClassLoader().getResource(filename)).toURI())));
    }

    private CustomerPoint getCustomerPoint() {
        final CustomerPoint customerPoint = new CustomerPoint();
        customerPoint.setLat(-45.9243);
        customerPoint.setLng(-23.24271);
        return customerPoint;
    }

}
