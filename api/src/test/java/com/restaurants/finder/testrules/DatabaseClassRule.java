package com.restaurants.finder.testrules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.junit.rules.ExternalResource;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ContainerCreation;
import com.spotify.docker.client.messages.HostConfig;
import com.spotify.docker.client.messages.PortBinding;

public class DatabaseClassRule extends ExternalResource {

	private final DockerClient docker;
	private final ContainerCreation container;
	private final String hostPort;
	private final Flyway flyway;
	private final String user;
	private final String password;
	private String url;

	public DatabaseClassRule(String imageName, String containerPort, String user, String password) {
		this.flyway = new Flyway();
		this.user = user;
		this.password = password;
		try {
			this.docker = DefaultDockerClient.fromEnv().build();
		} catch (DockerCertificateException e) {
			throw new RuntimeException("Error moutings docker environment", e);
		}
		Map<String, List<PortBinding>> configuredPorts = configurePorts(containerPort);
		this.hostPort = configuredPorts.get(containerPort).get(0).hostPort();

		final HostConfig hostConfig = HostConfig.builder().portBindings(configuredPorts).build();
		List<String> env = new ArrayList<>();
		env.add("POSTGRES_PASSWORD=" + password);
		env.add("POSTGRES_DB=restaurants");

		final ContainerConfig containerConfig = ContainerConfig.builder().hostConfig(hostConfig).image(imageName)
				.exposedPorts(new String[] { containerPort }).env(env).build();
		try {
			this.container = docker.createContainer(containerConfig);
		} catch (DockerException | InterruptedException e) {
			throw new RuntimeException("Error creating container", e);
		}
	}

	@Override
	protected void before() throws Throwable {
		super.before();
		docker.startContainer(this.container.id());
		this.url = "jdbc:postgresql://localhost:" + hostPort + "/restaurants";
		this.flyway.setDataSource(url, user, password);
		try {
			this.flyway.clean();
			this.flyway.migrate();
		} catch (FlywayException e) {
			docker.killContainer(container.id());
			docker.removeContainer(container.id(), true);	
		}
	}

	@Override
	protected void after() {
		super.after();
		try {
			docker.killContainer(container.id());
			docker.removeContainer(container.id(), true);
		} catch (DockerException | InterruptedException e) {
			e.printStackTrace();
		}
		docker.close();
	}

	public String getHostPort() {
		return this.hostPort;
	}

	public String getUrl() {
		return this.url;
	}

	private Map<String, List<PortBinding>> configurePorts(String port) {
		final Map<String, List<PortBinding>> portBindings = new HashMap<>();
		List<PortBinding> hostPorts = new ArrayList<>();
		// hostPort
		PortBinding portBinding = PortBinding.of("0.0.0.0", "5455");
		hostPorts.add(portBinding);
		portBindings.put(port, hostPorts);
		return portBindings;
	}

}
