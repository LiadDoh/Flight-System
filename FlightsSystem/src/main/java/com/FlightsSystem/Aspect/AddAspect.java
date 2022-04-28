package com.FlightsSystem.Aspect;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

//@Component
@Aspect
public class AddAspect {

    private int id = 1;

    @After("execution(* com.FlightsSystem.Facades.get*())")
    public void afterAdd(){
        RecordService recordService = new RecordService();
        Record record = new Record(id, LocalDate.now().toString());
        recordService.addRecord(record);
        increaseId();
        System.out.println("after add");
    }

    private void increaseId() {
        id++;
    }
}
