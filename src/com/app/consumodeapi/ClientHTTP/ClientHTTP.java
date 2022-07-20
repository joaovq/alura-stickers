package com.app.consumodeapi.ClientHTTP;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHTTP {
     public String searchData (String url) {
         try{
             URI adress = URI.create(url);
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(adress).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            System.out.println(body);
            return body;
         } catch (IOException | InterruptedException e){
             throw  new RuntimeException(e);
         }

     }

}
