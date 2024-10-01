package mattia.susin.CAPBACK.repositories;

import mattia.susin.CAPBACK.entities.Admin;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminsRepository extends JpaRepository<Admin, UUID> {
    Optional<Admin> findByEmail(String email);
}
