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

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "email")
    private String email;

    @Column(name = "numero_telefono")
    private String telefono;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "numero_Coperti")
    private int numeroCoperti;

    @Column(name = "orario")
    private double orario;

    @Column(name = "coperti_Disponibili")
    private int copertiDisponibili;

    @Column(name = "avatar")
    private String avatarURL;

    @ManyToOne()
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Prenotazione(String nome, String cognome, String email, String telefono, LocalDate data, int numeroCoperti, String orario, String avatarURL) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.telefono = String.valueOf(Integer.parseInt(telefono));
        this.data = data;
        this.numeroCoperti = numeroCoperti;
        this.orario = Double.parseDouble(orario);
    }


    public void getNumeroCoperti(int i) {
    }

    // COSTUTTORI
}
