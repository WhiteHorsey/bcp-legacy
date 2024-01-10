package com.example.conflitback.service.impl;

import com.example.conflitback.dto.request.InitConflictReqDTO;
import com.example.conflitback.repository.ClientRepository;
import com.example.conflitback.repository.ConflictRepository;
import com.example.conflitback.service.ConflictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConflictServiceImpl implements ConflictService {

    private final ConflictRepository conflictRepository;
    private final ClientRepository clientRepository;

    @Override
    public void initConflict(InitConflictReqDTO request) {
        if(request.getClientType().equals("Un particulier")){

        }else {

        }
    }
}
