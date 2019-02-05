package com.pashagmz.react.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor

@Entity
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority {

    private static final long serialVersionUID = -67142681348605707L;

    @NotBlank(message = "The name cannot be empty or consist of only spaces")
    @Column(name = "name", nullable = false)
    private String name;

    @JsonIgnore
    @Getter(onMethod = @__(@JsonProperty))
    @Setter(onMethod = @__(@JsonIgnore), value = AccessLevel.PRIVATE)
    @Enumerated(EnumType.STRING)
    @Column(name = "code", nullable = false, updatable = false)
    private Code code;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @Fetch(FetchMode.SUBSELECT)
    private Set<Permission> permissions;

    public enum Code {
        ADMIN,
        USER
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + this.code.toString();
    }

}