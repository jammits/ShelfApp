package com.coderscampus.ShelfApp.Web;



import com.coderscampus.ShelfApp.DTO.VolumeResponse;
import com.coderscampus.ShelfApp.Services.GoogleBookApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GoogleBookController {

    @Autowired
    GoogleBookApiService googleBookApiService;

    //Change to post request
    @PostMapping("/googlebook")
    public ResponseEntity<VolumeResponse> getVolume(@RequestBody String searchTerm) {
        return googleBookApiService.getResponse(searchTerm, VolumeResponse.class);
    }
}
