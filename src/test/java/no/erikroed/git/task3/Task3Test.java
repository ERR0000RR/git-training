package no.erikroed.git.task3;

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
public class Task3Test {

    private GithubClient client;

    @Autowired
    private Property property;

    @BeforeEach
    public void setUp() {
        client = new GithubClient(property.getBaseUrl(), property.getGithubUser(), property.getGithubRepo());
    }

    @Test
    public void task3_shouldReturnAddConstructorMessage() {
        List<Commit> commit = client.getCommits("task3");
        Assertions.assertEquals("Add constructor to Task3", commit.get(0).getCommits().getMessage());
    }

}
