package aibg23.selection.configuration;

import aibg23.selection.domain.User;
import aibg23.selection.service.SelectionService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Getter
@Setter
public class Configuration {
    private Logger LOG = LoggerFactory.getLogger(Configuration.class);
    private InputStream configFile = this.getClass().getResourceAsStream("/configuration");
    private List<String> usernames = new ArrayList<>();
    private List<String> passwords = new ArrayList<>();
    private List<String> types = new ArrayList<>();

    private SelectionService selectionService;

    @Autowired
    public Configuration(SelectionService selectionService) {
        this.selectionService = selectionService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void parse() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(this.configFile));

        String line;
        while ((line = br.readLine()) != null) {
            String[] keyValuePair = line.split(": ");
            String key = keyValuePair[0];
            String value = keyValuePair[1];

            switch (key) {
                case "usernames" -> {
                    String[] usernames = value.split(",");
                    this.usernames.addAll(Arrays.asList(usernames));
                }

                case "passwords" -> {
                    String[] passwords = value.split(",");
                    this.passwords.addAll(Arrays.asList(passwords));
                }

                case "types" -> {
                    String[] types = value.split(",");
                    this.types.addAll(Arrays.asList(types));
                }

                default -> LOG.info("Greška pri parsiranju konfiguracionog file-a.");
            }
        }

        this.addUsers();

    }

    private void addUsers() {
        for (int i = 0; i < usernames.size(); i++) {
            selectionService.getUsers().add(new User(usernames.get(i), passwords.get(i)));
        }

    }


}
