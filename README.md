# Beast
Beast is an easy to use Key/Value storage manager using Google's Gson serializer.

[![](https://jitpack.io/v/Jonbeckas/Beast.svg)](https://jitpack.io/#Jonbeckas/Beast)

## Getting Started
Create a new instance of Beast and give it a StoragType
```java
     Beast b = new Beast(new StoreToFile( CacheType,File);
```
### CacheTypes
NO_CACHE: Never caches
CACHE_ON_START: Caches every stored Data on start
CACHE: Caches reuested values only

### Get a BeastItem
```java
     BeastItem  bi = b.get(id);
```
If storeId does not exist, Beast add it using the default StorageType.
#### Store a Value
```java
    bi.set(object);
```

#### Get a Value
```java
   Object value = bi.get();
```
#### Get a List
```java
   List<Object> arrayList = bi.getList();
```
#### Move a Value
You can move a Value from a StorageType to an other
```java
 bi.move(storageId);
```

#### Remove a value
```java
 bi.remove();
```

## Register an other StorageType
```java
 b.addStorageType("storageId",StorageType);
```

## Register BeastItem in an other StorageType 
```java
 BeastItem bi = b.create(id,storageTypeid)
```

## Create custom StorageType
Just extend the abstract StorageType class and call 
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
