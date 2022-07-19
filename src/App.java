import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {

        //Fazer uma conexao http e buscar os top 250 filmes
        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body =  response.body();

        // extrair só os dados que interessam (título, poster, classificacao)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        // exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[40;2;255;209;0m"  + filme.get("title") + "\u001b[0m");
            System.out.println("\u001b[40;2;50;122;228m" + filme.get("image") + "\u001b[0m");
            System.out.println("\u001b[95;1m" + filme.get("imDbRating") + "\u001b[0m");
            System.out.println();

            Double d = Double.parseDouble(filme.get("imDbRating"));
            Long stars = Math.round(d);
            for(int star = 0; star < stars; star++) {
                System.out.printf("\u2b50");
            }
            System.out.println("\n");

        }
    }
}
