package com.projectManagementTool;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Value("${jwt.secret}")
    private String jwtSecret;
  
	
   @Autowired
   private UserRepository userRepository;
   
	@Override
	public User findUserProfileByJwt(String jwt) throws Exception {
		// TODO Auto-generated method stub
		String email=JwtProvider.getEmailFromToken(jwt,jwtSecret);
		return findUserByEmail(email);
	}

	@Override
	public User findUserByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		
		User user=userRepository.findByEmail(email);
		if(user==null) {
			throw new Exception("user not found");
		}
		return user;
	}

	@Override
	public User findUserById(Long userId) throws Exception {
		// TODO Auto-generated method stub
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isEmpty()) {
			throw new Exception("user not found");
		}
		return optionalUser.get();
	}

	@Override
	public User updateusersProjectSize(User user, int number) {
		// TODO Auto-generated method stub
		user.setProjectSize(user.getProjectSize()+number);

		return userRepository.save(user);
	}

}







//import com.projectManagementTool.JwtProvider;
//import com.projectManagementTool.User;
//import com.projectManagementTool.UserRepository;
//import com.projectManagementTool.UserService;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserServiceImpl implements UserService {
//	
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//    private final JwtProvider jwtProvider;
//
//    // Inject jwt.secret from application.properties
//    @Value("${jwt.secret}")
//    private String jwtSecret;
//
//    public UserServiceImpl(UserRepository userRepository,
//                           PasswordEncoder passwordEncoder,
//                           AuthenticationManager authenticationManager,
//                           JwtProvider jwtProvider) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationManager = authenticationManager;
//        this.jwtProvider = jwtProvider;
//    }
//
//    
//    @Autowired
//    private UserRepository userRepository;
//    
// 	@Override
// 	public User findUserProfileByJwt(String jwt) throws Exception {
// 		// TODO Auto-generated method stub
// 		String email=JwtProvider.getEmailFromToken(jwt,jwtSecret);
// 		return findUserByEmail(email);
// 	}
//
// 	@Override
// 	public User findUserByEmail(String email) throws Exception {
// 		// TODO Auto-generated method stub
// 		
// 		User user=userRepository.findByEmail(email);
// 		if(user==null) {
// 			throw new Exception("user not found");
// 		}
// 		return user;
// 	}
//
// 	@Override
// 	public User findUserById(Long userId) throws Exception {
// 		// TODO Auto-generated method stub
// 		Optional<User> optionalUser = userRepository.findById(userId);
// 		if(optionalUser.isEmpty()) {
// 			throw new Exception("user not found");
// 		}
// 		return optionalUser.get();
// 	}
//
// 	@Override
// 	public User updateusersProjectSize(User user, int number) {
// 		// TODO Auto-generated method stub
// 		user.setProjectSize(user.getProjectSize()+number);
//
// 		return userRepository.save(user);
// 	}
//}
//
//
//




