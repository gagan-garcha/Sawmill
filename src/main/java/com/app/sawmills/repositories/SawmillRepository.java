package com.app.sawmills.repositories;

import com.app.sawmills.models.Sawmill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SawmillRepository extends CrudRepository<Sawmill,Long> {

    Sawmill findByNameIgnoreCase (String name);

    List<Sawmill> findByNameContainingIgnoreCase(String query);

}
