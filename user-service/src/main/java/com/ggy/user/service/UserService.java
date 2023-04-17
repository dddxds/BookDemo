package com.ggy.user.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ggy.pojo.Collect;
import com.ggy.pojo.History;
import com.ggy.pojo.Notice;
import com.ggy.pojo.User;
import com.ggy.user.mapper.CollectMapper;
import com.ggy.user.mapper.HistoryMapper;
import com.ggy.user.mapper.NoticeMapper;
import com.ggy.user.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    HistoryMapper historyMapper;
    @Autowired
    NoticeMapper noticeMapper;

    public boolean updateUser(User user) {
        user.setModifyTime(LocalDateTime.now());
        int i = userMapper.updateById(user);
        if (i != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean del(Long id) {
        int i = userMapper.deleteById(id);
        if (i != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean registration(User user) {
        user.setCreatedTime(LocalDateTime.now());
        user.setCreatedUser("admin");
        user.setModifyUser("admin");
        user.setModifyTime(LocalDateTime.now());
        if (user.getPicture() == null) {
            user.setPicture("@/assets/logo.png");
        }
        int i = userMapper.insert(user);
        if (i != 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    public User findById(long id) {

        return userMapper.selectById(id);
    }

    public int uploadResource(Long id) {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();

        userUpdateWrapper.setSql("upload=upload+1 ").eq("id",id);
        scoreChange(id,5);
        return userMapper.update(null,userUpdateWrapper);
    }
    public int scoreChange(Long id ,Integer count){
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();

        userUpdateWrapper.setSql("integral=integral+"+count).eq("id",id);
        return userMapper.update(null,userUpdateWrapper);
    }
    public int addCollect(Collect collect){
        return collectMapper.insert(collect);
    }
    public int delCollect(Long id){
        return collectMapper.deleteById(id);
    }
    public int updateCollect(Collect collect){
        return collectMapper.updateById(collect);
    }
    public List<Collect> getCollect(Long id){
        return collectMapper.selectList(new QueryWrapper<Collect>().eq("userId",id));
    }
    public int addHistory(History collect){
        return historyMapper.insert(collect);
    }
    public int delHistory(Long id){
        return historyMapper.deleteById(id);
    }
    public int updateHistory(History collect){
        return historyMapper.updateById(collect);
    }
    public List<History> getHistory(Long id){
        return historyMapper.selectList(new QueryWrapper<History>().eq("userId",id));
    }
    public int addNotice(Notice notice){
        return noticeMapper.insert(notice);
    }
    public int delNotice(Long id){
        return noticeMapper.deleteById(id);
    }
    public List<Notice> getNotice(){
        return noticeMapper.selectList(null);
    }
}
