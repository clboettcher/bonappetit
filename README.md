# BonAppetit

BonAppetit is a Point-of-Sale application. It consists of a Android client and a Java server application.

This project is being refactored, modernized and moved to github. More stuff coming soon. Stay tuned.

## Server

The `:bonappetit-server` project generates a war file which can be deployed to any application server
(like tomcat, jetty, jboss, etc.).

Run the following command to generate the war file from the root directory of the project in your favorite shell:

```bash
gradlew war
```

You find the archive at `/bonappetit-server/build/libs/bonappetit-server-${X.Y.Z}.war`

## Maintainer

Claudius Boettcher, <pos.bonappetit@gmail.com>.

## License

This software is provided under the GNU GPL v3 license, read LICENSE file for details.
