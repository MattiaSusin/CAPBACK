package mattia.susin.CAPBACK.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "admins")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Admin {
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

    @Column(name = "password")
    private String password;

    @Column(name = "avatar")
    private String avatarURL;

    public Admin(String nome, String cognome, String email, String password, String avatarURL) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.avatarURL = avatarURL;
    }

    // COSTRUTTORI

}
