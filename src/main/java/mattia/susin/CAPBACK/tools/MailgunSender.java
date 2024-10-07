package mattia.susin.CAPBACK.tools;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import mattia.susin.CAPBACK.entities.Prenotazione;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import kong.unirest.core.Unirest;



@Component
public class MailgunSender {
    private String apiKey;
    private String domainName;
    private String email;

    public MailgunSender(@Value("${mailgun.key}") String apiKey, @Value("${mailgun.domain}") String domainName,@Value("${mailgun.email}") String email) {
        this.apiKey = apiKey;
        this.domainName = domainName;
        this.email = email;

    }

    public void sendRegistrationEmailPrenotazione(Prenotazione recipient) {
        try {
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domainName + "/messages")
                .basicAuth("api", this.apiKey)
                .queryString("from", this.email)
                .queryString("to", recipient.getEmail())
                .queryString("subject", "Registrazione completata")
                .queryString("text", "Gentile" + recipient.getNome() +  recipient.getCognome() + ", la tua prenotazione presso Lounge&Restaurant è stata confermata!")
                .asJson();
        System.out.println(response.getBody()); // <- Stampo il messaggio in risposta per rilevare eventuali errori

        }catch (Exception exception){throw exception;}
    }
}
