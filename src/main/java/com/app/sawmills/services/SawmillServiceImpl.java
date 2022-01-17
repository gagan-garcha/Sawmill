package com.app.sawmills.services;

import com.app.sawmills.models.Sawmill;
import com.app.sawmills.repositories.SawmillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SawmillServiceImpl implements SawmillService{

    SawmillRepository sawmillRepository;

    public SawmillServiceImpl(SawmillRepository sawmillRepository){
        this.sawmillRepository=sawmillRepository;
    }

    @Override
    public List<Sawmill> getSawmills(String query) {
        if(query == null || query.isEmpty())
            return (List<Sawmill>) sawmillRepository.findAll();

        return sawmillRepository.findByNameContainingIgnoreCase(query);
    }

    @Override
    public Optional<Sawmill> getSawmillByName(String name) {
        return   Optional.ofNullable(sawmillRepository.findByNameIgnoreCase(name));
    }

    @Override
    public Sawmill insert(Sawmill sawmill) {
        return sawmillRepository.save(sawmill);
    }

    @Override
    public Optional<Sawmill> updateSawmill(Long id, Map<String,Object> changes) {

        Sawmill sawmill = sawmillRepository.findById(id).orElse(null);

        if(sawmill == null) return Optional.empty();

        changes.forEach((change, value) -> {
            switch (change) {
                case "name":
                    sawmill.setName((String) value);
                    break;
                case "city":
                    sawmill.setCity((String) value);
                    break;
                case "country":
                    sawmill.setCountry((String) value);
                    break;
                default:
                    break;
            }
        });

        return Optional.of(sawmillRepository.save(sawmill));

    }
}
