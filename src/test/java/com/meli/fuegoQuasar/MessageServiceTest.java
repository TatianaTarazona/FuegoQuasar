package com.meli.fuegoQuasar;

import com.meli.fuegoQuasar.exceptions.MessageException;
import com.meli.fuegoQuasar.services.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;



    @Test
    public void getFinalMessage(){

        String[] mensaje11 = new String[]{"este", "", "un", "mensaje", ""};
        String[] mensaje21 = new String[]{"", "es", "un", "mensaje", ""};
        String[] mensaje31 = new String[]{"", "es", "un", "", "secreto"};

        List<String[]> mensajesC = new ArrayList<>();
        mensajesC.add(mensaje11);
        mensajesC.add(mensaje21);
        mensajesC.add(mensaje31);
        String expected = "este es un mensaje secreto";
        String finalMessage =  new String();
        try{
            finalMessage = messageService.getMessage(mensajesC);
        } catch (MessageException e) {
            e.printStackTrace();
        }


        assertEquals(expected,finalMessage.trim());


}


}
