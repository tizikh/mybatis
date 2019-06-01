package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {

    private InputStream in;

    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws Exception {
        //读取配置文件
        in= Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory的构建对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //使用构建者工厂对象SqlSessionFactory
        factory=builder.build(in);
        //使用工厂创建session对象
        session = factory.openSession();
        //使用SqlSession创建dao代理类
        userDao = session.getMapper(IUserDao.class);
    }
    @After
    public void destroy() throws Exception{
        session.commit();
        //释放资源
        in.close();
        session.close();
    }
    /**
     *测试查询所有
     */
    @Test
    public void testFindAll() {
        //使用代理对象查询所有
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
    /**
     * 测试保存
     */
    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("阿福");
        user.setAddress("安徽铜陵");
        user.setSex("男");
        user.setBirthday(new Date());
        System.out.println("保存之前："+user);
        //执行保存方法
        userDao.saveUser(user);
        System.out.println("保存之后："+user);
    }
    /**
     * 测试更新
     */
    @Test
    public void testUpdateUser(){
        //根据id查询
        User user = userDao.findById(41);
        //更新操作
        user.setUsername("老王");
        int res = userDao.updateUser(user);
        System.out.println(res);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDeleteUser(){
        int res = userDao.deleteUser(53);
        System.out.println(res);
    }
    /**
     * 测试模糊查询操作
     */
    @Test
    public void testFindByName(){
        List<User> users = userDao.findByName("%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }
    /**
     * 测试查询总记录数
     */
    @Test
    public void testFindTotal(){
        int total = userDao.findTotal();
        System.out.println(total);
    }
    /**
     * 测试包装类作为参数
     */
    @Test
    public void testFindByVo(){
        User user = new User();
        QueryVo queryVo = new QueryVo();
        user.setUsername("%福%");
        queryVo.setUser(user);
        List<User> byVo = userDao.findByVo(queryVo);
        for (User user1 : byVo) {
            System.out.println(user1);
        }
    }
    /**
     *测试查询参数为Map
     */
    @Test
    public void testFindByMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("id",52);
        map.put("username","阿福");
        List<User> byMap = userDao.findByMap(map);
        for (User user : byMap) {
            System.out.println(user);
        }

    }
}



