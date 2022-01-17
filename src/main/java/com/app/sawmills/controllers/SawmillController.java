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
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sawmill")
public class SawmillController {

    SawmillService sawmillService;

    public  SawmillController(SawmillService sawmillService){
        this.sawmillService = sawmillService;
    }

    /**
     * A POST request to save a sawmill
     * @return sawmill object
     */
    @PostMapping
    public ResponseEntity<Sawmill> saveSawmill(@Valid @NotNull @RequestBody Sawmill request) {
        Sawmill sawmill = sawmillService.insert(request);
        return new ResponseEntity<>(sawmill,  HttpStatus.CREATED);
    }

    /**
     * PATCH request to update the sawmill
     * @return Updated sawmill
     */
    @PatchMapping("/{sawmillId}")
    public ResponseEntity<?> updateSawmill(@PathVariable long sawmillId,@RequestBody Map<String,Object> changes){
        return sawmillService.updateSawmill(sawmillId,changes).map(ResponseEntity::ok).orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    /**
     * Get request to find the sawmill by name
     * @return A sawmill object with matching name
     */
    @GetMapping("/{name}")
    public ResponseEntity<Sawmill> getSawmill(@Valid @NotNull @PathVariable String name) {
        return sawmillService.getSawmillByName(name).map(ResponseEntity::ok).orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    /**
     * Get request that filters the sawmill by given query if the query is not present
     * then it gives all sawmills
     * @return List of sawmill
     */
    @GetMapping
    public ResponseEntity<List<Sawmill>> getAllSawmill(@RequestParam(required = false) String name) {
        List<Sawmill> sawmills = sawmillService.getSawmills(name);
        return ResponseEntity.ok(sawmills);
    }



}
