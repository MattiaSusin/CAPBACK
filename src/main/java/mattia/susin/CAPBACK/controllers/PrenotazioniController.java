package mattia.susin.CAPBACK.controllers;

import mattia.susin.CAPBACK.entities.Prenotazione;
import mattia.susin.CAPBACK.exceptions.BadRequestException;
import mattia.susin.CAPBACK.payloads.prenotazione.PrenotazioneDTO;
import mattia.susin.CAPBACK.payloads.prenotazione.PrenotazioneRespDTO;
import mattia.susin.CAPBACK.services.PrenotazioniService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {

    // IMPORTI

    private PrenotazioniService prenotazioniService;

    // METODI

    // 1 --> GET ALL

    @GetMapping
    public Page<Prenotazione> findAllPrenotazione(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "id") String sortBy) {
        return this.prenotazioniService.findAllPrenotazione(page, size, sortBy);
    }

    // 2 --> POST/SAVE
    @PostMapping("/crea")
    @PreAuthorize("hasAnyAuthority('UTENTE','ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public PrenotazioneRespDTO save(@RequestBody @Validated PrenotazioneDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));

            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        } else {
            return new PrenotazioneRespDTO(this.prenotazioniService.savePrenotazione(body).getId());
        }
    }

    // 3 --> GET ID
    @GetMapping("/{prenotazioneId}")
    public Prenotazione findIdPrenotazione(@PathVariable UUID prenotazioneId) {
        return this.prenotazioniService.findIdPrenotazione(prenotazioneId);
    }

    // 4 --> DELETE
    @DeleteMapping("/{prenotazioneId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findIdClienteAndDelete(@PathVariable UUID prenotazioneId) {
        this.prenotazioniService.findIdPrenotazioneAndDelete(prenotazioneId);
    }

    // 5 --> PUT

    @PutMapping("/prenotazioneId")
    @PreAuthorize("hasAuthority('ADMIN')")
    public PrenotazioneRespDTO findIdAndUpdatePrenotazione(@PathVariable UUID clienteId, @RequestBody @Validated PrenotazioneDTO body) {
        return this.prenotazioniService.findIdAndUpdatePrenotazione(clienteId, body);
    }

    // 7 --> FIND BY EMAIL --> prenotazioneRepository



}
