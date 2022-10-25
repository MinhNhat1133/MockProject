package com.vti.services.impl;

import java.util.UUID;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.entities.Order;
import com.vti.entities.Plan;
import com.vti.entities.RegistrationUserToken;
import com.vti.entities.User;
import com.vti.enums.UserStatus;
import com.vti.events.OnSendRegistrationUserConfirmViaEmailEvent;
import com.vti.forms.CustomerAndOrderCreateForm;
import com.vti.repositories.OrderRepository;
import com.vti.repositories.PlanRepository;
import com.vti.repositories.RegistrationUserTokenRepository;
import com.vti.repositories.UserRepository;
import com.vti.services.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepositoryy;
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RegistrationUserTokenRepository registrationUserTokenRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepositoryy.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				AuthorityUtils.createAuthorityList(user.getRole().toString()));
	}

	@Override
	public void createUser(User user) {
		// encode password
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// create user
		userRepositoryy.save(user);

		// create new user registration token
		createNewRegistrationUserToken(user);

		// send email to confirm
		sendConfirmUserRegistrationViaEmail(user.getEmail());
	}

	private void createNewRegistrationUserToken(User user) {

		// create new token for confirm Registration
		final String newToken = UUID.randomUUID().toString();
		RegistrationUserToken token = new RegistrationUserToken(newToken, user);

		registrationUserTokenRepository.save(token);
	}

	@Override
	public void sendConfirmUserRegistrationViaEmail(String email) {
		eventPublisher.publishEvent(new OnSendRegistrationUserConfirmViaEmailEvent(email));
	}

	@Override
	public User findUserByUserName(String name) {
		return userRepositoryy.findByEmail(name);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepositoryy.findByEmail(email);
	}

	@Override
	public void activeUser(String token) {
		// get token
		RegistrationUserToken registrationUserToken = registrationUserTokenRepository.findByToken(token);

		// active user
		User user = registrationUserToken.getUser();
		user.setStatus(UserStatus.ACTIVE);
		userRepositoryy.save(user);

		// remove Registration User Token
		registrationUserTokenRepository.deleteById(registrationUserToken.getId());
	}

	@Override
	public ResponseEntity<Object> createUAO(CustomerAndOrderCreateForm createCustomerAndOrderForm) {

		int test = userRepositoryy.getUserHasOrderStatusNotActiveByEmail(createCustomerAndOrderForm.getEmail());
		User user = this.userRepositoryy.findByEmail(createCustomerAndOrderForm.getEmail());
		if (this.userRepositoryy.existsByEmail(createCustomerAndOrderForm.getEmail())) { // neu ton tai email trong db
			if (test < 1) {// and ko co don hang active
				// update tai khoan hien tai
				User newUser = new User();
				newUser.setId(user.getId());
				newUser.setEmail(createCustomerAndOrderForm.getEmail());
				newUser.setPhone(createCustomerAndOrderForm.getPhone());
				newUser.setFullName(createCustomerAndOrderForm.getFullName());
				newUser.setPassword(passwordEncoder.encode(createCustomerAndOrderForm.getPassword()));
				newUser.setRole(createCustomerAndOrderForm.getRole());
				newUser.setStatus(UserStatus.NOT_ACTIVE);
				modelMapper.map(newUser, user);
				user = userRepositoryy.save(user);
			} else { // co don hang active
				return new ResponseEntity<>("Error! Tai khoan dang ton tai don hang", HttpStatus.OK);

			}

		} else { // email chua ton tai trong db
			user = new User();
			user.setEmail(createCustomerAndOrderForm.getEmail());
			user.setPhone(createCustomerAndOrderForm.getPhone());
			user.setFullName(createCustomerAndOrderForm.getFullName());
			user.setPassword(passwordEncoder.encode(createCustomerAndOrderForm.getPassword()));
			user.setRole(createCustomerAndOrderForm.getRole());
			user = userRepositoryy.save(user);
		}

		// create new user registration token
		createNewRegistrationUserToken(user);

		System.out.println(user);

//			user = userRepositoryy.findUserByEmailNotActive(user.getEmail());
//
//			// send email to confirm

		if (userRepositoryy.existsById(user.getId())) {

			Order order = new Order();
			order.setCurrentCity(createCustomerAndOrderForm.getCurrentCity());
			order.setNewCity(createCustomerAndOrderForm.getNewCity());
			order.setMovingDate(createCustomerAndOrderForm.getMovingDate());
			Plan plan = planRepository.findById(createCustomerAndOrderForm.getPlanId()).get();
			order.setPlan(plan);
			order.setCustomer(user);
			order.setIsHasApartmentAlready(createCustomerAndOrderForm.getIsHasApartmentAlready());
			order.setDistance(createCustomerAndOrderForm.getDistance());
			order.setStatus("0");
			order = orderRepository.save(order);
		}
		sendConfirmUserRegistrationViaEmail(user.getEmail());
		return new ResponseEntity<>("We have sent an email. Please check email to active account!", HttpStatus.OK);
	}

	@Override
	public User findUserByEmail4LogIn(String email) {
//		return null;
		return this.userRepositoryy.findUserByEmail4LogIn(email);
	}

	@Override
	public User findUserByEmailNotActive(String email) {
		// TODO Auto-generated method stub
		return this.userRepositoryy.findUserByEmailNotActive(email);
	}
}
