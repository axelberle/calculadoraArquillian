package br.com.berle.calculadora.backend.calculos;

import javax.ejb.Stateless;

@Stateless
public class CalculoService {

	public Integer somatoria(int x, int y) {
		return x + y;
	}
	
	
}
