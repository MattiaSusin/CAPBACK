package mattia.susin.CAPBACK.services;

import mattia.susin.CAPBACK.entities.Cliente;
import mattia.susin.CAPBACK.exceptions.BadRequestException;
import mattia.susin.CAPBACK.exceptions.NotFoundException;

import mattia.susin.CAPBACK.repositories.ClientiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientiService {

    // IMPORTI
    @Autowired
    private ClientiRepository clientiRepository;

    // METODI

    // 1 --> GET ALL
    public Page<Cliente> findAllClienti(int page, int size, String sortBy) {
        if (page > 100) page = 100;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.clientiRepository.findAll(pageable);
    }

    // 2 --> POST


    // 3 --> GET ID
    public Cliente findIdCliente(UUID clienteId) {
        return clientiRepository.findById(clienteId)
                .orElseThrow(() -> new NotFoundException("Cliente con id " + clienteId + " non trovato."));
    }
    // 4 --> DELETE
    public void findIdClienteAndDelete(UUID fattureId) {
        Cliente found = this.findIdCliente(fattureId);
        try {
            this.clientiRepository.delete(found);
        } catch (Exception e) {
            throw new BadRequestException("Errore nella cancellazione del cliente: " + e.getMessage());
        }
    }
    // 5 --> SAVE
}
