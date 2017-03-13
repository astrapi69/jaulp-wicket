## jaulp.wicket

The library jaulp.wicket is a collection of Apache Wicket components and utilities. 

The components are loosely coupled and can be easily integrated into an existing Apache Wicket application.

The components are also designed to be customised so the user can provide they own components over factory methods.

## License

The source code comes under the liberal Apache License V2.0, making jaulp.wicket great for all types of wicket applications.


# Build status and latest maven version

[![Build Status](https://travis-ci.org/astrapi69/jaulp.wicket.svg?branch=master)](https://travis-ci.org/astrapi69/jaulp.wicket)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/jaulp.wicket/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/jaulp.wicket)

## Maven projects and install

Add the following maven dependency to your project `pom.xml` if you want to import components:

```xml
<dependency>
   <groupId>de.alpharogroup</groupId>
   <artifactId>jaulp.wicket.components</artifactId>
   <version>7.1.0</version>
</dependency>
```

Add the following maven dependency to your project `pom.xml` if you want to import base stuff like base application, panel, pages etc.:

```xml
<dependency>
   <groupId>de.alpharogroup</groupId>
   <artifactId>jaulp.wicket.base</artifactId>
   <version>7.1.0</version>
</dependency>
```

Add the following maven dependency to your project `pom.xml` if you want to import behaviors etc.:

```xml
<dependency>
   <groupId>de.alpharogroup</groupId>
   <artifactId>jaulp.wicket.behaviors</artifactId>
   <version>7.1.0</version>
</dependency>
```

Check out [sonatype repository](https://oss.sonatype.org/index.html#nexus-search;quick~jaulp.wicket.components) for latest snapshots and releases.

## Run Examples 

Example projects are moved to the [wicket-examples](https://github.com/astrapi69/wicket-examples) project.

You can run every example project by doing following steps. For instance if you want to start the examples for the project jaulp.wicket.components you do this:

First change to the directory and build the project:
```bash
cd jaulp.wicket.components.examples
mvn clean install
```
Running the example:
```bash
mvn jetty:run
```
Call then http://localhost:8080/

Another way is to start the example application programmaticly with jetty. In every example project there exists a start class with a main method that have the prefix Start**** and the suffix ****Examples. 
 
## Open Issues
[![Open Issues](https://img.shields.io/github/issues/astrapi69/jaulp.wicket.svg?style=flat)](https://github.com/astrapi69/jaulp.wicket/issues) 

## Documentation


  * [BaseWebApplication][BaseWebApplication]
  * [Counting online users with wicket][Counting online users with wicket]
  * [Internationalization with ResourceModelFactory][Internationalization with ResourceModelFactory]
  * [Wicket and I18n][Wicket and I18n]
  
  [Wicket and I18n]: https://github.com/astrapi69/jaulp.wicket/wiki/Wicket-and-I18n "Wicket and I18n"  
  [Internationalization with ResourceModelFactory]: https://github.com/astrapi69/jaulp.wicket/wiki/Internationalization-with-StringResourceModel-and-ResourceModelFactory "Internationalization with ResourceModelFactory"
  [Counting online users with wicket]: https://github.com/astrapi69/jaulp.wicket/wiki/Counting-online-users-with-wicket "Counting online users with wicket"
   [BaseWebApplication]: https://github.com/astrapi69/jaulp.wicket/wiki/Extending-from-BaseWebApplication "Extending from BaseWebApplication"

## Want to Help and improve it? ###

The source code of jaulp.wicket are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [astrapi69/jaulp.wicket/fork](https://github.com/astrapi69/jaulp.wicket/fork)

To share your changes, [submit a pull request](https://github.com/astrapi69/jaulp.wicket/pull/new/master).

Don't forget to add new units tests on your changes.

## Contacting the Developer


Do not hesitate to contact the jaulp.wicket developers with your questions, concerns, comments, bug reports, or feature requests.

- Feature requests, questions and bug reports can be reported at the [issues page](https://github.com/astrapi69/jaulp.wicket/issues).

# Donate

If you like this library, please consider a donation through

<a href="http://flattr.com/thing/4067687/astrapi69jaulp-wicket-on-GitHub" target="_blank"><img src="http://api.flattr.com/button/flattr-badge-large.png" alt="Flattr this" title="Flattr this" border="0" /></a>

