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
        return sawmillRepository.findByNameLike(query);
    }

    @Override
    public Sawmill getSawmillByName(String name) {
        return   sawmillRepository.findByName(name);
    }

    @Override
    public Sawmill insert(Sawmill sawmill) {
        return sawmillRepository.save(sawmill);
    }

    @Override
    public Sawmill updateSawmill(Long id, Map<String,Object> changes) {

        Sawmill sawmill = sawmillRepository.findById(id).get();

        if(sawmill != null) {
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

            return sawmillRepository.save(sawmill);
        }

        return null;

    }
}
