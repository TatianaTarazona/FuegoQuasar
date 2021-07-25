package com.meli.fuegoQuasar.services;

import com.meli.fuegoQuasar.exceptions.MessageException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {


     public String getMessage(List<String[]> messages) throws MessageException {

        String[] listWords = new String[messages.get(0).length];

        for( String[] message : messages){

           for (int i = 0 ; i < message.length ; i++){
               if (!message[i].equals("")){
                   listWords[i] = message[i];
               }
           }
        }

        StringBuilder sb = new StringBuilder();

        for (String s : listWords){
            if (s == null){
                throw new MessageException("No es posible determinar el mensaje ");
            }
             sb.append(s).append(" ");
        }

        return sb.toString();
    }

}
