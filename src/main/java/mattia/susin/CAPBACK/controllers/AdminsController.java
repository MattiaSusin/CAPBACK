package mattia.susin.CAPBACK.controllers;

import mattia.susin.CAPBACK.entities.Admin;
import mattia.susin.CAPBACK.services.AdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AdminsController {

    // IMPORTI

    @Autowired
    private AdminsService adminsService;

    // METODI

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Admin> findAll(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sortBy) {
        return this.adminsService.findAllAdmin(page, size, sortBy);
    }

}
