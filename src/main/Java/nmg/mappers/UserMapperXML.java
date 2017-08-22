package nmg.mappers;

import java.util.List;

import nmg.entitys.UserEntity;
/**
 *  极简xml版本 极简xml版本保持映射文件的老传统，优化主要体现在不需要实现dao的是实现层，系统会自动根据方法名在映射文件中找对应的sql.
 *  无配置文件注解版、极简xml版本两种模式各有特点，注解版适合简单快速的模式，其实像现在流行的这种微服务模式，一个微服务就会对应一个自已的数据库，
 *  多表连接查询的需求会大大的降低，会越来越适合这种模式。
 *   老传统模式比适合大型项目，可以灵活的动态生成SQL，方便调整SQL，也有痛痛快快，洋洋洒洒的写SQL的感觉。
 */
public interface UserMapperXML {
	
	List<UserEntity> getAll();
	
	UserEntity getOne(Long id);

	UserEntity getOneByName(String userName);

	void insert(UserEntity user);

	void update(UserEntity user);

	void delete(Long id);

	void test(UserEntity user);


}