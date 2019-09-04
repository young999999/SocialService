package com.py.friend.dao;


import com.py.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoFriendDao extends JpaRepository<NoFriend, String> {
    NoFriend findByUseridAndFriendid(String userid, String friendid);
}
