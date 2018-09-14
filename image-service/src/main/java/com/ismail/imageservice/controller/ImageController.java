package com.ismail.imageservice.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ismail.imageservice.entity.Image;
import com.ismail.imageservice.exception.ImageNotFoundException;
import com.ismail.imageservice.repository.ImageRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class ImageController {

	@Autowired
	ImageRepository imageRepository;

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/images")
	@ApiOperation("Just upload a image.")
	public Image uploadImage(@RequestPart(required = false) MultipartFile file, @RequestPart String name) {
		Image image = new Image();
		try {
			image.setName(name);
			image.setImage(Base64.getEncoder().encode(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageRepository.save(image);
	}

	@DeleteMapping(value = "/images/{name}")
	public void deleteImage(@PathVariable(value = "name") String name) {

		imageRepository.delete(imageRepository.findByName(name).orElseThrow(() -> new ImageNotFoundException(name)));
	}

	@CrossOrigin(origins = "*")
	@ApiOperation("Get image by name")
	@GetMapping(value = "/images/{name}")
	public ResponseEntity<byte[]> getImage(@PathVariable(value = "name") String name) throws IOException {
		ResponseEntity<byte[]> responseEntity;
		final HttpHeaders headers = new HttpHeaders();
		byte[] media = imageRepository.findByName(name).get().getImage();
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}
}
