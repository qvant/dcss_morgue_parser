package ru.roguebase.dcssmorgueparser.domain;

import javax.persistence.*;
import java.time.Duration;

@Entity(name = "characters")
@SequenceGenerator(name = "s_characters", sequenceName = "s_characters", initialValue = 999, allocationSize = 1)
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_characters")
    private Long id;
    private String race;
    private String characterClass;
    private String god;
    private String killedBy;
    private String version;
    private String name;
    private int level;
    private Integer deathLevel;
    private String deathBranch;
    private boolean isWinner;
    private Duration time;

    @OneToOne
    @JoinColumn(name = "morgue_id")
    private Morgue morgue;

    private long turns;

    public Morgue getMorgue() {
        return morgue;
    }

    public void setMorgue(Morgue morgue) {
        this.morgue = morgue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public String getGod() {
        return god;
    }

    public void setGod(String god) {
        this.god = god;
    }

    public String getKilledBy() {
        return killedBy;
    }

    public void setKilledBy(String killedBy) {
        this.killedBy = killedBy;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Integer getDeathLevel() {
        return deathLevel;
    }

    public void setDeathLevel(Integer deathLevel) {
        this.deathLevel = deathLevel;
    }

    public String getDeathBranch() {
        return deathBranch;
    }

    public void setDeathBranch(String deathBranch) {
        this.deathBranch = deathBranch;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public long getTurns() {
        return turns;
    }

    public void setTurns(long turns) {
        this.turns = turns;
    }

    public Duration getTime() {
        return time;
    }

    public void setTime(Duration  time) {
        this.time = time;
    }
}
