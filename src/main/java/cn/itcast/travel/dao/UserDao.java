package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * @description:
 * @author: yangyh
 * @create: 2019-08-11 21:20
 */
public interface UserDao {

    User findByUserName(String username);

    void save(User user);
}
