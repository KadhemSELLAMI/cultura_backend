package com.culturascope.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.culturascope.api.model.Site;

public interface SiteRepository extends JpaRepository<Site, Long> {
	// List<Site> findByPublished(boolean published);
	List<Site> findByNameContaining(String name);
}
