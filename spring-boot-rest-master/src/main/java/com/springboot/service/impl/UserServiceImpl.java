package com.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.dao.UserDao;
import com.springboot.model.LoginDto;
import com.springboot.model.User;
import com.springboot.model.UserDto;
import com.springboot.service.UserService;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(int id) {
		userDao.deleteById(id);
	}

	@Override
	public User findOne(String userName) {
		return userDao.findByUserName(userName);
	}

	@Override
	public User findById(int id) {
		Optional<User> optionalUser = userDao.findById(id);
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}

    @Override
    public UserDto update(UserDto userDto) {
        User user = findById(userDto.getId());
        if(user != null) {
            BeanUtils.copyProperties(userDto, user, "password", "userName");
            userDao.save(user);
        }
        return userDto;
    }

    @Override
    public User save(UserDto user) {
	    User newUser = new User();
	    newUser.setUserName(user.getUserName());
	    newUser.setFirstName(user.getFirstName());
	    newUser.setLastName(user.getLastName());
	    newUser.setPassword(user.getPassword());
		newUser.setAge(user.getAge());
		newUser.setSalary(user.getSalary());
        return userDao.save(newUser);
    }
    
    public User login(LoginDto loginDto) {
		Optional<User> userOpt = userDao.findByUserNameAndPassword(loginDto.getUserName(), loginDto.getPassword());

		if (userOpt != null && userOpt.isPresent()) {
			//return mapper.convertEntityToDto(user.get());
			User user = userOpt.get();
			System.out.println("user = " + user);
			return user;
		} else {
			try {
				throw new Exception(HttpStatus.NOT_FOUND.toString()/*, UserApplicationConstants.USER_LOGIN_FAILURE*/);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
