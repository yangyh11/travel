package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @description:
 * @author: yangyh
 * @create: 2019-08-11 21:21
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUserName(String username) {

        // 1.定义sql
        String sql = "select * from tab_user where username = ?";

        // 2.执行sql
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class));
        } catch (DataAccessException e) {

        }
        return user;
    }

    @Override
    public void save(User user) {
        // 1.定义sql
        String sql = "insert into tab_user(username, password, name, birthday, sex, telephone, email) values(?,?,?,?,?,?,?)";
        // 2.执行sql
        jdbcTemplate.update(sql, user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail());
    }

}
