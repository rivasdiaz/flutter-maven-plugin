package com.github.rivasdiaz;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(
    name = "analyze",
    requiresProject = false
)
public class FlutterAnalyzeMojo extends AbstractFlutterMojo {

  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    flutter("analyze --no-pub");
  }
}
