package mattia.susin.CAPBACK.payloads;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record PrenotazioneDTO(
        @NotEmpty(message = "Campo obbligatorio. Inserisci la data della prenotazione")
        LocalDate data,
        @NotEmpty(message = "Campo obbligatorio. Inserisci l'orario di arrivo della prenotazione")
        String orario,
        @NotEmpty(message = "Campo obbligatorio. Inserisci il numero di persone")
        int numeroCoperti,
        @NotEmpty()
        int copertiDisponibili
) {
}
