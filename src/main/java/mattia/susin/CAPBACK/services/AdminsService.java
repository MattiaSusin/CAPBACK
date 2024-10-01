package mattia.susin.CAPBACK.services;

import mattia.susin.CAPBACK.entities.Admin;
import mattia.susin.CAPBACK.exceptions.NotFoundException;
import mattia.susin.CAPBACK.repositories.AdminsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminsService {

    // IMPORTI
    @Autowired
    private AdminsRepository adminsRepository;

    // METODI

    // 1 --> GET ALL

    public Page<Admin> findAllAdmin(int page, int size, String sortBy) {
        if (page > 100) page = 100;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.adminsRepository.findAll(pageable);
    }

    // 2 --> POST --> In AuthServices

    // 3 --> GET ID
    public Admin findByIdAdmin(UUID adminId) {
        return this.adminsRepository.findById(adminId).orElseThrow(() -> new NotFoundException(adminId));
    }
    // 4 --> PUT

    // 5 --> DELETE
    public void findByIdAndDeleteAdmin(UUID userId) {
       Admin found = this.findByIdAdmin(userId);
        this.adminsRepository.delete(found);
    }
    // 6 --> FIND BY EMAIL
    public Admin findByEmail(String email) {
        return adminsRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con l'email " + email + " non è stato trovato!"));
    }
    // 7 --> SAVE

   /* public Admin saveAdmin{
        // 1 --> Verifichiamo che la mail non sia stata utilizzata
        this.adminsRepository.f
    }*/
}

