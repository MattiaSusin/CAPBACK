package mattia.susin.CAPBACK.controllers;

import mattia.susin.CAPBACK.entities.Admin;
import mattia.susin.CAPBACK.services.AdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admins")
public class AdminsController {

    // IMPORTI

    @Autowired
    private AdminsService adminsService;

    // METODI

    // 1 --> GET ALL

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Admin> findAll(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sortBy) {
        return this.adminsService.findAllAdmin(page, size, sortBy);
    }

    // 2 --> POST --> in AuthControllers

    // 3 --> GET ID
    @GetMapping("/{userId}")
    public Admin findByIdAdmin(@PathVariable UUID adminId) {
        return this.adminsService.findByIdAdmin(adminId);
    }

    // 4 --> PUT

    // 5 --> DELETE

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')") // Solo gli admin possono cancellare altri utenti
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDeleteAdmin(@PathVariable UUID adminId) {
        this.adminsService.findByIdAndDeleteAdmin(adminId);
    }

    // 6 --> FIND BY EMAIL

    // 7 --> SAVE

}
