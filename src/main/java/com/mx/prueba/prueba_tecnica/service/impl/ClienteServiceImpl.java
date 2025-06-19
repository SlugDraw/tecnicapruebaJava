package com.mx.prueba.prueba_tecnica.service.impl;

import com.mx.prueba.prueba_tecnica.documents.Cliente;
import com.mx.prueba.prueba_tecnica.documents.Direccion;
import com.mx.prueba.prueba_tecnica.documents.Pedidos;
import com.mx.prueba.prueba_tecnica.models.ClienteDTO;
import com.mx.prueba.prueba_tecnica.repository.ClienteRepository;
import com.mx.prueba.prueba_tecnica.repository.DireccionRepository;
import com.mx.prueba.prueba_tecnica.repository.PedidosRepository;
import com.mx.prueba.prueba_tecnica.service.ClienteService;
import com.mx.prueba.prueba_tecnica.util.NotFoundException;
import com.mx.prueba.prueba_tecnica.util.ReferencedWarning;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final DireccionRepository direccionRepository;
    private final PedidosRepository pedidosRepository;

    public ClienteServiceImpl(final ClienteRepository clienteRepository,
                          final DireccionRepository direccionRepository,
                          final PedidosRepository pedidosRepository) {
        this.clienteRepository = clienteRepository;
        this.direccionRepository = direccionRepository;
        this.pedidosRepository = pedidosRepository;
    }

    @Override
    public List<Cliente> findAll() {
        final List<Cliente> clientes = clienteRepository.findAll(Sort.by("id"));
        return clientes.stream().toList();
    }

    @Override
    public Cliente get(final String id) {
        return clienteRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public String create(final ClienteDTO clienteDTO) {
        final Cliente cliente = new Cliente();
        mapToEntity(clienteDTO, cliente);
        return clienteRepository.save(cliente).getId();
    }

    @Override
    public void update(final String id, final ClienteDTO clienteDTO) {
        final Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(clienteDTO, cliente);
        clienteRepository.save(cliente);
    }

    @Override
    public void delete(final String id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public boolean idExists(final String id) {
        return clienteRepository.existsByIdIgnoreCase(id);
    }

    @Override
    public ReferencedWarning getReferencedWarning(final String id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Direccion clienteDireccion = direccionRepository.findFirstByClienteId(id);

        if (clienteDireccion != null) {
            referencedWarning.setKey("cliente.direccion.cliente.referenced");
            referencedWarning.addParam(clienteDireccion.getId());
            return referencedWarning;
        }
        final Pedidos clientePedidos = pedidosRepository.findFirstByClienteId(id);
        if (clientePedidos != null) {
            referencedWarning.setKey("cliente.pedidos.cliente.referenced");
            referencedWarning.addParam(clientePedidos.getId());
            return referencedWarning;
        }
        return null;
    }

    private ClienteDTO mapToDTO(final Cliente cliente, final ClienteDTO clienteDTO) {
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setApellidoMaterno(cliente.getApellidoMaterno());
        clienteDTO.setApellidoPaterno(cliente.getApellidoPaterno());
        clienteDTO.setCorreo(cliente.getCorreo());
        System.out.println(cliente.getDirecciones());

        return clienteDTO;
    }

    private void mapToEntity(final ClienteDTO clienteDTO, final Cliente cliente) {
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellidoMaterno(clienteDTO.getApellidoMaterno());
        cliente.setApellidoPaterno(clienteDTO.getApellidoPaterno());
        cliente.setCorreo(clienteDTO.getCorreo());
    }

}

