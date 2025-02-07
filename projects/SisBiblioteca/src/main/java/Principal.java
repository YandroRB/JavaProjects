import com.yandrorb.biblioteca.io.ConsolaUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Principal {
    public static void main(String[] args)  {
        ConsolaUI UI;

        try {
            UI = ConsolaUI.getInstance();
            UI.iniciar();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        try{
            System.out.println("Ingrese un numero del 0-9");
            int numero = Integer.parseInt((char)lector.read()+"");
            System.out.println("El numero ingresado es: "+numero);
        }catch(IOException|NumberFormatException e){
            e.printStackTrace();
        }
        System.out.println("Fin del programa");*/
        /*


        LocalDateTime fecha1 = LocalDateTime.now();
        LocalDateTime fecha2 = LocalDateTime.of(2021, 12, 31, 23, 59, 59);
        DateTimeFormatter formato= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println(ChronoUnit.DAYS.between(fecha2, fecha1));
         */
    }
}
