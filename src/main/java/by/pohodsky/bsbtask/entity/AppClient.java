package by.pohodsky.bsbtask.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AppClient {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    String id;

    @NotBlank(message = "firstName cannot be null")
    String firstName;

    @NotBlank(message = "lastName cannot be null")
    String lastName;

    String patronymic;

    @NotBlank(message = "phoneNumber cannot be null")
    String phoneNumber;

    @NotBlank(message = "email cannot be null")
    @Email(regexp = ".*@.*\\..*", message = "is not email")
    String email;

    Integer status;

}
