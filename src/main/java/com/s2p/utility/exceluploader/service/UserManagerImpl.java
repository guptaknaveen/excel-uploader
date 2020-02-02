package com.s2p.utility.exceluploader.service;

import com.s2p.utility.exceluploader.model.User;
import com.s2p.utility.exceluploader.repository.RepositoryFactory;
import com.s2p.utility.exceluploader.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagerImpl implements UserManager {

    @Autowired
    private RepositoryFactory repositoryFactory;

    public User addUser(User user) {
        UserRepository repository = repositoryFactory.getUserRepository();
        return repository.save(user);
    }
}
