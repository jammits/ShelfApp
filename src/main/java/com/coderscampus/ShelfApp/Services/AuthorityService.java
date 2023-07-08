package com.coderscampus.ShelfApp.Services;

import com.coderscampus.ShelfApp.Domain.Authorities;
import com.coderscampus.ShelfApp.Repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepo;
    public Authorities save(Authorities authority) {

        return authorityRepo.save(authority);
    }

    public List<Authorities> findAll(){
        return authorityRepo.findAll();
    }

    public Authorities findById(Integer id) {
        return authorityRepo.findById(id).get();
    }

}
