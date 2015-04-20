AddKeyStoreAction
=================
*A Testify Action to set keyStore properties as system properties*

[![Build Status](https://travis-ci.org/testify/AddKeyStoreAction.svg?branch=master)](https://travis-ci.org/testify/AddKeyStoreAction)
# Usage
### AddKeyStoreAction
  *Adds a java keystore and keystore password to the java system properties*
#### Parameters
  *This action takes no direct parameters*
  
  Keystore location and password are set instead in properties files
  
## Example
*example.properties*
    
    testify.keyStore=/path/to/keystore.jks
    testify.keyPass=password

*example.xml*
    
    <testcase>
      ...
      <preTestProcessorAction>
        AddKeyStoreAction
      </preTestProcessorAction>
      ...
    </testcase>
