package com.app.sawmills.controllers;

import com.app.sawmills.models.Sawmill;
import com.app.sawmills.services.SawmillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/sawmill")
public class SawmillController {

    SawmillService sawmillService;

    public  SawmillController(SawmillService sawmillService){
        this.sawmillService = sawmillService;
    }

    @PostMapping
    public ResponseEntity<Sawmill> saveSawmill(@Valid @NotNull @RequestBody Sawmill request) {
        Sawmill sawmill = sawmillService.insert(request);
        return new ResponseEntity<>(sawmill,  HttpStatus.CREATED);
    }

    @PatchMapping("/{sawmillId}")
    public ResponseEntity<?> updateSawmill(@PathVariable long sawmillId,@RequestBody Map<String,Object> changes){
        Sawmill sawmill = sawmillService.updateSawmill(sawmillId,changes);
        if(sawmill != null)
        return new ResponseEntity<>(sawmill,  HttpStatus.OK);

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Sawmill> getSawmill(@Valid @NotNull @PathVariable String name) {
        Sawmill sawmill = sawmillService.getSawmillByName(name);
        return new ResponseEntity<>(sawmill,  HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Sawmill>> getAllSawmill(@RequestParam(required = false) String name) {
        List<Sawmill> sawmills = sawmillService.getSawmills(name);
        return ResponseEntity.ok(sawmills);
    }



}
