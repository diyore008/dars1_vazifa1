package uz.pdp.dars1_vazifa1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dars1_vazifa1.entity.Address;
import uz.pdp.dars1_vazifa1.payload.ApiResponse;
import uz.pdp.dars1_vazifa1.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    /**
     * BARCHA address LARNI OLIB KELADI
     * @return LIST<Address></>
     */
    public List<Address> getAddress(){
        List<Address> allAddress = addressRepository.findAll();
        return allAddress;
    }



    /**
     * Address QO'SHADIGAN METHOD
     * @param address
     * @return ApiResponse
     * BIZGA Address tipida JSON OBJECT KELADI
     */
    public ApiResponse addAddress(Address address){
        Address addAddress = new Address();

        addAddress.setStreet(address.getStreet());
        addAddress.setHomeNumber(address.getHomeNumber());
        addressRepository.save(addAddress);
        return new ApiResponse("Saved address", true);
    }



    /**
     * Address NI TAHRIRLAYDIGAN METHOD
     * @param id
     * @param address
     * @return ApiResponse
     * Id VA Address TIPIDA JSON OBJECTI KELADI
     */
    public ApiResponse updateAddress(Integer id, Address address){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return new ApiResponse("Address not found", false);
        Address updateAddress = optionalAddress.get();
        updateAddress.setStreet(address.getStreet());
        updateAddress.setHomeNumber(address.getHomeNumber());
        addressRepository.save(updateAddress);
        return new ApiResponse("Updated address", true);
    }



    /**
     * Address O'CHIRADIGAN METHOD
     * @param id
     * @return
     * Id TIPIDA JSON BERADI
     */
    public ApiResponse deleteAddress(Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()){
            addressRepository.deleteById(id);
            return new ApiResponse("Deleted address", true);
        }
        return new ApiResponse("Address not found", false);
    }
}
