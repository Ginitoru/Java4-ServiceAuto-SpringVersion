package com.gini.iordache.util;

import org.springframework.security.core.Authentication;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logs {

    private Logs() {
    }



    public static void loginLogoutCreateUser(Authentication auth){

        String userName = auth.getName();
        String path = "./web/src/main/resources/log/" + userName + ".txt";
        String writeLog = "\n" + userName + " logged at: " + LocalDateTime.now()
                .format(TimeFormat.formatter());



        try(
                FileWriter x = new FileWriter(path,true);
                BufferedWriter br = new BufferedWriter(x)
        ){


            br.write(writeLog);


        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
