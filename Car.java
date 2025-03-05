package entity;

import entity.CarStatus;
import entity.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;

    @Enumerated(EnumType.STRING)
    private CarStatus status = CarStatus.IDLE; // IDLE, ON_LEASE, ON_SERVICE

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;



}
