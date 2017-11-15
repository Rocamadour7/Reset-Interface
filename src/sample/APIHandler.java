package sample;

import com.google.gson.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class APIHandler {

    private Controller controller;

    private static final String domain = "http://127.0.0.1:8080/api";
    private static int voteId = 0;

    public APIHandler() {}

    public void injectController(Controller controller) {
        this.controller = controller;
    }

    public ArrayList<String> getCandidates() throws IOException {
        ArrayList<String> candidates = new ArrayList<>();
        URL url = new URL(domain + "/candidates");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        JsonParser jsonParser = new JsonParser();
        JsonElement root = jsonParser.parse(new InputStreamReader((InputStream)connection.getContent()));
        JsonArray rootobj = root.getAsJsonArray();

        if (rootobj.size() != 0) {
            for (int i = 0; i < rootobj.size(); i++) {
                JsonObject jsonObject = rootobj.get(i).getAsJsonObject();
                StringBuilder name = new StringBuilder(jsonObject.get("firstName").getAsString());
                name.append(" ");
                name.append(jsonObject.get("lastName").getAsString());
                candidates.add(name.toString());
            }
        }
        return candidates;
    }

    public void postVote() throws IOException {
        Vote vote = new Vote(voteId, controller.getVote());
        Gson gson = new Gson();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(domain + "/votes");
        StringEntity postingString = new StringEntity(gson.toJson(vote));
        post.setEntity(postingString);
        post.setHeader("Content-Type", "application/json");
        HttpResponse response = httpClient.execute(post);

        voteId++;
    }

    public void getBlockchain() {

    }

    public void getLastBlockHash() {

    }

}
