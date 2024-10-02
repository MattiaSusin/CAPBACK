package mattia.susin.CAPBACK.services;

import mattia.susin.CAPBACK.entities.Prenotazione;
import mattia.susin.CAPBACK.exceptions.BadRequestException;
import mattia.susin.CAPBACK.exceptions.NotFoundException;
import mattia.susin.CAPBACK.payloads.prenotazione.PrenotazioneDTO;
import mattia.susin.CAPBACK.payloads.prenotazione.PrenotazioneRespDTO;
import mattia.susin.CAPBACK.tools.MailgunSender;
import mattia.susin.CAPBACK.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PrenotazioniService {

    // IMPORTI
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private MailgunSender mailgunSender;

    // METODI

    // 1 --> GET ALL
    public Page<Prenotazione> findAllPrenotazione(int page, int size, String sortBy) {
        if (page > 100) page = 100;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.prenotazioneRepository.findAll(pageable);
    }

    // 2 --> POST

    public Prenotazione save(PrenotazioneDTO body) {
        // 1. Verifico che l'email non sia già stata utilizzata
        this.prenotazioneRepository.findByEmail(body.email()).ifPresent(
                // 1.1 Se lo è mando un errore --> 404
                user -> {
                    throw new BadRequestException("L'email " + body.email() + " è già in uso!");
                }
        );

        // 2. Se tutto è ok procedo con l'aggiungere campi 'server-generated' (avatarURL)

        Prenotazione newPrenotazione = new Prenotazione(body.nome(),body.cognome(),body.email(),body.telefono(),
                body.data(),body.numeroCoperti(),body.orario());

        // 3. Salvo lo User
        Prenotazione savedPrenotazione = this.prenotazioneRepository.save(newPrenotazione);

        // 4. Invio email conferma registrazione
        mailgunSender.sendRegistrationEmailPrenotazione(savedPrenotazione);

        return savedPrenotazione;
    }

    // 3 --> GET ID
    public Prenotazione findIdPrenotazione(UUID clienteId) {
        return prenotazioneRepository.findById(clienteId)
                .orElseThrow(() -> new NotFoundException("Cliente con id " + clienteId + " non trovato."));
    }

    // 4 --> DELETE
    public void findIdPrenotazioneAndDelete(UUID fattureId) {
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
        found.setNome(newPrenotazioneData.nome());
        found.setCognome(newPrenotazioneData.cognome());
        found.setEmail(newPrenotazioneData.email());
        found.setTelefono(newPrenotazioneData.telefono());
        found.setData(newPrenotazioneData.data());
        found.setOrario(Double.parseDouble(newPrenotazioneData.orario()));
        found.getNumeroCoperti(newPrenotazioneData.numeroCoperti());

        return new PrenotazioneRespDTO(this.prenotazioneRepository.save(found).getId());
    }

    // 6 --> SAVE

    public Prenotazione savePrenotazione(PrenotazioneDTO body){
        Prenotazione newPrenotazione = new Prenotazione();

        newPrenotazione.setNome(body.nome());
        newPrenotazione.setCognome(body.cognome());
        newPrenotazione.setEmail(body.email());
        newPrenotazione.setTelefono(body.telefono());
        newPrenotazione.setOrario(Double.parseDouble(body.orario()));
        newPrenotazione.setData(body.data());
        newPrenotazione.setNumeroCoperti(body.numeroCoperti());

        return this.prenotazioneRepository.save(newPrenotazione);
    }

    // 7 --> EMAIL

    public Prenotazione findByEmail(String email) {
        return prenotazioneRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con l'email " + email + " non è stato trovato!"));
    }

}
