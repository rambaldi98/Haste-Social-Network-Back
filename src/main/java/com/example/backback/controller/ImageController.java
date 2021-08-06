package com.example.backback.controller;

import com.example.backback.domain.entity.Image;
import com.example.backback.dto.request.UpdateAvatarRequest;
import com.example.backback.service.IImageService;
import com.example.backback.service.impl.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(value = "*",maxAge = 3600)
@RequestMapping("/profile")
public class ImageController {
    @Autowired
    private IImageService imageService;

    @GetMapping("/image")
    public ResponseEntity<?> pageImage(@PageableDefault(sort = "url", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Image> imagePage = imageService.findAll(pageable);
        if(imagePage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(imagePage, HttpStatus.OK);
    }

    @DeleteMapping("/deleteImage/{imgId}")
    private ResponseEntity<?> deleteImage(@PathVariable Long imgId){
        Optional<Image> image = imageService.findById(imgId);
        imageService.delete(image.get().getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addImage")
    private ResponseEntity<?> addImage(@RequestBody Image image){
        imageService.save(image);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PutMapping("/updateAvatar")
//    private ResponseEntity<?> updateAvatar(@RequestBody Image image){
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
