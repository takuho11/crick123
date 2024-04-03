package cn.topcheer.pms2.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.topcheer.pms2.api.po.User;

public interface UserDao extends JpaRepository<User,String> {
    
}
