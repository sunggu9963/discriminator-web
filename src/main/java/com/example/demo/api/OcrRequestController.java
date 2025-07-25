package com.example.demo.api;

import com.example.demo.User.UserEntity;
import com.example.demo.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ocr")
public class OcrRequestController {
    private final ApiService apiService;
    private final UserRepository userRepository;


    @PostMapping("/upload")
    public ResponseEntity<Void> upload(@RequestParam("file") MultipartFile file, @RequestParam("title") String title, Principal principal) {
        //UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getName();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자 없음"));

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/"));

        apiService.fileUpload(file, title, user);

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

}
