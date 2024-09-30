package mattia.susin.CAPBACK.repositories;

import mattia.susin.CAPBACK.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClientiRepository extends JpaRepository<Cliente, UUID> {

    List<Cliente> findById(Long clienteId);
}

