package fr.polytech.simulatorspring.exception;

public class NotFoundException extends RuntimeException{

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException() {
		super("La ressource demandée est introuvable");
	}

}
