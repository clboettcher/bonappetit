package com.github.clboettcher.bonappetit.server.staff.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "EVENT")
@Data
@NoArgsConstructor
public class EventEntity {

    @Id
    @GeneratedValue
    @Column(name = "EVENT_ID")
    private Long id;

    @Column(name = "CURRENT_EVENT")
    private String currentEvent;

    @Builder
    public EventEntity(Long id, String currentEvent) {
        this.id = id;
        this.currentEvent = currentEvent;
    }

}
