package in.fssa.onlyhomefood.interfaces;

import java.util.Set;

import in.fssa.onlyhomefood.exception.PersistenceException;

public interface Base<T> {

	public abstract Set<T> findAll() throws PersistenceException;
	public abstract void create(T object) throws PersistenceException;
	public abstract void update(int id, T object) throws PersistenceException;
	public abstract void delete(int id) throws PersistenceException;
	public abstract T findById(int id) throws PersistenceException;
}
