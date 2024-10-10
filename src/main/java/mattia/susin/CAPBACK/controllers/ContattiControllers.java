package mattia.susin.CAPBACK.controllers;

import mattia.susin.CAPBACK.exceptions.BadRequestException;
import mattia.susin.CAPBACK.payloads.ContattoDTO;
import mattia.susin.CAPBACK.payloads.ContattoRespDTO;
import mattia.susin.CAPBACK.services.ContattiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/contatti")
public class ContattiControllers {

    // IMPORTI

    @Autowired
    private ContattiService contattiService;

    // METODI

    // 1 --> POST

    @PostMapping()
    public ResponseEntity<String> sendEmailToAzienda(
            @RequestBody ContattoDTO contattoRichiestaDTO) {
        String response = contattiService.sendEmail(contattoRichiestaDTO);
        if (response.startsWith("Email inviata all'azienda")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}
