package mattia.susin.CAPBACK.entities;


import jakarta.persistence.*;
import lombok.*;
import mattia.susin.CAPBACK.enums.Ruolo;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clienti")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "email")
    private String email;

    @Column(name = "numero_telefono")
    private int telefono;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

  /*  @OneToMany()
    private List<Prenotazione> prenotazione;*/

    // COSTRUTTORI

}