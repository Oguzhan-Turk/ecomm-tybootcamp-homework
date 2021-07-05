package com.tybootcamp.ecomm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tybootcamp.ecomm.entities.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
