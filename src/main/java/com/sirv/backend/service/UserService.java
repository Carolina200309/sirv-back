package com.sirv.backend.service;

import com.sirv.backend.EndUserException;
import com.sirv.backend.config.security.jwt.JwtUtils;
import com.sirv.backend.dto.model.UserDTO;
import com.sirv.backend.dto.request.LoginRequest;
import com.sirv.backend.dto.request.RegisterRequest;
import com.sirv.backend.model.User;
import com.sirv.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

    private final JwtUtils jwtUtils;

    private User mapToModel(UserDTO userDTO) {
        User user = new User();

        user.setId(userDTO.getId());
        user.setNombre(userDTO.getNombre());
        user.setAddress(userDTO.getAddress());
        user.setTelephone(userDTO.getTelephone());
        user.setDireccion_residencia(userDTO.getDireccion_residencia());

        return user;
    }

    private UserDTO mapToDTO(User user) {
        return  new UserDTO(user.getId(),user.getNombre(), user.getDate_registered(),user.getAddress(),user.getDireccion_residencia(),user.getTelephone());
    }

    public User createUser(RegisterRequest request, boolean isAdmin) throws EndUserException {
        if (userRepository.existsByNombre(request.nombre())) {
            throw new EndUserException("Ya existe un usuario con ese nombre");
        }

        User user = new User();
        user.setNombre(request.nombre());
        user.setAddress(request.address());
        user.setTelephone(request.telephone());
        user.setDate_registered(LocalDate.now());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setDireccion_residencia(request.direccion_residencia());

        if (isAdmin) user.setTipo(User.Tipo.ADMINISTRADOR);

        userRepository.save(user);

        return user;
    }


    public String loginUser(LoginRequest request) throws EndUserException {
        try {
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.nombre(), request.password()));

            User user = (User) authentication.getPrincipal();

            return jwtUtils.generateAccessToken(user);
        } catch (BadCredentialsException e ) {
            throw new EndUserException("Usuario o contraseña incorrectas");
        }
    }

    public String registerUser(RegisterRequest request) throws EndUserException {
        return jwtUtils.generateAccessToken(createUser(request, false));
    }

    public Optional<User> getUserByToken(String token) {
        return userRepository.findByNombre(jwtUtils.getSubject(token));
    }
}
