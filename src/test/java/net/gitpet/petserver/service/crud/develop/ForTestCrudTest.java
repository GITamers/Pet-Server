package net.gitpet.petserver.service.crud.develop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Objects;

public class ForTestCrudTest{
	
	@Test
	public void useage(){
		// given
		UserDTO user = new UserDTO("hello", 21);
		ForTest<UserDTO> testCrud = new ForTest<UserDTO>();
		
		// when
		UserDTO readed = testCrud.read(new TestExecute<UserDTO>(){
			@Override
			public UserDTO execute(){
				return user;
			}
		});
		
		// then
		Assertions.assertEquals(readed, user);
	}
	
	private static final class UserDTO{
		
		public final String name;
		public final int age;
		
		private UserDTO(String name, int age){
			this.name = name;
			this.age = age;
		}
		
		@Override
		public int hashCode(){
			return Objects.hash(name, age);
		}
		
		@Override
		public boolean equals(Object obj){
			if(!(obj instanceof UserDTO)) return false;
			UserDTO user = (UserDTO)obj;
			return user.name.equals(this.name) && user.age == this.age;
		}
		
	}
	
}