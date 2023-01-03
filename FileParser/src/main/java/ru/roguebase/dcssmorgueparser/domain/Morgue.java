package ru.roguebase.dcssmorgueparser.domain;

import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity(name = "morgues")
@SequenceGenerator(name = "s_morgues", sequenceName = "s_morgues", initialValue = 999, allocationSize = 1)
public class Morgue {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_morgues")
    private Long id;
    private String filename;
    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server;

    Morgue(){;}

    public Morgue(String filename, Server server) {
        this.filename = filename;
        this.server = server;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
