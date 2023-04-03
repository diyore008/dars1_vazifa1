package uz.pdp.dars1_vazifa1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dars1_vazifa1.entity.Company;
import uz.pdp.dars1_vazifa1.payload.ApiResponse;
import uz.pdp.dars1_vazifa1.payload.CompanyDto;
import uz.pdp.dars1_vazifa1.service.CompanyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    /**
     * COMPANY LARNI QAYTARADIGAN METHOD
     * @return List<Company> </>
     */
    @GetMapping("/api/getCompany")
    public ResponseEntity<List<Company>> getCompany(){
        List<Company> companies = companyService.getCompany();
        return ResponseEntity.ok(companies);
    }


    /**
     * COMPANY QOSHADIGAN METHOD
     * @param companyDto
     * @return ResponseEntity
     * BIZGA CompanyDto TIPIDA JSON OBJECKT KELADI
     */
    @PostMapping("/api/addCompany")
    public ResponseEntity<ApiResponse> addCompany(@Valid @RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = companyService.addCompany(companyDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * COMPANY NI TAHRIRLAYDIGAN METHOD
     * @param id
     * @param companyDto
     * @return ResponseEntity
     * BIZGA id VA CompanyDto TIPIDA JSON OBJECTI KELADI
     */
    @PutMapping("/api/updateCompany/{id}")
    public ResponseEntity<ApiResponse> updateCompany(@PathVariable Integer id, @RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = companyService.updateCompany(id, companyDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * COMPANY NI O'CHIRADIGAN METHOD
     * @param id
     * @return ResponseEntity
     * BIZGA Id TIPIDA JSON OBJECTI KELADI
     */
    @DeleteMapping("/api/deleteCompany/{id}")
    public ResponseEntity<ApiResponse> deleteCompany(@PathVariable Integer id){
        ApiResponse apiResponse = companyService.deleteCompany(id);
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
