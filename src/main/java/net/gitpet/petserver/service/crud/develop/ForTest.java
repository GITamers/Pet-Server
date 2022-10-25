package net.gitpet.petserver.service.crud.develop;

import net.gitpet.petserver.service.crud.Crud;


public final class ForTest<V extends Object> implements Crud<V>{
	
	@Override
	public void create(Object... args){
		return;
	}
	
	@Override
	public V read(Object... args){
		TestExecute execute = (TestExecute) args[0];
		return (V)(execute.execute());
	}
	
	@Override
	public void update(Object... args){
		return;
	}
	
	@Override
	public void delete(Object... args){
		return;
	}
	
}