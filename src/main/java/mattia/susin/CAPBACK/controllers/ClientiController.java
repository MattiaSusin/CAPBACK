package mattia.susin.CAPBACK.controllers;

import mattia.susin.CAPBACK.entities.Cliente;
import mattia.susin.CAPBACK.services.ClientiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/clienti")
public class ClientiController {

    //IMPORTI
    @Autowired
    private ClientiService clientiService;

    // METODI

    // 1 --> GET ALL
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Page<Cliente> findAllClienti(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "id") String sortBy) {
        return this.clientiService.findAllClienti(page, size, sortBy);
    }

    // 2 --> POST


    // 3 --> GET ID
    @GetMapping("/{clienteId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Cliente findIdCliente(@PathVariable UUID clienteId) {
        return this.clientiService.findIdCliente(clienteId);
    }

    // 4 --> DELETE
    @DeleteMapping("/{clienteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findIdClienteAndDelete(@PathVariable UUID clienteId) {
        this.clientiService.findIdClienteAndDelete(clienteId);
    }
}

