# Beast
Beast is an easy to use Key/Value storage manager with Gson serialization.

[![](https://jitpack.io/v/Jonbeckas/Beast.svg)](https://jitpack.io/#Jonbeckas/Beast)

## Getting Started
Open new Beast, with default BeastInstance
```java
     Beast b = new Beast(new StoreToFile( CacheType,File);
```
### CacheType
NO_CACHE: No Caching

CACHE_ON_START: Caches all store Data on start

CACHE: Caches on set() or get()

### Get BeastInstance
```java
     BeastInstance bi = b.get(storeId);
```
If storeId does not exist, Beast add it using the default BeastInstance.
#### Store a Value
```java
    bi.set(object,"id");
```

#### Get a Value
```java
   Object value = bi.get("id");
```
#### Get a List
```java
   List<Object> arrayList = bi.getList("id");
```
#### Move a Value
You can move a Value from a BeastInstance to an other
```java
 bi.move(id,storageId);
```

## Create custom BeastInstance
Just extend the abstract BeastInstance class and call 
```java
 super.loadCache();
```
at the end of your constructor;



## Create custom Serializer
Just implement the Serializer interface 

## Install
[![](https://jitpack.io/v/Jonbeckas/Beast.svg)](https://jitpack.io/#Jonbeckas/Beast)

### Gradle
#### Add JitPack Repository
``` 
repositories {
    maven { url 'https://jitpack.io' }
}
```

#### Add Dependency
```
dependencies {
    implementation 'com.github.Jonbeckas:Beast:version'
}
```

### Maven
#### Add JitPack Repository 
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

#### Add Dependency
```xml
<dependency>
    <groupId>com.github.Jonbeckas</groupId>
    <artifactId>Beast</artifactId>
    <version>Version</version>
</dependency>
```
## Used Librarys
### Gson
#### Project: 
https://github.com/google/gson
#### Licence:
```
Copyright 2008 Google Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
