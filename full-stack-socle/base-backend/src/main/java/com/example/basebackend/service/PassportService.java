package com.example.basebackend.service;

import com.example.basebackend.payload.request.PassportRequest;
import com.example.basebackend.payload.response.PassportResponse;
import com.example.basebackend.payload.response.StudentResponse;

import java.util.List;

public interface PassportService {
   List<PassportResponse> getAllPassports();

   StudentResponse createPassport(Long studentId, PassportRequest passportRequest);

   PassportResponse updatePassport(Long passportId, PassportRequest passportRequest);

   String deletePassport(Long passportId);

   PassportResponse getPassport(Long studentId);
}
