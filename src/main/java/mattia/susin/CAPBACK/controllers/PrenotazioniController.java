package mattia.susin.CAPBACK.controllers;

import mattia.susin.CAPBACK.entities.Prenotazione;
import mattia.susin.CAPBACK.payloads.PrenotazioneDTO;
import mattia.susin.CAPBACK.payloads.PrenotazioneRespDTO;
import mattia.susin.CAPBACK.services.PrenotazioniService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {

    // IMPORTI

    private PrenotazioniService prenotazioniService;

    // METODI

    // 1 --> GET ALL

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','UTENTE')")
    public Page<Prenotazione> findAllPrenotazione(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "id") String sortBy) {
        return this.prenotazioniService.findAllPrenotazione(page, size, sortBy);
    }

    // 2 --> POST

    

    // 3 --> GET ID
    @GetMapping("/{prenotazioneId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','UTENTE')")
    public Prenotazione findIdPrenotazione(@PathVariable UUID clienteId) {
        return this.prenotazioniService.findIdPrenotazione(clienteId);
    }

    // 4 --> DELETE
    @DeleteMapping("/{clienteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findIdClienteAndDelete(@PathVariable UUID clienteId) {
        this.prenotazioniService.findIdClienteAndDelete(clienteId);
    }

    // 5 --> PUT

    @PutMapping("/prenotazioneId")
    @PreAuthorize("hasAuthority('ADMIN')")
    public PrenotazioneRespDTO findIdAndUpdatePrenotazione(@PathVariable UUID clienteId, @RequestBody @Validated PrenotazioneDTO body) {
        return this.prenotazioniService.findIdAndUpdatePrenotazione(clienteId, body);
    }

}
