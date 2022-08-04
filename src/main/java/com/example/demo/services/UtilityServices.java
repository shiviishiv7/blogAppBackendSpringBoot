package com.example.demo.services;

import com.example.demo.aws.AWSUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UtilityServices {
    @Autowired
    private AWSUtility awsUtility;

    public ResponseEntity<String> putSignedUrl(String filename){
        String s = awsUtility.generatePresignedPutUrl(filename);
        return  ResponseEntity.ok(s);
    }
}
