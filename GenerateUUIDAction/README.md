GenerateUUIDAction [![Build Status](https://travis-ci.org/testify/GenerateUUIDAction.svg?branch=master)](https://travis-ci.org/testify/GenerateUUIDAction)
===================
*Testify Action to generate a uuid value*

[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/testify/testify?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)


# Usage
Generates a uuid value

### Parameters
* Property - Name of property to store the uuid in (Defaults to testify.uuid if none is provided)

# Example
*With Property Name*

    GenerateUUIDAction::some.property.name

*Without Property Name*

    GenerateUUIDAction
