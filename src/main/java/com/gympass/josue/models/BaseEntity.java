package com.gympass.josue.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    public UUID id;
}
