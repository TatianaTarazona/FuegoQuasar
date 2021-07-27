package com.meli.fuegoQuasar.services;

import com.meli.fuegoQuasar.exceptions.MessageException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {


    /**
     * Retorna el mensaje final a partir de los mensajes interceptados por los satellites
     * @param messages Lista de mensajes interceptados
     * @return Mensaje final
     * @throws MessageException
     */
    public String getMessage(List<String[]> messages) throws MessageException {

        messages = messages.stream().filter(mes -> mes!= null ).collect(Collectors.toList()) ;
        //Extrae la longitud del arreglo mas grande
        int size = messages.stream().mapToInt(m -> m.length).max().getAsInt();
        String[] listWords = new String[size];

        for (String[] message : messages) {
            if (message != null) {
                for (int i = 0; i < message.length; i++) {
                    if (!message[i].equals("")) {
                        listWords[i] = message[i];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (String s : listWords) {
            if (s == null) {
                throw new MessageException("No es posible determinar el mensaje");
            }
            sb.append(s).append(" ");
        }

        return sb.toString();
    }

}
