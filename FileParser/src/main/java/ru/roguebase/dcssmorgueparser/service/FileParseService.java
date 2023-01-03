package ru.roguebase.dcssmorgueparser.service;

import org.springframework.stereotype.Service;
import ru.roguebase.dcssmorgueparser.domain.Character;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@Service
public class FileParseService {

    private String getVersion(String line){
        String rest =  line.substring(line.indexOf("version") + 8);
        String version = rest.substring(0, rest.indexOf(" "));
        return version;
    }

    private String getName(String line){
        String rest =  line.substring(line.indexOf(" ") + 1);
        String version = rest.substring(0, rest.indexOf("("));
        return version;
    }
    private int getLevel(String line){
        String rest =  line.substring(line.indexOf("(level ") + 6);
        String version = rest.substring(0, rest.indexOf(","));
        return Integer.parseInt(version.trim());
    }
    private String getRace(String line){
        String rest =  line.substring(line.indexOf("(") + 1);
        String version = rest.substring(0, rest.indexOf(" "));
        return version;
    }
    private String getClass(String line){
        String rest =  line.substring(line.indexOf("(") + 1);
        String version = rest.substring(rest.indexOf(" ") + 1, rest.indexOf(")") );
        return version;
    }
    private String getKiller(String line){
        String rest =  line.substring(line.indexOf("Slain by") + 9);
        return rest;
    }
    private Integer getDeathLevel(String line){
        String rest =  line.substring(line.indexOf("... on level") + 12).trim();
        return Integer.parseInt(rest.substring(0, rest.indexOf(" ")).trim());
    }
    private String getDeathBranch(String line){
        String rest =  line.substring(line.indexOf("of the") + 1);
        return rest.substring(rest.indexOf(" "));
    }
    private String getGod(String line){
        String rest =  line.substring(line.indexOf("God:") + 4).trim();
        if (rest.length() == 0){
            return null;
        }
        return rest.substring(0, rest.indexOf("[")).trim();
    }
    private int getTurns(String line){
        String rest =  line.substring(line.indexOf("Turns:") + 6).trim();
        return Integer.parseInt(rest.substring(0, rest.indexOf(",")).trim());
    }
    private Duration getTime(String line) throws ParseException {
        String rest =  line.substring(line.indexOf("Time:") + 5).trim();
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date dt = formatter.parse(rest.substring(0).trim());
        Duration d = Duration.ofSeconds(dt.getSeconds() + dt.getMinutes() * 60 + dt.getHours() * 3600 );
        return  d;
    }
    public Character parse(String path) throws IOException, ParseException {;
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Character character = new Character();
        String currentLine = reader.readLine();
        character.setVersion(getVersion(currentLine));
        //empty
         currentLine = reader.readLine();
        //seed
         currentLine = reader.readLine();
         System.out.println(currentLine.indexOf("Game seed:"));
         if (currentLine.indexOf("Game seed:") >= 0) {
             //empty
             currentLine = reader.readLine();
             currentLine = reader.readLine();
         }
        // level and name
         character.setName(getName(currentLine));
        character.setLevel(getLevel(currentLine));
        while (!currentLine.equals("")){
            currentLine = reader.readLine();
            if (currentLine.trim().startsWith("Slain by")){
                character.setKilledBy(getKiller(currentLine));
            }
            if (currentLine.trim().startsWith("... on level")){
                character.setDeathLevel(getDeathLevel(currentLine));
                character.setDeathBranch(getDeathBranch(currentLine));
                character.setWinner(false);
            }
            if (currentLine.trim().startsWith("Escaped with the Orb")){
                character.setWinner(true);
            }
        }
        currentLine = reader.readLine();
        character.setRace(getRace(currentLine));
        character.setCharacterClass(getClass(currentLine));
        character.setTurns(getTurns(currentLine));
        character.setTime(getTime(currentLine));
        //empty
        currentLine = reader.readLine();
        currentLine = reader.readLine();
        while (!currentLine.equals("")){
            currentLine = reader.readLine();
            if (currentLine.indexOf("God:") > 0){
                character.setGod(getGod(currentLine));
            }
        }
        System.out.println(character);
        reader.close();
        return character;
    }
}
