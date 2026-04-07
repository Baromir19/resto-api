package com.resto.pizzeria_api.repository;

import com.resto.pizzeria_api.model.Status;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository
        extends JpaRepository<@NonNull Status, @NonNull Integer> { }
