package com.perumed.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Util {

    public static String BASE_URL = "http://observatorio.digemid.minsa.gob.pe/";

    public static HashMap<String, String> GETDEPARTAMENTOS(){
        HashMap<String, String> lista = new HashMap<String, String>();
        lista.put("Amazonas", "01");
        lista.put("Ancash", "02");
        lista.put("Apurimac", "03");
        lista.put("Arequipa", "04");
        lista.put("Ayacucho", "05");
        lista.put("Cajamarca", "06");
        lista.put("Callao", "07");
        lista.put("Cusco", "08");
        lista.put("Huancavelica", "09");
        lista.put("Huanuco", "10");
        lista.put("Ica", "11");
        lista.put("Junin", "12");
        lista.put("La Libertad", "13");
        lista.put("Lambayeque", "14");
        lista.put("Lima", "15");
        lista.put("Loreto", "16");
        lista.put("Madre de Dios", "17");
        lista.put("Moquegua", "18");
        lista.put("Pasco", "19");
        lista.put("Piura", "20");
        lista.put("Puno", "21");
        lista.put("San Martin", "22");
        lista.put("Tacna", "23");
        lista.put("Tumbes", "24");
        lista.put("Ucayali", "25");

        return lista;
    }

    public static List<String> GETDEPARTAMENTOSSTRINGS(){
        List<String> lista = new ArrayList<>();
        lista.add("Seleccione un Departamento");
        lista.add("Amazonas");
        lista.add("Ancash");
        lista.add("Apurimac");
        lista.add("Arequipa");
        lista.add("Ayacucho");
        lista.add("Cajamarca");
        lista.add("Callao");
        lista.add("Cusco");
        lista.add("Huancavelica");
        lista.add("Huanuco");
        lista.add("Ica");
        lista.add("Junin");
        lista.add("La Libertad");
        lista.add("Lambayeque");
        lista.add("Lima");
        lista.add("Loreto");
        lista.add("Madre de Dios");
        lista.add("Moquegua");
        lista.add("Pasco");
        lista.add("Piura");
        lista.add("Puno");
        lista.add("San Martin");
        lista.add("Tacna");
        lista.add("Tumbes");
        lista.add("Ucayali");

        return lista;
    }

}
