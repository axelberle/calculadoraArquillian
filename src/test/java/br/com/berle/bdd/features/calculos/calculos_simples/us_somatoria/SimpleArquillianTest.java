package br.com.berle.bdd.features.calculos.calculos_simples.us_somatoria;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.berle.calculadora.backend.calculos.CalculoService;

@RunWith(Arquillian.class)
public class SimpleArquillianTest {

	private static final Logger LOG = LoggerFactory.getLogger(SimpleArquillianTest.class);

	@Deployment
	public static Archive<?> createArchiveAndDeploy() {

		WebArchive war = ShrinkWrap.create(WebArchive.class);

		war.addPackages(true, CalculoService.class.getPackage());
		war.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		war.addAsResource("persistence_arquillian.xml", ArchivePaths.create("META-INF/persistence.xml"));

		war.addAsResource("log4j.xml");

		LOG.debug("Deployable test war: {} ", war.toString(true));

		return war;
	}

	@Inject
	CalculoService calculoService;

	static int calls = 0;

	// ----------------------------------------------------------------------------------------------------------------------

	@Test
	@InSequence(1)
	//@Dado("que quero calcular a somatória de dois números inteiros")
	public void que_quero_calcular_a_somatoria_de_dois_numeros_inteiros() {

		LOG.debug("testando injection " + calculoService);
		
		calls++;

	}

	@Test
	@InSequence(2)
	//@Quando("^somo os valores x e y$")
	public void somo_os_valores_x_e_y() {
		calls++;
		
		LOG.debug("somo_os_valores_x_e_y: " + calls);
	}

	@Test
	@InSequence(3)
	//@Entao("^espero a somatória de z$")
	public void espero_a_somatoria_de_z() {
		calls++;
		
		LOG.debug("espero_a_somatoria_de_z: " + calls);
	}

	@Test
	@InSequence(4)
	public void checkCalls() {
		
		LOG.debug("checkCalls");
		
		assertEquals(3, calls);
	}

}
