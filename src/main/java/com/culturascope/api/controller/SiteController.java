package com.culturascope.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.culturascope.api.model.Site;
import com.culturascope.api.repository.SiteRepository;
import com.culturascope.api.service.FilesStorageService;
import com.culturascope.api.utils.FileUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class SiteController {

	@Autowired
	SiteRepository siteRepository;
	
	@Autowired
 	FilesStorageService storageService;

	@GetMapping("/sites")
	public ResponseEntity<List<Site>> getAllSites(@RequestParam(required = false) String name) {
		try {
			List<Site> sites = new ArrayList<Site>();

			if (name == null)
				siteRepository.findAll().forEach(sites::add);
			else
				siteRepository.findByNameContaining(name).forEach(sites::add);

			if (sites.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(sites, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/sites/{id}")
	public ResponseEntity<Site> getSiteById(@PathVariable("id") long id) {
		Optional<Site> siteData = siteRepository.findById(id);

		if (siteData.isPresent()) {
			return new ResponseEntity<>(siteData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/sites")
	public String createSite(
        @RequestParam("name") String name,
        @RequestParam("location") String location,
        @RequestParam("description") String description,
        @RequestParam("media") MultipartFile[] media
	) {
		try {
			List<String> mediaList = new ArrayList<>();

			Arrays.asList(media).forEach(file -> {
				storageService.save(file);
				mediaList.add(FileUtils.generateFileName(file.getOriginalFilename()));
			});

			// Convert list of file names to JSON string
			ObjectMapper objectMapper = new ObjectMapper();
			String mediaListJson = objectMapper.writeValueAsString(mediaList);

			// Save the site with the image file names JSON string
			Site _site = siteRepository.save(
					new Site(
						name,
						location,
						description,
						mediaListJson
					)
			);

			return "success";
		} catch (JsonProcessingException e) {
			// Handle JSON serialization exception
			return e.toString();
		} catch (Exception e) {
			return e.toString();
		}
	}

	@PutMapping("/sites/{id}")
	public ResponseEntity<Site> updateSite(@PathVariable("id") long id, @RequestBody Site site) {
		Optional<Site> siteData = siteRepository.findById(id);

		if (siteData.isPresent()) {
			Site _site = siteData.get();
			_site.setName(site.getName());
			_site.setLocation(site.getLocation());
			_site.setDescription(site.getDescription());
			return new ResponseEntity<>(siteRepository.save(_site), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/sites/{id}")
	public ResponseEntity<HttpStatus> deleteSite(@PathVariable("id") long id) {
		try {
			siteRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/sites")
	public ResponseEntity<HttpStatus> deleteAllSites() {
		try {
			siteRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
