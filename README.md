# BonAppetit

[![Build Status](https://travis-ci.org/clboettcher/bonappetit.svg?branch=develop)](https://travis-ci.org/clboettcher/bonappetit) [![Sputnik](https://sputnik.ci/conf/badge)](https://sputnik.ci/app#/builds/clboettcher/bonappetit)

BonAppetit is a free Point-of-Sale solution for small and medium restaurants. It consists of an [Android App](https://github.com/clboettcher/bonappetit-android-app) and a Java
server application (this project) connected to a receipt printer.

The client app provides the ability to record, edit and delete
orders for menu items configured in the server. Orders are transferred to the server via WLAN.

The server saves the recorded orders to a database and prints receipts using a thermal receipt printer. The receipts
contain all information required to further process the order consisting of table number, item name, options,
additional free text notes as well as the service member who took the order and the time the order was registered.

This project is being refactored, modernized and moved to github. More stuff coming soon. Stay tuned.

For instructions on how to operate and develop BonAppetit, please see the [Reference Documentation](https://clboettcher.github.io/bonappetit/) 

## Contributors

In order of appearance.

* [Christine Böttcher](https://github.com/ChristineBoettcher)
* Sybille Steinberger
* [Manuel Huber](https://github.com/nelo112)
* [Alexander Lannes](https://github.com/alexander-lannes)

## Maintainer

Claudius Boettcher, <pos.bonappetit@gmail.com>.

## Sponsor

[![QAware GmbH](https://github.com/clboettcher/bonappetit/blob/develop/wiki/qaware.png?raw=true)](http://www.qaware.de)
[![CVJM München e. V.](https://github.com/clboettcher/bonappetit/blob/develop/wiki/cvjm.jpg?raw=true)](https://www.cvjm-muenchen.org/)

## GNU GPL v3 License

BonAppetit is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

BonAppetit is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
[GNU General Public License](LICENSE) for more details.
