package mattia.susin.CAPBACK.repositories;

import mattia.susin.CAPBACK.entities.CopertiDisponibili;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CopertiDisponibiliRepository extends JpaRepository<CopertiDisponibili,UUID> {
}
