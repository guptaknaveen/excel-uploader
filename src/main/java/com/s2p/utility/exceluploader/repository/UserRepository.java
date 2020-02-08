package com.s2p.utility.exceluploader.repository;

import com.s2p.utility.exceluploader.model.User;

public interface UserRepository extends Repository {
    User save(User user);
}
