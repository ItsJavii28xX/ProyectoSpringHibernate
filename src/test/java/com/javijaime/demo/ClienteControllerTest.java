package com.javijaime.demo;

import com.javijaime.demo.controller.ClienteController;
import com.javijaime.demo.model.Cliente;
import com.javijaime.demo.repository.ClienteRepository;
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
public class ClienteControllerTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteController clienteController;

    @Test
    public void getAllClientes_deberiaDevolverListaDeClientes() {
        List<Cliente> clientes = Arrays.asList(new Cliente(), new Cliente());
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> resultado = clienteController.getAllClientes();

        assertEquals(clientes, resultado);
    }

    @Test
    public void getClienteById_deberiaDevolverClientePorId() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente resultado = clienteController.getClienteById(1L);

        assertEquals(cliente, resultado);
    }

    @Test
    public void createCliente_deberiaCrearNuevoCliente() {
        Cliente cliente = new Cliente();
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente resultado = clienteController.createCliente(cliente);

        assertEquals(cliente, resultado);
    }

}
