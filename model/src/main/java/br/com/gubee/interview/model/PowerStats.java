package br.com.gubee.interview.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "power_stats")
public class PowerStats {
    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "strength", nullable = false)
    private Short strength;

    @Column(name = "agility", nullable = false)
    private Short agility;

    @Column(name = "dexterity", nullable = false)
    private Short dexterity;

    @Column(name = "intelligence", nullable = false)
    private Short intelligence;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToOne(mappedBy = "powerStats")
    private Hero hero;

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

    public Short getStrength() {
        return strength;
    }

    public void setStrength(Short strength) {
        this.strength = strength;
    }

    public Short getAgility() {
        return agility;
    }

    public void setAgility(Short agility) {
        this.agility = agility;
    }

    public Short getDexterity() {
        return dexterity;
    }

    public void setDexterity(Short dexterity) {
        this.dexterity = dexterity;
    }

    public Short getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Short intelligence) {
        this.intelligence = intelligence;
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

    public Hero getHero() {
        return hero;
    }

    public void setHeroes(Hero hero) {
        this.hero = hero;
    }
}
