package com.app.consumodeapi.bestFilms250;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//import com.fasterxml.jackson.databind.ObjectMapper;
public class JsonParser {

//Com a biblioteca Jackson
  /*  ObjectMapper mapper = new ObjectMapper();

    //JSON file to Java object
    Staff obj = mapper.readValue(new File("c:\\test\\staff.json"), Staff.class);

    //JSON URL to Java object
    Staff obj = mapper.readValue(new URL("http://some-domains/api/staff.json"), Staff.class);

    //JSON string to Java Object
    Staff obj = mapper.readValue("{'name' : 'mkyong'}", Staff.class);*/

    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");
    public List<Map<String, String>> parse(String json){

        Matcher matcher = REGEX_ITEMS.matcher(json);
        if (!matcher.find()) {

            throw new IllegalArgumentException("Não encontrou items.");
        }

        String[] items = matcher.group(1).split("\\},\\{");

        List<Map<String, String>> dados = new ArrayList<>();

        for (String item : items) {

            Map<String, String> atributosItem = new HashMap<>();

            Matcher matcherAtributosJson = REGEX_ATRIBUTOS_JSON.matcher(item);
            while (matcherAtributosJson.find()) {
                String atributo = matcherAtributosJson.group(1);
                String valor = matcherAtributosJson.group(2);
                atributosItem.put(atributo, valor);
            }

            dados.add(atributosItem);
        }

        return dados;
    }
}
