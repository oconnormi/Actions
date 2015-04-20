GenerateNonceAction [![Build Status](https://travis-ci.org/testify/GenerateNonceAction.svg?branch=master)](https://travis-ci.org/testify/GenerateNonceAction)
===================
*Testify Action to generate a nonce value*

[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/testify/testify?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)


# Usage
Generates a nonce value from the current time in milliseconds

### Parameters
* Property - Name of property to store the nonce in (Defaults to testify.nonce if none is provided)

# Example
*With Property Name*

    GenerateNonceAction::some.property.name

*Without Property Name*

    GenerateNonceAction
