package mattia.susin.CAPBACK.entities;

import com.sun.jdi.PrimitiveValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import mattia.susin.CAPBACK.enums.TipoDrink;

import java.util.UUID;

@Entity
@Table(name = "drinks")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Drink {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
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
    private TipoDrink tipoDrink;

    //COSTRUTTORI




}
