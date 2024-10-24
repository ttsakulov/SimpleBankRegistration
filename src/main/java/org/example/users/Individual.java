package org.example.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.interfaces.User;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class Individual implements User {
    private String firstName;
    private String lastName;
    private String userId;
    private String phone;
    private String email;
    private String ITN;

    public Individual(String firstName, String lastName, String phone, String email, String ITN) {
        this.email = email;
        this.firstName = firstName;
        userId = UUID.randomUUID().toString();
        this.lastName = lastName;
        this.phone = phone;
        this.ITN = ITN;
    }

    @Override
    public String getName() {
        return firstName + " " + lastName;
    }
}
