package mattia.susin.CAPBACK.payloads.prenotazione;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PrenotazioneDTO(
        @NotEmpty(message = "Campo obbligatorio. Inserire nome.")
        @Size(min = 3, max = 30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
        String nome,
        @NotEmpty(message = "Campo obbligatorio. Inserire cognome.")
        @Size(min = 3, max = 30, message = "Il cognome deve essere compreso tra 3 e 30 caratteri")
        String cognome,
        @Size(min = 3, max = 30, message = "L'email deve essere compreso tra 3 e 30 caratteri")
        @Email(message = "Inserire una email corretta.")
        String email,
        @NotEmpty(message = "Campo obbligatorio. Inserire password.")
        @Size(min = 3, max = 30, message = "La password deve essere compreso tra 3 e 30 caratteri")
        String password,
        @NotNull(message = "Inserire il numero di telefono")
        @Size(min = 3, max = 10, message = "Il numero di telefono deve essere di 9 cifre")
        String telefono,
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
