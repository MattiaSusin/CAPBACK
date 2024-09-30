package mattia.susin.CAPBACK.repositories;

import mattia.susin.CAPBACK.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientiRepository extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findById(UUID clienteId);
}

