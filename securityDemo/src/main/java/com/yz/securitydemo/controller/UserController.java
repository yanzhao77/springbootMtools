package com.yz.securitydemo.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.yz.securitydemo.entity.Message;
import com.yz.securitydemo.entity.Users;
import com.yz.securitydemo.mapper.MessageMapper;
import com.yz.securitydemo.mapper.UserMapper;
import com.yz.securitydemo.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

@Validated
@Slf4j
@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	private UserServiceI userServiceI;

	@Autowired
	public MessageMapper messageMapper;

	@Resource
	private UserMapper userMapper;

	@PostMapping("/createUser")
	public ModelAndView createUser(@RequestBody @Validated Users user, BindingResult rst, RedirectAttributes attr) {
		ModelAndView view = new ModelAndView();
		if (rst.hasErrors()) {
			List<ObjectError> allErrors = rst.getAllErrors();
			allErrors.forEach(System.out::println);
			view.setViewName("error");
			view.addObject("message", allErrors);
		}
		int createUser = userServiceI.createUser(user);
		view.addObject("num", createUser);
		return view;
	}

	@RequestMapping("/toUser")
	public ModelAndView toUser(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		Users userinfo = null;
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication auth = securityContext.getAuthentication();
		if (auth != null) {
			userinfo = (Users) auth.getPrincipal();
		}
		if (StringUtils.isEmpty(userinfo)) {
			modelAndView.setViewName("error");
			modelAndView.addObject("message", "user is not find");
			return modelAndView;
		}
		modelAndView.setViewName("user/toUser");
		modelAndView.addObject("user", userinfo);
		return modelAndView;
	}

	@RequestMapping("/list")
//	@PreAuthorize("hasAuthority('admin')")
//	@PreAuthorize("hasRole('role_admin')")
	public String list(Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<Users> pg = new PageInfo<Users>(userMapper.getAllUser());

		Message message = new Message();
		Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		message.setMsgData("UserController.list");
		message.setMsgProducer(user.getId());
		message.setMsgConsumer(user.getId());
		message.setId(UUID.randomUUID().toString());
		message.setCreatetime(new Date());
		message.setMqLevel(message.getMsgLevel());
		messageMapper.insert(message);

		log.info(user.toString());
		model.addAttribute("users", pg);
		return "user";
	}

	@DeleteMapping("/deleteById")
	public ModelAndView deleteById(@NotNull @Size(max = 10000, min = 1) int id, BindingResult rst,
			RedirectAttributes attr) {
		ModelAndView view = new ModelAndView();
		if (rst.hasErrors()) {
			List<ObjectError> allErrors = rst.getAllErrors();
			allErrors.forEach(System.out::println);
			view.setViewName("error");
			view.addObject("message", allErrors);
		}
		int deleteById = userServiceI.deleteById(id);
		view.addObject("message", deleteById);
		return view;
	}

	@PostMapping("/updateById")
	public ModelAndView updateById(@NotNull @Size(max = 16, min = 6) String name,
			@NotNull @Size(max = 18, min = 6) String password, @NotNull @Size(max = 10000, min = 1) String id,
			BindingResult rst, RedirectAttributes attr) {
		ModelAndView view = new ModelAndView();
		if (rst.hasErrors()) {
			List<ObjectError> allErrors = rst.getAllErrors();
			allErrors.forEach(System.out::println);
			view.setViewName("error");
			view.addObject("message", "user is not find");
		}
		int updateById = userServiceI.updateById(name, password, id);
		view.addObject("message", updateById);
		return view;
	}
}
