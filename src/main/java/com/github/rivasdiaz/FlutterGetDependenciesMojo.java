package com.github.rivasdiaz;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(
    name = "dependencies",
    defaultPhase = LifecyclePhase.INITIALIZE,
    requiresProject = false,
    requiresOnline = true
)
public class FlutterGetDependenciesMojo extends AbstractFlutterMojo {

  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    flutter("pub get");
  }
}
