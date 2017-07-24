package ru.academits.shedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Anna on 24.07.2017.
 */

@Component
public class EmailSheduler {

    @Scheduled(fixedRate = 10000)
    public void sendEmailWithContactList(){
        System.out.println("shedule");
    }
}
