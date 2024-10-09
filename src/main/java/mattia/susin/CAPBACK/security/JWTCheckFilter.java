package mattia.susin.CAPBACK.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mattia.susin.CAPBACK.entities.Admin;
import mattia.susin.CAPBACK.exceptions.UnauthorizedException;
import mattia.susin.CAPBACK.services.AdminsService;
import mattia.susin.CAPBACK.services.PrenotazioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTCheckFilter extends OncePerRequestFilter {
	@Autowired
	private JWTTools jwtTools;
	@Autowired
	private AdminsService adminsService;


	// ADMIN

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		// 1. Verifichiamo se nella richiesta c'è effettivamente l'Authorization Header, se non c'è --> 401
		String authHeader = request.getHeader("Authorization");
		if (authHeader == null || !authHeader.startsWith("Bearer "))
			throw new UnauthorizedException("Per favore inserisci correttamente il token nell'Authorization Header");

		// 2. Se c'è estraiamo il token dall'header
		String accessToken = authHeader.substring(7);

		jwtTools.verifyToken(accessToken);



		// 4.1 Cerco l'utente tramite id (l'id sta nel token)
		String id = jwtTools.extractIdFromToken(accessToken);
		Admin currentUser = this.adminsService.findByIdAdmin(UUID.fromString(id));

		// 4.2 Trovato l'utente posso associarlo al Security Context, praticamente è come associare l'utente autenticato alla richiesta corrente
		Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, null, currentUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication); // <-- Associo l'utente autenticato (Autentication) al Context

		// 4.3 Andiamo avanti
		filterChain.doFilter(request, response);

		// 5. Se il token non è ok --> 401
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		// Fare l'override di questo metodo ci serve per 'disabilitare' questo filtro per alcune richieste,
		// ad esempio richieste su determinati endpoint oppure direttamente su determinati controller
		// Nel nostro caso ad esempio ci interessa che il filtro, che dovrà verificare i token, non venga chiamato in causa
		// per tutte le richieste di Login o di Register perché sono richieste che non devono richiedere un token per poter essere eseguite
		// Se gli endpoint di Login e Register si trovano nello stesso controller avranno lo stesso URL di base "http://localhost:3001/auth/**"

		// Posso quindi escludere dal controllo del filtro tutte le richieste verso gli endpoint che contengono /auth nell'URL
		return new AntPathMatcher().match("/auth/**", request.getServletPath())||
		new AntPathMatcher().match("/menu/view/**", request.getServletPath())||
		new AntPathMatcher().match("/drinks/view/**", request.getServletPath());
	}
}
