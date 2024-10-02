package mattia.susin.CAPBACK.repositories;

import mattia.susin.CAPBACK.entities.Drink;
import mattia.susin.CAPBACK.entities.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DrinksRepository extends JpaRepository<Drink, UUID> {
    Page<Drink> findAll(Pageable pageable);
}
