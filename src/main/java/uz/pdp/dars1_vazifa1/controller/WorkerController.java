package uz.pdp.dars1_vazifa1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dars1_vazifa1.entity.Worker;
import uz.pdp.dars1_vazifa1.payload.ApiResponse;
import uz.pdp.dars1_vazifa1.payload.WorkerDto;
import uz.pdp.dars1_vazifa1.service.WorkerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WorkerController {

    @Autowired
    WorkerService workerService;

    /**
     * Worker LARNI QAYTARADIGAN METHOD
     * @return List<Worker> </>
     */
    @GetMapping("/api/getWorker")
    public ResponseEntity<List<Worker>> getWorker(){
        List<Worker> workerList = workerService.getWorker();
        return ResponseEntity.ok(workerList);
    }


    /**
     * Worker QO'SHADIGAN METHOD
     * @param workerDto
     * @return ResponseEntity
     * BIZGA WorkerDto TIPIDA JSON OBJECTI KIRIB KEALDI
     */
    @PostMapping("/api/addWorker")
    public HttpEntity<ApiResponse> addWorker(@RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.addWorker(workerDto);

        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * Worker NI TAHRIRLAYDIGAN METHOD
     * @param id
     * @param workerDto
     * @return ResponseEntity
     * BIZGA Id VA WorkerDto TIPIDAGI JSON OBJCETI KELADI
     */
    @PutMapping("/api/updateWorker/{id}")
    public ResponseEntity<ApiResponse> updateWorker(@PathVariable Integer id,@Valid @RequestBody WorkerDto workerDto){
        ApiResponse apiResponse= workerService.updateWorker(id, workerDto);

        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }



    @DeleteMapping("/api/deleteWorker/{id}")
    public ResponseEntity<ApiResponse> deleteWorker(@PathVariable Integer id){
        ApiResponse apiResponse = workerService.deleteWorker(id);
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
