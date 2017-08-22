package nmg.controllers;

import java.util.List;

import nmg.entitys.UserEntity;
import nmg.enums.UserSexEnum;
import nmg.mappers.UserMapperAn;
import nmg.mappers.UserMapperXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
	
	@Autowired
	private UserMapperXML userMapper;

    @Autowired
    private UserMapperAn userMapperAn;

	@RequestMapping("/getUsers")
	public List<UserEntity> getUsers() {
		List<UserEntity> users=userMapper.getAll();
		return users;
	}

    @RequestMapping("/getUsersAn")
    public List<UserEntity> getUsersAn() {
        List<UserEntity> users=userMapperAn.getAll();
        return users;
    }
	
    @RequestMapping("/getUser")
    public UserEntity getUser(Long id) {
    	UserEntity user=userMapper.getOne(id);
        return user;
    }
    
    @RequestMapping("/add")
    public void save(UserEntity user) {
    	userMapper.insert(user);
    }
    
    @RequestMapping(value="update")
    public void update(UserEntity user) {
    	userMapper.update(user);
    }
    
    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
    	userMapper.delete(id);
    }

    @RequestMapping("/test")
    public void test(@RequestParam(value="name", defaultValue="World") String name ,@RequestParam(value="age", defaultValue="10") String age ) {
        System.out.println("###test 被调用 ！！！"+name);
	    UserEntity userEntity = new UserEntity();
		userEntity.setUserName(name);
		userEntity.setUserSex(UserSexEnum.MAN);
		userEntity.setPassWord("123");
		userMapper.insert(userEntity);

    }


}