package iuh.edu.vn.backend.services.Impl;

import iuh.edu.vn.backend.models.Candidate;
import iuh.edu.vn.backend.repositories.CandidateRepository;
import iuh.edu.vn.backend.services.ICandidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateImpl implements ICandidate {

    @Autowired
    private CandidateRepository candidateRepository;

    //Du lieu phan trang
    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy,
                        String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return candidateRepository.findAll(pageable);
    }

    @Override
    public void add(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    @Override
    public void update(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    @Override
    public void delete(long id) {
        candidateRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return candidateRepository.existsByEmail(email);
    }

    @Override
    public List<Candidate> getFullNameByEmail(String email) {
        return candidateRepository.getFullNameByEmail(email);
    }

}
