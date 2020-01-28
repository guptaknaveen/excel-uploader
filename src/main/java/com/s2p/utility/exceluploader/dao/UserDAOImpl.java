package com.s2p.utility.exceluploader.dao;

import com.s2p.utility.exceluploader.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private JdbcTemplate template;

    @Override
    public User saveOrUpdate(final User user) {
        String query = "insert into user (firstName, lastName, email) values (?, ?, ?)";
        template.execute(query, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException,
                    DataAccessException {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getEmail());
                return ps.execute();
            }
        });
        return null;
    }

    @Override
    public User delete(int id) {
        return null;
    }

    @Override
    public List<User> view() {
        return ;
    }

    @Override
    public User view(int id) {
        return null;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
}
