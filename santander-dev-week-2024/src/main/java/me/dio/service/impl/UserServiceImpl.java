package me.dio.service.impl;

import org.springframework.stereotype.Service;

import jakarta.persistence.NoResultException;
import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //buscar por id
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoResultException::new);
    }

    //criando novo usuario
    @Override
    public User create(User userToCreate) {
        //verificando se o usuario ja existe
        if(userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new IllegalArgumentException("O usuario já existe");
        }

        return userRepository.save(userToCreate);
    }

}
