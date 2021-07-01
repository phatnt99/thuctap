package net.freetuts.backend.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@MappedSuperclass
public abstract class BaseEntity {
	
	@Id
	@GeneratedValue(generator = "uuid4")
	@GenericGenerator(name = "UUID", strategy = "uuid4")
	@Type(type = "org.hibernate.type.UUIDCharType")
	@Column(columnDefinition = "CHAR(36)")
	private UUID id;
	
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime createdAt;
	
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime updatedAt;	
	
	public BaseEntity() {
		super();
	}

	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	/**
     * Sets createdAt and updatedAt before insert
     */
	@PrePersist 
	public void setCreationDate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
	/**
     * Sets updatedAt before update
     */
    @PreUpdate
    public void setChangeDate() {
        this.updatedAt = LocalDateTime.now();
    }
}