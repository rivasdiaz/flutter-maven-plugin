package com.github.rivasdiaz;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(
    name = "doctor",
    requiresProject = false
)
public class FlutterDoctorMojo extends AbstractFlutterMojo {

  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    flutter("doctor");
  }
}
