# BonAppetit

[![Build Status](https://travis-ci.org/clboettcher/bonappetit.svg?branch=develop)](https://travis-ci.org/clboettcher/bonappetit)

BonAppetit is a free Point-of-Sale solution for small and medium restaurants. It consists of an Android client and a Java
server application connected to a receipt printer.

The client app provides the ability to record, edit and delete
orders for menu items configured in the server. Orders are transferred to the server via WLAN.

The server saves the recorded orders to a database and prints receipts using a thermal receipt printer. The receipts
contain all information required to further process the order consisting of table number, item name, options,
additional free text notes as well as the service member who took the order and the time the order was registered.

This project is being refactored, modernized and moved to github. More stuff coming soon. Stay tuned.

## Server

The `:bonappetit-server` project contains the server application that provides the data via RESTful APIs.

### Endpoints

To get started working with the server have a look at the following endpoints.

| URL                                       | Description                            |
|-------------------------------------------|----------------------------------------|
| `/v1/doc/index.html`                      | Shows an interactive API documentation |
| `/health`                                 | Shows application health information   |

### Running the server application

The `:bonappetit-server` project contains a spring-boot application that can be started in an embedded application server via 

```bash
./gradlew bootRun
```

Per default the application is deployed on `http://localhost:8080`

In addition to running the server embedded you can create a war file which can be deployed to any application server (like tomcat, jetty, jboss, etc.).

Run the following command to generate the war

```bash
./gradlew war
```

You find the archive at `/bonappetit-server/build/libs/bonappetit-server-${major.minor.bugfix}.war`

## Maintainer

Claudius Boettcher, <pos.bonappetit@gmail.com>.

## GNU GPL v3 License

BonAppetit is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

BonAppetit is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
[GNU General Public License](LICENSE) for more details.