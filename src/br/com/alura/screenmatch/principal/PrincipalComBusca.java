package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        System.out.println("Digite um filme para busca: ");
        var busca = leitura.nextLine();
        String endereco = "https://www.omdbapi.com/?t=" + busca + "&apikey=20b62d34";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        String json = response.body();

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

        TituloOmdb meutituloOmdb = gson.fromJson(json, TituloOmdb.class);
        System.out.println(meutituloOmdb);
        try {
            Titulo meutitulo = new Titulo(meutituloOmdb);
            System.out.println("Meu titulo convertido:");
            System.out.println(meutitulo);

        } catch (Exception e) {
            System.out.println("Aconteceteu um erro: ");
            System.out.println(e.getMessage());
        }

        System.out.println("O programa finalizou corretamente!");


    }
}
