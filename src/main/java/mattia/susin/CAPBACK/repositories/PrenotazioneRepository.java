package mattia.susin.CAPBACK.repositories;

import mattia.susin.CAPBACK.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione , UUID> {

}
