package com.resto.pizzeria_api.service;

import com.resto.pizzeria_api.exception.ApiNotFoundException;
import com.resto.pizzeria_api.model.Status;
import com.resto.pizzeria_api.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusService {
  private final StatusRepository statusRepository;

  public List<Status> getAllStatuses() {
    return statusRepository.findAll();
  }

  public Status getStatusById(
      final Integer id
  ) throws ApiNotFoundException {
    return statusRepository.findById(id)
        .orElseThrow(() -> new ApiNotFoundException(
            "Statut n'a pas été trouvé"));
  }
}

