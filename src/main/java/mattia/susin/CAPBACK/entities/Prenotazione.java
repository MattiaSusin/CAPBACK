package mattia.susin.CAPBACK.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Prenotazione {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "numero_Coperti")
    private int numeroCoperti;

    @Column(name = "orario")
    private double orario;

    @Column(name = "coperti_Disponibili")
    private int copertiDisponibili;

    @ManyToOne()
    @JoinColumn(name = "cliente_id")
    private Cliente clienteId;

    public void getNumeroCoperti(int i) {
    }

    @Getter
    @Setter
    private Cliente cliente;

    // COSTUTTORI
}
