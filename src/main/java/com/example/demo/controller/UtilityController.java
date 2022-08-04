package com.example.demo.controller;

import com.example.demo.services.UtilityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilityController {

    @Autowired
    private UtilityServices utilityServices;

    @GetMapping("/imageUrl/{filename}")
    public ResponseEntity<?> getImageUrl(@PathVariable String filename){
        return utilityServices.putSignedUrl(filename);
    }

    @GetMapping("/videoUrl/{filename}")
    public ResponseEntity<?> getVideoUrl(@PathVariable String filename){
        return utilityServices.putSignedUrl(filename);
    }

}
