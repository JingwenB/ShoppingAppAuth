package com.beaconfire.springsecurityauth.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="PERMISSION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Permission {
//
//    // Database table
//    private String username;
//    private String password;
//    // @OneToMany
//    private List<String> permissions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id", unique = true, nullable = false)
    private Integer id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;

    @Column(name = "status")
    private String status;

}
