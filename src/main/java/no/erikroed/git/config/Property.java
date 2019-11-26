package no.erikroed.git.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Property {

    @Value("${github.baseurl}")
    private String baseUrl;

    @Value("${github.username}")
    private String githubUser;

    @Value("${github.repo}")
    private String githubRepo;

}
