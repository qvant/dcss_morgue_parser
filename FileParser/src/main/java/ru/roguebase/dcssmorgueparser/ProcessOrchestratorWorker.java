package ru.roguebase.dcssmorgueparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.roguebase.dcssmorgueparser.domain.Server;
import ru.roguebase.dcssmorgueparser.repository.ServerRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SpringBootApplication
@EnableScheduling
public class ProcessOrchestratorWorker
{
    @Autowired
    private ServerRepository repository;

    public static void main(String[] args) throws IOException, ParseException {
        SpringApplication.run(ProcessOrchestratorWorker.class, args);
//        FileParser fileParser = new FileParser();
//        fileParser.parse("FileParser/in/morgue-ComCon-20221212-144705.txt");
    }

    @PostConstruct
    public void init() throws ParseException {
        for(int i = 0 ; i < 1; ++i) {
            repository.save(new Server("underhound.eu", "https://underhound.eu/crawl/morgue/", "FileParser/files/in/", "FileParser/files/proceed/", "FileParser/files/error/"));
//            DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.UK);
//            String dtString = "24-Oct-2022 19:53";//"24-Sep-2019 07:53";
//            var dt = formatter.parse(dtString);
//            System.out.println(dt);
        }
    }
}
