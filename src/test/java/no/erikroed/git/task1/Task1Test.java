package no.erikroed.git.task1;

import no.erikroed.git.client.GithubClient;
import no.erikroed.git.config.Property;
import no.erikroed.git.model.Commit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Task1Test {

    private GithubClient client;

    @Autowired
    private Property property;

    @BeforeEach
    public void setUp() {
        client = new GithubClient(property.getBaseUrl(), property.getGithubUser(), property.getGithubRepo());
    }

    @Test
    public void task1_shouldReturnCherryPickedCommitMessage() {
        List<Commit> commit = client.getAllCommits();
        Assertions.assertEquals("Add constructor to Task1", commit.get(0).getCommits().getMessage());
    }

}
