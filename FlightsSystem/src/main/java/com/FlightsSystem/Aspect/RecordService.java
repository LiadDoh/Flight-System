package com.FlightsSystem.Aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

    @Autowired
    private RecordsRepository recordsRepository;

    public void addRecord(Record record) {
        recordsRepository.save(record);
    }
}
