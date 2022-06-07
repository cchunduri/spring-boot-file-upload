package com.chaitu.fileupload.service;

import com.chaitu.fileupload.entities.Employee;
import com.chaitu.fileupload.repos.EmployeeRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class FileStorageService {
    private EmployeeRepo employeeRepo;

    public String saveFile(MultipartFile multipartFile) {

        try {
            Reader reader = new InputStreamReader(multipartFile.getInputStream());
            List<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader("firstname", "lastname", "email")
                    .withFirstRecordAsHeader()
                    .parse(reader).getRecords();

            var employee = records.stream().map(record -> Employee.builder()
                    .firstName(record.get("firstname"))
                    .lastName(record.get("lastname"))
                    .email(record.get("email"))
                    .build()).collect(Collectors.toList());

            employeeRepo.saveAll(employee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "Failed";
    }
}
