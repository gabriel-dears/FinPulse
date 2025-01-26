package com.fin_pulse.domain.user.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_status")
public class UserStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @OneToOne(mappedBy = "status")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

