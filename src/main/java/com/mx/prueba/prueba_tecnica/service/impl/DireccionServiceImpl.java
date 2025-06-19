package com.mx.prueba.prueba_tecnica.service.impl;

import com.mx.prueba.prueba_tecnica.documents.Cliente;
import com.mx.prueba.prueba_tecnica.documents.Direccion;
import com.mx.prueba.prueba_tecnica.models.DireccionDTO;
import com.mx.prueba.prueba_tecnica.repository.ClienteRepository;
import com.mx.prueba.prueba_tecnica.repository.DireccionRepository;
import com.mx.prueba.prueba_tecnica.service.DireccionService;
import com.mx.prueba.prueba_tecnica.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DireccionServiceImpl implements DireccionService {

    private final DireccionRepository direccionRepository;
    private final ClienteRepository clienteRepository;

    public DireccionServiceImpl(final DireccionRepository direccionRepository,
                            final ClienteRepository clienteRepository) {
        this.direccionRepository = direccionRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<DireccionDTO> findAll() {
        final List<Direccion> direccions = direccionRepository.findAll(Sort.by("id"));
        return direccions.stream()
                .map(direccion -> mapToDTO(direccion, new DireccionDTO()))
                .toList();
    }

    @Override
    public DireccionDTO get(final String id) {
        return direccionRepository.findById(id)
                .map(direccion -> mapToDTO(direccion, new DireccionDTO()))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public String create(final DireccionDTO direccionDTO) {
        final Direccion direccion = new Direccion();
        mapToEntity(direccionDTO, direccion);
        return direccionRepository.save(direccion).getId();
    }

    @Override
    public void update(final String id, final DireccionDTO direccionDTO) {
        final Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(direccionDTO, direccion);
        direccionRepository.save(direccion);
    }

    @Override
    public void delete(final String id) {
        direccionRepository.deleteById(id);
    }

    @Override
    public boolean idExists(final String id) {
        return direccionRepository.existsByIdIgnoreCase(id);
    }

    private DireccionDTO mapToDTO(final Direccion direccion, final DireccionDTO direccionDTO) {
        direccionDTO.setId(direccion.getId());
        direccionDTO.setCalle(direccion.getCalle());
        direccionDTO.setNumero(direccion.getNumero());
        direccionDTO.setColonia(direccion.getColonia());
        direccionDTO.setMunicipio(direccion.getMunicipio());
        direccionDTO.setCiudad(direccion.getCiudad());
        direccionDTO.setCliente(direccion.getCliente() == null ? null : direccion.getCliente().getId());
        return direccionDTO;
    }

    private void mapToEntity(final DireccionDTO direccionDTO, final Direccion direccion) {
        direccion.setCalle(direccionDTO.getCalle());
        direccion.setNumero(direccionDTO.getNumero());
        direccion.setColonia(direccionDTO.getColonia());
        direccion.setMunicipio(direccionDTO.getMunicipio());
        direccion.setCiudad(direccionDTO.getCiudad());
        final Cliente cliente = direccionDTO.getCliente() == null ? null : clienteRepository.findById(direccionDTO.getCliente())
                .orElseThrow(() -> new NotFoundException("cliente not found"));
        direccion.setCliente(cliente);
    }

}
