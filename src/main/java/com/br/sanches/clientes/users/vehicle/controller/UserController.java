package com.br.sanches.clientes.users.vehicle.controller;

import com.br.sanches.clientes.users.vehicle.basePath.BasePath;
import com.br.sanches.clientes.users.vehicle.controller.request.LoginRequest;
import com.br.sanches.clientes.users.vehicle.controller.request.UpdateLicensePlateOrModelVehicleRequest;
import com.br.sanches.clientes.users.vehicle.controller.request.UpdateUserRequest;
import com.br.sanches.clientes.users.vehicle.controller.request.UserRequest;
import com.br.sanches.clientes.users.vehicle.controller.response.UserResponse;
import com.br.sanches.clientes.users.vehicle.entity.EntityCars;
import com.br.sanches.clientes.users.vehicle.entity.UserEntity;
import com.br.sanches.clientes.users.vehicle.exception.BadRequestException;
import com.br.sanches.clientes.users.vehicle.exception.ObjectAlreadyExists;
import com.br.sanches.clientes.users.vehicle.exception.PreconditionFailedException;
import com.br.sanches.clientes.users.vehicle.service.UserService;
import com.br.sanches.clientes.users.vehicle.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(BasePath.BASE_PATH_URL)
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(BasePath.BASE_PATH_REGISTER_NEW_USER)
    @Operation(description = Constants.REGISTER_NEW_USER_AND_BEHICLE)
    public ResponseEntity<?> RegisterNewUser(
            @Valid @RequestBody UserRequest userRequest) throws ObjectAlreadyExists {

        final UserResponse response = this.userService.newUser(userRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(BasePath.BASE_PARAM_NAME)
    @Operation(description = Constants.SEARCH_BY_NAME)
    public ResponseEntity<?> searchByUserId(
            @RequestParam(
                    name = "name",
                    required = true,
                    value = "name") final String name) throws PreconditionFailedException {

        final UserResponse response = this.userService.searchByName(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(BasePath.BASE_PATH_ID_UPDATE)
    @Operation(description = Constants.UPDATE_A_USER_AND_A_VEHICLES_LOCATION_DATA)
    public ResponseEntity<?> updateUserAndCar(
            @RequestParam(
                    name = "idUser",
                    required = true,
                    value = "idUser") final Long idUser,
            @RequestBody UpdateUserRequest userRequest) throws BadRequestException {

        final UserResponse response = this.userService.updateUserAndCar(idUser, userRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(BasePath.BASE_PATH_ID_DELETE)
    @Operation(description = Constants.DELETE_USER_AND_VEHICLE_BY_IDUSER)
    public ResponseEntity<?> deletUser(
            @PathVariable("{id}") final Long idUser) throws PreconditionFailedException {

        this.userService.delete(idUser);
        return ResponseEntity.status(HttpStatus.OK).body(Constants.USER_DELECTED);
    }

    @PostMapping(BasePath.BASE_PATH_LOGIN_USER)
    @Operation(description = Constants.INITIALIZER_LOGIN_USER)
    public ResponseEntity<?> UserLogin(
            @RequestBody LoginRequest loginRequest) throws BadRequestException {

        this.userService.userLogin(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(Constants.LOGIN_SUCCESSES);
    }

    @GetMapping(BasePath.BASE_PARAM_SEARCH_LICENSE_PLATE)
    @Operation(description = Constants.SEARCH_BY_LICENSE_PLATE)
    public ResponseEntity<?> searchByPlateVehicle(
            @RequestParam(
                    name = "licensePlate",
                    required = true,
                    value = "licensePlate") String licensePlate) throws BadRequestException {

        EntityCars car = userService.searchByLicensePlate(licensePlate);
        return ResponseEntity.ok(car);
    }

    @PatchMapping(BasePath.BASE_PATH_ALTER_LICENSE_PLATE)
    @Operation(description = Constants.UPDATE_LICENSE_PLATE)
    public ResponseEntity<?> changingWronglyRegisteredPlate(
            @RequestParam(
                    name = "idUser",
                    required = true,
                    value = "idUser") final Long idUser,
            @RequestBody UpdateLicensePlateOrModelVehicleRequest request) throws BadRequestException {

        UserResponse response = this.userService.updateLicensePlate(idUser, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping(BasePath.BASE_PATH_ALTER_VEHICLE_MODEL)
    @Operation(description = Constants.UPDATE_VEHICLE_MODEL)
    public ResponseEntity<?> changingWronglyRegisteredVehicle(
            @RequestParam(
                    name = "licensePlate",
                    required = true,
                    value = "licensePlate") final String licensePlate,
            @RequestBody UpdateLicensePlateOrModelVehicleRequest request) throws BadRequestException {

        EntityCars response = this.userService.updateVehicleModel(licensePlate, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("list/all/users")
    public List<UserEntity> listAll()throws BadRequestException{

        return userService.getAllUsers();
    }
}