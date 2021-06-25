package com.github.rivasdiaz;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(
    name = "run",
    requiresProject = false
)
public class FlutterRunMojo extends AbstractFlutterMojo {

  @Parameter(defaultValue = "${flutter.target.device}", required = true)
  String targetDevice;

  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    flutter(String.format("run --no-pub --device-id '%s'", targetDevice));
  }
}
