package com.br.sanches.clientes.users.vehicle.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import kafka.utils.Json;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CARS_TB")
public class EntityCars {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "ID_CAR")
    private Long idCar;
    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private UserEntity userEntity;
    @Column(name = "LICENSE_PLATE")
    private String licensePlate;
    @Column(name = "VEHICLE_MODEL")
    private String vehicleModel;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "STATE")
    private String state;
    @Column(name = "CITY")
    private String city;
}
