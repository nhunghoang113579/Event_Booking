
package com.example.eventBooking.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@NullMarked
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // 1. Lấy Authorization Header
        String authHeader = request.getHeader("Authorization");

        // 2. Không có JWT: để Spring Security xử lý
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Lấy JWT
        String token = authHeader.substring(7);

        try {
            // 4. Kiểm tra JWT
            if (!jwtService.isTokenValid(token)) {
                writeUnauthorizedResponse(response, "Invalid token");
                return;
            }

            // 5. Lấy email từ JWT
            String email = jwtService.extractEmail(token);

            // 6. Tìm User trong Database
            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(email);

            // 7. Tạo Authentication
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

            // 8. Đưa Authentication vào SecurityContext
            SecurityContextHolder.getContext()
                    .setAuthentication(authenticationToken);

        } catch (ExpiredJwtException e) {
            writeUnauthorizedResponse(
                    response,
                    "Token has expired"
            );
            return;

        } catch (Exception e) {
            SecurityContextHolder.clearContext();

            writeUnauthorizedResponse(
                    response,
                    "Invalid token"
            );
            return;
        }

        // 9. Cho request đi tiếp
        filterChain.doFilter(request, response);
    }

    // 10. Trả về lỗi 401 dạng JSON
    private void writeUnauthorizedResponse(
            HttpServletResponse response,
            String message
    ) throws IOException {

        SecurityContextHolder.clearContext();

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(
                "{\"success\":false,\"message\":\""
                        + message
                        + "\"}"
        );
    }
}
