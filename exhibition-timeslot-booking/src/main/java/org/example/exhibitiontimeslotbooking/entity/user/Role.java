package org.example.exhibitiontimeslotbooking.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.enums.RoleType;

@Entity
@Table(name = "roles")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role {
    @Id @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 30, nullable = false)
    private RoleType name;

    public Role(RoleType name) {
        this.name = name;
    }
}
