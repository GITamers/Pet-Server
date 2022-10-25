package net.gitpet.petserver.service.crud;

import org.springframework.stereotype.Service;

@Service
public interface Crud<V>{
	
	void create(Object... args);
	V read(Object... args);
	void update(Object ...args);
	void delete(Object... args);
	
}