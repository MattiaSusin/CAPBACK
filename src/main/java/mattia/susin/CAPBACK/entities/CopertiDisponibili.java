package mattia.susin.CAPBACK.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "coperti_Disponibili")
@Setter
@Getter
@NoArgsConstructor(force = true)
@ToString
public class CopertiDisponibili {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private UUID id;

   @Column(name = "coperti_Massimi")
   private static final int copertiMax = 120;

   @Column(name = "giorni_Settimana")
    private static final int giorniSettimana = 7;

   @Column(name = "coperti_Disponibili")
   private int[] copertiDisponibili;

    @ManyToOne
    @JoinColumn(name = "prenotazione")
    private Prenotazione prenotazione;


    public CopertiDisponibili(int copertiDisponibili) {
        this.copertiDisponibili = new int[]{copertiDisponibili};
    }
}
