package com.example.ms.examplems.controller;
import com.example.ms.examplems.model.request.UpdateUserDetailsModel;
import com.example.ms.examplems.model.request.UserDetailsRequestModel;
import com.example.ms.examplems.model.response.UserRest;
import com.example.ms.examplems.userService.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;


@RestController
@RequestMapping("users")
public class UserController {

    /**
     * mapa para operaciones de users.
     */
    private Map<String, UserRest> users;

    /**
     * servicio user.
     */
    @Autowired
    private UserService userService;

    /**
     *
     * @param page
     * @param limit
     * @return Un mensaje con el paginado y el limite
     */
    @GetMapping
    public String getUsers(
            @RequestParam(value = "page", defaultValue = "1") final int page,
            @RequestParam(value = "limit") final int limit) {
        return "Users called with page: " + page + " and limit: " + limit;
    }

    /**
     *
     * @param id
     * @return el user correspondiente al id
     */
    @GetMapping(path = "{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<UserRest> getUser(@PathVariable final String id) {

        String firstName = null;

        if (users.containsKey(id)) {
            return new ResponseEntity<>(users.get(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     *
     * @param userDetailsRequestModel
     * @return el usuario creado
     */
    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE
    }, produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody
            final UserDetailsRequestModel userDetailsRequestModel) {

        UserRest user = userService.createUser(userDetailsRequestModel);
        return new ResponseEntity<UserRest>(user, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @param userModel
     * @return el usuario actualizado
     */
    @PutMapping(path = "/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    }, produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public UserRest updateUser(
            @PathVariable final String id,
            @Valid @RequestBody final UpdateUserDetailsModel userModel) {

        UserRest userDetails = users.get(id);
        userDetails.setLastName(userModel.getLastName());
        userDetails.setFirstName(userModel.getFirstName());

        users.put(id, userDetails);

        return userDetails;
    }

    /**
     *
     * @param id
     * @return salida vacia
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final String id) {
        users.remove(id);

        return ResponseEntity.noContent().build();
    }
}
