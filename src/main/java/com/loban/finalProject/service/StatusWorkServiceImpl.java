package com.loban.finalProject.service;

import com.loban.finalProject.model.StatusWork;
import com.loban.finalProject.repository.StatusWorkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusWorkServiceImpl implements StatusWorkService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DateEventServiceImpl.class);

    @Autowired
    private StatusWorkRepository statusWorkRepository;

    @Override
    public StatusWork findByStatusWork(String status) {
        StatusWork statusWork = statusWorkRepository.findByName(status);
        if (statusWork != null) {
            log.info("Find StatusWork by name {}, ID {}", status, statusWork.getId());
            return statusWork;
        }
        return null;
    }

    @Override
    public StatusWork findById(Long id) {
        StatusWork statusWork = statusWorkRepository.findOne(id);
        if (statusWork != null) {
            log.info("Find StatusWork by ID {}", statusWork.getId());
            return statusWork;
        }
        log.info("--------------------------- Not find StatusWork by ID {}", id);
        return null;
    }

    @Override
    public List<StatusWork> findAllStatusWork() {
        log.info("Find all StatusWorks ");
        return statusWorkRepository.findAll();
    }

    @Override
    public StatusWork save(StatusWork statusWork) {
        log.info("Save StatusWork by ID {}", statusWork.getId());
        StatusWork saveStatusWork = statusWorkRepository.save(statusWork);
        return saveStatusWork;
    }

    @Override
    public void update(StatusWork statusWork) {
        log.info("Update StatusWork by ID {}", statusWork.getId());
        statusWorkRepository.save(statusWork);
    }

    @Override
    public void delete(Long id) {
        StatusWork statusWork = statusWorkRepository.findOne(id);
        if (statusWork!=null){
            log.info("Save StatusWork by ID {}", statusWork.getId());
            statusWorkRepository.delete(statusWork);
        }
    }
}
