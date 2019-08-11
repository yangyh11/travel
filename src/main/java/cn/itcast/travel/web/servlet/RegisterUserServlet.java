package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 注解WebServlet用来描述一个Servlet
 * 属性name描述Servlet的名字,可选
 * 属性urlPatterns定义访问的URL,或者使用属性value定义访问的URL或者直接写URL.(定义访问的URL是必选属性)
 */
@WebServlet(value = "/registerUserServlet")
public class RegisterUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取数据
        Map<String, String[]> map = request.getParameterMap();
        // 2.封装数据
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 3.调用service完成注册
        UserService userService = new UserServiceImpl();
        boolean flag = userService.regist(user);
        ResultInfo info = new ResultInfo();
        // 4.响应结果
        if (flag) {
            // 注册成功
            info.setFlag(true);
        } else {
            // 注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }

        // 将info对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        // 将json数据写回客户端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
