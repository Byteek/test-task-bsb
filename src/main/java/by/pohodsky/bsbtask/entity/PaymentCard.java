package by.pohodsky.bsbtask.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCard {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    String id;

    @NotBlank
    String cardNumber;

    @NotBlank
    String currency;

    @NotBlank
    String type;

    @NotBlank
    String linkedAppClientPhoneNumber;
}
