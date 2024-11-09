package iuh.edu.vn.backend.services.Impl;

import iuh.edu.vn.backend.models.Company;
import iuh.edu.vn.backend.repositories.CompanyRepository;
import iuh.edu.vn.backend.services.ICompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyImpl implements ICompany {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public boolean existsByEmail(String email) {
        return companyRepository.existsByEmail(email);
    }

    @Override
    public Company findNameByEmail(String email) {
        return companyRepository.findNameByEmail(email);
    }
}
