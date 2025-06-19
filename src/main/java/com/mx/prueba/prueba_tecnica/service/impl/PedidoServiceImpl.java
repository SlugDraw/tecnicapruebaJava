package com.mx.prueba.prueba_tecnica.service.impl;

import com.mx.prueba.prueba_tecnica.documents.Cliente;
import com.mx.prueba.prueba_tecnica.documents.Pedidos;
import com.mx.prueba.prueba_tecnica.models.PedidosDTO;
import com.mx.prueba.prueba_tecnica.repository.ClienteRepository;
import com.mx.prueba.prueba_tecnica.repository.PedidosRepository;
import com.mx.prueba.prueba_tecnica.service.PedidosService;
import com.mx.prueba.prueba_tecnica.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl  implements PedidosService {

    private final PedidosRepository pedidosRepository;
    private final ClienteRepository clienteRepository;

    public PedidoServiceImpl(final PedidosRepository pedidosRepository,
                          final ClienteRepository clienteRepository) {
        this.pedidosRepository = pedidosRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<PedidosDTO> findAll() {
        final List<Pedidos> pedidoses = pedidosRepository.findAll(Sort.by("id"));
        return pedidoses.stream()
                .map(pedidos -> mapToDTO(pedidos, new PedidosDTO()))
                .toList();
    }

    @Override
    public PedidosDTO get(final String id) {
        return pedidosRepository.findById(id)
                .map(pedidos -> mapToDTO(pedidos, new PedidosDTO()))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public String create(final PedidosDTO pedidosDTO) {
        final Pedidos pedidos = new Pedidos();
        mapToEntity(pedidosDTO, pedidos);
        return pedidosRepository.save(pedidos).getId();
    }

    @Override
    public void update(final String id, final PedidosDTO pedidosDTO) {
        final Pedidos pedidos = pedidosRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(pedidosDTO, pedidos);
        pedidosRepository.save(pedidos);
    }

    @Override
    public void delete(final String id) {
        pedidosRepository.deleteById(id);
    }

    @Override
    public boolean idExists(final String id) {
        return pedidosRepository.existsByIdIgnoreCase(id);
    }


    private PedidosDTO mapToDTO(final Pedidos pedidos, final PedidosDTO pedidosDTO) {
        pedidosDTO.setId(pedidos.getId());
        pedidosDTO.setCodigo(pedidos.getCodigo());
        pedidosDTO.setCantidad(pedidos.getCantidad());
        pedidosDTO.setPrecio(pedidos.getPrecio());
        pedidosDTO.setCliente(pedidos.getCliente() == null ? null : pedidos.getCliente().getId());
        return pedidosDTO;
    }

    private void mapToEntity(final PedidosDTO pedidosDTO, final Pedidos pedidos) {
        pedidos.setCodigo(pedidosDTO.getCodigo());
        pedidos.setCantidad(pedidosDTO.getCantidad());
        pedidos.setPrecio(pedidosDTO.getPrecio());
        final Cliente cliente = pedidosDTO.getCliente() == null ? null : clienteRepository.findById(pedidosDTO.getCliente())
                .orElseThrow(() -> new NotFoundException("cliente not found"));
        pedidos.setCliente(cliente);
    }
}
