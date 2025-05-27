import java.net.URI;
import java.net.http.*;
import com.google.gson.*;

class GitHubUser {
    String login;
    int id;
    String url;
}

public class HttpExample {
    public static void main(String[] args) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.github.com/users/octocat"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status Code: " + response.statusCode());

            // Parse JSON
            Gson gson = new Gson();
            GitHubUser user = gson.fromJson(response.body(), GitHubUser.class);
            System.out.println("Login: " + user.login);
            System.out.println("User ID: " + user.id);
            System.out.println("Profile URL: " + user.url);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
