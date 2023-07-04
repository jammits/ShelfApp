package com.coderscampus.ShelfApp.Web;



import com.coderscampus.ShelfApp.DTO.VolumeResponse;
import com.coderscampus.ShelfApp.Services.GoogleBookApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoogleBookController {

    @Autowired
    GoogleBookApiService googleBookApiService;

    //Change to post request
    @GetMapping("/book")
    public ResponseEntity<VolumeResponse> getVolume(String searchTerm) {
        return googleBookApiService.getResponse(searchTerm, VolumeResponse.class);
    }
}
