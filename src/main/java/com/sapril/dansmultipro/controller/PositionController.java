package com.sapril.dansmultipro.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.sapril.dansmultipro.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PositionController {
    @GetMapping("/get-list-position")
    public ResponseEntity<?> getListPosition(@RequestHeader("Authorization") String token)  {
        if (JwtUtil.validateToken(token)){
            String result = getResponseData(null);
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/get-position-by-id/{id}")
    public ResponseEntity<?> getPositionById(@RequestHeader("Authorization") String token, @PathVariable String id)  {
        if (JwtUtil.validateToken(token)){
            String result = getResponseData(id);
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private String getResponseData(String id){
        try {
            // The URL of the endpoint you want to send the GET request to
            String url = "http://dev3.dansmultipro.co.id/api/recruitment/positions";
            if (id != null)
                url += "/"+id;
            else
                url += ".json";

            // Create a URL object from the given URL string
            URL apiUrl = new URL(url);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Read the response content
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            // Close the connection
            connection.disconnect();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
