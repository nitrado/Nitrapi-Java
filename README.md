Nitrapi-Java
============

Java based SDK for the Nitrapi RESTful API.

Usage
-----

**Maven**

Add the following to the `<dependencies>` section of your `pom.xml`:
``` xml
<dependency>
    <groupId>net.nitrado</groupId>
    <artifactId>nitrapi</artifactId>
    <version>1.2.0</version>
</dependency>
```

**Gradle**

Add this to your app `build.gradle`:
```gradle
dependencies {
    implementation 'net.nitrado:nitrapi:1.2.0'
}
```


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
} catch (NitrapiAccessTokenInvalidException e) {
    // The access token is no longer valid.
    // ...
} catch (NitrapiHttpException e) {
    // There was an error connecting to the api.
    // ...
} catch (NitrapiConcurrencyException e) {
    // The same action is already running.
    // ...
} catch (NitrapiMaintenanceException e) {
    // The Nitrapi is currently down for maintenance.
    // ...
}
```
