package model;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.Collection;

public class RepositorioGuardarropas implements WithSimplePersistenceUnit {

	private static final RepositorioGuardarropas INSTANCE = new RepositorioGuardarropas();

	public static RepositorioGuardarropas instance() {
		return INSTANCE;
	}

	public Collection<Guardarropa> obtenerTodos() {
		return entityManager().createQuery("FROM Guardarropa", Guardarropa.class).getResultList();
	}

	public Guardarropa obtener(long id) {
		return entityManager()
				.createQuery("FROM Guardarropa WHERE id = :id", Guardarropa.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
