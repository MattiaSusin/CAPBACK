package mattia.susin.CAPBACK.entities;

import jakarta.persistence.*;
import lombok.*;
import mattia.susin.CAPBACK.exceptions.BadRequestException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;


@Entity
@Table(name = "coperti_Disponibili")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class CopertiDisponibili {
    // Getter e Setter
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private UUID id;

    private LocalDate data;
    private int copertiDisponibili; // Solo un campo per il totale

    @ManyToOne
    @JoinColumn(name = "prenotazione_id")
    private Prenotazione prenotazione;

    public CopertiDisponibili(UUID id, LocalDate data, int copertiDisponibili) {
        this.id = id;
        this.data = data;
        this.copertiDisponibili = copertiDisponibili;
    }

    public void setCopertiDisponibili(int copertiDisponibili) {
        this.copertiDisponibili = copertiDisponibili;
    }

    // Metodo per scalare i coperti
    public void scalaCoperti(int numeroCoperti) {
        if (this.copertiDisponibili >= numeroCoperti) {
            this.copertiDisponibili -= numeroCoperti;
        } else {
            throw new BadRequestException("Coperti non sufficienti.");
        }
    }

    // Metodo per azzerare i coperti
    public void azzeraCoperti() {
        this.copertiDisponibili = 120; // o il numero massimo desiderato
    }
}