package com.pashagmz.react.model;

import com.pashagmz.react.model.dictionary.PermissionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor

@Entity
@Table(name = "permissions")
public class Permission extends BaseEntity implements GrantedAuthority {

    private static final long serialVersionUID = -8634331503673310404L;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private PermissionType type;


    public String getAuthority() {
        return "OP_" + this.module.getCode() + "_" + type.getCode();
    }

}