Nitrapi-Java
============

Java based SDK for the Nitrapi RESTful API.

Using
-----

- Install Maven 3 (mvn) on your build system
- Run the build.sh script in the main directory
- Include the generated .jar file from the target directoy into your Java application

Example
-------

``` java
    Nitrapi api = new Nitrapi("<access token>");
    try {
        Service[] services = api.getServices();
        // ...
    } catch (NitrapiErrorException e) {
        // There was an error in our request to the api.
        // ...
    } catch (NitrapiHttpException e) {
        // There was an error connecting to the api.
        // ...
    }

```