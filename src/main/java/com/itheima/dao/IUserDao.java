package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface IUserDao {
    /**
     * 根据id查询
     * @param userId
     * @return
     */
    //@Select("select * from user where id=#{uid}")
    User findById(Integer userId);

    /**
     *查询所有操作
     * @return
     */
    //@Select("select * from user")
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     * @return
     */
    //@Insert("insert into user(username,birthday,sex,address)values(#{username},#{birthday},#{sex},#{address})")
    int saveUser(User user);

    /**
     * 更新用户
     * @param user
     * @return  影响数据库的行数
     */
    @Update("update user set username=#{username},birthday=#{birthday},sex=#{sex},  address=#{address} where id=#{id}")
    int updateUser(User user);

    /**
     * 删除用户
     * @param user
     * @return
     */
    @Delete("delete from user where id = #{uid}")
    int deleteUser(int user);

    /**
     * 根据名称模糊查询
     * @param username
     * @return
     */
    @Select("select * from user where username like #{username}")
    List<User> findByName(String username);

    /**
     * 查询总记录数
     * @return
     */
    int findTotal();
    /**
     * 根据QueryVo查询所有用户
     * @param queryVo
     */
    List<User> findByVo(QueryVo queryVo);

    /**
     * 查询条件是map类型
     * @param map
     * @return
     */
    List<User> findByMap(Map<String, Object> map);
    /**
     *
     */
}
