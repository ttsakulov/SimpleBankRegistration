package org.example.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.interfaces.User;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Corporate implements User {
    private String companyName;
    private String director;
    private String userId;
    private String phone;
    private String email;
    private String TXN;

    public Corporate(String companyName, String director, String phone, String email, String TXN) {
        this.companyName = companyName;
        this.director = director;
        userId = UUID.randomUUID().toString();
        this.phone = phone;
        this.email = email;
        this.TXN = TXN;
    }

    @Override
    public String getName() {
        return companyName;
    }
}
