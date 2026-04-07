package com.resto.pizzeria_api.controller;

import com.resto.pizzeria_api.exception.ApiNotFoundException;
import com.resto.pizzeria_api.model.Client;
import com.resto.pizzeria_api.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

  private final ClientService clientService;

  // READ : Récupérer tous les clients (GET /api/clients)
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Client> getAllClients() {
    return clientService.getAllClients();
  }

  // READ : Récupérer un client précis (GET /api/clients/{id})
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Client getClientById(@PathVariable final Integer id) throws ApiNotFoundException {
    return clientService.getClientById(id);
  }

  // CREATE : Ajouter un nouveau client (POST /api/clients)
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED) // Optimisation RESTful appliquée ici
  public Client createClient(@RequestBody final Client client) {
    // Le client reçu en JSON n'a pas d'ID, la base de données va le générer
    return clientService.saveClient(client);
  }

  // UPDATE : Modifier un client existant (PUT /api/clients/{id})
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Client updateClient(
      @PathVariable final Integer id,
      @RequestBody final Client updatedClient
  ) throws ApiNotFoundException {

    // 1. On vérifie que le client existe (sinon ça lève l'exception)
    final Client existing = clientService.getClientById(id);

    // 2. On met à jour uniquement les champs modifiables
    existing.setFirstName(updatedClient.getFirstName());
    existing.setLastName(updatedClient.getLastName());

    // 3. On sauvegarde les modifications
    return clientService.saveClient(existing);
  }

  // DELETE : Supprimer un client (DELETE /api/clients/{id})
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT) // Code 204 : Action réussie, pas de contenu à renvoyer
  public void deleteClient(@PathVariable final Integer id) throws ApiNotFoundException {
    clientService.deleteClient(id);
  }
}