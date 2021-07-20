package com.aoyia.machineshop.service;

import com.aoyia.machineshop.domain.Status;
import com.aoyia.machineshop.exception.BadRequestException;
import com.aoyia.machineshop.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Log4j2
public class StatusService {
    private final StatusRepository statusRepository;

    public Status find(Long id) {
        return statusRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("Object not found!"));
    }

    public Page<Status> find(Pageable pageable, Boolean excluded){
        if (excluded == null){
            return this.statusRepository.findAll(pageable);
        }
        return this.statusRepository.findAll(pageable, excluded);
    }

    public Page<Status> find(Pageable pageable, Status status, String searchType){
        if (status == null){
            throw new RuntimeException("Invalid arguments!");
        }
        if (searchType == null){
            searchType = "and";
        }
        if (!searchType.equals("or") && !searchType.equals("and")){
            throw new RuntimeException("Invalid query method. The available methods are 'and' or 'or'.");
        }

        Specification<Status> myQuery = Specification.where(null);
        if (status.getId() != null){
            myQuery = searchType.equals("or") ? myQuery.or(StatusRepository.hasId(status)) : myQuery.and(StatusRepository.hasId(status));
        }
        if (status.getName() != null){
            myQuery = searchType.equals("or") ? myQuery.or(StatusRepository.hasName(status)) : myQuery.and(StatusRepository.hasName(status));
        }
        if (status.getExcluded() != null){
            myQuery = searchType.equals("or") ? myQuery.or(StatusRepository.hasExcluded(status)) : myQuery.and(StatusRepository.hasExcluded(status));
        }
        if (status.getModified() != null){
            myQuery = searchType.equals("or") ? myQuery.or(StatusRepository.hasModified(status)) : myQuery.and(StatusRepository.hasModified(status));
        }

        return this.statusRepository.findAll(myQuery, pageable);
    }

    public Page<Status> find(Pageable pageable, ArrayList<Status> statusList, String searchType){
        if (statusList == null || statusList.isEmpty() || searchType == null){
            return this.statusRepository.findAll(pageable);
        }
        if (!searchType.equals("or") && !searchType.equals("and")){
            throw new RuntimeException("Invalid query method. The available methods are 'and' or 'or'.");
        }

        Specification<Status> myQuery = Specification.where(null);
        for (Status status : statusList){
            Specification<Status> mySubQuery = Specification.where(null);
            if (status.getId() != null){
                mySubQuery = mySubQuery.and(StatusRepository.hasId(status));
            }
            if (status.getName() != null){
                mySubQuery = mySubQuery.and(StatusRepository.hasName(status));
            }
            if (status.getExcluded() != null){
                mySubQuery = mySubQuery.and(StatusRepository.hasExcluded(status));
            }
            if (status.getModified() != null){
                mySubQuery = mySubQuery.and(StatusRepository.hasModified(status));
            }
            myQuery = searchType.equals("or") ? myQuery.or(mySubQuery) : myQuery.and(mySubQuery);
        };

        return this.statusRepository.findAll(myQuery, pageable);
    }

    public Status create(Status status){
        if (status == null){
            throw new RuntimeException("Null object error!");
        }
        status.setExcluded(Boolean.FALSE);
        status.setModified(Boolean.FALSE);
        return this.statusRepository.save(status);
    }

    public void delete(Long id){
        Status status = this.find(id);
        status.setExcluded(Boolean.TRUE);
        this.statusRepository.save(status);
    }

    public Status restore(Long id){
        Status status = this.find(id);
        if (status.getExcluded() != null && !status.getExcluded()){
            throw new RuntimeException("Object has not been deleted!");
        }
        status.setExcluded(Boolean.FALSE);
        return statusRepository.save(status);
    }

    public Status replace(Status status){
        if (status == null){
            throw new RuntimeException("Null object error!");
        }
        if (!statusRepository.existsById(status.getId())){
            throw new RuntimeException("Object not found!");
        }
        status.setModified(Boolean.TRUE);
        return statusRepository.save(status);
    }
}
