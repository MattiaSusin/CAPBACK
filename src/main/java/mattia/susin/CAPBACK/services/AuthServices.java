package mattia.susin.CAPBACK.services;

import mattia.susin.CAPBACK.entities.Admin;
import mattia.susin.CAPBACK.exceptions.UnauthorizedException;
import mattia.susin.CAPBACK.payloads.AdminLoginDTO;
import mattia.susin.CAPBACK.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    // IMPORTI

    @Autowired
    private AdminsService adminsService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    // METODI

    public String checkCredentialsAndGenerateToken(AdminLoginDTO body) {
        // 1. Controllo le credenziali
        // 1.1 Cerco nel db tramite email se esiste l'utente
        Admin found = this.adminsService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())) {
            // 1.2 Se lo trovo verifico se la pw trovata combacia con quella passataci tramite body
            // 2. Se è tutto ok --> genero un access token e lo restituisco
            return jwtTools.createToken(found);
        } else {
            // 3. Se le credenziali sono errate --> 401 (Unauthorized)
            throw new UnauthorizedException("Credenziali errate!");
        }
        }
}
