package uz.pdp.dars1_vazifa1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dars1_vazifa1.entity.Address;
import uz.pdp.dars1_vazifa1.payload.ApiResponse;
import uz.pdp.dars1_vazifa1.service.AddressService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class AddressController {

    @Autowired
    AddressService addressService;

    /**
     *BARCHA address LARNI OLIB KELADIGAN METHOD
     * @return List<Address></>
     */
    @GetMapping("/api/getAddress")
    public ResponseEntity<List<Address>> getAddress(){
        List<Address> allAddress = addressService.getAddress();
        return ResponseEntity.ok(allAddress);
    }


    /**
     * Address QO'SHADIGAN METHOD
     * @param address
     * @return ResponseEntity
     * BIZGA Address tipida JSON OBJECT KELADI
     */
    @PostMapping("/api/addAddress")
    public ResponseEntity<ApiResponse> addAddress(@Valid @RequestBody Address address){
        ApiResponse apiResponse = addressService.addAddress(address);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * Address NI TAHRIRLAYDIGAN METHOD
     * @param id
     * @param address
     * @return ResponseEntity
     * Id VA Address TIPIDA JSON OBJECTI KELADI
     */
    @PutMapping("/api/updateAddress/{id}")
    public ResponseEntity<ApiResponse> updateAddress(@PathVariable Integer id, @RequestBody Address address){
        ApiResponse apiResponse = addressService.updateAddress(id, address);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * Address O'CHIRADIGAN METHOD
     * @param id
     * @return
     * Id TIPIDA JSON BERADI
     */
    @DeleteMapping("/api/deleteAddress/{id}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Integer id){
        ApiResponse apiResponse = addressService.deleteAddress(id);
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
