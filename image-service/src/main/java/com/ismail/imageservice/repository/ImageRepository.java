package com.ismail.imageservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ismail.imageservice.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	
	public Optional<Image> findByName(String name);
}
