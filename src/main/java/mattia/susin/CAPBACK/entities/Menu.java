package mattia.susin.CAPBACK.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import mattia.susin.CAPBACK.enums.Ruolo;
import mattia.susin.CAPBACK.enums.TipoPiatto;

import java.util.UUID;

@Entity
@Table(name = "menu")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Menu {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private UUID id;

    @Column(name = "titolo")
    private String titolo;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "prezzo")
    private double prezzo;

    @Column(name = "immagine")
    private String immagine;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoPiatto tipoPiatto;

    public Menu(String titolo, String descrizione, String prezzo, String tipoPiatto) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.prezzo = Double.parseDouble(prezzo);
        this.tipoPiatto = TipoPiatto.valueOf(tipoPiatto);
    }

    // COSTRUTTORI

}