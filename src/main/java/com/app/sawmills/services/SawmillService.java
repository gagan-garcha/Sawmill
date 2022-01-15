package com.app.sawmills.services;

import com.app.sawmills.models.Sawmill;

import java.util.List;
import java.util.Map;

public interface SawmillService {

    List<Sawmill> getSawmills(String query);

    Sawmill getSawmillByName(String name);

    Sawmill insert(Sawmill sawmill);

    Sawmill updateSawmill(Long id, Map<String,Object> sawmill);

}
