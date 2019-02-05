package com.pashagmz.react.model.dictionary;

import com.pashagmz.react.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@EqualsAndHashCode(callSuper = true)

@MappedSuperclass
public abstract class Dictionary extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

}