package uz.pdp.dars1_vazifa1.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    @NotNull(message = "name must not be empty")
    private String name;
    @NotNull(message = "directorName must not be empty")
    private String directorName;
    @NotNull(message = "street must be not empty")
    private String street;
    private String homeNumber;
}
