package com.pdvsystem.PdvSystemApplication.repositories;

import com.pdvsystem.PdvSystemApplication.entitys.PdvDTO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "client", path = "client")
public interface PdvRepository extends PagingAndSortingRepository<PdvDTO, Integer> {

    PdvDTO findById(@Param("id") final int id);

}
