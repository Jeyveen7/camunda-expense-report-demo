package com.camunda.bpm.servicesdemo.entity;

import javax.persistence.*;
import java.sql.Date;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    @Column(name="ID")
    private Long id;
    @Column(name="CREATION_DATE", updatable = false)
    private Date creationDate;
    @Column(name="LAST_UPDATE_DATE", insertable = false)
    private Date lastUpdateDate;

    @PrePersist
    public void onPrePersist(){
        this.creationDate = new Date(System.currentTimeMillis());
    }

    @PreUpdate
    public void onPreUpdate(){
        this.lastUpdateDate = new Date(System.currentTimeMillis());
    }
}
