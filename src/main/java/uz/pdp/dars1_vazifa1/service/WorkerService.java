package uz.pdp.dars1_vazifa1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.dars1_vazifa1.entity.Address;
import uz.pdp.dars1_vazifa1.entity.Department;
import uz.pdp.dars1_vazifa1.entity.Worker;
import uz.pdp.dars1_vazifa1.payload.ApiResponse;
import uz.pdp.dars1_vazifa1.payload.WorkerDto;
import uz.pdp.dars1_vazifa1.repository.AddressRepository;
import uz.pdp.dars1_vazifa1.repository.DepartmentRepository;
import uz.pdp.dars1_vazifa1.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AddressRepository addressRepository;

    /**
     * Worker LARNI QAYTARADIGAN METHOD
     * @return List<Worker> </>
     */
    public List<Worker> getWorker(){
        List<Worker> workerList = workerRepository.findAll();
        return workerList;
    }





    /**
     * Worker QO'SHADIGAN METHOD
     * @param workerDto
     * @return ResponseEntity
     * BIZGA WorkerDto TIPIDA JSON OBJECTI KIRIB KEALDI
     */
    public ApiResponse addWorker(WorkerDto workerDto){
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent())
            return new ApiResponse("Department not found", false);

        Address address = new Address();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        Address saveAddress = addressRepository.save(address);

        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(saveAddress);
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new ApiResponse("Saved worker", true);
    }






    /**
     * Worker NI TAHRIRLAYDIGAN METHOD
     * @param id
     * @param workerDto
     * @return ResponseEntity
     * BIZGA Id VA WorkerDto TIPIDAGI JSON OBJCETI KELADI
     */
    public ApiResponse updateWorker(Integer id, WorkerDto workerDto){
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent())
            return new ApiResponse("Department not found", false);
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return new ApiResponse("Worker not found", false);

        Address address = new Address();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        Address saveAddress = addressRepository.save(address);

        Worker worker = optionalWorker.get();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(saveAddress);
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new ApiResponse("Updated Worker", true);
    }





    public ApiResponse deleteWorker(Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        try{
            workerRepository.deleteById(id);
            return new ApiResponse("Deleted worker", true);
        } catch (Exception e){
            return new ApiResponse("Error", false);
        }
    }
}
