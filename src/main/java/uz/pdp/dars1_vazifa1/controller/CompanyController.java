package uz.pdp.dars1_vazifa1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.dars1_vazifa1.entity.Company;
import uz.pdp.dars1_vazifa1.service.CompanyService;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    /**
     * COMPANY LARNI QAYTARADIGAN METHOD
     * @return List<Company> </>
     */
    @GetMapping("/api/getCompany")
    public List<Company> getCompany(){
        List<Company> companies = companyService.getCompany();
        return companies;
    }
}
