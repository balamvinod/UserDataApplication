package com.infy.userdata.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.userdata.dto.UserDTO;
import com.infy.userdata.entity.User;
import com.infy.userdata.exception.UserDataException;
import com.infy.userdata.repository.UserDataRepository;
import com.infy.userdata.validator.UserValidator;

import jakarta.transaction.Transactional;


@Service(value="userDataService")
@Transactional
public class UserDataServiceImpl implements UserDataService {

	@Autowired
	private UserDataRepository userDataRepository;

	@Override
	public Integer addUser(UserDTO userDTO) throws UserDataException  {
		UserValidator userValidator=new UserValidator();
		userValidator.validate(userDTO);
		User user=new User();
		user.setUserId(userDTO.getUserId());
		user.setUserName(user.getUserName());
		user.setPassword(userDTO.getPassword());
		user.setPhoneNo(userDTO.getPhoneNo());
		user.setCity(userDTO.getCity());
		User u1=userDataRepository.save(user);
		return u1.getUserId();
		
		
		
	}

	@Override
	public List<UserDTO> getDetailsByUserName(String userName) throws UserDataException {
		
		List<User> opt=userDataRepository.findByUserName(userName);
		if(opt.isEmpty()) {
			throw new UserDataException("Service.NO_DETAILS_FOUND");
		}
		List<UserDTO> userDTO=new ArrayList<>();
		for(User user:opt) {
			UserDTO u1=new UserDTO();
			u1.setCity(user.getCity());
			u1.setPassword(user.getPassword());
			u1.setPhoneNo(user.getPhoneNo());
			u1.setUserId(user.getUserId());
			u1.setUserName(user.getUserName());
			userDTO.add(u1);
		}
		return userDTO;
	}

	
	
}



