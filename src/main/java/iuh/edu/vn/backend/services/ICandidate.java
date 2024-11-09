package iuh.edu.vn.backend.services;

import iuh.edu.vn.backend.models.Candidate;

import java.util.List;

public interface ICandidate {
    public void add(Candidate candidate);
    public void update(Candidate candidate);
    public void delete(long id);
    public boolean existsByEmail(String email);
    public List<Candidate> getFullNameByEmail(String email);
}
