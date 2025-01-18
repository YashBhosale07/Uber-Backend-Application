package in.yash.UberApplication.Security;
import in.yash.UberApplication.entities.User;
import in.yash.UberApplication.exceptions.InvalidAccessTokenException;
import in.yash.UberApplication.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private static final String[] PUBLIC_ROUTES = {"/auth/signUp", "/auth/login"};
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {

            String servletPath = request.getServletPath();
            for (String route : PUBLIC_ROUTES) {
                if (servletPath.equals(route)) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            String header = request.getHeader("Authorization");
            if (header == null || !header.startsWith("Bearer ")) {
                throw new InvalidAccessTokenException("Invalid or Missing Access Token");
            }
            String accessToken = header.substring(7);
            Long userId = jwtService.getUserIdFromToken(accessToken);
            if (userId == null) {
                throw new InvalidAccessTokenException("Invalid Token cannot fetch the username");
            }
            User user = userRepository.findById(userId).orElseThrow(() -> new InvalidAccessTokenException("Cannot find the user, Token is invalid"));
            if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }catch (Exception e ){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(e.getMessage());
            return;
        }
        filterChain.doFilter(request, response);


    }
}
