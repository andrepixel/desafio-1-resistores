package com.example.desafio3.Usecases;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.desafio3.Dtos.UserListRequestDTO;
import com.example.desafio3.Dtos.UserListResponseDTO;
import com.example.desafio3.Entities.UserListEntity;
import com.example.desafio3.Mappers.UserMapper;
import com.example.desafio3.Repositories.ListRepository;

@Transactional
@Service
public class ListUseCase {
    private ListRepository repository;
    private final UserMapper userMapper;

    public ListUseCase(UserMapper userMapper, ListRepository repository) {
        this.userMapper = userMapper;
        this.repository = repository;
    }

    public Optional<List<UserListEntity>> getAllUserList() {
        List<UserListEntity> entityListDatabase = repository.findAll();

        return Optional.of(entityListDatabase);
    }

    public Optional<UserListResponseDTO> deleteUserList(String idUserList) {
        Optional<UserListEntity> entityDatabase = repository.findById(UUID.fromString(idUserList));

        if (entityDatabase.isPresent()) {
            repository.deleteById(UUID.fromString(idUserList));

            UserListResponseDTO dto = userMapper.toUserListResponseDTO(entityDatabase.get());

            return Optional.of(dto);
        }

        return Optional.empty();
    }

    public Optional<UserListResponseDTO> updateUserList(UserListRequestDTO dto) {
        Optional<UserListEntity> optional = repository.findById(UUID.fromString(dto.id()));

        if (optional.isPresent()) {
            UserListEntity userListEntity = userMapper.toUserListEntity(dto);

            UserListEntity entity = repository.save(userListEntity);

            UserListResponseDTO userListResponseDTO = userMapper.toUserListResponseDTO(entity);

            return Optional.of(userListResponseDTO);
        }

        return Optional.empty();
    }

    public UserListResponseDTO createUserList() {
        UserListEntity entity = new UserListEntity();
        entity.setTitle("Nova Tarefa");

        repository.save(entity);

        return userMapper.toUserListResponseDTO(entity);
    }

    public Optional<UserListEntity> findEntityById(UUID id) {
        Optional<UserListEntity> entity = repository.findById(id);

        return entity;
    }
}
