package com.pashagmz.react.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "modules")
public class Module extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "code", nullable = false, unique = true)
    private Code code;

    public enum Code {
        USER_MANAGEMENT
    }

}
