package com.nexa.acceso.security;

import com.nexa.acceso.model.Sesion;
import com.nexa.acceso.repository.SesionRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final SesionRepository sesiones;
    public JwtAuthFilter(JwtService jwtService, SesionRepository sesiones) {
        this.jwtService=jwtService; this.sesiones=sesiones;
    }
    @Override protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain)
      throws ServletException,IOException {
        String header=request.getHeader("Authorization");
        if(header!=null && header.startsWith("Bearer ")) {
            try {
                Claims claims=jwtService.validar(header.substring(7));
                Long cuentaId=Long.valueOf(claims.getSubject());
                Long sesionId=claims.get("sid",Number.class).longValue();
                Sesion sesion=sesiones.findById(sesionId).orElseThrow();
                if(sesion.isActiva() && sesion.getCuenta().getId().equals(cuentaId) && sesion.getCuenta().isActiva()) {
                    String rol=claims.get("rol",String.class);
                    UsuarioAutenticado principal=new UsuarioAutenticado(cuentaId,sesionId,
                      com.nexa.acceso.model.Rol.valueOf(rol));
                    var auth=new UsernamePasswordAuthenticationToken(principal,null,
                      List.of(new SimpleGrantedAuthority("ROLE_"+rol)));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch(Exception ignored) {
                SecurityContextHolder.clearContext();
            }
        }
        chain.doFilter(request,response);
    }
}
