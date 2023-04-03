package uz.pdp.dars1_vazifa1.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.dars1_vazifa1.entity.Company;
import uz.pdp.dars1_vazifa1.entity.Department;
import uz.pdp.dars1_vazifa1.payload.ApiResponse;
import uz.pdp.dars1_vazifa1.payload.DepartmentDto;
import uz.pdp.dars1_vazifa1.repository.CompanyRepository;
import uz.pdp.dars1_vazifa1.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;






    /**
     * Department LARNI QAYTARADIGAN METHOD
     * @return List<Department> </>
     */
    public List<Department> getDepartment(){
        List<Department> departmentList = departmentRepository.findAll();
        return departmentList;
    }




    /**
     * Department QO'SHADIGAN METHOD
     * @param departmentDto
     * @return
     * BIZGA DepartmentDto TIPIDA JSON OBJECT KELADI
     */
    public ApiResponse addDepartment(@Valid @RequestBody  DepartmentDto departmentDto){
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new ApiResponse("Company not found", false);

        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponse("Saved department", true);
    }



    /**
     * Department NI TAHRIRLAYDIGAN METHOD
     * @param id
     * @param departmentDto
     * @return  ApiResponse
     * BIZGA Id VA ApiResponse TIPIDA JSON OBJECTI KELADI
     */
    public ApiResponse updateDepartment(Integer id, DepartmentDto departmentDto){
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new ApiResponse("Company not found", false);
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new ApiResponse("Department not found", false);

        Department department = optionalDepartment.get();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponse("Updated department", true);
    }





    /**
     * Department NI O'CHIRISH UCHUN ISHLATILADI
     * @param id
     * @return  ApiResponse
     * Id TIPIDA JSON OBJECTI KELADI
     */
    public ApiResponse deleteDepartment(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        try {
            departmentRepository.deleteById(id);
            return new ApiResponse("Deleted department", true);
        } catch (Exception e){
            return new ApiResponse("Error", false);
        }

    }
}
