package br.com.gubee.interview.model;

import br.com.gubee.interview.enums.Race;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "hero")
public class Hero {
    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false, columnDefinition = "id PRIMARY KEY NOT NULL DEFAULT public.uuid_generate_v4()")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "race", nullable = false)
    @Enumerated(EnumType.STRING)
    private Race race;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "power_stats_id", nullable = false)
    private PowerStats powerStats;

    @Column(name = "enabled", columnDefinition = "BOOLEAN NOT NULL DEFAULT TRUE", nullable = false)
    private Boolean enabled;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public PowerStats getPowerStats() {
        return powerStats;
    }

    public void setPowerStats(PowerStats powerStats) {
        this.powerStats = powerStats;
    }

    public void copyNonNullAttributes(Hero hero) {
        if (hero.getId() != null)
            this.setId(hero.getId());

        if (hero.getName() != null)
            this.setName(hero.getName());

        if (hero.getRace() != null)
            this.setRace(hero.getRace());

        if (hero.getEnabled() != null)
            this.setEnabled(hero.getEnabled());

        if (hero.getCreatedAt() != null)
            this.setCreatedAt(hero.getCreatedAt());

        if (hero.getUpdatedAt() != null)
            this.setUpdatedAt(hero.getUpdatedAt());
    }
}
