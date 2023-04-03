package uz.pdp.dars1_vazifa1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dars1_vazifa1.entity.Address;
import uz.pdp.dars1_vazifa1.entity.Company;
import uz.pdp.dars1_vazifa1.payload.ApiResponse;
import uz.pdp.dars1_vazifa1.payload.CompanyDto;
import uz.pdp.dars1_vazifa1.repository.AddressRepository;
import uz.pdp.dars1_vazifa1.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;

    /**
     * COMPANY LARNI QAYTARADIGAN METHOD
     * @return List<Company> </>
     */
    public List<Company> getCompany(){
        List<Company> allCompany = companyRepository.findAll();
        return allCompany;
    }




    /**
     * COMPANY QOSHADIGAN METHOD
     * @param companyDto
     * @return ApiResponse
     * BIZGA CompanyDto TIPIDA JSON OBJECKT KELADI
     */
    public ApiResponse addCompany(CompanyDto companyDto){
        Address address = new Address();
        address.setHomeNumber(companyDto.getHomeNumber());
        address.setStreet(companyDto.getStreet());
        Address saveAddress = addressRepository.save(address);

        Company company = new Company();
        company.setCorpName(companyDto.getName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(saveAddress);
        companyRepository.save(company);
        return new ApiResponse("Saved Company", true);
    }









    /**
     * COMPANY NI TAHRIRLAYDIGAN METHOD
     * @param id
     * @param companyDto
     * @return ApiResponse
     * BIZGA id VA CompanyDto TIPIDA JSON OBJECTI KELADI
     */
    public ApiResponse updateCompany(Integer id, CompanyDto companyDto){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new ApiResponse("Company not found", false);
        Address address = new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address saveAddress = addressRepository.save(address);

        Company company = optionalCompany.get();
        company.setCorpName(companyDto.getName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(saveAddress);
        companyRepository.save(company);
        return new ApiResponse("Updated company", true);
    }




    public ApiResponse deleteCompany(Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        try {
            companyRepository.deleteById(id);
            return new ApiResponse("Deleted company", true);
        } catch (Exception e){
            return new ApiResponse("Error", false);
        }
    }
}
