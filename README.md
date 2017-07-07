## Overview

The library jaulp-wicket is a collection of Apache Wicket components and utilities. 

The components are loosely coupled and can be easily integrated into an existing Apache Wicket application.

The components are also designed to be customised so the user can provide they own components over factory methods.

## License

The source code comes under the liberal Apache License V2.0, making jaulp-wicket great for all types of wicket applications.

# Build status

[![Build Status](https://travis-ci.org/astrapi69/jaulp-wicket.svg?branch=master)](https://travis-ci.org/astrapi69/jaulp-wicket)

## Maven Central

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/jaulp-wicket/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/jaulp-wicket)

## Maven projects and install

You can add the following maven dependencies to your project `pom.xml` if you want to import the library. 

You can first define the version properties:

```
<properties>
	...
	<!-- JAULP-WICKET version -->
	<jaulp-wicket.version>7.6.0</jaulp-wicket.version>
	<jaulp-wicket-annotated-header-contributors.version>${jaulp-wicket.version}</jaulp-wicket-annotated-header-contributors.version>
	<jaulp-wicket-base.version>${jaulp-wicket.version}</jaulp-wicket-base.version>
	<jaulp-wicket-behaviors.version>${jaulp-wicket.version}</jaulp-wicket-behaviors.version>
	<jaulp-wicket-components.version>${jaulp-wicket.version}</jaulp-wicket-components.version>
	<jaulp-wicket-data-provider.version>${jaulp-wicket.version}</jaulp-wicket-data-provider.version>
	<jaulp-wicket-dialogs.version>${jaulp-wicket.version}</jaulp-wicket-dialogs.version>
	<jaulp-wicket-dropdownchoices.version>${jaulp-wicket.version}</jaulp-wicket-dropdownchoices.version>
	...
</properties>
```

Add the following maven dependency to your project `pom.xml` if you want to import annotated-header-contributors:

```xml
<dependency>
	<groupId>de.alpharogroup</groupId>
	<artifactId>jaulp-wicket-annotated-header-contributors</artifactId>
	<version>${jaulp-wicket-annotated-header-contributors.version}</version>
</dependency>
```

Add the following maven dependency to your project `pom.xml` if you want to import base:

```xml
<dependency>
	<groupId>de.alpharogroup</groupId>
	<artifactId>jaulp-wicket-base</artifactId>
	<version>${jaulp-wicket-base.version}</version>
</dependency>
```

Add the following maven dependency to your project `pom.xml` if you want to import behaviors:

```xml
<dependency>
	<groupId>de.alpharogroup</groupId>
	<artifactId>jaulp-wicket-behaviors</artifactId>
	<version>${jaulp-wicket-behaviors.version}</version>
</dependency>
```

Add the following maven dependency to your project `pom.xml` if you want to import components:

```xml
<dependency>
	<groupId>de.alpharogroup</groupId>
	<artifactId>jaulp-wicket-components</artifactId>
	<version>${jaulp-wicket-components.version}</version>
</dependency>
```

Add the following maven dependency to your project `pom.xml` if you want to import data-provider:

```xml
<dependency>
	<groupId>de.alpharogroup</groupId>
	<artifactId>jaulp-wicket-data-provider</artifactId>
	<version>${jaulp-wicket-data-provider.version}</version>
</dependency>
```

Add the following maven dependency to your project `pom.xml` if you want to import dialogs:

```xml
<dependency>
	<groupId>de.alpharogroup</groupId>
	<artifactId>jaulp-wicket-dialogs</artifactId>
	<version>${jaulp-wicket-dialogs.version}</version>
</dependency>
```

Add the following maven dependency to your project `pom.xml` if you want to import dropdownchoices:

```xml
<dependency>
	<groupId>de.alpharogroup</groupId>
	<artifactId>jaulp-wicket-dropdownchoices</artifactId>
	<version>${jaulp-wicket-dropdownchoices.version}</version>
</dependency>
```
## Run Examples 

Example projects are moved to the [wicket-examples](https://github.com/astrapi69/wicket-examples) project.

You can run every example project by doing following steps. For instance if you want to start the examples for the project jaulp-wicket-components you do this:

First change to the directory and build the project:
```bash
cd jaulp-wicket-components-examples
mvn clean install
```
Running the example:
```bash
mvn jetty:run
```
Call then http://localhost:8080/

Another way is to start the example application programmaticly with jetty. In every example project there exists a start class with a main method that have the prefix Start**** and the suffix ****Examples. 
 
## Open Issues
[![Open Issues](https://img.shields.io/github/issues/astrapi69/jaulp-wicket.svg?style=flat)](https://github.com/astrapi69/jaulp-wicket/issues) 

## Documentation

  * [BaseWebApplication][BaseWebApplication]
  * [Counting online users with wicket][Counting online users with wicket]
  * [Internationalization with ResourceModelFactory][Internationalization with ResourceModelFactory]
  * [Wicket and I18n][Wicket and I18n]
  * [Replacing wicket panels with ajax][Replacing wicket panels with ajax]
  
  [Replacing wicket panels with ajax]: https://github.com/astrapi69/jaulp-wicket/wiki/Replacing-wicket-panels-with-ajax "Replacing wicket panels with ajax"
  [Wicket and I18n]: https://github.com/astrapi69/jaulp-wicket/wiki/Wicket-and-I18n "Wicket and I18n"  
  [Internationalization with ResourceModelFactory]: https://github.com/astrapi69/jaulp-wicket/wiki/Internationalization-with-StringResourceModel-and-ResourceModelFactory "Internationalization with ResourceModelFactory"
  [Counting online users with wicket]: https://github.com/astrapi69/jaulp-wicket/wiki/Counting-online-users-with-wicket "Counting online users with wicket"
   [BaseWebApplication]: https://github.com/astrapi69/jaulp-wicket/wiki/Extending-from-BaseWebApplication "Extending from BaseWebApplication"

## Want to Help and improve it? ###

The source code of jaulp-wicket are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [astrapi69/jaulp-wicket/fork](https://github.com/astrapi69/jaulp-wicket/fork)

To share your changes, [submit a pull request](https://github.com/astrapi69/jaulp-wicket/pull/new/master).

Don't forget to add new units tests on your changes.

## Contacting the Developer

Do not hesitate to contact the jaulp-wicket developers with your questions, concerns, comments, bug reports, or feature requests.

- Feature requests, questions and bug reports can be reported at the [issues page](https://github.com/astrapi69/jaulp-wicket/issues).

## Note

No animals were harmed in the making of this library.

# Donate

If you like this library, please consider a donation through

<a href="http://flattr.com/thing/4067687/astrapi69jaulp-wicket-on-GitHub" target="_blank"><img src="http://api.flattr.com/button/flattr-badge-large.png" alt="Flattr this" title="Flattr this" border="0" /></a>

## Similar projects

Here is a list of awesome projects that use wicket, extends or decorate it.

- [wicket-matchers](https://github.com/NitorCreations/wicket-matchers) Wicket 6 / Hamcrest 1.3 utility matchers
- [wicket-utils](https://github.com/NitorCreations/wicket-utils) Useful components, models and other stuff for Wicket (mainly 6) projects
- [awesome-wicket](https://github.com/PhantomYdn/awesome-wicket) A curated list of awesome projects powered by Apache Wicket


