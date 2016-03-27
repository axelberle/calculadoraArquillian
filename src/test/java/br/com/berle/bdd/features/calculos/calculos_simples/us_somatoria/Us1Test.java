package br.com.berle.bdd.features.calculos.calculos_simples.us_somatoria;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.berle.calculadora.backend.calculos.CalculoService;
import cucumber.api.CucumberOptions;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import cucumber.runtime.arquillian.CukeSpace;

@CucumberOptions(strict = true)
@RunWith(CukeSpace.class)
public class Us1Test {

	private static final Logger LOG = LoggerFactory.getLogger(Us1Test.class);

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

	private static int calls = 0;

	// ----------------------------------------------------------------------------------------------------------------------

	// Scenario 1:

	@Dado("^que quero calcular a somatória de dois números inteiros$")
	public void que_quero_calcular_a_somatoria_de_dois_numeros_inteiros() {

		LOG.debug("testando injection " + calculoService);
		calls++;
	}

	@Quando("^somo os valores x e y$")
	public void somo_os_valores_x_e_y() {
		calls++;

		LOG.debug("somo_os_valores_x_e_y: " + calls);
	}

	@Entao("^espero a somatória de z$")
	public void espero_a_somatoria_de_z() {
		calls++;

		LOG.debug("espero_a_somatoria_de_z: " + calls);
	}

	@AfterClass
	public static void checkCalls() {

		LOG.debug("@AfterClass: " + calls);

	}

}
