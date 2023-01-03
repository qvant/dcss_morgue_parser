package ru.roguebase.dcssmorgueparser.domain;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "players")
@SequenceGenerator(name = "s_players", sequenceName = "s_players", initialValue = 999, allocationSize = 1)
public class Player {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_players")
    private Long id;
    private String login;
    private Date lastUpdate;

    public Player() {
        ;
    }

    public Player(String login, Date lastUpdate) {
        this.login = login;
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
