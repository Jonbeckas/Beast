# Beast
Beast is an easy Gson using Key/Value storage manager.

[![](https://jitpack.io/v/Jonbeckas/Beast.svg)](https://jitpack.io/#Jonbeckas/Beast)

## Getting Started
```java
     Beast beast = new Beast("Path_to_beast_confDir");
     BeastInstance bi = BEAST.openInstanceAndGet(StoreToFile.class,"INDEX");
```
Replace StoreToFile.class with the StorageType.class you want
#### Store a Value
```java
    bi.set(object,"id");
```

#### Get a Value
```java
   value_object value = bi.get(value_object.class ,"id");
```
#### Get a List
```java
   ArrayList<Object> arrayList = bi.getList("id");
```

## Create custom Storage Type
just implement the StorageType interface

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
