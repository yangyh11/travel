package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;

/**
 * @description:
 * @author: yangyh
 * @create: 2019-08-11 21:20
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 注册
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        // 1.根据用户名查询用户对象
        User u = userDao.findByUserName(user.getUsername());
        // 判断user是否为null
        if (u != null) {
            // 用户名存在，注册失败
            return false;
        } else {
            // 2.保存用户信息
            userDao.save(user);
            return true;
        }
    }

}
