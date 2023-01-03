package ru.roguebase.dcssmorgueparser.domain;

import javax.persistence.*;

@Entity(name = "servers")
@SequenceGenerator(name = "s_servers", sequenceName = "s_servers", initialValue = 999, allocationSize = 1)
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_servers")
    private long id;


    private String name;
    private String url;
    private String inputFolder;
    private String proceedFolder;
    private String errorFolder;

    public Server(){;}

    public Server(String name, String url, String inputFolder, String proceedFolder, String errorFolder) {
        this.name = name;
        this.url = url;
        this.inputFolder = inputFolder;
        this.proceedFolder = proceedFolder;
        this.errorFolder = errorFolder;
    }


    public String getInputFolder() {
        return inputFolder;
    }

    public void setInputFolder(String inputFolder) {
        this.inputFolder = inputFolder;
    }

    public String getProceedFolder() {
        return proceedFolder;
    }

    public void setProceedFolder(String proceedFolder) {
        this.proceedFolder = proceedFolder;
    }

    public String getErrorFolder() {
        return errorFolder;
    }

    public void setErrorFolder(String errorFolder) {
        this.errorFolder = errorFolder;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
