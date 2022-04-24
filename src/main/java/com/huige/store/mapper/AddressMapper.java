package com.huige.store.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huige.store.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author huige
 * Create on 2022/4/13 17:20
 * 收货地址持久层的接口
 */
public interface AddressMapper extends BaseMapper<Address> {
    /**
     * 插入用户的收货地址数据
     *
     * @param address 收货地址数据
     * @return 受影响的行数
     */
    public Integer insertAddress(Address address);

    /**
     * 根据用户的id统计收货地址数量
     *
     * @param uid 用户的id
     * @return 当前用户的收货地址总数
     */
    public Integer countByUid(Integer uid);

    public List<Address> findByUid(Integer uid);

    public Address findByAid(Integer aid);

    /**
     * 根据用户的uid值来修改用户的收货地址设置为非默认
     *
     * @param uid 用户的id值
     * @return 受影响的行数
     */
    public Integer updateNonDefault(Integer uid);

    public Integer updateDefaultByAid(@Param("aid") Integer aid,
                                      @Param("modifiedUser") String modifiedUser,
                                      @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据aid删除数据库的数据
     * @param aid 收货地址的id
     * @return 受影响的行数
     */
    public Integer deleteByAid(Integer aid);

    /**
     * 根据用户uid来查询当前用户最后一次被修改的收货地址数据
     * @param uid 用户id
     * @return 收获地址
     */
    public Address findListModified(Integer uid);
}
