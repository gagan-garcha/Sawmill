package com.app.sawmills.controllers;

import com.app.sawmills.models.Sawmill;
import com.app.sawmills.services.SawmillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/sawmill")
public class SawmillController {

    SawmillService sawmillService;

    public  SawmillController(SawmillService sawmillService){
        this.sawmillService = sawmillService;
    }

    @PostMapping
    public ResponseEntity<Sawmill> saveSawmill(@Valid @NotNull @RequestBody Sawmill sawmill) {
        Sawmill todo1 = sawmillService.insert(sawmill);
        return new ResponseEntity<>(todo1,  HttpStatus.CREATED);
    }

    @PatchMapping("/{sawmillId}")
    public ResponseEntity<?> updateSawmill(@PathVariable long sawmillId,@RequestBody Map<String,Object> changes){
        Sawmill sawmill = sawmillService.updateSawmill(sawmillId,changes);
        if(sawmill != null)
        return new ResponseEntity<>(sawmill,  HttpStatus.OK);

        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<Sawmill> getSawmill(@Valid @NotNull @RequestParam String name) {
        Sawmill todo1 = sawmillService.getSawmillByName(name);
        return new ResponseEntity<>(todo1,  HttpStatus.OK);
    }



}
