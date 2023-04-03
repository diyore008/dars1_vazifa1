package uz.pdp.dars1_vazifa1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dars1_vazifa1.entity.Department;
import uz.pdp.dars1_vazifa1.payload.ApiResponse;
import uz.pdp.dars1_vazifa1.payload.DepartmentDto;
import uz.pdp.dars1_vazifa1.service.DepartmentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;


    /**
     * Department LARNI QAYTARADIGAN METHOD
     * @return List<Department> </>
     */
    @GetMapping("/api/getDepartment")
    public ResponseEntity<List<Department>> getDepartment(){
        List<Department> departmentList = departmentService.getDepartment();
        return ResponseEntity.ok(departmentList);
    }


    /**
     * Department QO'SHADIGAN METHOD
     * @param departmentDto
     * @return
     * BIZGA DepartmentDto TIPIDA JSON OBJECT KELADI
     */
    @PostMapping("/api/addDepartment")
    public ResponseEntity<ApiResponse> addDepartment(@RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse = departmentService.addDepartment(departmentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }





    /**
     * Department NI TAHRIRLAYDIGAN METHOD
     * @param id
     * @param departmentDto
     * @return  ResponseEntity
     * BIZGA Id VA ResponseEntity TIPIDA JSON OBJECTI KELADI
     */
    @PutMapping("/api/updateDepartment/{id}")
    public ResponseEntity<ApiResponse> updateDepartment(@PathVariable Integer id, @RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse = departmentService.updateDepartment(id, departmentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * Department NI O'CHIRISH UCHUN ISHLATILADI
     * @param id
     * @return  ResponseEntity
     * Id TIPIDA JSON OBJECTI KELADI
     */
    @DeleteMapping("/api/deleteDepartment/{id}")
    public ResponseEntity deleteDepartment(@PathVariable Integer id){
        ApiResponse apiResponse = departmentService.deleteDepartment(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }




    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(
            MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
