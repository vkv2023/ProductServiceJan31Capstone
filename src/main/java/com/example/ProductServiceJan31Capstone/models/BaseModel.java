package com.example.ProductServiceJan31Capstone.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected String name;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastModifiedAt;

    @Column(name = "is_deleted", columnDefinition = "TINYINT(1)")
    protected boolean isDeleted;

    // you can use both @PrePersist and @PreUpdate together in a single entity
    // Before entity is inserted
    // Marks a method to be called before an entity is updated
    @PrePersist
    protected void onCreate() {
        Date now = new Date();
        this.createdAt = now;
        this.lastModifiedAt = now;
    }

    // Before entity is updated
    // Marks a method to be called after an entity is updated
    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedAt = new Date();
    }

    //
    public void markAsDeleted() {
        this.isDeleted = true;
    }

}
