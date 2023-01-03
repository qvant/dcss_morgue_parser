package ru.roguebase.dcssmorgueparser.service;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roguebase.dcssmorgueparser.domain.Player;
import ru.roguebase.dcssmorgueparser.domain.Server;
import ru.roguebase.dcssmorgueparser.repository.PlayerRepository;
import ru.roguebase.dcssmorgueparser.repository.ServerRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class FileDownloadService {

    private final ServerRepository serverRepository;
    private final PlayerRepository playerRepository;

    @Transactional
   @Scheduled(initialDelay = 2000, fixedRate = 3000)
    public void processFiles() throws IOException, ParseException {
        var servers = serverRepository.findAll();
        for (Server server : servers
        ) {
//            URL url = new URL(server.getUrl());
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            int status = con.getResponseCode();
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer content = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//            in.close();
//            var lines = content.toString().split("\n");
//            for (String line : lines
//                 ) {
//                System.out.println(line);
//                System.out.println("____");
//            }
            //System.out.println(content);
            //processFolder(server);
            String blogUrl = server.getUrl();
            Document doc = Jsoup.connect(blogUrl).get();
            Elements links = doc.select("a");
            for (Element link : links
                    ) {
                String playerLogin = link.attr("href");
                Player player =  playerRepository.findByLogin(playerLogin).orElse(new Player(playerLogin, null));
                DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.UK);
                String dtString = link.nextSibling().toString().trim();
                Date dt = null;
                if (dtString.trim().length() > 10){
                    System.out.println("dtString");
                    System.out.println(dtString);
                    System.out.println(dtString.substring(0, dtString.length() - 2));
                    try {
                        dt = formatter.parse(dtString.substring(0, dtString.length() - 2).trim());
                    }
                    catch (Exception exception){
                        if (player.getLastUpdate() != null){
                            dt = Date.from(Instant.now();
                            player.setLastUpdate(dt);
                            playerRepository.save(player);
                        }
                    }
                    }

                if (player.getLastUpdate() == dt)
                {
                    continue;
                }
                player.setLastUpdate(Date.from(Instant.now()));
                playerRepository.save(player);
                System.out.println(link.attr("href"));
                System.out.println(link.nextSibling());
            }

        }
    }
}
