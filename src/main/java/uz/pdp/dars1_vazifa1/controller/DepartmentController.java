package uz.pdp.dars1_vazifa1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.dars1_vazifa1.entity.Department;

import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/api/getDepartment")
    public List<Department> getDepartment(){

    }
}
