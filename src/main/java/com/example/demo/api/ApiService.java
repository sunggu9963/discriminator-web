package com.example.demo.api;

import com.example.demo.Item.ItemDto;
import com.example.demo.User.UserEntity;
import com.example.demo.board.BoardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ApiService {

    private String ocrServerUrl;

    public void fileUpload(MultipartFile file ,String title, UserEntity user) {

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        ocrServerUrl = "http://localhost:3435/api/ocr/analyze";

        try{
            body.add("file", new MultipartInputStreamFileResource(file.getInputStream(), file.getOriginalFilename()));


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(ocrServerUrl, requestEntity, Map.class);

            Map<String, Object> responseBody = response.getBody();

            ItemDto itemDto = new ItemDto();
            itemDto.setModelSpec(responseBody.get("modelSpec").toString());
            itemDto.setSerialNumber(responseBody.get("serialNumber").toString());

            BoardRequestDto boardRequestDto = new BoardRequestDto();
            boardRequestDto.setTitle(title);
            boardRequestDto.setItem(itemDto);



        } catch (Exception e) {
        e.printStackTrace();
        }

    }
}
