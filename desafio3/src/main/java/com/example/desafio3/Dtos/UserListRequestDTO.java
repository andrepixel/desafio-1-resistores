package com.example.desafio3.Dtos;

import java.util.List;

public record UserListRequestDTO(String id, String title, List<UserItemRequestDTO> itens) {}

