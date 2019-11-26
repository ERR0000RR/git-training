package no.erikroed.git.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.erikroed.git.model.Commit;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public class GithubClient {

    private String baseUrl;
    private String user;
    private String repo;

    private Client client = ClientBuilder.newClient();

    public GithubClient(String baseUrl, String user, String repo) {
        this.baseUrl = baseUrl;
        this.user = user;
        this.repo = repo;
    }

    public List<Commit> getAllCommits() {
        Response response = client.target(baseUrl)
                .path("repos")
                .path(user)
                .path(repo)
                .path("commits")
                .request()
                .accept(APPLICATION_JSON)
                .get();

        if (response.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL) {
            throw new RuntimeException("Could not retrieve commit history");
        }

        return parseResponse(response);
    }

    private static List<Commit> parseResponse(Response response) {
        try {
            String json = response.readEntity(String.class);
            return new ObjectMapper().readValue(json, new TypeReference<List<Commit>>() {});
        } catch (IOException e) {
            throw new RuntimeException("An error occured when parsing response: " + e.getMessage());
        }
    }

}
