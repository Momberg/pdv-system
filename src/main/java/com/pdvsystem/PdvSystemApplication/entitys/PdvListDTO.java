package com.pdvsystem.PdvSystemApplication.entitys;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.OneToMany;
import java.util.List;

public class PdvListDTO {

    @OneToMany
    @JsonProperty(value = "pdvs")
    private List<PdvDTO> pdvDTOS;

    public List<PdvDTO> getPdvDTOS() {
        return pdvDTOS;
    }

    public void setPdvDTOS(List<PdvDTO> pdvDTOS) {
        this.pdvDTOS = pdvDTOS;
    }
}
