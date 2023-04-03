package uz.pdp.dars1_vazifa1.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
    @NotNull(message = "name must be not empty")
    private String name;
    @NotNull(message = "phone number be not empty")
    private String phoneNumber;
    @NotNull(message = "street must be not empty")
    private String street;
    @NotNull(message = "home number must be not empty")
    private String homeNumber;
    @NotNull(message = "department id must be not empty")
    private Integer departmentId;
}
