package uz.pdp.dars1_vazifa1.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.dars1_vazifa1.entity.Company;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    @NotNull(message = "name must be not empty")
    private String name;
    private Integer companyId;
}
