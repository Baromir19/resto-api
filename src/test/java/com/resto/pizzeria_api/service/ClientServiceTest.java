package com.resto.pizzeria_api.service;

import com.resto.pizzeria_api.exception.ApiNotFoundException;
import com.resto.pizzeria_api.model.Client;
import com.resto.pizzeria_api.repository.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

  @Mock
  private ClientRepository clientRepository;

  @InjectMocks
  private ClientService clientService;

  @Test
  @DisplayName("getAllClients() doit retourner la liste complète des clients")
  void getAllClients_ShouldReturnListOfClients() {
    // --- ARRANGE ---
    Client client1 = new Client();
    client1.setFirstName("Mario");

    Client client2 = new Client();
    client2.setFirstName("Luigi");

    List<Client> expectedClients = Arrays.asList(client1, client2);

    when(clientRepository.findAll()).thenReturn(expectedClients);

    // --- ACT ---
    List<Client> actualClients = clientService.getAllClients();

    // --- ASSERT ---
    assertEquals(2, actualClients.size(), "La liste doit contenir 2 clients");
    assertEquals("Mario", actualClients.getFirst().getFirstName());
    verify(clientRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("getClientById() doit retourner le client si l'ID existe")
  void getClientById_WhenIdExists_ShouldReturnClient() throws ApiNotFoundException {
    // --- ARRANGE ---
    Integer clientId = 1;
    Client expectedClient = new Client();
    expectedClient.setId(clientId);
    expectedClient.setLastName("Rossi");

    when(clientRepository.findById(clientId)).thenReturn(Optional.of(expectedClient));

    // --- ACT ---
    Client actualClient = clientService.getClientById(clientId);

    // --- ASSERT ---
    assertNotNull(actualClient);
    assertEquals(clientId, actualClient.getId());
    assertEquals("Rossi", actualClient.getLastName());
    verify(clientRepository, times(1)).findById(clientId);
  }

  @Test
  @DisplayName("getClientById() doit lever une exception si l'ID n'existe pas")
  void getClientById_WhenIdDoesNotExist_ShouldThrowException() {
    // --- ARRANGE ---
    Integer clientId = 999;
    when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

    // --- ACT & ASSERT ---
    ApiNotFoundException exception = assertThrows(
        ApiNotFoundException.class,
        () -> clientService.getClientById(clientId)
    );

    // On vérifie que le message d'erreur est exactement celui défini dans votre service
    assertTrue(exception.getMessage().contains("Le client avec l'ID 999 n'a pas été trouvé."));
    verify(clientRepository, times(1)).findById(clientId);
  }

  @Test
  @DisplayName("saveClient() doit retourner le client sauvegardé")
  void saveClient_ShouldReturnSavedClient() {
    // --- ARRANGE ---
    Client newClient = new Client();
    newClient.setFirstName("Peppone");

    when(clientRepository.save(newClient)).thenReturn(newClient);

    // --- ACT ---
    Client savedClient = clientService.saveClient(newClient);

    // --- ASSERT ---
    assertNotNull(savedClient);
    assertEquals("Peppone", savedClient.getFirstName());
    verify(clientRepository, times(1)).save(newClient);
  }

  @Test
  @DisplayName("deleteClient() doit supprimer le client si l'ID existe")
  void deleteClient_WhenIdExists_ShouldDeleteSuccessfully() throws ApiNotFoundException {
    // --- ARRANGE ---
    Integer clientId = 1;
    when(clientRepository.existsById(clientId)).thenReturn(true);

    // --- ACT ---
    clientService.deleteClient(clientId);

    // --- ASSERT ---
    verify(clientRepository, times(1)).existsById(clientId);
    verify(clientRepository, times(1)).deleteById(clientId);
  }

  @Test
  @DisplayName("deleteClient() doit lever une exception si l'ID n'existe pas")
  void deleteClient_WhenIdDoesNotExist_ShouldThrowException() {
    // --- ARRANGE ---
    Integer clientId = 999;
    when(clientRepository.existsById(clientId)).thenReturn(false);

    // --- ACT & ASSERT ---
    ApiNotFoundException exception = assertThrows(
        ApiNotFoundException.class,
        () -> clientService.deleteClient(clientId)
    );

    assertTrue(exception.getMessage().contains("Impossible de supprimer"));

    verify(clientRepository, times(1)).existsById(clientId);
    // On s'assure que la méthode deleteById n'est jamais appelée pour éviter de faire planter la base
    verify(clientRepository, never()).deleteById(anyInt());
  }
}