package net.gitpet.petserver.develop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ForTestCrudTest{
	
	@Test
	public void useage(){
		// given
		UserDTO user = new UserDTO("hello", 21);
		ForTest<UserDTO> testCrud = new ForTest<>();
		
		// when
		UserDTO readed = testCrud.read(()->{return user});
		
		// then
		()-> Assertions.assertEquals(readed, user);
	}
	
	private static final class UserDTO{
		
		public final string name;
		public final int age;
		
		private UserDTO(String name, int age){
			this.name = name;
			this.age = age;
		}
		
		@Override
		public int hashCode(){
			Objects.hash(name, age);
		}
		
		@Override
		public boolean equals(Object obj){
			if!((obj instanceof UserDTO)) return false;
			UserDTo user = (UserDTO)obj;
			return user.name.equals(this.name) && user.age == this.age;
		}
		
	}
	
}