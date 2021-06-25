package com.github.rivasdiaz;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.Os;

@Mojo(
    name = "build",
    defaultPhase = LifecyclePhase.COMPILE,
    requiresProject = false
)
public class FlutterBuildMojo extends AbstractFlutterMojo {

  @Parameter(defaultValue = "${flutter.target.web}")
  Boolean buildWebTarget;

  @Parameter(defaultValue = "${flutter.target.web.skip}")
  Boolean skipWebTarget;

  @Parameter(defaultValue = "${flutter.target.android}")
  Boolean buildAndroidTarget;

  @Parameter(defaultValue = "${flutter.target.android.skip}")
  Boolean skipAndroidTarget;

  @Parameter(defaultValue = "${flutter.target.ios}")
  Boolean buildIOSTarget;

  @Parameter(defaultValue = "${flutter.target.ios.skip}")
  Boolean skipIOSTarget;

  @Parameter(defaultValue = "${flutter.target.macos}")
  Boolean buildMacTarget;

  @Parameter(defaultValue = "${flutter.target.macos.skip}")
  Boolean skipMacTarget;

  @Parameter(defaultValue = "${flutter.target.linux}")
  Boolean buildLinuxTarget;

  @Parameter(defaultValue = "${flutter.target.linux.skip}")
  Boolean skipLinuxTarget;

  @Parameter(defaultValue = "${flutter.target.windows}")
  Boolean buildWindowsTarget;

  @Parameter(defaultValue = "${flutter.target.windows.skip}")
  Boolean skipWindowsTarget;

  @Parameter(defaultValue = "${flutter.target.web.renderer}")
  WebRendererType webRenderer;

  @Parameter(defaultValue = "${flutter.target.android.package-type}")
  AndroidPackageType androidPackageType;

  @Parameter(defaultValue = "${flutter.target.ios.package-type}")
  IOSPackageType iosPackageType;

  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    if (skipWebTarget == null || skipWebTarget.equals(Boolean.FALSE)) {
      if ((buildWebTarget == null || buildWebTarget.equals(Boolean.TRUE)) && hasWebTarget()) {
        final var renderer = webRenderer != null ? webRenderer : WebRendererType.AUTO;
        flutter(String.format("build web --no-pub --web-renderer %s", renderer.name().toLowerCase()));
      }
    }

    if (skipAndroidTarget == null || skipAndroidTarget.equals(Boolean.FALSE)) {
      if ((buildAndroidTarget == null || buildAndroidTarget.equals(Boolean.TRUE)) && hasAndroidTarget()) {
        final var packageType = androidPackageType != null ? androidPackageType : AndroidPackageType.APPBUNDLE;
        flutter(String.format("build %s --no-pub", packageType.name().toLowerCase()));
      }
    }

    if (Os.isFamily(Os.FAMILY_MAC)) {
      if (skipIOSTarget == null || skipIOSTarget.equals(Boolean.FALSE)) {
        if ((buildIOSTarget == null || buildIOSTarget.equals(Boolean.TRUE)) && hasAndroidTarget()) {
          final var packageType = iosPackageType != null ? iosPackageType : IOSPackageType.IPA;
          flutter(String.format("build %s --no-pub", packageType.name().toLowerCase()));
        }
      }
    }

    if (Os.isFamily(Os.FAMILY_MAC)) {
      if (skipMacTarget == null || skipMacTarget.equals(Boolean.FALSE)) {
        if ((buildMacTarget == null || buildMacTarget.equals(Boolean.TRUE)) && hasMacTarget()) {
          flutter("build macos --no-pub");
        }
      }
    }

    if (Os.isFamily(Os.FAMILY_UNIX)) {
      if (skipLinuxTarget == null || skipLinuxTarget.equals(Boolean.FALSE)) {
        if ((buildLinuxTarget == null || buildLinuxTarget.equals(Boolean.TRUE)) && hasLinuxTarget()) {
          flutter("build linux --no-pub");
        }
      }
    }

    if (Os.isFamily(Os.FAMILY_WINDOWS)) {
      if (skipWindowsTarget == null || skipWindowsTarget.equals(Boolean.FALSE)) {
        if ((buildWindowsTarget == null || buildWindowsTarget.equals(Boolean.TRUE)) && hasWindowsTarget()) {
          flutter("build macos --no-pub");
        }
      }
    }
  }
}
