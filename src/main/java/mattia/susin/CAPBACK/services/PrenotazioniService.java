package mattia.susin.CAPBACK.services;

import mattia.susin.CAPBACK.entities.Prenotazione;
import mattia.susin.CAPBACK.exceptions.BadRequestException;
import mattia.susin.CAPBACK.exceptions.NotFoundException;
import mattia.susin.CAPBACK.payloads.PrenotazioneDTO;
import mattia.susin.CAPBACK.payloads.PrenotazioneRespDTO;
import mattia.susin.CAPBACK.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PrenotazioniService {

    // IMPORTI
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    // METODI

    // 1 --> GET ALL
    public Page<Prenotazione> findAllPrenotazione(int page, int size, String sortBy) {
        if (page > 100) page = 100;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.prenotazioneRepository.findAll(pageable);
    }

    // 2 --> POST


    // 3 --> GET ID
    public Prenotazione findIdPrenotazione(UUID clienteId) {
        return prenotazioneRepository.findById(clienteId)
                .orElseThrow(() -> new NotFoundException("Cliente con id " + clienteId + " non trovato."));
    }
    // 4 --> DELETE
    public void findIdClienteAndDelete(UUID fattureId) {
        Prenotazione found = this.findIdPrenotazione(fattureId);
        try {
            this.prenotazioneRepository.delete(found);
        } catch (Exception e) {
            throw new BadRequestException("Errore nella cancellazione del cliente: " + e.getMessage());
        }
    }

    // 5 --> PUT
    public PrenotazioneRespDTO findIdAndUpdatePrenotazione(UUID prenotazioneId, PrenotazioneDTO newPrenotazioneData){
        Prenotazione found = this.findIdPrenotazione(prenotazioneId);

        found.setData(newPrenotazioneData.data());
        found.setOrario(Double.parseDouble(newPrenotazioneData.orario()));
        found.getNumeroCoperti(newPrenotazioneData.numeroCoperti());
        return new PrenotazioneRespDTO(this.prenotazioneRepository.save(found).getId());
    }

}
