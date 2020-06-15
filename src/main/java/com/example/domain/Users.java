package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * @Author changxizhao
 * @Date 2020/6/14 21:22
 * @Description
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    /*** 用户ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long userId;

    /*** 用户账号 */
    @Column(name = "user_name")
    private String userName;

    /*** 密码 */
    @Column(name = "password")
    private String password;

    /*** 帐号状态（0正常 1停用） */
    @Column(name = "status")
    private String status;

    /*** 用户角色（多角色用逗号间隔） */
    @Column(name = "roles")
    private String roles;
}
