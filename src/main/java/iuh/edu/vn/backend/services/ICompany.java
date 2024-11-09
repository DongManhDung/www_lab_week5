package iuh.edu.vn.backend.services;

import iuh.edu.vn.backend.models.Candidate;
import iuh.edu.vn.backend.models.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

public interface ICompany {
    boolean existsByEmail(String email);

    @Query("SELECT c.compName FROM Company c WHERE c.email = ?1")
    Company findNameByEmail(String email);

}
