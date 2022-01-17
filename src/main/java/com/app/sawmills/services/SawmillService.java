package com.app.sawmills.services;

import com.app.sawmills.models.Sawmill;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SawmillService {

    List<Sawmill> getSawmills(String query);

    Optional<Sawmill> getSawmillByName(String name);

    Sawmill insert(Sawmill sawmill);

    Optional<Sawmill> updateSawmill(Long id, Map<String,Object> sawmill);

}
