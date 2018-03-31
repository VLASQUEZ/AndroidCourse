package com.example.andresvelasquez.practica_9_fragments.util;

import com.example.andresvelasquez.practica_9_fragments.model.Mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Util {
    private static String[] colors = new String[]
            {"F44336","E91E63","9C27B0","673AB7","3F51B5",
                    "03A9F4","009688","4CAF50","CDDC39","FCC107",
                    "FFC107","795548","9E9E9E","455A64","FF5722"
            };

    public static List<Mail> getDummyData(){
        return new ArrayList<Mail>(){{
            add(new Mail("Saludo desde el mas allá","Hola mundo inmundo","pepito@perez.com"));
            add(new Mail("Saludo desde el mas allá","Hola mundo inmundo","pepito@perez.com"));
            add(new Mail("Saludo desde el mas allá","Hola mundo inmundo","pepito@perez.com"));
            add(new Mail("Saludo desde el mas allá","Hola mundo inmundo","pepito@perez.com"));
            add(new Mail("Saludo desde el mas allá","Hola mundo inmundo","pepito@perez.com"));
            add(new Mail("Saludo desde el mas allá","Hola mundo inmundo","pepito@perez.com"));
            add(new Mail("Saludo desde el mas allá","Hola mundo inmundo","pepito@perez.com"));
            add(new Mail("Saludo desde el mas allá","Hola mundo inmundo","pepito@perez.com"));
            add(new Mail("Saludo desde el mas allá","Hola mundo inmundo","pepito@perez.com"));
            add(new Mail("Saludo desde el mas allá","Hola mundo inmundo","pepito@perez.com"));
        }};
    }
    public static String getRandomColor(){
        //Numero aleatorio
        int randomNumber = new Random().nextInt(colors.length) +0;
        return colors[randomNumber];
    }
}
