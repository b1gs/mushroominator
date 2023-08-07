package com.b1gs.controllers.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "telegram_chat", schema = "mushroominator")
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class TelegramChatEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "t_chat_id", nullable = false)
    private Long chatId;

    @Column(name = "t_user_id", nullable = false)
    private Long userId;

    @Column(name = "t_username", nullable = false)
    private String username;

    @Column(name = "t_firstname", nullable = false)
    private String firstName;

    @Column(name = "t_lastname", nullable = false)
    private String lastName;

    @Column(name = "device_id")
    private String deviceId;

}
