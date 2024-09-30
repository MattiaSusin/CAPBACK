package mattia.susin.CAPBACK.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record AdminDTO(
        @Size(min = 3, max = 30, message = "L'email deve essere compreso tra 3 e 30 caratteri")
        @Email(message = "Inserire una email corretta.")
        String email,
        @NotEmpty(message = "Campo obbligatorio. Inserire password.")
        @Size(min = 3, max = 30, message = "La password deve essere compreso tra 3 e 30 caratteri")
        String password
) {
}
